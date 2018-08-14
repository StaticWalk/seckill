# 秒杀Demo

## 项目技术
- SpringBoot 
- Thymeleaf 
- Mybatis
- Druid 
- Jedis 
- log4j2

## 项目导入(IDEA编译器)
- Project Address : https://github.com/StaticWalk/seckill.git
- File -> New -> Project From Version Control -> Github -> 项目地址   

## 项目资源
- 源码:  [seckill.zip](https://github.com/StaticWalk/seckill/blob/master/seckill.zip)
- 打包:  
	>- 打包好的jar，下载后配置好JDK和数据库(mysql和redis),双击运行。
	>- 浏览器输入：http://localhost/list ,即可访问。

## 项目结构
```
├─java
│  └─org.redin.seckill
│     │  SeckillApplication.java            //启动类，程序入口
│     ├─config
│     │      Beans.java
│     ├─dao
│     │  │  SeckillMapper.java
│     │  │  SuccessKilledMapper.java
│     │  └─cache
│     │          RedisDao.java
│     ├─dto
│     │      Exposer.java                  //数据传输层，封装数据格式
│     │      SeckillExecution.java
│     ├─enums
│     │      SeckillStateEnum.java
│     ├─exception
│     │      RepeatKillException.java        
│     │      SeckillClosedException.java     
│     │      SeckillException.java           
│     ├─po
│     │      Seckill.java
│     │      SuccessKilled.java
│     ├─service
│     │  │  SeckillService.java            //service逻辑
│     │  │  
│     │  └─impl
│     │          SeckillServiceImpl.java
│     ├─vo
│     │      SeckillResult.java             //值返回对象
│     └─web
│            SeckillController.java          //页面控制                  
└─resources
    │  application.properties                //SpringBoot配置文件
    │  log4j2.xml                            //log4j2配置文件，放到该目录下，SpringBoot会自动扫描
    ├─mapper
    │      SeckillMapper.xml
    │      SuccessKilledMapper.xml
    ├─sql
    │      procedure.sql                     //存储过程
    │      schema.sql                        //建库建表
    ├─static                                //静态资源
    └─templates
            detail.html                      //秒杀详情页
            list.html                        //秒杀列表
```


