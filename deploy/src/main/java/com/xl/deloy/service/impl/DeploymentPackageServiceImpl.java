package com.xl.deloy.service.impl;

import com.entity.DeploymentEntity;
import com.xl.deploy.service.DeploymentPackageService;
import com.xl.util.DateUtil;
import com.xl.util.FileUtil;
import com.xl.util.StringUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jetbrains.annotations.NotNull;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-04-13
 * @time 15:51
 * To change this template use File | Settings | File Templates.
 */
public class DeploymentPackageServiceImpl implements DeploymentPackageService {
    @Override
    public void createFile(DeploymentEntity entity) throws IOException {
        String author = entity.getAuthor();
        String codeTextText = entity.getCodeString();
        String directoryName;
        if (entity.isFlag()) {
            directoryName = String.format(DateUtil.formatLocalDate() + "_%s_" + author + "_wh", entity.getTaskName());
        } else {
            directoryName = String.format(DateUtil.formatLocalDate() + "_%s_" + author + "_wh", "BUG修复");
        }
        File publishPackageNameDirectory = FileUtil.createTempDirectoy(directoryName);
        //生成code.txt
        if (StringUtil.isNotEmpty(codeTextText)) {
            File code = new File(publishPackageNameDirectory, "code.txt");
            try {
                FileUtil.write(code, codeTextText);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //生成部署文档
        XWPFDocument doc = new XWPFDocument();
        XWPFParagraph docParagraph = doc.createParagraph();
        XWPFRun xwpfRun = docParagraph.createRun();
        if (entity.isFlag()) {
            //    任务名称
            xwpfRun.setText("[任务名称]");
        } else {
            xwpfRun.setText("[BUG修复]");
        }
        addTitleStyle(xwpfRun);
        xwpfRun.addBreak();
        xwpfRun = addDocContent(docParagraph, entity.getTaskName());
        xwpfRun.addBreak();
        xwpfRun.addBreak();
        XWPFRun run = docParagraph.createRun();
        addTitleStyle(run);
        run.setText("[部署说明]");
        run.addBreak();
        XWPFRun run1 = docParagraph.createRun();
        run1.setFontSize(14);
        String docString = entity.getDocString();
        if (StringUtil.isNotEmpty(docString)) {
            String[] split = docString.split("\n");
            for (String s : split) {
                addDocContent(docParagraph, s);
            }
        } else {
            XWPFRun xwpfRun1 = addDocContent(docParagraph, "1.更新code.txt。");
            xwpfRun1.addBreak();
            addDocContent(docParagraph, "2.重启服务。");
        }
        doc.write(new BufferedOutputStream(new FileOutputStream(new File(publishPackageNameDirectory, "部署说明.docx"))));
        doc.close();
        FileUtil.open(publishPackageNameDirectory);
    }
    
    /**
     * 添加内容
     *
     * @param docParagraph
     * @param content
     * @return
     */
    @NotNull
    public XWPFRun addDocContent(XWPFParagraph docParagraph, String content) {
        XWPFRun xwpfRun = docParagraph.createRun();
        xwpfRun.setText(content);
        xwpfRun.setFontSize(14);
        return xwpfRun;
    }
    
    public void addTitleStyle(XWPFRun xwpfRun) {
        xwpfRun.setFontFamily("宋体");
        xwpfRun.setFontSize(22);
        xwpfRun.setBold(true);
    }
}
