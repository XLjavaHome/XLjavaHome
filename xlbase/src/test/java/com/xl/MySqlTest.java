package com.xl;

import com.xl.util.IOUtil;
import com.xl.util.MySQLJdbcUtil;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.需要权限
 *
 * @author 徐立
 * @date 2019-08-25
 * @time 23:21
 * To change this template use File | Settings | File Templates.
 */
public class MySqlTest {
    Runtime runtime = Runtime.getRuntime();
    
    @Test
    void start() throws SQLException {
        //1.获取连接
        Connection connection = MySQLJdbcUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select  123 from dual");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int anInt = resultSet.getInt(1);
            System.out.println(anInt);
        }
    }
    
    @Test
    void startService() throws IOException {
        Process service_start_mysql = runtime.exec("net start  mysql");
        if (!service_start_mysql.isAlive()) {
            System.out.println("mysql启动成功");
        }
    }
    
    @Test
    void stop() throws IOException {
        Process exec = runtime.exec("cmd /c net stop MySQL ");
        InputStream inputStream = exec.getInputStream();
        String content = IOUtil.getContent(inputStream);
        System.out.println(content);
    }
}
