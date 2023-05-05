package com.example;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {

    final static int pool_size = 5;

    private static List<MyWorker> thread_pool = new ArrayList<>();
    private static Queue<Integer> queue = new ArrayDeque<>();

    private static void insert_and_wake_up(Integer work) {

        for (MyWorker myWorker:thread_pool) {
            myWorker.wakeMe();
        }
        queue.add(work);
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < pool_size; i++) {
            MyWorker myWorker = new MyWorker("worker " + i, queue);
            thread_pool.add(myWorker);
            // need to check all of the threads finished
            // myWorker.setDaemon(true);
            myWorker.start();
        }

        insert_and_wake_up(5);
        insert_and_wake_up(10);
        insert_and_wake_up(8);
        insert_and_wake_up(2);
        insert_and_wake_up(3);
        insert_and_wake_up(2);
        insert_and_wake_up(8);

        while (queue.size() > 0) {
            Thread.sleep(100);
        }

        // if not daemon
        for (MyWorker myWorker:thread_pool) {
            myWorker.stopMe();
        }

    }

}
