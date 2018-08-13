package com.xxy.dto;

import com.xxy.enums.SeckillStateEnum;
import com.xxy.po.SuccessKilled;
import lombok.Data;

/**
 * Created by xiongxiaoyu
 * Data:2018/8/13
 * Time:19:54
 */
@Data
public class SeckillExecution {

	//秒杀执行结果状态
	private long seckillId;

	//秒杀执行结果状态
	private int state;

	//状态标识
	private String stateInfo;

	//秒杀对象
	private SuccessKilled successKilled;

	/**
	 * 秒杀成功
	 * @param seckillId
	 * @param seckillStateEnum
	 * @param successKilled
	 */
	public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum, SuccessKilled successKilled) {
		this.seckillId = seckillId;
		this.state = seckillStateEnum.getState();
		this.stateInfo = seckillStateEnum.getStateInfo();
		this.successKilled = successKilled;
	}

	/**
	 * 秒杀失败
	 * @param seckillId
	 * @param seckillStateEnum
	 */
	public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum) {
		this.seckillId = seckillId;
		this.state = seckillStateEnum.getState();
		this.stateInfo = seckillStateEnum.getStateInfo();
	}
}
