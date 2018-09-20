package com.xl.excel;

import com.xl.util.FileUtil;
import com.xl.util.ResourceUtil;
import com.xl.util.excel.POIExcelUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.poi工具类
 * User: 徐立
 * Date: 2017-10-20
 * Time: 16:28
 * To change this template use File | Settings | File Templates.
 */
public class POIExcelTest {
    private String path;

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
        FileUtil.open(file.getParentFile());
    }

    @Before
    public void before() {
        path = "excel/员工资料库导入模板.xls";
    }

    @Test
    public void readTest() {
        Workbook workbook = POIExcelUtil.initWorkbook(ResourceUtil.getResourceInputStream(path));
        System.out.println(workbook);
        int numberOfSheets = workbook.getNumberOfSheets();
        System.out.println(numberOfSheets);
        Sheet sheet = workbook.getSheetAt(0);
        //最大行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        System.out.println(rowNum);
    }
}
