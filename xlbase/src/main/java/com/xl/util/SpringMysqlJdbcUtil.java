package com.xl.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA. JDBC工具类：关闭流和取得连接
 * User: 徐立
 * Date: 2017-10-17
 * Time: 17:27
 * To change this template use File | Settings | File Templates.
 */
//@Configuration
@Component
@PropertySource(value = "classpath:db/db.properties", encoding = "UTF-8")
@Data
public class SpringMysqlJdbcUtil {
    @Value("${driver}")
    private String driver = null;
    @Value("${url}")
    private String url = null;
}
