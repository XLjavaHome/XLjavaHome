package com.xl.face;

import com.xl.util.FileUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * @author 徐立
 * @Decription 到底关闭了吗, close方法也抛异常
 * @date 2014-5-15
 */
public class IOTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String src = FileUtil.getCurrentPath(IOTest.class) + "\\1";
        System.out.println(src);
    }

    public static void copy(String src, String dest) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(src);
            out = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int n;
            while ((n = in.read(buf)) > 0) {
                out.write(buf, 0, n);
            }
        } finally {
            // 这是不好的做法
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    public static void copy2(String src, String dest) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(src);
            out = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int n;
            while ((n = in.read(buf)) > 0) {
                out.write(buf, 0, n);
            }
        } finally {
            // 建议
            if (in != null) {
                try {
                    in.close();
                } finally {
                    in = null;
                }
            }
            if (out != null) {
                try {
                    out.close();
                } finally {
                    out = null;
                }
            }
        }
    }
}
