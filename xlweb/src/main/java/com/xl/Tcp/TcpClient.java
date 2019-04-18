package com.xl.Tcp;

import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) throws Exception {
        // 创建客户端的socket服务。指定目的的主机和端口
        Socket s = new Socket("192.168.1.254", 10003);
        OutputStream out = s.getOutputStream();
        out.write("top ge men lai le".getBytes());
        s.close();
    }
}
