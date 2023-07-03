package com.stillinn.hotchpotch.toolkit.asyncTool;

import com.jd.platform.async.executor.Async;
import com.jd.platform.async.wrapper.WorkerWrapper;

import java.util.concurrent.ExecutionException;

/**
 * @author stillinn
 * Created on 2023-06-27
 */
public class CombineWorkerExample {
    public static void main(String[] args) {
//        fork();
        merge();
    }

    // A执行完后，BC并行执行
    private static void fork() {
        WorkerA workerA = new WorkerA();
        WorkerB workerB = new WorkerB();
        WorkerC workerC = new WorkerC();
        WorkerWrapper<Integer, Integer> workerWrapperA = new WorkerWrapper.Builder<Integer, Integer>()
                .id("WorkerA")
                .worker(workerA)
                .param(100)
                .callback(workerA)
                .build();
        WorkerWrapper<Integer, Integer> workerWrapperC = new WorkerWrapper.Builder<Integer, Integer>()
                .id("WorkerC")
                .worker(workerC)
                .param(100)
                .callback(workerC)
                .depend(workerWrapperA)
                .build();
        WorkerWrapper<Integer, Integer> workerWrapperB = new WorkerWrapper.Builder<Integer, Integer>()
                .id("WorkerB")
                .worker(workerB)
                .param(100)
                .callback(workerB)
                .depend(workerWrapperA)
                .build();

        try {
            Async.beginWork(1000, workerWrapperA);
        } catch (ExecutionException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    // BC并行执行完，再执行A
    private static void merge() {
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
                .build();
        WorkerWrapper<Integer, Integer> workerWrapperA = new WorkerWrapper.Builder<Integer, Integer>()
                .id("WorkerA")
                .worker(workerA)
                .param(100)
                .callback(workerA)
                .depend(workerWrapperB, workerWrapperC)
                .build();

        try {
            Async.beginWork(1000, workerWrapperB, workerWrapperC);
        } catch (ExecutionException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
