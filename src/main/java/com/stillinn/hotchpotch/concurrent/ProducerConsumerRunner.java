package com.stillinn.hotchpotch.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author stillinn
 * Created on 2023-06-15
 */
public class ProducerConsumerRunner {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<IntData> blockingQueue = new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(blockingQueue);
        Producer producer1 = new Producer(blockingQueue);
        Producer producer2 = new Producer(blockingQueue);

        Consumer consumer = new Consumer(blockingQueue);
        Consumer consumer1 = new Consumer(blockingQueue);
        Consumer consumer2 = new Consumer(blockingQueue);

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(producer);
        service.execute(producer1);
        service.execute(producer2);
        service.execute(consumer);
        service.execute(consumer1);
        service.execute(consumer2);

        TimeUnit.SECONDS.sleep(10);
        producer.stop();
        producer1.stop();
        producer2.stop();

        TimeUnit.SECONDS.sleep(3);
        service.shutdown();
    }
}
