package com.xxy.dao;

import com.xxy.SeckillApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by xiongxiaoyu
 * Data:2018/8/13
 * Time:18:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SeckillApplication.class)
public class SeckillMapperTest {


	@Resource
	private SeckillMapper seckillMapper;

	@Test
	public void reduceNumber() {
		int rowindex=seckillMapper.reduceNumber(1000L,new Date());
	}

	@Test
	public void queryById() {
	}

	@Test
	public void queryAll() {
	}

	@Test
	public void killByProcedure() {
	}
}