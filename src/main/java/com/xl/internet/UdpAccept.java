package com.xl.internet;

/*需求：
 定义一个应用程序，用于接受udp协议传输数据并处理的。

 定义udp的接收端，通常会监听一个端口，其实就是给这个接受这个网络应用程序定义数字标识。不定义话的系统会随即给。
 方便与明确哪些数据过来哪些应用程序可以处理
 思路：
 1.定义udpsocket服务
 2.定义一个数据包。因为要存储接受到的字节数据。因为数据包对象中有更多功能可以提取字节数据中的不同数据信息
 3.通过socket服务的receive方法收到的数据存入已经定义好的数据包中
 4.通过数据包对象的特有功能。将这些不同的数据取出。打印在控制台上
 5.关闭资源
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpAccept {
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // 1.定义udpsocket服务，建立端点
        DatagramSocket ds = new DatagramSocket(1666); // 监听端口，不能写在while里面,要不两个服务写在一个端口，会出现绑定异常
        // 2.定义一个数据包。因为要存储接受到的字节数据。因为数据包对象中有更多功能可以提取字节数据中的不同数据信息
        System.out.println("服务器启动..");
        while (true) {
            byte[] buf = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            // 3.通过socket服务的receive方法收到的数据存入已经定义好的数据包中
            ds.receive(dp);//阻塞时方法
            // 4.通过数据包对象的特有功能。将这些不同的数据取出。打印在控制台上
            String ip = dp.getAddress().getHostAddress();
            String data = new String(dp.getData(), 0, dp.getLength()); // 因为数据就一部分，不需要1024
            int port = dp.getPort();
            System.out.println(ip + "" + data + ".." + port);
            // 5.关闭资源
            ds.close();
        }
    }
}
