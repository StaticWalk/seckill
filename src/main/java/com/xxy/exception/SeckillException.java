package com.xxy.exception;

/**
 * Created by xiongxiaoyu
 * Data:2018/8/13
 * Time:21:29
 */
public class SeckillException extends RuntimeException{

	public SeckillException(String message) {
		super(message);
	}

	public SeckillException(String message, Throwable cause) {
		super(message, cause);
	}
}
