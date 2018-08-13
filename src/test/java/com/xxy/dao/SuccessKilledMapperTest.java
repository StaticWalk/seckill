package com.xxy.dao;

import com.xxy.po.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by xiongxiaoyu
 * Data:2018/8/13
 * Time:19:41
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class SuccessKilledMapperTest {

	@Resource
	SuccessKilledMapper successKilledMapper;

	@Test
	public void insertSuccessKilled() throws Exception {
		int num = successKilledMapper.insertSuccessKilled(1001L,13671285902L);
		System.out.println("num:"+num);
	}

	@Test
	public void queryByIdWithSeckill() throws Exception {
		SuccessKilled successKilled = successKilledMapper.queryByIdWithSeckill(1000L,13671285902L);
		System.out.println(successKilled);
	}
}