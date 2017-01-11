package com.me.daydaystudy.utils;

/**
 * @author :   郗琛
 * @date :   2017/1/11
 */

public class RunnablePermissionUtils {
    private Runnable successRunnable;
    private Runnable failRunnable;

    public RunnablePermissionUtils(Runnable successRunnable, Runnable failRunnable) {
        this.successRunnable = successRunnable;
        this.failRunnable = failRunnable;
    }

    public Runnable getSuccessRunable() {
        return successRunnable;
    }

    public Runnable getFailRunable() {
        return failRunnable;
    }
}
