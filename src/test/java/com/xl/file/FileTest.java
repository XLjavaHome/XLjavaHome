package com.xl.file;

import com.xl.enumsupport.CharsetEnum;
import com.xl.service.FileService;
import com.xl.service.FileServiceImpl;
import com.xl.util.FileUtil;
import com.xl.util.StringUtil;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
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
    
    /**
     * 拷贝
     */
    @Test
    public void copyTest() {
        //遍历目录
        String filePath = "E:\\download\\Document-master";
        List<File> files = FileUtil.queryAll(filePath);
        //FileUtil.print(files);
        //    将文件复制
        FileService service = new FileServiceImpl();
        int num = 0;
        for (File file : files) {
            File target = new File(filePath, file.getName());
            if (service.copy(file, target)) {
                num++;
                System.out.println(String.format("成功复制:%s", file.getAbsolutePath()));
            } else {
                System.out.println(String.format("复制失败:%s", file.getAbsolutePath()));
            }
        }
        System.out.println(String.format("成功复制了%s个文件", num));
    }
}
