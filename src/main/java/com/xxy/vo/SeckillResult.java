package com.xxy.vo;

import lombok.Data;

/**
 * Created by xiongxiaoyu
 * Data:2018/8/13
 * Time:22:33
 */
@Data
public class SeckillResult <T> {
	private boolean success;
	private T data;
	private String error;

	public SeckillResult(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	public SeckillResult(boolean success, String error) {
		this.success = success;
		this.error = error;
	}
}
