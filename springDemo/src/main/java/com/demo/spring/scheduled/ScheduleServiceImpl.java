package com.demo.spring.scheduled;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时器
 * @author long.yu
 * @date 2019-04-14
 * @version 0.0.1
 *
 * @Scheduled的配置项
 * cron               String          使用表达式的方式定义任务执行时间，有6~7个空格分隔的时间元素，按顺序依次是“秒 分 时 天 月 星期 年”
 * zone               String          可以通过它设定区域时间
 * fixedDelay         long           表示从上一个任务完成开始到下一个任务开始的间隔 单位为毫秒
 * fixedDelayString   String         与fixedDelay相同，只是使用字符串，这样可以用sqEL来引入配置文件的配置
 * initialDelay       long           在spring IoC容器完成初始化后，首次任务执行延迟时间，单位为毫秒
 * initialDelayString String         与initialDelay相同，只是使用字符串，这样可以用sqEL来引入配置文件的配置
 * fixedRate          long           从上一个任务开始到下一个任务开始的间隔，单位为毫秒
 * fixedRateString    String         与fixedRate相同，只是使用字符串，这样可以用sqEL来引入配置文件的配置
 *
 * @通配符含义
 * *         表示任意值
 * ?         不指定值，用于处理天和星期配置的冲突
 * -         指定时间区间
 * /         指定时间间隔执行
 * L         最后的
 * #         第几个
 * ，        列举多个项
 *
 *
 * @cron表达式列举
 * 0 0 0 * * ?"        每天0点整触发
 * 0 15 23 ? * *       每天23:15触发
 * 0 15 0 * * ?        每天0:15触发
 * 0 15 10 * * ? *      每天早上10:15触发
 * 0 30 10 * * ? 2018   2018年每天早上10:30触发
 * 0 * 23 * * ?         每天从23点开始到23:59每分钟一次触发
 * 0 0/3 23 * * ?       每天从23点开始到23:59每3分钟一次触发
 * 0 0/3 20,23 * * ?    每天的20点至20:59和23点至23:59两个时间段内每3分钟一次触发
 * 0 0-5 21 * * ?       每天21:00至21:05每分钟一次触发
 * 0 10,44 14 ? 3 WED    3月每周三的14:10和14:44分触发
 * 0 0 23 ? * MON-FRI    每周一到周五的23:00触发
 * 0 30 23 ? * 6L 2017-2020 2017年至2020年每月最后一个周五的23:30分触发
 * 0 15 22 ? * 6#3       每月第三周周五的22:15触发
 */
//@Component
public class ScheduleServiceImpl {
    int count3=1;
    int count4=1;

    //Spring IOC容器初始化后，第一次延迟3秒 每隔1秒执行一次
    @Scheduled(initialDelay = 3000,fixedRate = 1000)
    @Async
    public void job3(){
        System.out.println(Thread.currentThread().getName()+"每秒执行一次，执行第"+(count3++)+"次");
    }

    //16:00到16::59每分钟执行一次
    @Scheduled(cron = "0 * 16 * * ?")
    @Async
    public void job4(){
        System.out.println(Thread.currentThread().getName()+"每分钟执行一次，执行第"+(count4++)+"次");
    }
}
