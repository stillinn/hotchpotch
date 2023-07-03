package com.stillinn.hotchpotch.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author stillinn
 * Created on 2023-06-28
 */
@Slf4j
public class CompletableFutureTest {

    @Test
    public void test() {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture.runAsync(() -> {
            log.info("run task in thread: {}", Thread.currentThread().getName());
        }, executorService).whenComplete((t, e) -> {
            log.info("cost: {}", System.currentTimeMillis() - start);
        });
    }
}
