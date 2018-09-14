package com.xl.file;

import com.xl.enumsupport.CharsetEnum;
import com.xl.util.FileUtil;
import com.xl.util.ResourceUtil;
import com.xl.util.StringUtil;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: 徐立
 * Date: 2017/10/16
 * Time: 13:48
 * To change this template use File | Settings | File Templates.
 */
public class FileTest {
    @Test
    public void spTest() {
        //文件的分隔符
        System.out.println(File.separator);
        System.out.println(File.separatorChar);
        System.out.println(File.pathSeparator);       //;
        System.out.println(File.pathSeparatorChar);   //;
    }

    @Test
    public void getDeskTest() {
        System.out.println(FileUtil.getDesktopPath());
    }

    @Test
    public void lowerleveTest() throws UnsupportedEncodingException {
        System.out.println(FileUtil.queryAll(ResourceUtil.getResourceFile("excel")));
    }

    @Test
    public void demoTest() throws UnsupportedEncodingException {
        File file = ResourceUtil.getResourceFile("");
        System.out.println(file);
        //这个文件夹下新建一个文件
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        File testFile = new File("C:\\Users\\Administrator\\Desktop\\1\\j界面原型.txt");
        //文件的最后修改时间
        cal.setTimeInMillis(testFile.lastModified());
        testFile.lastModified();
        System.out.println(sdf.format(cal.getTime()));
        System.out.println(testFile);
    }

    @Test
    public void encodeTest() throws IOException {
        List<File> files = FileUtil.queryAll("D:\\code\\bbs1\\src\\main\\java\\cms\\bean\\data");
        for (File file : files) {
            Charset fileEncode = FileUtil.getFileEncode(file);
            String content = FileUtil.getContent(file);
            FileUtil.write(file, content);
            System.out.println(content);
            if (fileEncode != null && StringUtil.equals(CharsetEnum.UTF8.getValue(), fileEncode.name())) {
            }
        }
    }
}
