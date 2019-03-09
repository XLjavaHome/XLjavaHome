package com.xl.excel.word;

import com.xl.base.IdWorker;
import com.xl.util.FileUtil;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-03-09
 * @time 15:46
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class WordTest {
    private static FileOutputStream stream = null;
    private static String fileName;
    
    /**
     * 生成word文档名称
     */
    @BeforeClass
    public static void generateWordDocumentName() {
        IdWorker worker = new IdWorker(1, 1);
        fileName = worker.nextId() + ".docx";
        try {
            stream = new FileOutputStream(FileUtil.createTempFile(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    @After
    public void after() throws IOException {
        FileUtil.open(FileUtil.createTempFile(fileName));
    }
    
    /**
     * poi生成word
     */
    @Test
    public void poiWordTest() throws IOException {
        XWPFDocument doc = new XWPFDocument(); //创建一个段落
        XWPFParagraph para = doc.createParagraph(); //一个XWPFRun代表具有相同属性的一个区域：一段文本
        XWPFRun run = para.createRun();
        run.setBold(true); //加粗
        run.setText("加粗的内容");
        run = para.createRun();
        run.setColor("FF0000");
        run.setText("红色的字。");
        doc.write(stream);
    }
    
    /**
     * 表格测试
     */
    @Test
    public void tableTest() throws IOException {
        XWPFDocument doc = new XWPFDocument(); //创建一个5行5列的表格
        //这里增加的列原本初始化创建的那5行在通过getTableCells()方法获取时获取不到，但通过row新增的就可以。 //table.addNewCol(); //给表格增加一列，变成6列
        int rowNums = 10;
        int colNums = 5;
        XWPFTable table = doc.createTable(rowNums, colNums);
        //给表格新增一行，变成6行
        table.createRow();
        List<XWPFTableRow> rows = table.getRows();
        for (int i = 0; i < rows.size(); i++) {
            //新增单元格
            XWPFTableRow row = rows.get(i);
            //row.addNewTableCell(); //设置行的高度
            //行属性 //CTTrPr rowPr = row.getCtRow().addNewTrPr(); //这种方式是可以获取到新增的cell的。 //List list = row.getCtRow().getTcList();
            row.setHeight(500);
            List<XWPFTableCell> cells = row.getTableCells();
            //徐立todo 添加表格标题
            for (int j = 0; j < colNums; j++) {
                XWPFTableCell cell = cells.get(j);
                //单元格属性
                CTTcPr cellPr = cell.getCTTc().addNewTcPr();
                cellPr.addNewVAlign().setVal(STVerticalJc.CENTER);
                //设置单元格宽度
                //第一列宽度是500，后面是1500
                if (j == 0) {
                    cellPr.addNewTcW().setW(BigInteger.valueOf(800));
                    if (i == 0) {
                        cell.setColor("606060");
                        cell.setText("编号");
                    }
                } else {
                    cellPr.addNewTcW().setW(BigInteger.valueOf(2000));
                    cell.setText(i + ", " + j);
                }
            }
        } //文件不存在时会自动创建
        doc.write(stream);
    }
}
