package com.xl.eployment.service;

import com.xl.eployment.entity.DeploymentEntity;
import java.io.IOException;

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
     *  @param author
     * @param flag
     * @param codeTextText
     */
    void createFile(String author, boolean flag, String codeTextText) throws IOException;
    
    /**
     * 创建部署包
     *
     * @param entity
     */
    void createFile(DeploymentEntity entity) throws IOException;
}