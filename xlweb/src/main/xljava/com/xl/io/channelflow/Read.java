package com.xl.io.channelflow;

import java.io.IOException;
import java.io.PipedInputStream;

class Read implements Runnable  //读取线程
{
    private PipedInputStream pis;

    Read(PipedInputStream pis) {
        this.pis = pis;
    }

    @Override
    public void run()   //覆盖run方法不能抛只能处理
    {
        try {
            byte[] buf = new byte[1024];
            //读取前没数据，写入数据要等6秒
            System.out.println("读取前请等待...");
            int len = pis.read(buf);  // 将读取的数据存储到buf数组，并赋值给len
            String s = new String(buf, 0, len);  //转换成字符串
            System.out.println("读取数据..阻塞结束");
            System.out.println(s);
            pis.close();  //关闭流
        } catch (IOException e) {
            throw new RuntimeException("管道读取流失败 ");
        }
    }
}
