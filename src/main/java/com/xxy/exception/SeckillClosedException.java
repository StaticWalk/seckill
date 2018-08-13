package com.xxy.exception;

/**
 * Created by xiongxiaoyu
 * Data:2018/8/13
 * Time:21:31
 */
public class SeckillClosedException extends SeckillException {
	public SeckillClosedException(String message) {
		super(message);
	}

	public SeckillClosedException(String message, Throwable cause) {
		super(message, cause);
	}
}
