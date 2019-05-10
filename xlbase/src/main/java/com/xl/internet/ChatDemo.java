package com.xl.internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/*

 有收数据的部分，和发数据的部分。
 这两部分需要同时执行。
 那就需要多线程技术。
 一个线程负责收，一个线程负责发。
因为收和发动作是不一致的。所以要定义两个run方法。
而且这两个方法要封装到两个类中
 */
public class ChatDemo {
    public static void main(String[] args) {
        try {
            DatagramSocket sendSocket = new DatagramSocket();
            DatagramSocket receSocket = new DatagramSocket(10002);
            new Thread(new Send(sendSocket)).start();
            new Thread(new Rece(receSocket)).start();
        } catch (SocketException e) {
            //自动生成的 catch 块
            throw new RuntimeException("出错啦");
        }
    }
}

class Send implements Runnable {
    private DatagramSocket ds;

    Send(DatagramSocket ds) {
        this.ds = ds;
    }

    @Override
    public void run() {  //发送要try
        try {
            BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));//用到转换流
            String line = null;
            while ((line = bufr.readLine()) != null) {
                if ("886".equals(line)) {
                    break;
                }
                byte[] buf = line.getBytes();
                DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.1.255"), 10002);//给所有的发广播
                ds.send(dp);
            }
        } catch (Exception e) {
            throw new RuntimeException("发送失败");
        }
    }
}

class Rece implements Runnable {
    private DatagramSocket ds;

    Rece(DatagramSocket ds) {
        this.ds = ds;
    }

    @Override
    public void run() {
        try {
            while (true) {
                byte[] buf = new byte[1024];
                DatagramPacket dp = new DatagramPacket(buf, buf.length);
                ds.receive(dp);
                //接数据之前看看ip
                String ip = dp.getAddress().getHostAddress();
                String data = new String(dp.getData(), 0, dp.getLength());
                System.out.println(ip + ":" + data);
            }
        } catch (IOException e) {
            throw new RuntimeException("接受失败");
        }
    }
}
