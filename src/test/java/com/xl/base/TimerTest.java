package com.xl.base;

import com.xl.util.TimerUtil;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA. 【强制】获取当前毫秒数System.currentTimeMillis();而不是new Date().getTime();
 * 说明：如果想获取更加精确的纳秒级时间值，使用System.nanoTime()的方式。在JDK8中，针对统计时间等场景，推荐使用Instant类。     <br/>
 * 不建议这样使用timer哈，多个timer的话因为不在一个线程池子里，虽然理论上可以实现多线程但是线程并发的可控性不强。建议使用ScheduledExecutorService(轻量简单，有一个线程池)或者使用Quartz(较重，但是功能更多)替代，方便对线程做控制以及处理一些可能出现的异常 :)...
 * <p>
 * Jnuit没有显示是因为：junit测试完成后就会关闭程序。而线程需要程序一直执行，2秒后才会打印出文字，所以Junit不会显示。                                                                                    <p/>
 * timer有且仅有一个线程去执行定时任务，如果存在多个任务，且任务时间过长会导致执行效果与预期不同不符
 * <br/>
 * 不适应： 对时效行、对复杂任务的调度高，建议用quartz
 *
 * @author: 徐立
 * @Date: 2017-11-28
 * @Time: 11:03
 * To change this template use File | Settings | File Templates.
 */
public class TimerTest {
    public static void main(String[] args) {
        TimerUtil.schedule(() -> {
            System.out.println("测试");
        }, 4000L);
        //Timer timer = new Timer();
        //任务-要调度的任务。延迟-任务执行前的延迟(毫秒)。连续任务执行之间的周期(以毫秒为单位)。
        //第一次执行是在当前时间两秒之后，之后每隔一秒执行一次
        //timer.schedule(new MyTimerTask(), 2000L, 1000L);
        //第一次执行是在当前时间2秒后，只执行一次
        //timer.schedule(new MyTimerTask(), 2000L);
    }

    /**
     * 用测试类不行
     *
     * @throws InterruptedException
     */
    @Test
    public void timerTest() throws InterruptedException {
        TimerUtil.schedule(() -> {
            System.out.println("测试");
        }, 4000L);
    }
}
