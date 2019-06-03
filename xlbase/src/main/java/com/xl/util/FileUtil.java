package com.xl.util;

import com.xl.base.IdWorker;
import java.awt.Desktop;
import java.io.*;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.filechooser.FileSystemView;
import lombok.extern.log4j.Log4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.misc.Nullable;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.FileFileFilter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 徐立
 * @Date: 2018-05-16
 * @Time: 17:53
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class FileUtil {
    /**
     * 利用第三方开源包cpdetector获取文件编码格式
     *
     * @param file
     * @return
     */
    @Deprecated
    public static Charset getFileEncode(File file) {
        file.getAbsolutePath();
        /* *//*
         * detector是探测器，它把探测任务交给具体的探测实现类的实例完成。
         * cpDetector内置了一些常用的探测实现类，这些探测实现类的实例可以通过add方法 加进来，如ParsingDetector、
         * JChardetFacade、ASCIIDetector、UnicodeDetector。
         * detector按照“谁最先返回非空的探测结果，就以该结果为准”的原则返回探测到的
         * 字符集编码。使用需要用到三个第三方JAR包：antlr.jar、chardet.jar和cpdetector.jar
         * cpDetector是基于统计学原理的，不保证完全正确。
         *//*
         CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
         *//*
         * ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于
         * 指示是否显示探测过程的详细信息，为false不显示。
         *//*
         detector.add(new ParsingDetector(false));
         *//*
         * JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码
         * 测定。所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以
         * 再多加几个探测器，比如下面的ASCIIDetector、UnicodeDetector等。
         *//*
         detector.add(JChardetFacade.getInstance());// 用到antlr.jar、chardet.jar
         // ASCIIDetector用于ASCII编码测定
         detector.add(ASCIIDetector.getInstance());
         // UnicodeDetector用于Unicode家族编码的测定
         detector.add(UnicodeDetector.getInstance());
         File f = new File(path);
         try {
             return detector.detectCodepage(f.toURI().toURL());
         } catch (Exception ex) {
             log.error(ex);
         }*/
        return null;
    }
    
    /**
     * 复制文件
     *
     * @param src
     * @param target void
     * @throws IOException
     */
    public static void copyFile(File src, File target) throws IOException {
        copyFileStream(new FileInputStream(src), target);
    }
    
    /**
     * 将流复制到文件
     *
     * @param src
     * @param target
     */
    public static void copyFileStream(InputStream src, File target) throws IOException {
        IOUtils.copy(src, new FileOutputStream(target));
    }
    
    /**
     * 对文件大小进行格式化
     *
     * @param fileS
     * @return String
     */
    public static String FormetFileSize(long fileS) {// 转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }
    
    /**
     * 返回该文件大小,leng就可以获得大小了
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws IOException int
     * @see File#length
     */
    @Deprecated
    public static int getSize(File file) throws IOException {
        return new FileInputStream(file).available();
    }
    
    /**
     * 打印集合
     *
     * @param con void
     */
    public static <T> void print(Collection<T> con) {
        Iterator<T> i = con.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
    
    /**
     * 遍历该目录下所有文件
     *
     * @param file
     * @return List<File>
     */
    public static List<File> queryAll(File file, List<File> list) {
        if (file != null) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (int i = 0; i < files.length; i++) {
                        if (files[i] != null) {
                            queryAll(files[i], list);
                        }
                    }
                }
            } else if (file.isFile()) {
                list.add(file);
            }
        }
        return list;
    }
    
    /**
     * 遍历该目录下所有文件
     *
     * @param file
     * @return List<File>
     */
    public static List<File> queryAll(File file) {
        return queryAll(file.getAbsolutePath());
    }
    
    /**
     * 遍历该目录下所有文件
     *
     * @param filePath
     * @return List<File>
     */
    public static List<File> queryAll(String filePath) {
        File file = new File(filePath);
        List<File> list = new ArrayList<File>();
        return queryAll(file, list);
    }
    
    /**
     * 遍历该目录下所有文件
     *
     * @param filePath
     * @return List<File>
     */
    public static List<File> queryAll(String filePath, String endWith) {
        File file = new File(filePath);
        List<File> list = new ArrayList<File>();
        return queryAll(file, list, endWith);
    }
    
    public static List<File> queryAll(File file, List<File> list, String end) {
        if (file != null) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (files[i] != null) {
                        queryAll(files[i], list, end);
                    }
                }
            } else if (file.isFile()) {
                if (file.getName().endsWith(end)) {
                    list.add(file);
                }
            }
        }
        return list;
    }
    
    /**
     * 往目标写入
     *
     * @param file
     * @param is void
     * @throws IOException
     */
    public static void write(File file, InputStream is) throws IOException {
        BufferedOutputStream bos = null;
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            bos = new BufferedOutputStream(new FileOutputStream(file));
            byte[] buf = StreamTool.getBytes(is);
            bos.write(buf);
            bos.flush();
        } finally {
            bos.close();
            is.close();
        }
    }
    
    public static void write(String filePath, String content) throws IOException {
        write(new File(filePath), content);
    }
    
    /**
     * 向指定文件输入内容
     *
     * @param file
     * @param content
     * @throws IOException
     */
    public static void write(File file, String content) throws IOException {
        BufferedWriter bw = null;
        try {
            if (content == null) {
                return;
            }
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            bw = new BufferedWriter(new FileWriter(file));
            //2019/4/14 解决换行的问题
            String[] split = content.split("\n");
            for (String s : split) {
                bw.write(s);
                bw.newLine();
            }
            bw.flush();
        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }
    
    /**
     * 获取文件内容
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static StringBuilder getContent(File file) throws IOException {
        return getContent(new FileReader(file));
    }
    
    public static String getSize(Long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }
    
    /**
     * 获取桌面路径
     *
     * @return
     */
    public static String getDesktopPath() {
        return getDesktop().getPath();
    }
    
    /**
     * 获取桌面目录
     *
     * @return
     */
    public static File getDesktop() {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        return fsv.getHomeDirectory();
    }
    
    /**
     * 获取临时目录
     *
     * @return
     */
    public static File getTempDrectory() {
        File temp = new File(getDesktop(), "temp");
        if (!temp.exists()) {
            temp.mkdirs();
        }
        return temp;
    }
    
    /**
     * 获取桌面文件
     *
     * @param name 文件名
     * @return
     */
    public static File getDesktopFile(String name) {
        return getTempDrectory(FileSystemView.getFileSystemView().getHomeDirectory(), name);
    }
    
    /**
     * 打开文件
     *
     * @param file
     * @throws IOException
     */
    public static void open(File file) throws IOException {
        Desktop.getDesktop().open(file);
    }
    
    /**
     * 打开文件的所在目录
     *
     * @param file
     * @throws IOException
     */
    public static void openDirectory(File file) throws IOException {
        open(file.getParentFile());
    }
    
    /**
     * 不包含目录下的文件
     *
     * @param filePath
     * @return
     */
    public static List<File> queryAllNotDirectoryLowFile(String filePath) {
        List<File> files = queryAll(filePath);
        File directoryFile = new File(filePath);
        File[] files1 = directoryFile.listFiles((FileFilter) FileFileFilter.FILE);
        files.removeAll(Arrays.asList(files1));
        return files;
    }
    
    public static <T> String getCurrentPath(T obj) {
        return getCurrentPath(obj.getClass());
    }
    
    /**
     * 获取当前类的路径<br/>
     * 思路：工程路径+src+类名
     *
     * @param clazz 得到类的全名
     * @return
     * @throws UnsupportedEncodingException
     */
    public static <T> String getCurrentPath(Class<T> clazz) {
        String projectPath = getCurrentClassPath() + File.separator + "src";
        String name = clazz.getName();
        name = name.substring(0, name.lastIndexOf(".")).replace(".", File.separator);
        return projectPath + File.separator + name;
    }
    
    public static String getProjectPath() {
        return Thread.currentThread().getContextClassLoader().getResource("").getPath();
    }
    
    /**
     * 得到当前工程的绝对路径
     *
     * @return String
     */
    public static String getCurrentClassPath() {
        return FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    }
    
    /**
     * 获取jar的所在目录
     *
     * @return
     */
    public static File getJarFile() {
        File f = new File(getCurrentClassPath());
        return f.getParentFile();
    }
    
    /**
     * 创建临时文件
     *
     * @param filePath
     * @return
     */
    public static File createTempFile(String filePath) {
        File file = getTempDrectory(getTempDrectory(), filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                log.error("创建文件失败");
            }
        }
        return file;
    }
    
    @NotNull
    public static File getTempDrectory(File desktop, String s) {
        return new File(desktop, s);
    }
    
    /**
     * 获取文件内容
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static StringBuilder getContent(String filePath) throws IOException {
        return getContent(new FileReader(filePath));
    }
    
    @Nullable
    public static StringBuilder getContent(FileReader fileReader) throws IOException {
        BufferedReader br = new BufferedReader(fileReader);
        StringBuilder sb = null;
        try {
            sb = new StringBuilder();
            String content = null;
            while ((content = br.readLine()) != null) {
                sb.append(content).append("\n");
            }
            return sb;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }
    
    /**
     * 建立临时文件
     *
     * @return
     */
    public static File createRandomFile() {
        IdWorker worker = new IdWorker();
        long l = worker.nextId();
        return createTempFile(l + ".txt");
    }
    
    /**
     * 在临时目录下面再创建一个目录
     *
     * @param directoryName 目录名称
     * @return
     */
    @NotNull
    public static File createTempDirectoy(String directoryName) {
        File publishPackageNameDirectory = new File(getTempDrectory(), directoryName);
        if (!publishPackageNameDirectory.exists()) {
            publishPackageNameDirectory.mkdirs();
        }
        return publishPackageNameDirectory;
    }
}
