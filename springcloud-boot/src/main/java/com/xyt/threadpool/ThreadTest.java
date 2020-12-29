package com.xyt.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;


/**
 * 线程 的三种创建方式
 *         通过Runnable和Callable两种方式可以共享一个target
 *         Callable比Runnable多一个返回值，并且call()方法可以抛出异常
 *
 *
 * @Author: abby
 * @Date: 2020/12/25 10:27
 */
@Slf4j
public class ThreadTest {

    private static Logger logger = LoggerFactory.getLogger(ThreadTest.class);

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.setName("thread");
        thread.start();

        Thread thread1 = new Thread(new MyRunnable());
        thread1.setName("runnable");
        thread.start();

        //通过Callable实现
        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Callbale -> thread";
            }
        });

        Thread thread2 = new Thread(task);
        thread2.setName("callbale-Thread");
        thread2.start();
        while (!task.isDone()){
            try {
                logger.info("Callbale is not done");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            String s = task.get();
            logger.info("task-result ----->" + s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    /**
     * 通过继承Thread类重写run()创建线程
     */
    static class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            logger.info("通过Thread创建线程池---->running----" + Thread.currentThread().getName());
        }
    }

    static class MyRunnable implements  Runnable{

        @Override
        public void run() {
            logger.info("Runnable----------->" + Thread.currentThread().getName());
        }
    }

}
