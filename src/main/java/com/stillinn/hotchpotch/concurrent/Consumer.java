package com.stillinn.hotchpotch.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author stillinn
 * Created on 2023-06-11
 */
@Slf4j
public class Consumer implements Runnable {
    private BlockingQueue<IntData> blockingQueue;
    private static final int SLEEP_TIME = 1000;

    public Consumer(BlockingQueue<IntData> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        log.info("consumer starts, id={}", Thread.currentThread().getId());
        while (true) {
            try {
                IntData data = blockingQueue.take();
                if (data != null) {
                    log.info("consumer data: {}", data.getData());
                }
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                log.error("", e);
                Thread.currentThread().interrupt(); // 重新设置中断标志位
            }
        }
    }
}
