package com.xl.internet;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 1.tcp分为客户端和服务端
 2.客户端分为Socket
 服务端对应的对象是ServerSocket

 */

/*
 客户端：
 因为tcp是面向连接的，所以在建立socket服务时，就要有服务端的存在，连接成功，形成通路后，在该通路进行数据传输
 需求：给服务端发送一个文本数据。
 步骤：
 1.创建Socket服务，并指定要连接的主机和端口

 */
public class TcpClient {
    public static void main(String[] args) throws Exception {
        // 创建客户端的socket服务。指定目的的主机和端口
        Socket s = new Socket("192.168.1.254", 10003);
        OutputStream out = s.getOutputStream();
        out.write("top ge men lai le".getBytes());
        s.close();
    }
}

/*
  需求：定义短点接受数据并打印在控制台上。 
  服务器： 
  1.建立服务器端的socket服务。ServerSocket(); 并监听一个端口
  2.获取连接过来的客户端对象。 通过ServerSocket的accept方法。所以这个方法是阻塞式的
  3.客户端如果发过来数据，那么服务端要使用对应的客户端对像，并获取到该客户端的读取流来读取发送过来的数据 4.关闭客户端。（可选）
 */
class TcpServer {
    public static void main(String[] args) throws Exception {
        // 建立服务端Socket服务，并监听一个端口
        ServerSocket ss = new ServerSocket(10003);
        // 通过accept方法连接过来的客户端对象。
        Socket s = ss.accept();
        String ip = s.getInetAddress().getHostAddress();
        System.out.println("ip地址是：" + ip);
        // 获取客户端发送过来的数据，那么要用客户端的对象读取流读取对象
        InputStream in = s.getInputStream();
        byte[] buf = new byte[1024];
        int len = in.read(buf);
        System.out.println(new String(buf, 0, len));
        // udp通过数据包获取对方IP
        s.close(); // 关对方,
        ss.close();// 可选操作
    }
}


