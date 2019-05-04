package com.xl.Tcp;

import java.io.InputStream;
import java.net.Socket;

public class TcpClient2 {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("127.0.0.1", 10004);
        java.io.OutputStream out = s.getOutputStream();
        out.write("服务端，你好，我是客户端。".getBytes());
        //读
        InputStream in = s.getInputStream();
        //建立缓冲区
        byte[] buf = new byte[1024];
        int len = in.read(buf);
        System.out.println(new String(buf, 0, len));
        s.close();
    }
}
