package com.xl.word.service;

import com.xl.entity.MetadbWordEntity;
import com.xl.word.entity.TableColumnEnum;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-03-13
 * @time 22:05
 * To change this template use File | Settings | File Templates.
 */
public class WordService {
    //表格宽度窄
    private static final int leftWidth = 1700;
    //表格宽度窄
    private static final int rightWidth = 2200;
    
    /**
     * 根据metadbWordEntity生成word
     *
     * @param metadbWordEntities
     * @return
     */
    public XWPFDocument initWord(List<MetadbWordEntity> metadbWordEntities) {
        if (metadbWordEntities == null || metadbWordEntities.size() == 0) {
            return null;
        }
        XWPFDocument doc = new XWPFDocument();
        int rowNums = metadbWordEntities.size() + 1;
        int colNums = TableColumnEnum.values().length;
        //生成表格数据
        XWPFTable wordTable = doc.createTable(rowNums, colNums);
        List<XWPFTableRow> rows = wordTable.getRows();
        //新增标题
        addTableTitle(rows);
        for (int i = 0; i < metadbWordEntities.size(); i++) {
            XWPFTableRow xwpfTableRow = rows.get(i + 1);
            MetadbWordEntity entity = metadbWordEntities.get(i);
            //每一行的单元格
            List<XWPFTableCell> tableCells = xwpfTableRow.getTableCells();
            //左侧单元格
            addLeftTableCell(tableCells, entity);
            //右侧单元格
            addRightTableCell(tableCells, TableColumnEnum.displayName, entity.getPropertyInfo());
            addRightTableCell(tableCells, TableColumnEnum.typeColumn, entity.getPropertyType());
            addRightTableCell(tableCells, TableColumnEnum.descriptionColumn, entity.getPropertyDescription());
        }
        return doc;
    }
    
    /**
     * 左侧单元格
     *
     * @param tableCells
     * @param entity
     */
    private void addLeftTableCell(List<XWPFTableCell> tableCells, MetadbWordEntity entity) {
        tableCells.get(TableColumnEnum.attributeNameColumn.getColumn()).setText(entity.getPropertyName());
        //左侧单元格
        leftCellStyle(tableCells.get(TableColumnEnum.attributeNameColumn.getColumn()));
    }
    
    /**
     * @param tableCells
     * @param TableColumn
     * @param propertyInfo
     */
    private void addRightTableCell(List<XWPFTableCell> tableCells, TableColumnEnum TableColumn, String propertyInfo) {
        XWPFTableCell cell = tableCells.get(TableColumn.getColumn());
        cell.setText(propertyInfo);
        rightCellStyle(cell);
    }
    
    /**
     * 添加表格标题
     *
     * @param rows
     */
    private void addTableTitle(List<XWPFTableRow> rows) {
        XWPFTableRow titleRow = rows.get(0);
        //添加左侧标题栏
        addLeftTitleCell(titleRow.getCell(TableColumnEnum.attributeNameColumn.getColumn()));
        addRightTitleCell(titleRow.getCell(TableColumnEnum.displayName.getColumn()), "显示名");
        addRightTitleCell(titleRow.getCell(TableColumnEnum.typeColumn.getColumn()), "类型");
        addRightTitleCell(titleRow.getCell(TableColumnEnum.descriptionColumn.getColumn()), "说明");
    }
    
    /**
     * 添加左侧标题栏
     *
     * @param titleCell
     */
    private void addLeftTitleCell(XWPFTableCell titleCell) {
        titleCell.setText("属性名称");
        addGrayedOutBackground(titleCell);
        leftHeadingStyle(titleCell);
    }
    
    /**
     * 添加右侧标题栏
     *
     * @param titleCell 标题单元格
     * @param text 内容
     */
    private void addRightTitleCell(XWPFTableCell titleCell, String text) {
        //徐立todo 颜色
        titleCell.setText(text);
        increaseTableHeadingStyle(titleCell);
    }
    
    /**
     * 增加表格标题样式
     *
     * @param cell
     */
    private void increaseTableHeadingStyle(XWPFTableCell cell) {
        addGrayedOutBackground(cell);
        increaseCellStyle(cell, rightWidth);
    }
    
    /**
     * 左侧标题样式
     *
     * @param cell
     */
    private void leftHeadingStyle(XWPFTableCell cell) {
        //背景变灰
        addGrayedOutBackground(cell);
        increaseCellStyle(cell, leftWidth);
    }
    
    private void rightHeadingStyle(XWPFTableCell cell) {
        addGrayedOutBackground(cell);
        increaseCellStyle(cell, rightWidth);
    }
    
    /**
     * 左侧表格的样式
     *
     * @param cell
     */
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
    private void addGrayedOutBackground(XWPFTableCell cell) {
        cell.setColor("606060");
    }
    
    /**
     * 生成表格数据
     *
     * @return
     */
    public List<MetadbWordEntity> initTableData() {
        List<MetadbWordEntity> resultList = new ArrayList<>(50);
        resultList.add(new MetadbWordEntity("objid", "主键", "Integer", "由Metadb2自动创建"));
        resultList.add(new MetadbWordEntity("Groupid", "组id", "Varchar2(200)", "关联ForecastGroup.objid"));
        resultList.add(new MetadbWordEntity("sourceId", "风险主体id", "Integer", "关联PM_PRODUCT.OBJID\n" + "或PM_INSTITUTIONS.OBJID"));
        return resultList;
    }
}
