package com.xl.base;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-04-13
 * @Time: 14:23
 * To change this template use File | Settings | File Templates.
 */
public class TimerTaskThreadTest {
    public class TimerTaskThread extends Thread {
        public TimerTaskThread() {
            super.setName("TimerTaskThread");
        }
    }
}
