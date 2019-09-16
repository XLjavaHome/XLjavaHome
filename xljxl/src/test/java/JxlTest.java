import com.xl.entity.Student;
import com.xl.util.FileUtil;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import jxl.format.CellFormat;
import jxl.format.UnderlineStyle;
import jxl.write.*;
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
    public void name() throws IOException, WriteException {
        File file = FileUtil.getTempFile(".xls");
        OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
        WritableWorkbook workbook = jxl.Workbook.createWorkbook(os);
        //sheet1.mergeCells(0, 1, 1, 1);//添加合并单元格:第一个参数是起始列;第二个参数是起始行;第三个参数是终止列;第四个参数是终止行
        // 通过函数WritableFont（）设置字体样式
        // 第一个参数表示所选字体
        // 第二个参数表示字体大小
        // 第三个参数表示粗体样式，有BOLD和NORMAL两种样式
        // 第四个参数表示是否斜体
        // 第五个参数表示下划线样式
        // 第六个参数表示颜色样式
        WritableSheet sheet1 = workbook.createSheet("客户详情", 0);//第一个sheet名
        WritableFont wf = new WritableFont(WritableFont.TIMES, 16, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                                           Colour.BLACK);
        CellFormat cf = new WritableCellFormat(wf);
        sheet1.addCell(new Label(0, 0, "客户详情", cf));
        sheet1.addCell(new Label(0, 2, "客户编号"));
        sheet1.addCell(new Label(1, 2, "客户名称"));
        List<Student> list = initStudent();
        //根据内容自动设置列宽(内容为英文时)
        //CellView cellView = new CellView();
        //cellView.setAutosize(true);
        //sheet1.setColumnView(0, cellView);
        //sheet1.setColumnView(1, cellView);
        // 生成主体内容
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            //sheet1.setColumnView(0, bstrLength1.length+2);
            //sheet1.setColumnView(1, bstrLength2.length+2); //根据内容自动设置列宽(内容可以为中文)
            sheet1.addCell(new Label(0, i + 3, stu.getId() + ""));
            sheet1.addCell(new Label(1, i + 3, stu.getName()));
        }
        workbook.write(); // 写入文件
        workbook.close();
        os.close(); // 关闭流
        FileUtil.openParent(file);
    }
    
    private List<Student> initStudent() {
        List<Student> resulit = new ArrayList<>(2000);
        for (int i = 0; i < 1000; i++) {
            Student student = new Student();
            student.setId(i);
            student.setName("测试" + i);
            resulit.add(student);
        }
        return resulit;
    }
}
