package com.xxy.enums;

/**
 * Created by xiongxiaoyu
 * Data:2018/8/13
 * Time:19:58
 */

public enum SeckillStateEnum {
	SUCCESS(1,"秒杀成功"),
	END(0,"秒杀结束"),
	REPEAT_KILL(-1,"重复秒杀"),
	INNER_ERROR(-2,"系统异常"),
	DATA_REWRITE(-3,"数据篡改");

	private int state;
	private String stateInfo;

	SeckillStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static SeckillStateEnum stateOf(int state){
		for(SeckillStateEnum seckillStateEnum : values()){
			if(seckillStateEnum.getState()==state){
				return seckillStateEnum;
			}
		}
		return null;
	}
}
