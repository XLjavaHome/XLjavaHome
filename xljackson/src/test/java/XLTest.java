import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-23
 * @time 13:02
 * To change this template use File | Settings | File Templates.
 */
public class XLTest {
    public static ObjectMapper mapper = new ObjectMapper();
    static {
        // 转换为格式化的json
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    @org.junit.jupiter.api.Test
    void name() throws IOException {
        XwjUser user = new XwjUser(1, "Hello World", new Date());
        mapper.writeValue(new File("test.txt"), user); // 写到文件中
        // mapper.writeValue(System.out, user); //写到控制台
        String jsonStr = mapper.writeValueAsString(user);
        System.out.println("对象转为字符串：" + jsonStr);
        byte[] byteArr = mapper.writeValueAsBytes(user);
        System.out.println("对象转为byte数组：" + byteArr);
        XwjUser userDe = mapper.readValue(jsonStr, XwjUser.class);
        System.out.println("json字符串转为对象：" + userDe);
        XwjUser useDe2 = mapper.readValue(byteArr, XwjUser.class);
        System.out.println("byte数组转为对象：" + useDe2);
    }
}
