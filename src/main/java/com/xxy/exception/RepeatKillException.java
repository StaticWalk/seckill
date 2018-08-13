package com.xxy.exception;



/**
 * Created by xiongxiaoyu
 * Data:2018/8/13
 * Time:21:30
 */
public class RepeatKillException extends SeckillException {

	public RepeatKillException(String message) {
		super(message);
	}

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}
}
