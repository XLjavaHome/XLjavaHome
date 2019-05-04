package com.xl.util.excel;

import com.xl.excel.annotation.Cell;
import com.xl.excel.annotation.Entity;
import com.xl.reflect.MethodTool;
import com.xl.util.ResourceUtil;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.poi.hssf.usermodel.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Created by Administrator on 14-5-6.
 */
public class SimpleExportUtil {
    public static int PRECISION = 3;
    private static SimpleExportUtil tool = new SimpleExportUtil();

    public static HSSFWorkbook exportToExcel(List<? extends Object> list, String sheetName) throws Exception {
        BigDecimal bigDecimal = null;
        MathContext mathContext = new MathContext(PRECISION, RoundingMode.HALF_UP);
        Class clazz = list.get(0).getClass();
        List entities = tool.parseAnnotation(clazz);
        if ((entities == null) || (entities.size() == 0)) {
            entities = tool.parseXML(clazz);
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);
        HSSFRow titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(25.0F);
        tool.createTitleRow(workbook, titleRow, entities, sheet);
        int rowNum = 1;
        for (int i = 0; i < list.size(); ++i) {
            HSSFRow row = sheet.createRow(rowNum++);
            row.setHeightInPoints(23.0F);
            HSSFCellStyle cellStyle = tool.createCellStyle(workbook);
            int colNum = 0;
            for (Iterator localIterator = entities.iterator(); localIterator.hasNext(); ) {
                Entity entity = (Entity) localIterator.next();
                String type = entity.getType();
                String name = entity.getName();
                HSSFCell cell = row.createCell(colNum);
                cell.setCellStyle(cellStyle);
                Object obj = null;
                if (("boolean".equals(type)) || ("java.lang.Boolean".equals(type))) {
                    obj = MethodTool.executeMethod(list.get(i), MethodTool.returnIsBooleanMethodName(name));
                } else {
                    obj = MethodTool.executeMethod(list.get(i), MethodTool.returnGetMethodName(name));
                }
                if (obj != null) {
                    if (type.endsWith("String")) {
                        cell.setCellValue((String) obj);
                    } else if (("int".equals(type)) || ("java.lang.Integer".equals(type))) {
                        cell.setCellValue(((Integer) obj).intValue());
                    } else if (("double".equals(type)) || ("java.lang.Double".equals(type))) {
                        bigDecimal = new BigDecimal(((Double) obj).doubleValue(), mathContext);
                        cell.setCellValue(bigDecimal.doubleValue());
                    } else if (("boolean".equals(type)) || ("java.lang.Boolean".equals(type))) {
                        cell.setCellValue(((Boolean) MethodTool.executeMethod(list.get(i), MethodTool.returnIsBooleanMethodName(name))).booleanValue());
                    } else if (("float".equals(type)) || ("java.lang.Float".equals(type))) {
                        bigDecimal = new BigDecimal(((Float) obj).floatValue(), mathContext);
                        cell.setCellValue(bigDecimal.doubleValue());
                    } else if (("java.util.Date".equals(type)) || (type.endsWith("Date"))) {
                        cell.setCellValue((Date) obj);
                    } else if ("java.util.Calendar".equals(type)) {
                        cell.setCellValue((Calendar) obj);
                    } else if (("char".equals(type)) || ("java.lang.Character".equals(type))) {
                        cell.setCellValue(obj.toString());
                    } else if (("long".equals(type)) || ("java.lang.Long".equals(type))) {
                        cell.setCellValue(((Long) obj).longValue());
                    } else if (("short".equals(type)) || ("java.lang.Short".equals(type))) {
                        cell.setCellValue(((Short) obj).shortValue());
                    } else if ("java.math.BigDecimal".equals(type)) {
                        bigDecimal = (BigDecimal) obj;
                        bigDecimal = new BigDecimal(bigDecimal.doubleValue(), mathContext);
                        cell.setCellValue(bigDecimal.doubleValue());
                    } else {
                        throw new Exception("data type errored!");
                    }
                }
                ++colNum;
            }
        }
        return workbook;
    }

    private List<Entity> parseAnnotation(Class<? extends Object> clazz) throws ClassNotFoundException {
        List entities = new ArrayList();
        Field[] fields = clazz.getDeclaredFields();
        Field[] arrayOfField1 = fields;
        int j = fields.length;
        for (int i = 0; i < j; ++i) {
            Field field = arrayOfField1[i];
            Entity e = new Entity();
            boolean hasAnnotation = field.isAnnotationPresent(Cell.class);
            if (hasAnnotation) {
                Cell annotation = field.getAnnotation(Cell.class);
                e.setText(annotation.title());
                e.setType(field.getType().getName());
                e.setName(field.getName());
                entities.add(e);
            }
        }
        return entities;
    }

    private List<Entity> parseXML(Class<? extends Object> clazz) throws Exception {
        String classPath = clazz.getName();
        List list = new ArrayList();
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(ResourceUtil.getResourceInputStream(
                "src/src/main/resources/excel/Medicine.ete.xml"));
        NodeList nodeList = doc.getElementsByTagName("property");
        for (int i = 0; i < nodeList.getLength(); ++i) {
            Element element = (Element) nodeList.item(i);
            Entity e = new Entity();
            e.setName(element.getAttribute("name"));
            e.setText(element.getAttribute("text"));
            e.setType(element.getAttribute("type"));
            list.add(e);
        }
        return list;
    }

    private void createTitleRow(HSSFWorkbook workbook, HSSFRow row, List<Entity> entityList, HSSFSheet sheet) {
        HSSFCellStyle style = createHeadStyle(workbook);
        HSSFFont font = workbook.createFont();
        font.setColor((short) 12);
        font.setBoldweight((short) 700);
        style.setFont(font);
        style.setFillBackgroundColor((short) 13);
        int i = 0;
        for (Iterator localIterator = entityList.iterator(); localIterator.hasNext(); ) {
            Entity e = (Entity) localIterator.next();
            sheet.setColumnWidth(i, 3200);
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(e.getText());
            cell.setCellStyle(style);
            ++i;
        }
    }

    private HSSFCellStyle createCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment((short) 2);
        style.setVerticalAlignment((short) 1);
        style.setFillForegroundColor((short) 9);
        HSSFFont font = workbook.createFont();
        font.setColor((short) 8);
        font.setFontHeightInPoints((short) 12);
        style.setWrapText(true);
        return style;
    }

    private HSSFCellStyle createHeadStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment((short) 2);
        style.setVerticalAlignment((short) 1);
        style.setFillForegroundColor((short) 55);
        style.setFillPattern((short) 1);
        style.setBorderBottom((short) 1);
        style.setBorderLeft((short) 1);
        style.setBorderRight((short) 1);
        style.setBorderTop((short) 1);
        style.setWrapText(true);
        return style;
    }

    private void createTitileRow(HSSFWorkbook workbook, HSSFRow row, String[] titles, HSSFSheet sheet) {
        int length = titles.length;
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setColor((short) 12);
        font.setBoldweight((short) 14);
        style.setFont(font);
        style.setFillBackgroundColor((short) 13);
        for (int i = 0; i < length; ++i) {
            sheet.setColumnWidth(i, 2560);
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(style);
        }
    }
}
