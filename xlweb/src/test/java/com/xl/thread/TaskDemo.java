package com.xl.thread;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author 徐立
 * @Decription 1.必须放在主线程里面，用junit里面代码不能执行
 * @date 2014年3月2日
 */
public class TaskDemo {
    public static void main(String[] args) throws InterruptedException {
        // Schedele();
        // 过2秒调度一次，过4秒调度一次，过8秒调度一次
        // Incremental();
        init();
    }

    public static void init() {
        Calendar instance = Calendar.getInstance();
        new Timer().schedule(new MyTimeTask(), instance.getTime());
    }

    public static void Incremental() throws InterruptedException {
        new Timer().schedule(new MyTimeTask(), 2000);
        while (true) {
            Thread.sleep(2000);
            System.out.println(System.currentTimeMillis());
        }
    }
}

class MyTimeTask extends TimerTask {
    /**
     * 内部类不能是静态的，如果不是静态的数字是不会变得
     */
    static long i = 2000;

    public MyTimeTask() {
        super();
    }

    @Override
    public void run() {
        System.out.println(i);
        new Timer().schedule(new MyTimeTask(), i = i * 2);
    }
}
