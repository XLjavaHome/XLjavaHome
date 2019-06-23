package com.xl.io;

import com.xl.util.FileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-22
 * @time 19:04
 * To change this template use File | Settings | File Templates.
 */
public class NIOTest {
    private static String pathname = "E:\\vmacoustic\\CentOS 6.vmdk";
    
    public static void main(String[] args) throws Exception {
        long nioStart = System.currentTimeMillis();
        readNIO();
        System.out.println("nio-time: " + ((System.currentTimeMillis() - nioStart)));
        long ioStart = System.currentTimeMillis();
        readIO();
        System.out.println("io-time: " + ((System.currentTimeMillis() - ioStart)));
    }
    
    public static void readNIO() throws Exception {
        File readFile = new File(pathname);
        FileInputStream fis = new FileInputStream(readFile);
        FileOutputStream fos = new FileOutputStream(FileUtil.createTempFile("NIO.map4"));
        FileChannel channel = fis.getChannel();
        FileChannel outchannel = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (channel.read(buffer) != -1) {
            buffer.flip();
            outchannel.write(buffer);
            buffer.clear();
        }
        channel.close();
        fis.close();
        fos.close();
    }
    
    public static void readIO() throws Exception {
        File file = new File(pathname);
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(FileUtil.createTempFile("BIO.map4"));
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = fis.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        fis.close();
        fos.close();
    }
}
