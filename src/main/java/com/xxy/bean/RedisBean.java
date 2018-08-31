package com.xxy.bean;

import com.xxy.dao.cache.RedisDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xiongxiaoyu
 * Data:2018/8/13
 * Time:20:07
 */
@Configuration
public class RedisBean {

	//在spring运行期使用的话可以通过bean注入
	@Bean
	public RedisDao redisDao(@Value("${spring.redis.host}") String ip, @Value("${spring.redis.port}") Integer port){
		return new RedisDao(ip,port);
	}
}
