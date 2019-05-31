package com.xl.entity;

import com.xl.enumsupport.CharacterEnum;
import freemarker.template.Configuration;
import java.io.File;
import java.util.Map;
import lombok.Data;

@Data
public class Freemark {
    /**
     * freemark模板配置
     */
    private Configuration configuration;
    /**
     * 输出文件
     */
    private File outFile;
    /**
     * 要解析的模板名称
     */
    private String templetName;
    /**
     * 解析的参数，如果文档有参数没给值会报错
     */
    private Map param;

    /**
     * freemark初始化
     *
     * @param templatePath 模板文件位置
     */
    public Freemark(String templatePath) {
        configuration = new Configuration();
        configuration.setDefaultEncoding(CharacterEnum.UTF8.getValue());
        configuration.setClassForTemplateLoading(this.getClass(), templatePath);
    }
}
