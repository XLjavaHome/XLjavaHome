package com.xl;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-09-19
 * @time 22:05
 * To change this template use File | Settings | File Templates.
 */
public class xlTest {
    @Test
    void name() throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        DataSource dataSource = context.getBean(DataSource.class);
        System.out.println(dataSource);
        Connection connection = dataSource.getConnection();
    }
}
