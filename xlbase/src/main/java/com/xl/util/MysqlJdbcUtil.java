package com.xl.util;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import lombok.extern.log4j.Log4j;

/**
 * Created with IntelliJ IDEA. JDBC工具类：关闭流和取得连接
 * User: 徐立
 * Date: 2017-10-17
 * Time: 17:27
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public final class MysqlJdbcUtil {
    private static String driver = null;
    public static String url = null;
    public static String user = null;
    public static String password = null;
    // 静态块：加载文件
    static {
        Properties props = new Properties();
        // 获得工程目录
        try {
            InputStream is = ResourceUtil.getResourceInputStream(JdbcUtil.MYSQL_MYSQL_PROPERTIESPATH);
            props.load(is);
            driver = props.getProperty("driver");
            url = props.getProperty("url");
            user = props.getProperty("user");
            password = props.getProperty("password");
            Class.forName(driver);
        } catch (Exception e) {
            log.error("数据库连接错误", e);
        }
    }
    // 关闭连接
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}