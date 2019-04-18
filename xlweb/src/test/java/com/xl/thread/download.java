package com.xl.thread;

import com.xl.util.FileUtil;
import com.xl.util.StreamTool;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author 徐立
 * @Decription
 * @date 2014-2-24
 */
public class download {
    /**
     * 下载路径
     */
    public static final String PATH = "http://www.hao123.com";
    private static Object obj = new download();

    public static void main(String[] args) throws Exception {
        /** 下载文件保存路径 */
        File file;
        URL url = new URL(PATH);
        file = new File(FileUtil.getCurrentPath(obj), "1.html");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
        int code = conn.getResponseCode();
        if (code == 200) {
            int len = conn.getContentLength();
            // 1.设置本地文件大小跟服务器的文件大小一致
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.setLength(len);
            // 2 .假设开启3 个线程
            int threadnumber = 3;
            int blocksize = len / threadnumber;
            /**
             * 线程1 0~ blocksize 线程2 1*bolocksize ~ 2*blocksize 线程3 2*blocksize ~
             * 文件末尾
             */
            for (int i = 0; i < threadnumber; i++) {
                int startposition = i * blocksize;
                int endpositon = (i + 1) * blocksize;
                if (i == (threadnumber - 1)) {
                    // 最后一个线程
                    endpositon = len;
                }
                DownLoadTask task = new DownLoadTask(i, PATH, file.getAbsolutePath(), startposition, endpositon);
                task.start();
            }
        }
    }

    public static String getFilenName(String path) {
        int start = path.lastIndexOf("/") + 1;
        return path.substring(start, path.length());
    }
}

/**
 * @author 徐立
 * @Decription 断点续传
 * @date 2014-2-24
 */
class DownLoadTask extends Thread {
    int startposition;
    int endpositon;
    /**
     * 线程id
     */
    private int threadid;
    /**
     * url下载路径
     */
    private String urlPath;
    /**
     * 下载的文件的路径
     */
    private String filePath;

    public DownLoadTask(int threadid, String urlPath, String filePath, int startposition, int endpositon) {
        super();
        this.threadid = threadid;
        this.urlPath = urlPath;
        this.filePath = filePath;
        this.startposition = startposition;
        this.endpositon = endpositon;
    }

    @Override
    public void run() {
        try {
            // 实现断点续传中间File以线程id命名，记录文件位置
            File postionfile = new File(FileUtil.getCurrentPath(this), +threadid + ".txt");
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            System.out.println("线程" + threadid + "正在下载 " + "开始位置 : " + startposition + "结束位置 " + endpositon);
            if (postionfile.exists()) {
                FileInputStream fis = new FileInputStream(postionfile);
                byte[] result = StreamTool.getBytes(fis);
                int newstartposition = Integer.parseInt(new String(result));
                if (newstartposition > startposition) {
                    startposition = newstartposition;
                }
            }
            // "Range", "bytes=2097152-4194303")
            conn.setRequestProperty("Range", "bytes=" + startposition + "-" + endpositon);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            InputStream is = conn.getInputStream();
            // 设置 数据从文件哪个位置开始写
            RandomAccessFile file = new RandomAccessFile(filePath, "rwd");
            file.seek(startposition);
            byte[] buffer = new byte[1024];
            int len = 0;
            // 代表当前读到的服务器数据的位置 ,同时这个值已经存储的文件的位置
            int currentPostion = startposition;
            // 创建一个文件对象 ,记录当前某个文件的下载位置
            while ((len = is.read(buffer)) != -1) {
                file.write(buffer, 0, len);
                currentPostion += len;
                // 需要把currentPostion 信息给持久化到存储设备
                String position = currentPostion + "";
                // 如果放在外面出现write error
                FileOutputStream fos = new FileOutputStream(postionfile);
                fos.write(position.getBytes());
                fos.flush();
                fos.close();
            }
            file.close();
            System.out.println("线程" + threadid + "下载完毕");
            // 当线程下载完毕后 把文件删除掉
            if (postionfile.exists()) {
                postionfile.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.run();
    }
}
