package com.xl.util;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.commons.io.IOUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 徐立
 * @Date: 2018-01-16
 * @Time: 13:01
 * To change this template use File | Settings | File Templates.
 */
public class ZipUtils {
    /**
     * 解压缩
     *
     * @param file      源文件 以.zip结尾
     * @param outputDir 解压的目录
     * @throws IOException
     */
    public static void uncompression(File file, String outputDir) throws IOException {
        ZipFile zipFile = new ZipFile(file);
        try {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                File entryDestination = new File(outputDir, entry.getName());
                if (entry.isDirectory()) {
                    entryDestination.mkdirs();
                } else {
                    entryDestination.getParentFile().mkdirs();
                    InputStream in = zipFile.getInputStream(entry);
                    OutputStream out = new FileOutputStream(entryDestination);
                    try {
                        IOUtils.copy(in, out);
                    } finally {
                        IOUtils.closeQuietly(in);
                        IOUtils.closeQuietly(out);
                    }
                }
            }
        } finally {
            zipFile.close();
        }
    }
}
