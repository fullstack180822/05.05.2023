package com.example;

import java.util.ArrayList;
import java.util.Queue;

public class MyWorker extends Thread {

    Queue<Integer> work;
    private Object sleepObject = new Object();

    public MyWorker(String name, Queue<Integer> work) {
        super(name);
        this.work = work;
    }

    private boolean quit = false;

    public void stopMe() {
        System.out.format("stop me for thread %s \n", this.getName());
        quit = true;
    }

    public void wakeMe() {
        System.out.format("wake me for thread %s \n", this.getName());
        synchronized (sleepObject) {
            sleepObject.notifyAll();
        }
    }

    @Override
    public void run() {
        while (!quit) {
            if (work.size() > 0) {
                Integer the_job = work.remove();
                try {
                    System.out.format("working in thread %s, this will take %d seconds \n", this.getName(), the_job);
                    Thread.sleep(the_job * 1000);
                    System.out.format("working in thread %s finished (took %d seconds) \n", this.getName(), the_job);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.format("no work, going to sleep for thread %s \n", this.getName());
                synchronized (sleepObject) {
                    try {
                        sleepObject.wait();
                        System.out.format("thread %s woke up \n", this.getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
