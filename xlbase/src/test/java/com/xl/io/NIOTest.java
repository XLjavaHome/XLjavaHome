package com.xl.io;

import com.xl.util.FileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-22
 * @time 19:04
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class NIOTest {
    private static String pathname = "E:\\vmacoustic\\CentOS 6.vmdk";
    
    public static void main(String[] args) throws IOException {
        //启动NIO
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //监听8080端口
        serverSocketChannel.bind(new InetSocketAddress(8081));
        //有数据
        log.info("监听开始。。。");
        //通过监听选择操作系统底层
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            //等待一秒
            selector.select(1000);
            //查询出的底层
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                //我只需要处理新连接
                if (next.isAcceptable()) {
                    //调用后立即返回
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (next.isReadable()) {
                    //如果连接有数据
                    SocketChannel socketChannel = (SocketChannel) next.channel();
                    //针对这个连接，取消这个查询条件
                    next.cancel();
                    // TODO 2019/7/15 23:34 徐立 拿到该链接交由线程池处理
                    //new ThreadPoolExecutor()
                    {
                        //不像stream非阻塞io
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        socketChannel.read(byteBuffer);
                        byteBuffer.flip();
                        String s = new String(byteBuffer.array());
                        System.out.println(s);
                        //给一个返回值
                        String response = "http/1.1 200 ok\n" + "Content-Length: 11\n" + "Hello World";
                        socketChannel.write(ByteBuffer.wrap(response.getBytes()));
                        //告诉selector继续监听
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }
                }
            }
        }
        //创建一个容量为1024个byte
        // 的缓冲区
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
