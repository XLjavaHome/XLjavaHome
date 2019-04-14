package com.xl.service;

import java.io.IOException;
import javax.swing.JTextArea;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-04-13
 * @time 15:40
 * To change this template use File | Settings | File Templates.
 */
public interface DeploymentPackageService {
    /**
     * 生成部署包
     *  @param flag
     * @param codeText
     * @param textArea2 部署文档中的内容
     */
    void createFile(boolean flag, JTextArea codeText, JTextArea textArea2) throws IOException;
}
