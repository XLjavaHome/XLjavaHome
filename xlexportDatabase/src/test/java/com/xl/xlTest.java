package com.xl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.commons.collections4.MapUtils;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-09-19
 * @time 22:05
 * To change this template use File | Settings | File Templates.
 */
public class xlTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
    
    @Test
    void name() throws SQLException {
        DataSource dataSource = context.getBean(DataSource.class);
        System.out.println(dataSource);
        Connection connection = dataSource.getConnection();
    }
    
    @Test
    void tables() throws SQLException {
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        DataSource dataSource = jdbcTemplate.getDataSource();
        //todo
        List<Map<String, Object>> stringObjectMap = jdbcTemplate.queryForList(
                "select table_name,table_comment from information_schema.tables  where table_schema=? ", "xl");
        stringObjectMap.forEach(stringObjectMap1 -> {
            String table_name = MapUtils.getString(stringObjectMap1, "table_name");
            String table_comment = MapUtils.getString(stringObjectMap1, "table_comment");
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(
                    "select * from information_schema.columns\n" + "  where table_name = ?", table_name);
            System.out.println(maps);
        });
    }
}
