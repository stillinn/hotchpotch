package com.stillinn.hotchpotch.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class Producer implements Runnable {
    private volatile boolean running = true;

    private BlockingQueue<IntData> blockingQueue;

    private static final int SLEEP_TIME = 1000;

    public Producer(BlockingQueue<IntData> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        log.info("producer: {} starts work", Thread.currentThread().getId());
        while (running) {
            try {
                Random random = new Random();
                IntData data = new IntData(random.nextInt());
                if (!blockingQueue.offer(data)) {
                    log.info("producer add data fail");
                }
                Thread.sleep(random.nextInt(SLEEP_TIME));
            } catch (InterruptedException e) {
                log.error("", e);
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        running = false;
    }
}
