package com.xl.io;

import com.xl.util.FileUtil;
import java.io.*;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-02
 * @time 16:38
 * To change this template use File | Settings | File Templates.
 */
public class IOTest {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "项目操作日志优化20191023（修2).docx";
        InputStream input = new FileInputStream(FileUtil.getDesktopFile(fileName));
        //InputStream input =  httpconn.getInputStream(); //这里可以写你获取到的流
        ByteArrayOutputStream baos = cloneInputStream(input);
        // 打开两个新的输入流
        InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());
        InputStream stream2 = new ByteArrayInputStream(baos.toByteArray());
    }
    
    private static ByteArrayOutputStream cloneInputStream(InputStream input) {
        //是否关闭都不影响 用ByteArrayInputStream、ByteArrayOutputStream实现流缓存，只适用不大的流
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
}
