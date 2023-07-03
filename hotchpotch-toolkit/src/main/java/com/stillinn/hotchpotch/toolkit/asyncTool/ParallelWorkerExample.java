package com.stillinn.hotchpotch.toolkit.asyncTool;

import com.jd.platform.async.executor.Async;
import com.jd.platform.async.wrapper.WorkerWrapper;

import java.util.concurrent.ExecutionException;

/**
 * @author stillinn
 * Created on 2023-06-27
 */
public class ParallelWorkerExample {
    public static void main(String[] args) {
        WorkerA workerA = new WorkerA();
        WorkerB workerB = new WorkerB();
        WorkerC workerC = new WorkerC();

        // 并行执行
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
                .build();
        WorkerWrapper<Integer, Integer> workerWrapperA = new WorkerWrapper.Builder<Integer, Integer>()
                .id("WorkerA")
                .worker(workerA)
                .param(100)
                .callback(workerA)
                .build();

        try {
            Async.beginWork(100, workerWrapperA, workerWrapperB, workerWrapperC);
        } catch (ExecutionException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
