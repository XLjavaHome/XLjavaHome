package com.xl.util.excel;

import com.xl.util.DateUtil;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.log4j.Log4j;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 徐立
 * @Date: 2018-03-16
 * @Time: 9:35
 * To change this template use File | Settings | File Templates.
 */
@Log4j
@SuppressWarnings("deprecation")
public class POIExcelUtil {
    /**
     * 读取excel ,判断excel版本用流
     *
     * @param in
     * @return
     */
    public static Workbook initWorkbook(InputStream in) throws IOException, InvalidFormatException {
        if (POIFSFileSystem.hasPOIFSHeader(in)) {
            return new HSSFWorkbook(in);
        } else if (POIXMLDocument.hasOOXMLHeader(in)) {
            return new XSSFWorkbook(OPCPackage.open(in));
        }
        return null;
    }
    
    /**
     * 获取日期
     *
     * @param cell
     * @return
     */
    public static Date getDate(Cell cell) throws ParseException {
        if (cell == null) {
            return null;
        }
        CellType cellTypeEnum = cell.getCellTypeEnum();
        switch (cellTypeEnum) {
            case NUMERIC:
                double value = cell.getNumericCellValue();
                short format = cell.getCellStyle().getDataFormat();
                SimpleDateFormat sdf = null;
                if (format == 14 || format == 31 || format == 57 || format == 58 || (176 <= format && format <= 178) || (
                        182 <= format && format <= 196) || (210 <= format && format <= 213) || (208 == format)) { // 日期
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                } else if (format == 20 || format == 32 || format == 183 || (200 <= format && format <= 209)) { // 时间
                    sdf = new SimpleDateFormat("HH:mm");
                } else {
                    return DateUtil.formatDate(new BigDecimal(value).toString(), "yyyyMMdd");
                }
                return sdf.parse(value + "");
            case STRING:
                String stringCellValue = cell.getStringCellValue();
                return DateUtil.formatDate(stringCellValue, "yyyy-MM-dd");
        }
        Date dateCellValue = cell.getDateCellValue();
        return dateCellValue;
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
            String cellValue;
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
