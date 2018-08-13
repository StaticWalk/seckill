package com.xxy.po;

import lombok.Data;

import java.util.Date;

/**
 * Created by xiongxiaoyu
 * Data:2018/8/13
 * Time:10:59
 * 成功秒杀表
 */

@Data
public class SuccessKilled {
	private long seckillId;
	private long userPhone;
	private short state;
	private Date createTime;
	//Mybatis里面的级联要在xml文件内配置
	private Seckill seckill;
}
