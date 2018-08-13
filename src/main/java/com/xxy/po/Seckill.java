package com.xxy.po;

import lombok.Data;

import java.util.Date;

/**
 * Created by xiongxiaoyu
 * Data:2018/8/13
 * Time:10:58
 *
 * 库存表
 */

@Data
public class Seckill {
	private long seckillId;
	private String name;
	private int number;
	private Date startTime;
	private Date endTime;
	private Date createTime;
}
