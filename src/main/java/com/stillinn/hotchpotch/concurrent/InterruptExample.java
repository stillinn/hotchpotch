package com.stillinn.hotchpotch.concurrent;

import lombok.extern.slf4j.Slf4j;

/**
 * interrupt a thread
 *
 * @author stillinn
 * Created on 2023-06-24
 */
@Slf4j
public class InterruptExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    log.info("interrupted and break");
                    break;
                }

                try {
                    Thread.sleep(1000);
                    log.info("time: {}", System.currentTimeMillis());
                } catch (InterruptedException e) {
                    log.info("interrupt while sleep");
                    Thread.currentThread().interrupt(); // 重置中断标志
                }
            }
        });

        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
}
