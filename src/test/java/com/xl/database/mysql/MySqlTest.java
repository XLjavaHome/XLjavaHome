package com.xl.database.mysql;

import com.xl.util.MysqlJdbcUtil;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: 徐立
 * Date: 2017/9/6
 * Time: 17:17
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class MySqlTest {
    @Test
    public void getConnectTest() {
        Connection c = MysqlJdbcUtil.getMySqlConnection();
        log.info(c);
    }
}
