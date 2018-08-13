package com.xxy.dao;

import com.xxy.po.SuccessKilled;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by xiongxiaoyu
 * Data:2018/8/13
 * Time:10:58
 */
@Repository
public interface SuccessKilledMapper {
	/**
	 * 插入购买明细，可过滤重复
	 * seckillId和userPhone组成联合主键
	 *
	 * @param seckillId
	 * @param userPhone
	 * @return
	 */
	@Insert("INSERT IGNORE INTO success_killed(seckill_id,user_phone) " +
			"VALUES (#{seckillId},#{userPhone})")
	int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

	/**
	 * 根据id查询SuccessKilled并携带秒杀对象实体
	 *
	 * @param seckillId
	 * @return
	 */
	@Select("SELECT sk.*,s.seckill_id as s_seckill_id,s.name,s.number,s.start_time,s.end_time,s.create_time as s_create_time " +
			"FROM success_killed sk INNER JOIN seckill s ON s.seckill_id = sk.seckill_id WHERE sk.seckill_id=#{seckillId} AND sk.user_phone=#{userPhone}")
	@ResultMap("org.redin.seckill.dao.SuccessKilledMapper.SuccessKilledMap")
	SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

}
