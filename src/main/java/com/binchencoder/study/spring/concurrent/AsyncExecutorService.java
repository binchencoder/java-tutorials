package com.binchencoder.study.spring.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
@Slf4j
public class AsyncExecutorService {

    @Async("testExecutor")
    public void execute() {
        log.info("Execute async task");
    }
}
