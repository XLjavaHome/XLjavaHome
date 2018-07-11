package com.xl.database.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {
    //4个 静态常量
    private final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private final static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private final static String USERNAME = "xl";

    //连接方法
    public Connection getConnection() {
        Connection conn = null;
        //加载驱动
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, "xl");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
