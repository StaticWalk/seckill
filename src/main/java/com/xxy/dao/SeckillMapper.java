package com.xxy.dao;

import com.xxy.po.Seckill;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by xiongxiaoyu
 * Data:2018/8/13
 * Time:10:58
 */

@Repository
public interface SeckillMapper {
	/**
	 * 减库存
	 *
	 * @param seckillId
	 * @param killTime
	 * @return
	 */
	@Update("UPDATE seckill SET number = number-1 " +
			"WHERE seckill_id=#{seckillId} " +
			"AND start_time <= #{killTime} " +
			"AND end_time >= #{killTime} AND number>0")
	int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

	/**
	 * 根据ID查询秒杀对象
	 *
	 * @param seckillId
	 * @return
	 */
	@Select("SELECT seckill_id,name,number,start_time,end_time,create_time " +
			"FROM seckill " +
			"WHERE seckill_id=#{seckillId}")
	@ResultMap("com.xxy.dao.SeckillMapper.SeckillMap")
	Seckill queryById(long seckillId);

	/**
	 * 根据偏移量查询秒杀商品列表
	 *
	 * @param offet
	 * @return
	 */
	@Select("SELECT seckill_id,name,number,start_time,end_time,create_time " +
			"FROM seckill ORDER BY create_time DESC LIMIT #{offet},#{limit}")
	@ResultMap("com.xxy.dao.SeckillMapper.SeckillMap")
	List<Seckill> queryAll(@Param("offet") int offet, @Param("limit") int limit);

	/**
	 * 使用存储过程执行秒杀
	 * @param paramsMap
	 */
	@Select("CALL execute_seckill(#{seckillId,jdbcType=BIGINT,mode=IN}," +
			"#{phone,jdbcType=BIGINT,mode=IN}," +
			"#{killTime,jdbcType=TIMESTAMP,mode=IN}," +
			"#{result,jdbcType=INTEGER,mode=OUT})")
	@Options(statementType = StatementType.CALLABLE)
	void killByProcedure(Map<String,Object> paramsMap);
}
