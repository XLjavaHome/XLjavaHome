package com.xl.excel.word;

import com.xl.base.IdWorker;
import com.xl.entity.MetadbWordEntity;
import com.xl.util.FileUtil;
import com.xl.word.service.WordService;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

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
        //生成数据
        WordService wordService = new WordService();
        List<MetadbWordEntity> metadbWordEntities = wordService.initTableData();
        XWPFDocument doc = wordService.initWord(metadbWordEntities);
        doc.write(stream);
    }
    
    
}
