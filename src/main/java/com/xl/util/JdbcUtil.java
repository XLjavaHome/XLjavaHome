package com.xl.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

//JDBC工具类：关闭流和取得连接
public final class JdbcUtil {
    public static final String MYSQL_MYSQL_PROPERTIESPATH = "db/db.properties";
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    // 静态块：加载文件
    static {
        Properties props = new Properties();
        // 获得工程目录
        try {
            InputStream is = JdbcUtil.class.getClassLoader().getResourceAsStream(MYSQL_MYSQL_PROPERTIESPATH);
            is = JdbcUtil.class.getResourceAsStream(JdbcUtil.MYSQL_MYSQL_PROPERTIESPATH);
            props.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver = props.getProperty("driver");
        url = props.getProperty("url");
        user = props.getProperty("user");
        password = props.getProperty("password");
    }

    // 静态块：注册驱动
    static {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
