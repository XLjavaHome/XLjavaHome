package com.xl.internet;

import static com.xl.util.IPUtil.*;
import org.junit.Test;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/*
UDP的特点
1.将数据源和目的封装成数据包中，不需要建立连接
2.每个数据报的大小在限制在64k内
3.因无连接，是不可靠协议
4.不需要建立连接，速度快
*/
public class IPTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress i = InetAddress.getLocalHost(); // 未知主机异常
        // Print.print(i.toString());
        // Print.print("主机名："+i.getHostName());
        // 获取任意一台主机的IP地址对象
        InetAddress ia = InetAddress.getByName("www.baidu.com");
        byte[] buf = ia.getAddress();
        System.out.println("地址;" + ia.getHostAddress());
        System.out.println("主机名：" + ia.getHostName());// 需要解析过程
        // 如果IP地址没有在网络上，解析不成功，就只能返回这个IP地址
        // byte[] buf = InetAddress.getAllByName("www.baidu.com");
        for (byte b : buf) {
            System.out.println(b + " ");
        }
    }

    /**
     *
     */
    @Test
    public void demoTest() throws UnknownHostException, SocketException {
        //只能在Windows上使用，Linux平台就gei屁了
        System.out.println("本机的IP = " + InetAddress.getLocalHost());
        //都可以使用
        System.out.println(getIp4());
    }
}

