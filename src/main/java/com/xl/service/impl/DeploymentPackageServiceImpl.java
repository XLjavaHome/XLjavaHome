package com.xl.service.impl;

import com.xl.service.DeploymentPackageService;
import com.xl.util.DateUtil;
import com.xl.util.FileUtil;
import com.xl.util.StringUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JTextArea;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

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
    public void createFile(boolean flag, JTextArea codeText, JTextArea textArea2) throws IOException {
        String directoryName;
        if (flag) {
            directoryName = String.format(DateUtil.formatLocalDate() + "_%s_" + "徐立_wh", "任务名称");
        } else {
            directoryName = String.format(DateUtil.formatLocalDate() + "_%s_" + "徐立_wh", "BUG修复");
        }
        File publishPackageNameDirectory = FileUtil.createTempDirectoy(directoryName);
        //生成code.txt
        String codeTextText = codeText.getText();
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
        try {
            XWPFParagraph docParagraph = doc.createParagraph();
            XWPFRun run = docParagraph.createRun();
            run.setText("部署说明");
            run.setFontSize(18);
            run.addBreak();
            XWPFRun run1 = docParagraph.createRun();
            run1.setText("1.更新code.txt");
            run1.addBreak();
            run1.setText("2.重启服务");
            doc.write(new BufferedOutputStream(new FileOutputStream(new File(publishPackageNameDirectory, "部署说明.docx"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileUtil.open(publishPackageNameDirectory);
    }
}
