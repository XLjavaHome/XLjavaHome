package com.xl.database.mysql;

import com.xl.util.MysqlJdbcUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
    public void getConnectTest() {
        Connection c = MysqlJdbcUtil.getMySqlConnection();
        try {
            c.setAutoCommit(false);
        Statement stmt = c  .createStatement();
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //1 or more queries or updates
    
        log.info(c);
    }
}
