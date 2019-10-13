package com.xl.util;

import jxl.Cell;
import jxl.format.UnderlineStyle;
import jxl.write.*;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-10-13
 * @time 19:05
 * To change this template use File | Settings | File Templates.
 */
public class JxlUtil {
    /**
     * 合并
     *
     * @param sheet
     * @throws WriteException
     */
    public static void mergeColumns(WritableSheet sheet) throws WriteException {
        WritableFont mergeFont = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false,
                                                  UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
        WritableCellFormat writableCellFormat = new WritableCellFormat(mergeFont);
        // 通过调整宽度和高度自动换行
        writableCellFormat.setWrap(true);
        // 把垂直对齐方式指定为居中
        writableCellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
        writableCellFormat.setAlignment(jxl.format.Alignment.CENTRE);
        for (int column = 0; column < sheet.getColumns(); column++) {
            //循环每一行，如果该行的内容与下行的内容一致则继续循环，如果不一致则合并
            int startIndex = 0;
            //如果遍历完都一样则合并
            for (int i = 0; i < sheet.getRows() - 1; i++) {
                Cell cell = sheet.getCell(column, i);
                Cell nextCell = sheet.getCell(column, i + 1);
                //和下一行不一致，合并上面
                if (!StringUtil.equals(cell.getContents(), nextCell.getContents())) {
                    if (startIndex != nextCell.getRow() - 1) {
                        doMerge(sheet, column, startIndex, cell, nextCell, writableCellFormat);
                    }
                    startIndex = nextCell.getRow();
                } else if (i == (sheet.getRows() - 2) && StringUtil.isNotEmpty(cell.getContents())) {
                    //倒数第二行
                    settingStyles(sheet, column, startIndex, cell, writableCellFormat);
                    //设置样式2(cell);
                    sheet.mergeCells(column, startIndex, column, nextCell.getRow());
                }
            }
        }
    }
    
    /**
     * 执行合并
     *
     * @param sheet
     * @param column
     * @param startIndex
     * @param cell
     * @param nextCell
     * @param mergeStyle
     * @throws WriteException
     */
    private static void doMerge(WritableSheet sheet, int column, int startIndex, Cell cell, Cell nextCell,
            WritableCellFormat mergeStyle) throws WriteException {
        settingStyles(sheet, column, startIndex, cell, mergeStyle);
        //设置样式2(cell);
        sheet.mergeCells(column, startIndex, column, nextCell.getRow() - 1);
    }
    
    /**
     * 设置合并后的样式
     *
     * @param sheet
     * @param column
     * @param startIndex
     * @param cell
     * @param mergeStyle 合并之后的样式
     * @throws WriteException
     */
    private static void settingStyles(WritableSheet sheet, int column, int startIndex, Cell cell,
            WritableCellFormat mergeStyle) throws WriteException {
        sheet.addCell(new Label(column, startIndex, cell.getContents(), mergeStyle));
    }
}
