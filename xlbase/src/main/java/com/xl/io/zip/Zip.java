package com.xl.io.zip;

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class Zip {
    private long length = 0;                // A uncompress directory's length

    public static void main(String[] args) {
        Zip zip = new Zip();
        zip.compress("D:/ziptest");
    }

    /**
     * Compress files to *.zip.
     *
     * @param fileName: file to be compress
     */
    public void compress(String fileName) {
        String targetFile = null;
        File sourceFile = new File(fileName);
        Vector<File> vector = getAllFiles(sourceFile);
        try {
            if (sourceFile.isDirectory()) {
                targetFile = fileName + ".zip";
            } else {
                char ch = '.';
                targetFile = fileName.substring(0, fileName.lastIndexOf((int) ch)) + ".zip";
            }
            BufferedInputStream bis = null;
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(targetFile));
            ZipOutputStream zipos = new ZipOutputStream(bos);
            byte[] data = new byte[1024];
            for (int i = 0; i < vector.size(); i++) {
                File file = vector.get(i);
                zipos.putNextEntry(new ZipEntry(getEntryName(fileName, file)));
                bis = new BufferedInputStream(new FileInputStream(file));
                int count;
                while ((count = bis.read(data, 0, 1024)) != -1) {
                    zipos.write(data, 0, count);
                }
                bis.close();
                zipos.closeEntry();
            }
            zipos.close();
            bos.close();
            showDetail(sourceFile, new File(targetFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Uncompress *.zip files.
     *
     * @param fileName: file to be uncompress
     */
    public void uncompress(String fileName) {
        File sourceFile = new File(fileName);
        String filePath = sourceFile.getParent() + "/";
        try {
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            ZipFile zipFile = new ZipFile(fileName);
            Enumeration en = zipFile.entries();
            byte[] data = new byte[1024];
            while (en.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) en.nextElement();
                if (entry.isDirectory()) {
                    new File(filePath + entry.getName()).mkdirs();
                    continue;
                }
                bis = new BufferedInputStream(zipFile.getInputStream(entry));
                File file = new File(filePath + entry.getName());
                File parent = file.getParentFile();
                if (parent != null && (!parent.exists())) {
                    parent.mkdirs();
                }
                bos = new BufferedOutputStream(new FileOutputStream(file));
                int count;
                while ((count = bis.read(data, 0, 1024)) != -1) {
                    bos.write(data, 0, count);
                }
                bis.close();
                bos.close();
            }
            zipFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * To get a directory's all files.
     *
     * @param sourceFile: the source directory
     * @return the files' collection
     */
    private Vector<File> getAllFiles(File sourceFile) {
        Vector<File> fileVector = new Vector<File>();
        if (sourceFile.isDirectory()) {
            File[] files = sourceFile.listFiles();
            for (int i = 0; i < files.length; i++) {
                fileVector.addAll(getAllFiles(files[i]));
            }
        } else {
            fileVector.add(sourceFile);
        }
        return fileVector;
    }

    private String getEntryName(String base, File file) {
        File baseFile = new File(base);
        String filename = file.getPath();
        if (baseFile.getParentFile().getParentFile() == null) {
            return filename.substring(baseFile.getParent().length());
        }
        return filename.substring(baseFile.getParent().length() + 1);
    }

    /**
     * Show the compress or uncompress detail.
     *
     * @param sourceFile: the source file
     * @param targetFile: the target file
     */
    private void showDetail(File sourceFile, File targetFile) {
        long sourceFileLength = getDirectoryLength(sourceFile);
        long targetFileLength = targetFile.length();
        System.out.println("The uncompress file's size is " + sourceFileLength + " bytes.");
        System.out.println("The compress file's size is " + targetFileLength + " bytes.");
        System.out.println("The compress rate is " + ((double) (sourceFileLength - targetFileLength) / (double) sourceFileLength) * 100 + "%");
    }

    /**
     * Get length of a directory.
     *
     * @param path: the directory
     * @return the length of directory
     */
    private long getDirectoryLength(File path) {
        if (path.isDirectory()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                getDirectoryLength(files[i]);
            }
        } else {
            length += path.length();
        }
        return length;
    }
}
