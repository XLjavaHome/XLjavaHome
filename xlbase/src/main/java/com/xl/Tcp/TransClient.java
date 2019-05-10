package com.xl.Tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
public class TransClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("127.0.0.1", 10005);
        //最方便的就是用转换用的狂读狂写
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in)); //源，定义读取键盘输入的流对象
        //		BufferedWriter bufOut=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));//定义目的是输出流。发给服务端
        //定义一个socket度曲流，读取服务端返回的大写信息
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream())); //
        String line = null;
        while ((line = bufr.readLine()) != null) {  //读的键盘
            System.out.println(line); //查看服务器发过来的数据
            //			bufOut.write(line);
            //			//由于写入的时候只能读取回车符号之前的数据
            //			bufOut.newLine();
            //			bufOut.flush(); //写缓冲区里面去了
            if ("over".equals(line))  //写下面就会把over发过去
            {
                break;
            }
            out.println(line);
            String str = bufIn.readLine(); //读的是服务器发过来的信息
            System.out.println("server:" + str);
        }
        bufr.close();
        s.close();
    }
}

