package com.everday.useapp.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
* data 2019/6/24
* author Everday
* email wangtaohandsome@gmail.com
* desc 线程池
**/
public class ThreadUtils {
    //核心线程数量
    private int corePoolSize = 10;
    //最大线程数量
    private int maximumPoolSize = 30;
    //非核心线程闲置时的超时时长，超过这个时长，非核心线程就会被回收
    private long keepAliveTime = 10000;
    //单位秒
    private TimeUnit unit = TimeUnit.SECONDS;
    private BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(2);
    private ThreadFactory threadFactory = Executors.defaultThreadFactory();
    private static ThreadUtils instance;
    private ThreadPoolExecutor threadPoolExecutor;
    private ThreadUtils(){
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,blockingQueue,threadFactory);
    }

    public static ThreadUtils getInstance(){
        synchronized(ThreadUtils.class){
            if(instance == null){
                instance = new ThreadUtils();
            }
        }
        return instance;
    }

    /**
     * 执行
     * @param runnable
     */
    public void execute(Runnable runnable){
        threadPoolExecutor.execute(runnable);
    }

    /**
     * 终止线程池
     */
    public void onDestroy(){
        //不会立即终止线程池，而是要等所有任务缓存队列中的任务都执行完后才终止，但再也不会接受新的任务
        threadPoolExecutor.shutdown();
        //立即终止线程池，并尝试打断正在执行的任务，并且清空任务缓存队列，返回尚未执行的任务
//        threadPoolExecutor.shutdownNow();
    }
}
