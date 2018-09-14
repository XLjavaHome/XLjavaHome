package com.xl;

import com.xl.util.FileUtil;

/**
 * Created with IntelliJ IDEA.  pom生成jar包使用
 * User: 徐立
 * Date: 2017-10-30
 * Time: 17:16
 * To change this template use File | Settings | File Templates.
 */
public class XLMain {
    public static void main(String[] args) {
        System.out.println(FileUtil.getProjectPath());
        System.out.println(FileUtil.getProjectFile());
        System.out.println("这是主函数");
    }
}
