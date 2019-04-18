package com.xl.internet;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 需求：通过udp传输方式，将一段文字数据发送出去。
 思路：
 1.建立updsocket服务 //找到邮局
 2.提供数据，并将数据封装到数据包中。 
 3.通过socket服务发送功能，将数据包发送出去
 4.关闭资源   //至少要用到网卡，走底层资源
 */
public class UdpSend {
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        //  自动生成的方法存根
        // 1.创建udp服务，通过DataGramSocket
        DatagramSocket ds = new DatagramSocket(1666);
        // 2确定数据并封装成数据包
        byte[] buf = "udp ge men lai le".getBytes();
        DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.1.32"), 1666);
        // 3.通过Socket服务通过已有的数据包发送出去
        ds.send(dp);
        // 4.关闭资源
        ds.close();
    }
}
