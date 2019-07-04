package com.xl.io;

import com.xl.util.FileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import org.junit.jupiter.api.Test;

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
    
    @Test
    void name() {
        //创建一个容量为1024个byte的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        /*写入数据到Buffer
调用flip()方法将Buffer从写模式切换到读模式
从Buffer中读取数据
调用clear()方法或者compact()方法清空缓冲区，让它可以再次被写入*/
    }
    
    public void readIO() throws Exception {
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
    
    @Test
    void nio() throws Exception {
        readNIO();
    }
    
    public void readNIO() throws Exception {
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
}
