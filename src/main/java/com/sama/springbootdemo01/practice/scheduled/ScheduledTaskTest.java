package com.sama.springbootdemo01.practice.scheduled;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * 基于注解@Scheduled实现定时任务
 * 注解 1、@Configuration 主要用于标记配置类，兼备Component的效果。
 * 注解 2、@EnableScheduling  用于开启定时任务。
 * @author fjk
 * @date 2020年11月30日
 * @since jdk1.8
 */
@Configuration
@EnableScheduling
public class ScheduledTaskTest {

    /**
     * 3.添加定时任务
     * 或直接指定时间间隔，例如：5秒 @Scheduled(fixedRate=5000)
     * 注
     * Cron表达式参数分别表示：
     * 秒（0~59） 例如0/5表示每5秒
     * 分（0~59）
     * 时（0~23）
     * 日（0~31）的某天，需计算
     * 月（0~11）
     * 周几（ 可填1-7 或 SUN/MON/TUE/WED/THU/FRI/SAT）
     * 注解 @Scheduled：除了支持灵活的参数表达式cron之外，还支持简单的延时操作，例如 fixedDelay ，fixedRate 填写相应的毫秒数即可。
     */
    @Scheduled(cron = "0/5 * * * * ?")
    private void configureTasks() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now().toLocalTime());
    }
}
