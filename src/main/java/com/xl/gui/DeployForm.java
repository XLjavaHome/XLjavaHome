package com.xl.gui;

import com.xl.util.DateUtil;
import com.xl.util.FileUtil;
import com.xl.util.GUIUtil;
import com.xl.word.service.WordService;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * Created with 徐立.生成部署目录
 *
 * @author 徐立
 * @version 1.0 2019-04-12 17:17
 * To change this template use File | Settings | File Templates.
 * @date 2019-04-12
 * @time 17:17
 */
public class DeployForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JButton demandReleasePackage;
    private JButton BUG部署包Button;
    
    public DeployForm() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(demandReleasePackage);
        buttonCancel.addActionListener(e -> onCancel());
        demandReleasePackage.addActionListener(e -> {
            createFile(true);
        });
        BUG部署包Button.addActionListener(e -> {
            createFile(false);
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(e -> {
            onCancel();
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
    
    private void createFile(boolean flag) {
        String directoryName;
        if (flag) {
            directoryName = String.format(DateUtil.formatLocalDate() + "_%s_" + "徐立_wh", "任务名称");
        } else {
            directoryName = String.format(DateUtil.formatLocalDate() + "_%s_" + "徐立_wh", "BUG修复");
        }
        File publishPackageNameDirectory = FileUtil.createTempDirectoy(directoryName);
        //生成code.txt
        File code = new File(publishPackageNameDirectory, "code.txt");
        try {
            code.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //生成部署文档
        XWPFDocument doc = new XWPFDocument();
        WordService service = new WordService();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FileUtil.createTempFile("部署")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void onCancel() {
        dispose();
    }
    
    public static void main(String[] args) {
        DeployForm dialog = new DeployForm();
        dialog.pack();
        GUIUtil.makeCenter(dialog);
        dialog.setVisible(true);
    }
}
