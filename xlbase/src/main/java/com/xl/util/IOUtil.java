package com.xl.util;

import java.io.*;

public class IOUtil {
    /**
     * 得到流中的信息
     *
     * @param is
     * @return String
     */
    public static String getContent(InputStream is) throws IOException {
        return new String(getBytes(is));
    }
    
    /**
     * 将输入流转化为数组
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len;
        while ((len = is.read(buf)) != -1) {
            bos.write(buf, 0, len); // 写到bos中去
        }
        is.close();
        bos.flush();
        bos.close();
        return bos.toByteArray();
    }
    
    /**
     * 复制流
     *
     * @param input
     * @return
     */
    private static ByteArrayOutputStream cloneInputStream(InputStream input) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return baos;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 转换字符编码说需要的char数组
     *
     * @param is
     * @return char[]
     * @throws IOException
     */
    public static char[] getChar(Reader is) throws IOException {
        char[] buf = new char[1024];
        CharArrayWriter caw = new CharArrayWriter();
        int len;
        while ((len = is.read(buf)) != -1) {
            caw.write(buf, 0, len);
        }
        is.close();
        caw.flush();
        caw.close();
        return caw.toCharArray();
    }
}
