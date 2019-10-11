import com.xl.entity.Student;
import com.xl.util.FileUtil;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jxl.Cell;
import jxl.CellView;
import jxl.format.CellFormat;
import jxl.format.UnderlineStyle;
import jxl.write.Number;
import jxl.write.*;
import lombok.Data;
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
        //sheet.mergeCells(0, 1, 1, 1);//添加合并单元格:第一个参数是起始列;第二个参数是起始行;第三个参数是终止列;第四个参数是终止行
        // 通过函数WritableFont（）设置字体样式
        // 第一个参数表示所选字体
        // 第二个参数表示字体大小
        // 第三个参数表示粗体样式，有BOLD和NORMAL两种样式
        // 第四个参数表示是否斜体
        // 第五个参数表示下划线样式
        // 第六个参数表示颜色样式
        WritableSheet sheet = workbook.createSheet("客户详情", 0);//第一个sheet名
        WritableFont wf = new WritableFont(WritableFont.TIMES, 16, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                                           Colour.BLACK);
        CellFormat cf = new WritableCellFormat(wf);
        sheet.addCell(new Label(0, 0, "客户详情", cf));
        sheet.addCell(new Label(0, 2, "客户编号"));
        sheet.addCell(new Label(1, 2, "客户名称"));
        List<Student> list = initStudent();
        //根据内容自动设置列宽(内容为英文时)
        CellView cellView = new CellView();
        cellView.setAutosize(true);
        sheet.setColumnView(0, cellView);
        sheet.setColumnView(1, cellView);
        // 生成主体内容
        Map<String, MerEntity> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            //sheet.setColumnView(0, bstrLength1.length+2);
            //sheet.setColumnView(1, bstrLength2.length+2); //根据内容自动设置列宽(内容可以为中文)
            int row = i + 3;
            int cell = 0;
            sheet.addCell(new Label(cell, row, stu.getId() + ""));
            sheet.addCell(new Label(++cell, row, stu.getName()));
            Number number = new Number(++cell, row, stu.getAge());
            sheet.addCell(number);
            String address = stu.getAddress();
            MerEntity merEntity = map.get(address);
            //
            if (merEntity == null) {
                merEntity = new MerEntity();
                merEntity.setCol(4);
                merEntity.setStart(row);
                merEntity.setEnd(row);
                merEntity.setContent(address);
                map.put(address, merEntity);
                sheet.addCell(new Label(++cell, row, address));
            } else {
                merEntity.setEnd(row);
                sheet.mergeCells(merEntity.getCol(), merEntity.getStart(), merEntity.getCol(), merEntity.getEnd());
            }
        }
        //合并单元格 todo 怎么居中？
        // TODO 2019/10/9 23:42 徐立 如果单元格有一样的内容则不能合并
        //aa(sheet, map);
        workbook.write(); // 写入文件
        workbook.close();
        os.close(); // 关闭流
        FileUtil.open(file);
    }
    
    private List<Student> initStudent() {
        List<Student> resulit = new ArrayList<>(2000);
        for (int i = 0; i < 1000; i++) {
            Student student = new Student();
            student.setId(i);
            student.setName("测试" + i);
            student.setAge(i % 60);
            resulit.add(student);
        }
        for (int i = 0, resulitSize = 10; i < resulitSize; i++) {
            Student student = resulit.get(i);
            student.setAddress("雄楚大道");
        }
        for (int i = 10; i < 20; i++) {
            Student student = resulit.get(i);
            student.setAddress("徐东");
        }
        for (int i = 20, resulitSize = 30; i < resulitSize; i++) {
            Student student = resulit.get(i);
            student.setAddress("测试");
        }
        return resulit;
    }
    
    private void aa(WritableSheet sheet1, Map<String, MerEntity> map) throws WriteException {
        for (MerEntity merEntity : map.values()) {
            sheet1.mergeCells(merEntity.getCol(), merEntity.getStart(), merEntity.getCol(), merEntity.getEnd());
            Cell cell = sheet1.getCell(merEntity.getCol(), merEntity.getStart());
            CellFormat cellFormat = cell.getCellFormat();
            jxl.format.VerticalAlignment verticalAlignment = cellFormat.getVerticalAlignment();
        }
    }
    
    @Data
    private class MerEntity {
        private int col;
        private int start;
        private int end;
        private String content;
    }
}
