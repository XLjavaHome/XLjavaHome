import lombok.extern.log4j.Log4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.BackedList;
import org.dom4j.tree.DefaultElement;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-09-08
 * @time 11:25
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class Dom4jTest {
    @Test
    void read() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("xml/user.xml"));
        Element rootElement = document.getRootElement();
        BackedList elements = (BackedList) rootElement.elements();
        elements.forEach(o -> {
            DefaultElement o1 = (DefaultElement) o;
            System.out.println(o1.getName());
        });
    }
}
