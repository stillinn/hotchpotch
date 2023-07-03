package com.stillinn.hotchpotch.toolkit.asyncTool;

import com.jd.platform.async.callback.ICallback;
import com.jd.platform.async.callback.IWorker;
import com.jd.platform.async.executor.timer.SystemClock;
import com.jd.platform.async.worker.WorkResult;
import com.jd.platform.async.wrapper.WorkerWrapper;
import com.stillinn.hotchpotch.component.JacksonUtil;

import java.util.Map;

/**
 * @author stillinn
 * Created on 2023-06-27
 */
public class WorkerB implements IWorker<Integer, Integer>, ICallback<Integer, Integer> {

    /**
     * Worker开始的时候先执行begin
     */
    @Override
    public void begin() {
        System.out.println("B - Thread:" + Thread.currentThread().getName() + "- start --" + SystemClock.now());
    }

    /**
     * Worker中耗时操作在此执行RPC/IO
     *
     * @param object      object
     * @param allWrappers 任务包装
     * @return
     */
    @Override
    public Integer action(Integer object, Map<String, WorkerWrapper> allWrappers) {
//        allWrappers.forEach((s, workerWrapper) -> {
//            System.out.println("WorkerB action: " + s + ", " + workerWrapper.getWorkResult());
//        });
        return object + 2;
    }

    /**
     * action执行结果的回调
     *
     * @param success
     * @param param
     * @param workResult
     */
    @Override
    public void result(boolean success, Integer param, WorkResult<Integer> workResult) {
        System.out.println("B - param:" + JacksonUtil.toJson(param));
        System.out.println("B - result:" + JacksonUtil.toJson(workResult));

        System.out.println("B - Thread:" + Thread.currentThread().getName() + "- end --" + SystemClock.now());
    }

    /**
     * Worker异常时的回调
     *
     * @return
     */
    @Override
    public Integer defaultValue() {
        System.out.println("B - defaultValue");
        return 0;
    }
}
