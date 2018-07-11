package com.xl.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

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
    private static String url = null;
    private static String user = null;
    private static String password = null;
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

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void connectionTest() {
        Connection c = getMySqlConnection();
        log.info(c);
    }

    // 取得连接
    public static Connection getMySqlConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
