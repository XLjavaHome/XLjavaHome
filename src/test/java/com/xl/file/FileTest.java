package com.xl.file;

import com.xl.enumsupport.CharsetEnum;
import com.xl.service.FileService;
import com.xl.service.FileServiceImpl;
import com.xl.util.FileUtil;
import com.xl.util.StringUtil;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private File file = FileUtil.createRandomFile();
    private String str = "中文测试asdb";
    
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
            StringBuilder content = FileUtil.getContent(file);
            FileUtil.write(file, content.toString());
            System.out.println(content);
            if (fileEncode != null && StringUtil.equals(CharsetEnum.UTF8.getValue(), fileEncode.name())) {
            }
        }
    }
    
    @Test
    public void BufferedWriter() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(str);
        writer.close();
        open();
    }
    
    @Test
    public void appendFileWithBufferedWriter() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(str);
        writer.close();
        open();
    }
    
    @Test
    public void FileOutputStream() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        // 需要将String转换为bytes
        byte[] strToBytes = str.getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
        open();
    }
    
    /**
     * 拷贝
     */
    @Test
    public void copyTest() {
        //遍历目录
        String filePath = "E:\\download\\九：【BATJ面试班】(1)";
        filePath = "E:\\download\\Document-master\\document\\一，互联网工程专题";
        List<File> files = FileUtil.queryAll(filePath);
        //FileUtil.print(files);
        //    将文件复制
        FileService service = new FileServiceImpl();
        int num = 0;
        File parentFile = new File(filePath, "copy");
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        for (File file : files) {
            File target = new File(parentFile, file.getName());
            if (service.copy(file, target)) {
                num++;
                System.out.println(String.format("成功复制:%s", file.getAbsolutePath()));
            } else {
                System.out.println(String.format("复制失败:%s", file.getAbsolutePath()));
            }
        }
        System.out.println(String.format("成功复制了%s个文件", num));
    }
    
    /**
     * 处理大文件的时候，FileChannel会比标准的io更快。
     */
    @Test
    public void fileChannel() throws IOException {
        RandomAccessFile stream = new RandomAccessFile(file, "rw");
        FileChannel channel = stream.getChannel();
        byte[] strBytes = str.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
        buffer.put(strBytes);
        buffer.flip();
        channel.write(buffer);
        stream.close();
        channel.close();
        open();
    }
    
    /**
     * Files是Java7引入的工具类，通过它，我们可以创建，移动，删除，复制文件。目录也是一种特殊的文件，对目录也适用。当然也可以用于读写文件
     */
    @Test
    public void Files() throws IOException {
        String str = "Hello";
        Path path = Paths.get(file.getAbsolutePath());
        byte[] strToBytes = str.getBytes();
        Files.write(path, strToBytes);
        String read = Files.readAllLines(path).get(0);
        System.out.println(str.equals(read));
        open();
    }
    
    public void open() throws IOException {
        FileUtil.open(file);
    }
    
    /**
     * 无法得到文件的路径
     */
    @Test
    public void fdTest() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        FileDescriptor fd = fileOutputStream.getFD();
        System.out.println(fd);
        System.out.println(fd.valid());
        FileChannel channel = fileOutputStream.getChannel();
    }
}
