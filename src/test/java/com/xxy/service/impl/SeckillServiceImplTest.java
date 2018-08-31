package com.xxy.service.impl;

import com.xxy.dto.Exposer;
import com.xxy.dto.SeckillExecution;
import com.xxy.po.Seckill;
import com.xxy.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xiongxiaoyu
 * Data:2018/8/13
 * Time:22:34
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillServiceImplTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private SeckillService seckillService;

	@Test
	public void queryAll() throws Exception {
		List<Seckill> list = seckillService.getAll();
	}

	@Test
	public void queryById() throws Exception {
		Seckill seckill = seckillService.getById(1000L);
	}

	@Test
	public void exoportSeckillUrl() throws Exception {
		long id = 1000;
		Exposer exposer = seckillService.exoportSeckillUrl(1000L);
		//expser=Exposer(exposed=false,
		//md5=null,
		//seckillId=1000,
		//nowTime=1506951707793,
		//tartTime=1446307200000,
		//endTime=1446393600000)

		//exposed=true,
		// md5=100010459d1ff8c1056f5adc9ec5c466fb63,
		// seckillId=1000,
		// nowTime=0,
		// startTime=0,
		// endTime=0)
	}

	@Test
	public void executeSeckill() throws Exception {
		SeckillExecution seckillExcution = seckillService.executeSeckill(1000L,13783425232L,"100010459d1ff8c1056f5adc9ec5c466fb63");
		logger.info("seckillExcution={}",seckillExcution);
	}

	@Test
	public void executeSeckillProcedure(){
		long seckillId = 1000L;
		long phone = 13680667711L;
		Exposer exposer = seckillService.exoportSeckillUrl(seckillId);
		if(exposer.isExposed()){
			String md5 = exposer.getMd5();
			SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId,phone,md5);
			logger.info(execution.getStateInfo() + execution.getState());
		}
	}
}