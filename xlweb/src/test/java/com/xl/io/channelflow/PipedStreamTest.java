package com.xl.io.channelflow;

import org.junit.Test;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedStreamTest {
    @Test
    public void demoTest() throws IOException {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream();
        in.connect(out); // 连接
        Read r = new Read(in);
        Write w = new Write(out);
        new Thread(r).start();
        new Thread(w).start();
    }
}
