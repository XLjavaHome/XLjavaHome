package com.xl.excel;

import com.xl.util.FileUtil;
import com.xl.util.ResourceUtil;
import com.xl.util.excel.POIExcelUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import lombok.extern.log4j.Log4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.poi工具类
 * User: 徐立
 * Date: 2017-10-20
 * Time: 16:28
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class POIExcelTest {
    @Test
    public void excelTest() throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet1 = wb.createSheet("第一个工作簿");
        HSSFRow row = sheet1.createRow((short) 0);
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue(1);
        row.createCell((short) 1).setCellValue(1.2);
        row.createCell((short) 2).setCellValue("这是String");
        row.createCell((short) 3).setCellValue(true);
        row.createCell((short) 4).setCellValue(new Date());
        HSSFSheet sheet2 = wb.createSheet("第二个工作簿");
        File file = FileUtil.createTempFile("workbook.xls");
        FileOutputStream fileOut = new FileOutputStream(file);
        wb.write(fileOut);
        fileOut.close();
        FileUtil.open(file);
    }
    
    @Test
    public void readTest() throws ParseException {
        Workbook workbook = POIExcelUtil.initWorkbook(ResourceUtil.getResourceInputStream("excel/员工资料库导入模板.xls"));
        int numberOfSheets = workbook.getNumberOfSheets();
        log.info(numberOfSheets);
        Sheet sheet = workbook.getSheetAt(0);
        Date date = POIExcelUtil.getDate(sheet.getRow(1).getCell(4));
        Date date2 = POIExcelUtil.getDate(sheet.getRow(2).getCell(4));
        log.info(date);
        log.info(date2);
        //最大行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        log.info(rowNum);
    }
}
