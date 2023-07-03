package com.stillinn.hotchpotch.toolkit.asyncTool;

import com.jd.platform.async.callback.ICallback;
import com.jd.platform.async.callback.IWorker;
import com.jd.platform.async.executor.timer.SystemClock;
import com.jd.platform.async.worker.WorkResult;
import com.jd.platform.async.wrapper.WorkerWrapper;
import com.stillinn.hotchpotch.component.JacksonUtil;

import java.util.Map;

/**
 *
 * @author stillinn
 * Created on 2023-06-26
 */
public class WorkerA implements IWorker<Integer, Integer>, ICallback<Integer, Integer> {
    /**
     * 执行任务
     * @param object
     * @param allWrappers
     * @return
     */
    @Override
    public Integer action(Integer object, Map<String, WorkerWrapper> allWrappers) {
        return object + 1;
    }

    @Override
    public Integer defaultValue() {
        return 0;
    }


    @Override
    public void begin() {
        System.out.println("A - Thread:" + Thread.currentThread().getName() + "- start --" + SystemClock.now());
    }

    @Override
    public void result(boolean success, Integer param, WorkResult<Integer> workResult) {
        System.out.println("A - param:" + JacksonUtil.toJson(param));
        System.out.println("A - result:" + JacksonUtil.toJson(workResult));

        System.out.println("A - Thread:" + Thread.currentThread().getName() + "- end --" + SystemClock.now());
    }
}
