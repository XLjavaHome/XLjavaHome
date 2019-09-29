import com.xl.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-09-29
 * @time 14:37
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class AppTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Test
    public void name() {
        System.out.println(jdbcTemplate.getDataSource());
    }
}
