import com.xl.entity.Student;
import com.xl.util.FileUtil;
import com.xl.util.JxlUtil;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import jxl.write.Number;
import jxl.write.*;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-09-16
 * @time 22:47
 * To change this template use File | Settings | File Templates.
 */
public class JxlTest {
    @Test
    public void writeTest() throws IOException, WriteException {
        File file = FileUtil.getTempFile(".xls");
        OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
        WritableWorkbook workbook = jxl.Workbook.createWorkbook(os);
        // 通过函数WritableFont（）设置字体样式
        // 第一个参数表示所选字体
        // 第二个参数表示字体大小
        // 第三个参数表示粗体样式，有BOLD和NORMAL两种样式
        // 第四个参数表示是否斜体
        // 第五个参数表示下划线样式
        // 第六个参数表示颜色样式
        WritableSheet sheet = workbook.createSheet("jxl生成excel测试", 0);
        List<Student> list = initStudent();
        //根据内容自动设置列宽(内容为英文时)
        //CellView cellView = new CellView();
        //cellView.setAutosize(true);
        //sheet.setColumnView(0, cellView);
        //sheet.setColumnView(1, cellView);
        // 生成主体内容
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            int cell = 0;
            sheet.addCell(new Label(cell, i, stu.getId() + ""));
            sheet.addCell(new Label(++cell, i, stu.getName()));
            sheet.addCell(new Number(++cell, i, stu.getAge()));
            sheet.addCell(new Label(++cell, i, stu.getPhone()));
            sheet.addCell(new Label(++cell, i, stu.getAddress()));
        }
        //合并
        JxlUtil.mergeColumns(sheet);
        // 写入文件
        workbook.write();
        workbook.close();
        // 关闭流
        os.close();
        FileUtil.open(file);
    }
    
    private List<Student> initStudent() {
        List<Student> resulit = new ArrayList<>(2000);
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Student student = new Student();
            student.setId(i);
            student.setName("测试" + RandomUtils.nextInt());
            student.setAge(random.nextInt(100));
            resulit.add(student);
        }
        int startIndex = addAddress(resulit, 0, "雄楚大道");
        startIndex = addAddress(resulit, startIndex, "徐东");
        startIndex = addAddress(resulit, startIndex, "光谷广场");
        startIndex = addAddress(resulit, startIndex, "徐东");
        addAddress(resulit, startIndex, "测试文字超长测试文字超长测试文字超长测试文字超长测试文字超长测试文字超长测试文字超长测试文字超长测试文字超长测试文字超长测试文字超长");
        return resulit;
    }
    
    private int addAddress(List<Student> resulit, int startIndex, String content) {
        int endIndex = startIndex + 10;
        for (int i = startIndex; i < endIndex; i++) {
            Student student = resulit.get(i);
            student.setAddress(content);
        }
        return endIndex;
    }
}
