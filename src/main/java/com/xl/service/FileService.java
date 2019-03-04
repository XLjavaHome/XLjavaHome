package com.xl.service;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-05-17
 * @Time: 15:26
 * To change this template use File | Settings | File Templates.
 */
public interface FileService {
    /**
     * 复制文件
     *
     * @param file
     * @param target
     * @return
     */
    boolean copy(File file, File target);
}
