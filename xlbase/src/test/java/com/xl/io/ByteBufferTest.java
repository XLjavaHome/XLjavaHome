package com.xl.io;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-08-16
 * @time 13:29
 * To change this template use File | Settings | File Templates.
 */
public class ByteBufferTest {
    @Test
    void name() {
        System.out.println("----------Test allocate--------");
        System.out.println("before alocate:" + Runtime.getRuntime().freeMemory());
        // 如果分配的内存过小，调用Runtime.getRuntime().freeMemory()大小不会变化？
        // 要超过多少内存大小JVM才能感觉到？
        ByteBuffer buffer = ByteBuffer.allocate(102400);
        System.out.println("buffer = " + buffer);
        System.out.println("after alocate:" + Runtime.getRuntime().freeMemory());
        // 这部分直接用的系统内存，所以对JVM的内存没有影响
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(102400);
        System.out.println("directBuffer = " + directBuffer);
        System.out.println("after direct alocate:" + Runtime.getRuntime().freeMemory());
        System.out.println("----------Test wrap--------");
        byte[] bytes = new byte[32];
        buffer = ByteBuffer.wrap(bytes);
        System.out.println(buffer);
        buffer = ByteBuffer.wrap(bytes, 10, 10);
        System.out.println(buffer);
    }
}
