package com.xl.service;

import com.xl.entity.ColumnInfo;
import com.xl.entity.TableInfo;
import com.xl.util.FileUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.*;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.springframework.stereotype.Component;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-09-20
 * @time 23:30
 * To change this template use File | Settings | File Templates.
 */
@Component
public class FileServiceImpl implements FileService {
    @Override
    public void generateWordDocument(Collection<TableInfo> columnInfoList) {
        //    生成数据库字典
        XWPFDocument doc = new XWPFDocument();
        for (TableInfo columnInfo : columnInfoList) {//表名  说明
            XWPFParagraph docParagraph = doc.createParagraph();
            XWPFRun xwpfRun = docParagraph.createRun();
            xwpfRun.setText(MessageFormat.format("{0}  ", columnInfo.getTable_name()));
            List<ColumnInfo> columnInfos = columnInfo.getColumnInfos();
            XWPFTable wordTable = doc.createTable(columnInfos.size(), 3);
            for (int i = 0; i < columnInfos.size(); i++) {
                ColumnInfo info = columnInfos.get(i);
                XWPFTableRow row = wordTable.getRow(i);
                row.setHeight(30);
                setText(row, 0, info.getColumn_name());
                setText(row, 1, info.getColumn_type());
                setText(row, 2, info.getColumn_comment());
            }
            doc.createParagraph();
        }
        File file = new File(MessageFormat.format("数据库字典{0}.doc", RandomUtils.nextInt(0, 1000)));
        try {
            doc.write(new BufferedOutputStream(new FileOutputStream(file)));
            doc.close();
            FileUtil.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void setText(XWPFTableRow row, int pos, String column_name) {
        XWPFTableCell cell = row.getCell(pos);
        cell.setText(column_name);
        CTTc cttc = cell.getCTTc();
        CTTcPr cellPr = cttc.addNewTcPr();
        cellPr.addNewTcW().setW(new BigInteger("3000"));
    }
    
    @Override
    public Map<String, TableInfo> clearData(List<Map<String, Object>> stringObjectMap) {
        Map<String, TableInfo> tableInfoListMap = new TreeMap<>();
        for (Map<String, Object> map : stringObjectMap) {
            //1.获取数据库中的字段
            String table_name = MapUtils.getString(map, "table_name");
            String table_comment = MapUtils.getString(map, "table_comment");
            String column_name = MapUtils.getString(map, "column_name");
            String column_type = MapUtils.getString(map, "column_type");
            String column_comment = MapUtils.getString(map, "column_comment");
            //2。封装实体
            TableInfo tableInfo = tableInfoListMap.get(table_name);
            if (tableInfo == null) {
                tableInfo = new TableInfo(table_name, table_comment);
            }
            List<ColumnInfo> infoColumnInfos = tableInfo.getColumnInfos();
            if (infoColumnInfos == null) {
                infoColumnInfos = new ArrayList<>(50);
                tableInfo.setColumnInfos(infoColumnInfos);
            }
            ColumnInfo infoColumnInfo = new ColumnInfo(column_name, column_type, column_comment);
            infoColumnInfos.add(infoColumnInfo);
            tableInfoListMap.put(table_name, tableInfo);
        }
        return tableInfoListMap;
    }
}
