package com.demon.common.current;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;


/**
 * @author jundemon
 * @description: 线程池
 */
@Slf4j
public class ThreadPool {

  private volatile ExecutorService executorService;
  private final String threadName;
  private final Integer corePoolSize;
  private final Integer maximumPoolSize;
  private final Integer keepAliveTime;
  private final TimeUnit timeUnit;
  private final BlockingQueue<Runnable> workQueue;
  private final RejectedExecutionHandler handler;


  public ThreadPool(String threadName, Integer corePoolSize, Integer maximumPoolSize,
      Integer keepAliveTime, TimeUnit timeUnit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
    this.threadName = threadName;
    this.corePoolSize = corePoolSize;
    this.maximumPoolSize = maximumPoolSize;
    this.keepAliveTime = keepAliveTime;
    this.timeUnit = timeUnit;
    this.workQueue = workQueue;
    this.handler = handler;
  }

  public void init() {
    log.info("init thread pool {}-pool success", threadName);
    this.executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
        timeUnit, workQueue,
        new ThreadFactoryBuilder().setNameFormat(threadName + "-pool-%d").build(), handler);
  }

  public void close() {
    log.info("thread pool {}-pool shutdown", threadName);
    this.executorService.shutdown();
  }

  /**
   * 异步执行方法
   *
   * @param runnable 异步任务
   */
  public void execute(Runnable runnable) {
    this.executorService.execute(runnable);
  }


}
