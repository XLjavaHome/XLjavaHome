package com.xl.deploy.gui;

import com.xl.deloy.service.DeploymentPackageService;
import com.xl.deloy.service.impl.DeploymentPackageServiceImpl;
import org.springframework.context.annotation.Bean;

/**
 * Created by pkumar on 8/27/17.
 */
public class AppConfig {
    @Bean
    public DeploymentPackageService getDeploymentPackageService() {
        return new DeploymentPackageServiceImpl();
    }
    
    @Bean
    public DeployForm getDeployForm() {
        return new DeployForm();
    }
}
