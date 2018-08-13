package com.xxy.dto;

import lombok.Data;

/**
 * Created by xiongxiaoyu
 * Data:2018/8/13
 * Time:19:53
 * 暴露秒杀地址DTO
 */

@Data
public class Exposer {

	private boolean exposed;

	private String md5;

	private long seckillId;

	private long nowTime;

	private long startTime;

	private long endTime;


	public Exposer(boolean exposed, String md5, long seckillId) {
		this.exposed = exposed;
		this.md5 = md5;
		this.seckillId = seckillId;
	}

	public Exposer(boolean exposed, long seckillId, long nowTime, long startTime, long endTime) {
		this.exposed = exposed;
		this.seckillId = seckillId;
		this.nowTime = nowTime;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Exposer(boolean exposed, long seckillId) {
		this.exposed = exposed;
		this.seckillId = seckillId;
	}
}
