package com.xxy.service;

import com.xxy.dto.Exposer;
import com.xxy.dto.SeckillExecution;
import com.xxy.po.Seckill;

import java.util.List;

/**
 * Created by xiongxiaoyu
 * Data:2018/8/13
 * Time:19:52
 */
public interface SeckillService {

	List<Seckill> getAll();

	Seckill getById(long seckillId);

	/**
	 * 秒杀开始时输出秒杀接口地址
	 * 否则输出系统时间和秒杀时间
	 * @param seckillId
	 * @return
	 */
	Exposer exoportSeckillUrl(long seckillId);

	SeckillExecution executeSeckill(long seckllId, long userPhone, String md5);

	SeckillExecution executeSeckillProcedure(long seckillid,long userPhone,String md5);
}
