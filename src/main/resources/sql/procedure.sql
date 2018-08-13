-- 秒杀执行存储过程
DELIMITER $$ -- console; 转换为
-- 定义存储过程
-- 参数:in 输入参数; out 输出参数
CREATE PROCEDURE `seckill`.`execute_seckill`
  (IN v_seckill_id BIGINT, IN v_phone BIGINT,
   IN v_kill_time  TIMESTAMP, OUT r_result INT)
  BEGIN
    DECLARE insert_count INT DEFAULT 0;
    START TRANSACTION;
    INSERT IGNORE INTO success_killed
    (seckill_id, user_phone, create_time)
    VALUES (v_seckill_id, v_phone, v_kill_time);
    SELECT row_count()
    INTO insert_count;
    IF (insert_count = 0)
    THEN
      ROLLBACK;
      SET r_result = -1; -- 重复秒杀
    ELSEIF (insert_count < 0)
      THEN
        ROLLBACK;
        SET r_result = -2; -- 系统错误
    ELSE
      UPDATE seckill
      SET number = number - 1
      WHERE seckill_id = v_seckill_id
            AND end_time > v_kill_time
            AND start_time < v_kill_time
            AND number > 0;
      SELECT row_count()
      INTO insert_count;
      IF (insert_count = 0)
      THEN
        ROLLBACK;
        SET r_result = 0; -- 秒杀结束
      ELSEIF (insert_count < 0)
        THEN
          ROLLBACK;
          SET r_result = -2; -- 系统错误
      ELSE
        COMMIT;
        SET r_result = 1; -- 秒杀成功
      END IF;
    END IF;
  END;
$$
    
