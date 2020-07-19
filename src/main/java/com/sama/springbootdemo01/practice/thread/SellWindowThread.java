package com.sama.springbootdemo01.practice.thread;

public class SellWindowThread implements Runnable {

    //总票数
    private volatile int ticketNum = 10;

    public SellWindowThread(int ticketNum){
        this.ticketNum = ticketNum;
    }

    public SellWindowThread() {

    }

    @Override
    public void run(){

        while (ticketNum>0){
            ticketNum--;
            System.out.println(Thread.currentThread().getName()+"售卖1张票，剩余票数:"+ ticketNum + "张。");
            //使线程休眠一秒
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
