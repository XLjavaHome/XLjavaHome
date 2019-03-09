package com.xl.excel.word;

import com.xl.base.IdWorker;
import com.xl.entity.MetadbWordEntity;
import com.xl.util.FileUtil;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
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
    //表格宽度窄
    private int leftWidth = 1500;
    //表格宽度窄
    private int rightWidth = 2000;
    
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
        //创建一个段落
        XWPFDocument doc = new XWPFDocument();
        //一个XWPFRun代表具有相同属性的一个区域：一段文本
        XWPFParagraph para = doc.createParagraph();
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
     * cell.getCTTc().getTcPr() 会为空
     *
     * @throws IOException
     */
    @Test
    public void tableTest() throws IOException {
        XWPFDocument doc = new XWPFDocument(); //创建一个5行5列的表格
        //这里增加的列原本初始化创建的那5行在通过getTableCells()方法获取时获取不到，但通过row新增的就可以。
        int rowNums = 10;
        int colNums = 4;
        XWPFTable wordTable = doc.createTable(rowNums, colNums);
        //给表格新增一行，变成6行
        List<XWPFTableRow> rows = wordTable.getRows();
        //新增标题
        //属性名称	显示名	类型	说明
        //样式有4中
        XWPFTableRow titleRow = rows.get(0);
        int 属性名称列 = 0;
        标题单元格名称(titleRow.getCell(属性名称列), "属性名称");
        int 显示名列 = 1;
        标题单元格名称(titleRow.getCell(显示名列), "显示名");
        int 类型列 = 2;
        标题单元格名称(titleRow.getCell(类型列), "类型");
        int 说明列 = 3;
        标题单元格名称(titleRow.getCell(说明列), "说明");
        //生成表格数据
        List<MetadbWordEntity> metadbWordEntities = initTableData();
        //新增属性
        for (int i = 0; i < metadbWordEntities.size(); i++) {
            XWPFTableRow xwpfTableRow = rows.get(i + 1);
            MetadbWordEntity entity = metadbWordEntities.get(i);
            List<XWPFTableCell> tableCells = xwpfTableRow.getTableCells();
            tableCells.get(属性名称列).setText(entity.getPropertyInfo());
            leftCellStyle(tableCells.get(属性名称列));
            tableCells.get(显示名列).setText(entity.getPropertyInfo());
            rightCellStyle(tableCells.get(显示名列));
            tableCells.get(类型列).setText(entity.getPropertyType());
            rightCellStyle(tableCells.get(类型列));
            tableCells.get(说明列).setText(entity.getPropertyDescription());
            rightCellStyle(tableCells.get(说明列));
        }
        doc.write(stream);
    }
    
    private List<MetadbWordEntity> initTableData() {
        List<MetadbWordEntity> resultList = new ArrayList<>(50);
        MetadbWordEntity entity = new MetadbWordEntity();
        entity.setPropertyName("objid");
        entity.setPropertyInfo("主键");
        entity.setPropertyType("Integer");
        resultList.add(entity);
        MetadbWordEntity entity1 = new MetadbWordEntity();
        entity1.setPropertyName("Groupid");
        entity1.setPropertyInfo("组id");
        entity1.setPropertyType("Varchar2(200)");
        entity1.setPropertyDescription("关联ForecastGroup.objid");
        resultList.add(entity1);
        return resultList;
    }
    
    private void 标题单元格名称(XWPFTableCell titleCell, String text) {
        titleCell.setText(text);
        increaseTableHeadingStyle(titleCell);
    }
    
    /**
     * 增加表格标题样式
     *
     * @param cell
     */
    private void increaseTableHeadingStyle(XWPFTableCell cell) {
        grayedOutBackground(cell);
        increaseCellStyle(cell, 2000);
    }
    
    /**
     * 左侧标题样式
     *
     * @param cell
     */
    private void leftHeadingStyle(XWPFTableCell cell) {
        //背景变灰
        grayedOutBackground(cell);
        increaseCellStyle(cell, leftWidth);
    }
    
    private void rightHeadingStyle(XWPFTableCell cell) {
        grayedOutBackground(cell);
        increaseCellStyle(cell, rightWidth);
    }
    
    void leftCellStyle(XWPFTableCell cell) {
        increaseCellStyle(cell, leftWidth);
    }
    
    /**
     * 右侧单元格的样式
     *
     * @param cell
     */
    void rightCellStyle(XWPFTableCell cell) {
        increaseCellStyle(cell, rightWidth);
    }
    
    /**
     * 增加单元格样式
     *
     * @param cell
     * @param width
     */
    private void increaseCellStyle(XWPFTableCell cell, int width) {
        CTTcPr cellPr1 = cell.getCTTc().addNewTcPr();
        cellPr1.addNewVAlign().setVal(STVerticalJc.CENTER);
        cellPr1.addNewTcW().setW(BigInteger.valueOf(width));
    }
    
    /**
     * 背景变灰
     *
     * @param cell
     */
    private void grayedOutBackground(XWPFTableCell cell) {
        cell.setColor("606060");
    }
}
