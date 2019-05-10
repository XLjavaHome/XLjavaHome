package com.xl.service;

import com.xl.util.FileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import lombok.extern.log4j.Log4j;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @Date: 2019-02-27
 * @Time: 20:20
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class FileServiceImpl implements FileService {
    @Override
    public boolean copy(File file, File target) {
        if (file.getAbsolutePath().equals(target.getAbsolutePath())) {
            log.info("文件已经存在");
            return false;
        }
        try {
            FileUtil.copyFileStream(new FileInputStream(file), target);
            return true;
        } catch (IOException e) {
            log.error(String.format("复制文件失败：从%s复制到%s", file.getAbsolutePath(), target.getAbsolutePath()), e);
            return false;
        }
    }
}
