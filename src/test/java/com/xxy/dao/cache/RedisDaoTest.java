package com.xxy.dao.cache;

import com.xxy.po.Seckill;
import org.junit.Test;

import java.util.Date;

/**
 * Created by xiongxiaoyu
 * Data:2018/8/31
 * Time:11:10
 */
public class RedisDaoTest {


	@Test
	public void RedisDaoTest(){

		RedisDao redisDao =new RedisDao("118.89.36.125",6379);

		Seckill seckill=new Seckill();
		seckill.setSeckillId(1263);
		seckill.setCreateTime(new Date());
		seckill.setEndTime(new Date());
		seckill.setName("hahaha");
		seckill.setNumber(171781949);
		seckill.setStartTime(new Date());
		redisDao.putSeckill(seckill);
		System.out.println(redisDao.getSeckill(1263));

	}



}