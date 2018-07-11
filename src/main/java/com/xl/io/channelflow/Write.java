package com.xl.io.channelflow;

import java.io.PipedOutputStream;

public class Write implements Runnable  //写入线程
{
    private PipedOutputStream pos;

    Write(PipedOutputStream pos) {
        this.pos = pos;
    }

    @Override
    public void run()   //覆盖run方法不能抛只能处理
    {
        try {
            //写入之前让写入的线程停一会，要抛异常
            Thread.sleep(6000); //sleep释放执行权
            pos.write("piped 来 啦".getBytes());  //write方法只能接受Byte及其数组
            pos.close();
        } catch (Exception e) {
            throw new RuntimeException("管道输出流失败 ");  //又称为写入
        }
    }
}
