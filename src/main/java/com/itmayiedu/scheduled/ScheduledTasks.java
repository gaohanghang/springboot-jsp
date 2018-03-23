package com.itmayiedu.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Scheduled(fixedRate = 10000)
    public void test() {
        // 执行任务带调度方法
        System.out.println("我正在每隔一秒打印...");
    }
}
