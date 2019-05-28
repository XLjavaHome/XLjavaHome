package com.xl.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-05-21
 * @time 11:46
 * To change this template use File | Settings | File Templates.
 */
public class ExcelTest {
    /**
     * 判断excel的版本 WorkbookFactory.create
     *
     * @throws IOException
     * @throws InvalidFormatException
     */
    @Test
    public void 判断excel的版本() throws IOException, InvalidFormatException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("excel");
        String file = url.getFile();
        File f = new File(file);
        File[] files = f.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file1 = files[i];
            //判断excel的版本
            Workbook wb = WorkbookFactory.create(new FileInputStream(file1));
            System.out.println(wb.getClass().getName());
        }
    }
}
