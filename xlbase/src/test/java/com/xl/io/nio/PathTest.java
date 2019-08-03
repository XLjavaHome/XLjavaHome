package com.xl.io.nio;

import com.xl.util.FileUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.IntStream;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.path.normalize()：规范路径
 *
 * @author 徐立
 * @date 2019-08-02
 * @time 23:43
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class PathTest {
    private Path path = Paths.get(".", "1.txt");
    
    @Test
    void readFile() throws IOException {
        List<String> lines = Files.readAllLines(path);
        lines.forEach(System.out::println);
    }
    
    /**
     * 数字会乱码
     *
     * @throws IOException
     */
    @Test
    void writeFile2() throws IOException {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            bufferedWriter.write("张三");
            bufferedWriter.write("李四");
            bufferedWriter.write("王五");
            bufferedWriter.write(12321);
            IntStream.range(0, 10).forEach(s -> {
                try {
                    bufferedWriter.write(s);
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println("已写入完成.path = " + path.getRoot());
        FileUtil.openParent(path.toFile());
    }
    
    @Test
    void copyTest() throws IOException {
        //默认不会覆盖同名文件
        Path old = Paths.get(".", "1.txt");
        FileUtil.openParent(old.toFile());
        Path copy = Paths.get(".", "2.txt");
        //Files.copy(old,copy);
        //传入第三个参数,使其可以覆盖同名文件
        Files.copy(old, copy, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("拷贝完成");
    }
    
    @Test
    void writeFile() throws IOException {
        String destFile = "测试.txt";
        Path path = Paths.get(this.getClass().getResource("/").getPath().substring(1) + destFile);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        //可以选择传入第三个OpenOptions参数,不传入默认CREATE和TRUNCATE_NEW,即如果文件不存在,创建该文件,如果存在,覆盖
        //      Files.write(path, lines);
        // 如果不传第三个参数,则文件不存在的话默认会选择创建
        Files.write(path, "测试".getBytes());
        Files.write(path, "李四".getBytes());
        FileUtil.open(path.toFile());
    }
    
    @Test
    void name() {
        //在传统java.io中， 文件和目录都被抽象成File对象， 即 File file = new File(".");
        //NIO.2中则引入接口Path代表与平台无关的路径，文件和目录都用Path对象表示
        //通过路径工具类Paths返回一个路径对象Path
        Path path = Paths.get(".");
        log.info("path里包含的路径数量：" + path.getNameCount());
        log.info("path的根路径： " + path.getRoot());
        //path的绝对路径
        //对比传统java.io, 取绝对路径 file.getAbsoluteFile()
        Path absolutePath = path.toAbsolutePath();
        log.info("path的绝对路径：");
        log.info(absolutePath);
        log.info("absolutePath的根路径： " + absolutePath.getRoot());
        log.info("absolutePath里包含的路径数量：" + absolutePath.getNameCount());
        log.info(absolutePath.getName(3));
        //以多个String来构建path
        Path path2 = Paths.get("g:", "publish", "codes");
        log.info(path2);
    }
    
    @Test
    void demo2() {
        Path path = Paths.get("C:\\home\\joe\\foo");    // Microsoft Windows syntax
        print(path);
        Path path2 = Paths.get("/home/joe/foo");
        print(path2);
    }
    
    private void print(Path path) {
        String x = "path.toString()--" + path.toString();
        log.info(x);
        log.info("path.getName(1)--" + path.getName(1));
        log.info(path.getName(0));
        log.info(path.getNameCount());
        log.info(path.subpath(0, 2));
        Path parent = path.getParent();
        log.info(parent);
        log.info(path.getRoot());
    }
    
    /**
     * 获取path有三种方法
     */
    @Test
    void getPathTest() {
        //1.通过FileSystems.getDefault()
        Path ex = FileSystems.getDefault().getPath("c:/", "ex", "access.log");
        System.out.println(ex.getFileName());
        Path path = FileSystems.getDefault().getPath("c:/ex", "access.log");
        //两种方法是相等的
        System.out.println(ex.equals(path));
        //2.获得path方法二，用File的toPath()方法获得Path对象,内部FileSystems.getDefault()
        File file = new File("e:/ex/access.log");
        Path pathOther = file.toPath();
        //3.获得path方法三,内部也是FileSystems.getDefault
        Path path3 = Paths.get("c:/ex", "access.log");
        System.out.println(path3.toString());
    }
    
    @Test
    void join() {
        //join two paths
        Path path4 = Paths.get("c:/ex");
        System.out.println("path4: " + path4.resolve("access.log"));
    }
}
