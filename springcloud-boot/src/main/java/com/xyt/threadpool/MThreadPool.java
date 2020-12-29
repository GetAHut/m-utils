package com.xyt.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 线程池日常使用
 *          ---->阿里规范要求：
 *                  线程池不能使用Executors对象创建，而是通过ThreadPoolExecutor来创建
 *          ThreadPoolExecutor参数
 *              1. corePoolSize： 核心池子的大小
 *              2. maximumPoolSize： 线程池最大线程数量
 *              3. keepAliveTime: 表示线程没有工作会存活多久
 *              4. unit ： keepAliveTime的取值单位 从TimeUnit中取
 *              5. workQueue： 阻塞队列
 *
 * @Author: abby
 * @Date: 2020/12/25 10:22
 */
@Slf4j
public class MThreadPool {

    private final static Logger logger = LoggerFactory.getLogger(MThreadPool.class);

    private BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    /**
     * 线程池的创建
     */
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5,
            1000L, TimeUnit.SECONDS, queue);

    public static void main(String[] args) {

        //不建议使用Executors创建线程
        ExecutorService executorService = Executors.newFixedThreadPool(5);


    }
}
