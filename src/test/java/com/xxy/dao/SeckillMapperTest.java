package com.xxy.dao;

import com.xxy.SeckillApplication;
import com.xxy.po.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by xiongxiaoyu
 * Data:2018/8/13
 * Time:18:30
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SeckillApplication.class)
public class  SeckillMapperTest {

	@Autowired
	private SeckillMapper seckillMapper;

	@Test
	public void reduceNumber() throws Exception{
		seckillMapper.reduceNumber(1000L,new Date());
	}

	@Test
	public void queryById() {
		Seckill seckill = seckillMapper.queryById(1000L);
		System.out.println(seckill.getName());
		System.out.println(seckill);
	}

	@Test
	public void queryAll() {
		List<Seckill> list = seckillMapper.queryAll(2,4);
		for(Seckill seckill:list){
			System.out.println(seckill);
		}
	}

	@Test
	public void killByProcedure() {
	}
}