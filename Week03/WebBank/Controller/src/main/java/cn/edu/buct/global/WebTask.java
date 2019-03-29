package cn.edu.buct.global;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WebTask {

    // 每天24点刷新次数
    @Scheduled(cron = "0 0 24 * * ?")
    public void TaskJob() {

    }
}