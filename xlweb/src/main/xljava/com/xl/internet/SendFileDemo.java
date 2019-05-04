package com.xl.internet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class TextClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 10006);
        BufferedReader bufr = new BufferedReader(new FileReader("ip.java"));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        String line = null;
        while ((line = bufr.readLine()) != null) // 因为源是文件，所以可以结束
        {
            out.println(line);
        }
        // 这样并不能结束
        out.println("over");
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str = bufIn.readLine();
        System.out.println(str);
        bufr.close();
        s.close();
    }
}

class TextServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10006);
        Socket s = ss.accept();
        String ip = s.getInetAddress().getHostAddress();
        System.out.println(ip);
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
        // 真是的应该把名字取一样。如果有同名判断一下
        PrintWriter out = new PrintWriter(new FileWriter("C:\\Documents and Settings\\All Users\\桌面\\server.txt"), true);
        String line = null;
        while ((line = bufIn.readLine()) != null) {
            if ("over".equals(line)) {
                break;
            }
            out.println(line);
        }
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
        pw.println("上传成功！");
        out.close();
        s.close();
        ss.close();
    }
}

public class SendFileDemo {
}
