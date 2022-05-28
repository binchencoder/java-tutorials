package com.binchencoder.study.spring.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@Slf4j
public class ThreadPoolConfig {

    @Bean("testExecutor")
    public ThreadPoolTaskExecutor testExecutor() {
        ThreadPoolTaskExecutor taskExec = new ThreadPoolTaskExecutor();
        taskExec.setCorePoolSize(2);
        taskExec.setMaxPoolSize(2);
        taskExec.setThreadNamePrefix("Test_Task_Async_");
        taskExec.initialize();
        return taskExec;
    }

}
