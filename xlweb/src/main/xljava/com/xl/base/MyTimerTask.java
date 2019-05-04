package com.xl.base;

import com.xl.util.DateUtil;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-05-04
 * @Time: 22:25
 * To change this template use File | Settings | File Templates.
 */
public class MyTimerTask extends TimerTask {
    private int num = 1;

    @Override
    public void run() {
        System.out.println("开始"+DateUtil.getDate());
    }
}
