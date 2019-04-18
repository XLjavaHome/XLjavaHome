package com.xl.database.mysql;

import com.xl.util.JdbcUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: 徐立
 * Date: 2017/9/6
 * Time: 17:17
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class MySqlTest {
    /**
     * 事物测试
     */
    @Test
    public void getConnectTest() throws SQLException {
        Connection c = JdbcUtil.getConnection();
        //事务 ,falsekoi不会生效
        c.setAutoCommit(false);
        c.setAutoCommit(true);
        //可以两个sql一起执行
        String sql = "insert into user(name) values (?)";
        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setString(1, "aaa");
        stmt.executeUpdate();
        PreparedStatement preparedStatement = c.prepareStatement(sql);
        preparedStatement.setString(1, "bbb");
        preparedStatement.executeUpdate();
        preparedStatement.close();
        stmt.close();
        JdbcUtil.close(c);
    }
    
    @Test
    public void name() throws SQLException {
        Connection c = JdbcUtil.getConnection();
        //可以两个sql一起执行
        String sql = "select 1 from dual";
        PreparedStatement stmt = c.prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();
        String string = resultSet.getString(0);
        System.out.println(string);
        stmt.close();
        JdbcUtil.close(c);
    
    }
}
