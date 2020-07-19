package com.sama.springbootdemo01.practice.thread;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * java多线程练习
 * 多线程常用方法：
 * start() 启动线程
 * sleep() 休眠线程
 * join() 使其他线程等待当前线程结束
 * yield() 使当前线程释放处理器资源
 * currentThread() 获取当前线程
 * 注：结束线程不要用end()、interrupt()方法，要用逻辑方法停止线程，如：设置退出标识
 */
@RestController
@RequestMapping("/practice/thread/test")
public class TestThreadController {

    @RequestMapping(value = "sellTicket1")
    public void sellTicket() throws InterruptedException {
        SellWindowThread sellWindowThread = new SellWindowThread();
        Thread window1 = new Thread(sellWindowThread,"窗口一");
        Thread window2 = new Thread(sellWindowThread,"窗口二");
        Thread window3 = new Thread(sellWindowThread, "窗口三");
        window1.start();
        window2.start();
        window3.start();
        //window1.join();
    }

    @RequestMapping(value = "sellTicket2")
    public void sellTicket2() {
        for(int i = 1; i<=10; i++){
            WindowThread windowThread = new WindowThread(10,i);
            Thread window = new Thread(windowThread,"窗口"+i);
            window.start();
        }
    }

}
