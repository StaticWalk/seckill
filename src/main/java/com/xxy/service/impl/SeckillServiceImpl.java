package com.xxy.service.impl;

import com.xxy.dao.SeckillMapper;
import com.xxy.dao.SuccessKilledMapper;
import com.xxy.dao.cache.RedisDao;
import com.xxy.dto.Exposer;
import com.xxy.dto.SeckillExecution;
import com.xxy.enums.SeckillStateEnum;
import com.xxy.exception.RepeatKillException;
import com.xxy.exception.SeckillClosedException;
import com.xxy.exception.SeckillException;
import com.xxy.po.Seckill;
import com.xxy.po.SuccessKilled;
import com.xxy.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by xiongxiaoyu
 * Data:2018/8/13
 * Time:20:06
 */
@Service
public class SeckillServiceImpl implements SeckillService {


	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final String salt = "test";

	@Resource
	private SeckillMapper seckillMapper;

	@Resource
	private SuccessKilledMapper successKilledMapper;

	@Resource
	private RedisDao redisDao;

	@Override
	public List<Seckill> getAll() {
		return seckillMapper.queryAll(0,10);
	}

	@Override
	public Seckill getById(long seckillId) {
		return seckillMapper.queryById(seckillId);
	}


	/**
	 *
	 * @param seckillId
	 * @return
	 * 接口暴露就是控制秒杀接口开启时间
	 */
	@Override
	public Exposer exoportSeckillUrl(long seckillId) {
		//高并发优化第一步，使用缓存存储秒杀接口，错开对服务器秒杀接口的请求
		//使用缓存时的qbs远高于服务器
		Seckill seckill = redisDao.getSeckill(seckillId);
		if(seckill==null){
			seckill=seckillMapper.queryById(seckillId);
			if(seckill==null){
				return new Exposer(false,seckillId);
			}else{
				redisDao.putSeckill(seckill);
			}
		}

		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}

		String md5 = getMD5(seckillId);
		return new Exposer(true, md5, seckillId);
	}


	private String getMD5(long seckillId) {
		String base = seckillId + "/" + salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return seckillId + md5;
	}


	@Override
	@Transactional//通过事物执行秒杀逻辑(减库存+记录购买行为)
	public SeckillExecution executeSeckill(long seckllId, long userPhone, String md5) {
		if (md5 == null || !md5.equals(getMD5(seckllId))) {
			throw new SeckillException("seckill data rewirite");
		}

		Date nowTime = new Date();
		//执行秒杀逻辑
		//高并发优化第二步,减少行级锁的持有时间,减少阻塞时间
		// update(rowLock) + gc + insert + gc + commit/rollback(freeLock)
		//		---->  insert + gc + update(rowLock) + gc + commit/rollback(freeLock)
		try {
			//记录购买行为
			int insertCount = successKilledMapper.insertSuccessKilled(seckllId, userPhone);
			if (insertCount <= 0) {
				//重复秒杀
				throw new RepeatKillException("seckill repeated");
			} else {
				int updateCount = seckillMapper.reduceNumber(seckllId, nowTime);
				if (updateCount <= 0) {
					//没有更新到记录
					throw new SeckillClosedException("seckill is closed");
				} else {
					SuccessKilled successKilled = successKilledMapper.queryByIdWithSeckill(seckllId,userPhone);
					return new SeckillExecution(seckllId, SeckillStateEnum.SUCCESS, successKilled);
				}
			}
		} catch (SeckillClosedException e) {
			throw e;
		} catch (RepeatKillException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			//所有编译器异常转换为运行期异常
			throw new SeckillException("seckill inner error:" + e.getMessage());
		}

	}

	@Override
	public SeckillExecution executeSeckillProcedure(long seckillid, long userPhone, String md5) {
		return null;
	}
}
