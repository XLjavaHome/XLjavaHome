package com.xl.util.excel;

import com.xl.util.DateUtil;
import lombok.extern.log4j.Log4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-03-16
 * @Time: 9:35
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class POIExcelUtil {
    /**
     * 读取excel
     *
     * @param in
     * @return
     */
    public static Workbook initWorkbook(InputStream in) {
        Workbook workbook = null;
        try {
            workbook = new HSSFWorkbook(in);
        } catch (Exception ex) {
            try {
                workbook = new XSSFWorkbook(in);
            } catch (Exception e) {
                return workbook;
            }
        }
        return workbook;
    }

    /**
     * 获取日期
     *
     * @param cell
     * @return
     */
    public static Date getDate(Cell cell) {
        if (cell != null) {
            Date d = null;
            try {
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {  //判断日期类型
                        d = cell.getDateCellValue();
                        if (!DateUtil.formatDate(d, "yyyy-MM-dd").matches("\\d{4}-\\d{2}-\\d{2}")) {
                            d = null;
                        }
                    }
                }
            } catch (IllegalStateException e) {
                log.error("时间格式不正确:" + getString(cell));
            }
            return d;
        } else {
            return null;
        }
    }

    /**
     * 获取单元格中内容
     *
     * @param cell
     * @return
     */
    public static String getString(Cell cell) {
        if (cell != null) {
            int cellType = cell.getCellType();
            String cellValue = null;
            switch (cellType) {
                case Cell.CELL_TYPE_STRING: //字符串类型
                    cellValue = cell.getStringCellValue().trim();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:  //布尔类型
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_NUMERIC: //数值类型
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {  //判断日期类型
                        cellValue = DateUtil.formatDate(cell.getDateCellValue(), "yyyy-MM-dd");
                    } else {  //否
                        cellValue = new DecimalFormat("#.######").format(cell.getNumericCellValue());
                    }
                    break;
                default: //其它类型，取空串吧
                    cellValue = "";
                    break;
            }
            return cellValue;
        } else {
            return null;
        }
    }
}
