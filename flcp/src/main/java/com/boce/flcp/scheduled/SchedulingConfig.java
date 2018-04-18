package com.boce.flcp.scheduled;

import org.assertj.core.internal.Dates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author xutang
 * @version V1.0
 * @Package com.boce.flcp.scheduled
 * @Description: TODO(定时任务)
 * @date 2017/11/30 10:59
 */
//@Configuration
//@EnableScheduling // 启用定时任务
@Component
public class SchedulingConfig {
//    public final static long ONE_Minute =  60 * 1000;
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//    @Scheduled(cron = "0/20 * * * * ?") // 每20秒执行一次
//    public void scheduler() {
//        logger.info(">>>>>>>>>>>>> scheduled ... ");
//    }
//
//    //fixedDelay 当前业务执行完成之后，按照时间执行
//    @Scheduled(fixedDelay=ONE_Minute)
//    public void fixedDelayJob(){
//        logger.info(" >>fixedDelay执行....");
//    }
//    //fixedRate 按照时间执行，忽略业务执行时间
//    @Scheduled(fixedRate=ONE_Minute)
//    public void fixedRateJob(){
//        logger.info(" >>fixedRate执行....");
//    }
//    //cron 按照规定日期时间执行
//    @Scheduled(cron="0 15 3 * * ?")
//    public void cronJob(){
//        logger.info(" >>cron执行....");
//    }
}
