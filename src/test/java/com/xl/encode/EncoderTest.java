package com.xl.encode;

import com.xl.enumsupport.CharsetEnum;
import com.xl.util.FileUtil;
import com.xl.util.ResourceUtil;
import com.xl.util.StringUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: 徐立
 * Date: 2017-08-14
 * Time: 12:38
 * To change this template use File | Settings | File Templates.
 */
public class EncoderTest {
    private InputStream gbkFile = ResourceUtil.getResourceInputStream("gbk.txt");
    private InputStream utfFile = ResourceUtil.getResourceInputStream("utf-8.txt");

    public EncoderTest() throws UnsupportedEncodingException {
    }


    /**
     * 将指定目录下其他格式文件为UTF-8文件
     *
     * @throws IOException
     */
    @Test
    public void encodeTest() throws IOException, InterruptedException {
        System.out.println(FileUtil.getCurrentPath(getClass()));
        List<File> fileList = FileUtil.queryAll(FileUtil.getCurrentClassPath(), "java");
        for (int i = 0; i < fileList.size(); i++) {
            File file = fileList.get(i);
            Charset encode = FileUtil.getFileEncode(file);
            if (encode != null && !CharsetEnum.UTF8.getValue().equals(encode)) {
                OutputStreamWriter w1 = null;
                try {
                    String content = FileUtil.getContent(file);
                    if (StringUtil.isEmpty(content)) {
                        System.out.println(file.getAbsolutePath() + "为空");
                    } else {
                        w1 = new OutputStreamWriter(new FileOutputStream(file), CharsetEnum.UTF8.getValue());
                        w1.write(content);
                        w1.flush();
                        System.out.println(file.getAbsolutePath() + "转换完成:" + content);
                    }
                } finally {
                    if (w1 != null) {
                        w1.close();
                    }
                }
            }
        }
    }

    /**
     * 将指定目录下其他格式文件为GBK文件
     *
     * @throws IOException
     */
    @Test
    public void encodeGBKTest() throws IOException, InterruptedException {
        System.out.println(FileUtil.getCurrentPath(getClass()));
        List<File> fileList = FileUtil.queryAll("D:\\code\\bbs1\\src\\main\\java\\cms\\bean\\data", "java");
        for (int i = 0; i < fileList.size(); i++) {
            File file = fileList.get(i);
            Charset encode = FileUtil.getFileEncode(file);
            if (encode != null) {
                OutputStreamWriter w1 = null;
                try {
                    String content = FileUtil.getContent(file);
                    if (StringUtil.isEmpty(content)) {
                        System.out.println(file.getAbsolutePath() + "为空");
                    } else {
                        w1 = new OutputStreamWriter(new FileOutputStream(file), "GBK");
                        w1.write(content);
                        w1.flush();
                        System.out.println(file.getAbsolutePath() + "转换完成:" + content);
                    }
                } finally {
                    if (w1 != null) {
                        w1.close();
                    }
                }
            }
        }
    }

    @Test
    public void demoTest() throws UnsupportedEncodingException {
        System.out.println(new String("你好".getBytes(CharsetEnum.GBK.getValue()), CharsetEnum.UTF8.getValue()));
    }
}
