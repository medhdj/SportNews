package com.medhdj.samples.sportnews.executor;

import com.medhdj.samples.domain.executor.ThreadExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;

public class AppThreadPoolExecutor implements ThreadExecutor {
    private final ThreadPoolExecutor threadPoolExecutor;

    public AppThreadPoolExecutor() {
        this.threadPoolExecutor = new ThreadPoolExecutor(3, 6, 10,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new JobThreadFactory());
    }

    @Override
    public void execute(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }


    private static class JobThreadFactory implements ThreadFactory {
        private int counter = 0;

        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, "sportnews_thread_" + counter++);
        }
    }
}
