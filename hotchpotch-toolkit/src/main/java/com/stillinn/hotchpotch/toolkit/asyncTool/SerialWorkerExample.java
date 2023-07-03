package com.stillinn.hotchpotch.toolkit.asyncTool;

import com.jd.platform.async.executor.Async;
import com.jd.platform.async.wrapper.WorkerWrapper;

import java.util.concurrent.ExecutionException;

/**
 * case出自：https://blog.csdn.net/rongtaoup/article/details/125247148
 * 问题：串行编排性能不是很好，不如在单线程中执行
 *
 * @author stillinn
 * Created on 2023-06-26
 */
public class SerialWorkerExample {
    public static void main(String[] args) {
        // 任务执行顺序：A -> B -> C
        WorkerA workerA = new WorkerA();
        WorkerB workerB = new WorkerB();
        WorkerC workerC = new WorkerC();

        WorkerWrapper<Integer, Integer> workerWrapperC = new WorkerWrapper.Builder<Integer, Integer>()
                .id("WorkerC")
                .worker(workerC)
                .param(100)
                .callback(workerC)
                .build();
        WorkerWrapper<Integer, Integer> workerWrapperB = new WorkerWrapper.Builder<Integer, Integer>()
                .id("WorkerB")
                .worker(workerB)
                .param(100)
                .callback(workerB)
                .next(workerWrapperC)
                .build();
        WorkerWrapper<Integer, Integer> workerWrapperA = new WorkerWrapper.Builder<Integer, Integer>()
                .id("WorkerA")
                .worker(workerA)
                .param(100)
                .callback(workerA)
                .next(workerWrapperB)
                .build();

        try {
            Async.beginWork(1000, workerWrapperA);
        } catch (ExecutionException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}
