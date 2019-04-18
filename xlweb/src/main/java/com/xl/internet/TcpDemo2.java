package com.xl.internet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 需求：建立一个文本转换服务器。
 客户端给服务端发送文本，服务端会将文本转换成大写返回给客户端
 而且客户端可以不端的进行文本转换。当客户端输入over，转换结束

 分析：
 客户端：
 既然是操作设备上的数据，那么可以使用IO技术，并安装io流来思考
 源：键盘录入
 目的：网络设备，网络输出流。
 而且操作的是网络文本数据。可以选择字符流。
 步骤
 1.建立服务。
 2.获取键盘录入
 3.将数据发给服务端。
 4.获取服务端返回的大写数据。
 5.结束，关资源。
 都是文本数据，可以使用字符流进行操作，同时提高效率加入缓冲

 */
class TransClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("127.0.0.1", 10005);
        // 最方便的就是用转换用的狂读狂写
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in)); // 源，定义读取键盘输入的流对象
        BufferedWriter bufOut = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));// 定义目的是输出流。发给服务端
        // 定义一个socket度曲流，读取服务端返回的大写信息
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String line = null;
        while ((line = bufr.readLine()) != null) { // 读的键盘
            bufOut.write(line);
            if ("over".equals(line)) // 写下面就会把over发过去
            {
                break;
            }
            String str = bufIn.readLine(); // 读的是服务器发过来的信息
            System.out.println("server:" + str);
        }
        bufr.close();
        s.close();
    }
}

/*
 * 客户端 源：socket读取流 目的：socket输出流 都是文本，装饰一下。
 */
class TransServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(10005);
        Socket s = ss.accept();
        String ip = s.getInetAddress().getHostAddress();
        System.out.println("客户端Ip" + ip);// 可以检验连没连上
        // OutputStream os=s.getOutputStream();
        // InputStream is =s.getInputStream();
        // 要一行行的读才方便
        // 读取socket读取流中的数据
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
        // 目的。socket输出流，将大写数据写入到socket输出流，并发送给客户端
        BufferedWriter bufOut = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        String line = null;
        while ((line = bufIn.readLine()) != null) {
            bufOut.write(line.toUpperCase());
        }
        s.close();
        ss.close();
    }
}

/*
 * 该例子出现的问题. 现象：客户端和服务端都在莫名的等等。 为什么呢？ 因为客户端和服务端都有阻塞式方法，这些方法没有读到结束标记。那么就一直等
 * 而导致两端，都在等待
 */
public class TcpDemo2 {
}
