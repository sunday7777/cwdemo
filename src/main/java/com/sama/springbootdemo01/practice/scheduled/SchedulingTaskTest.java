package com.sama.springbootdemo01.practice.scheduled;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;

/**
 * 基于接口SchedulingConfigurer实现定时任务，适用于从数据库中读取指定时间来动态执行定时任务
 * 注解 1、@Configuration 主要用于标记配置类，兼备Component的效果。
 * 注解 2、@EnableScheduling  用于开启定时任务。
 * @author fjk
 * @date 2020年11月30日
 * @since jdk1.8
 */
@Configuration
@EnableScheduling
public class SchedulingTaskTest implements SchedulingConfigurer {

    /**
     * 模拟从数据库中获取cron表达式
     * @return
     */
    public String getCronByDb(){
        return "0/10 * * * * ?";
    }

    /**
     * 执行定时任务.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> System.out.println("执行动态定时任务: " + LocalDateTime.now().toLocalTime()),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期，并合法性校验.
                    if (StringUtils.isEmpty(this.getCronByDb())) {
                        System.out.println("cron表达式不合法");
                        return null;
                    }
                    //2.2 返回执行周期(Date)
                    return new CronTrigger(this.getCronByDb()).nextExecutionTime(triggerContext);
                }
        );
    }
}
