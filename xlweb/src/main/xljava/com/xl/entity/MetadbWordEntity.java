package com.xl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with 徐立.导出word的实体
 *
 * @author 徐立
 * @date 2019-03-10
 * @time 10:15
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetadbWordEntity {
    /**
     * 属性名称
     */
    private String propertyName;
    /**
     * 显示名
     */
    private String propertyInfo;
    /**
     * 属性类型
     */
    private String propertyType;
    /**
     * 属性说明
     */
    private String propertyDescription = "Metadb2自动创建";
}
