package com.sama.springbootdemo01.practice.thread;

/**
 * java多线程练习（模拟售票窗口）
 */
public class WindowThread implements Runnable {

    //总票数
    private volatile int ticketNum;
    //购买票数
    private volatile int sellNum;
    //创建了一个锁对象，用于线程之间的互斥。
    private final Object lockObject = new Object();


    public WindowThread(int ticketNum, int sellNum){
        this.ticketNum = ticketNum;
        this.sellNum = sellNum;
    }

    @Override
    public void run(){
        //synchronized对lockObject进行加锁，实现了各线程之间的互斥行为。
        synchronized(lockObject){
            //使不符合条件的线程（买的票数大于总票数）等待。
            while (ticketNum<sellNum) {
                try {
                    lockObject.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ticketNum -= sellNum;
            System.out.println(Thread.currentThread().getName()+"售卖" + sellNum + "张票，剩余票数:"+ ticketNum + "张。");
            ticketNum += sellNum;
            System.out.println(Thread.currentThread().getName()+"补充了" + sellNum + "张票，剩余票数:"+ ticketNum + "张。");
            //通知等待的线程可以运行(同步)
            lockObject.notifyAll();
        }

    }
}
