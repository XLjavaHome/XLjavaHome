package com.xl;

import com.xl.util.FileUtil;
import lombok.extern.log4j.Log4j;

/**
 * Created with IntelliJ IDEA.  pom生成jar包使用
 * User: 徐立
 * Date: 2017-10-30
 * Time: 17:16
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class XLMain {
    public static void main(String[] args) {
        System.out.println(FileUtil.getCurrentClassPath());
        ClassLoaderTest.print(FileUtil.getJarFile());
        System.out.println("这是主函数");
        log.info("开始了");
    }
}
