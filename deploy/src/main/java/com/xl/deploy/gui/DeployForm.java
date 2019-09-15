package com.xl.deploy.gui;

import com.xl.deloy.service.DeploymentPackageService;
import com.xl.deploy.entity.DeploymentEntity;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Properties;
import javax.swing.*;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created with 徐立.生成部署目录
 *
 * @author 徐立
 * @version 1.0 2019-04-12 17:17
 * To change this template use File | Settings | File Templates.
 * @date 2019-04-12
 * @time 17:17
 */
@Log4j
@ComponentScan(basePackages = {"com.xl.deploy"})
public class DeployForm extends JDialog {
    private static final String AUTHOR = "author";
    private static volatile String author = "徐立";
    private static String propertyFileName = "deploy.properties";
    static {
        File propertFile = new File(propertyFileName);
        if (propertFile.exists()) {
            try (FileInputStream is = new FileInputStream(propertFile)) {
                Properties properties = new Properties();
                properties.load(is);
                author = properties.getProperty(AUTHOR);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private JPanel contentPane;
    private JButton buttonCancel;
    private JButton demandReleasePackageBtn;
    private JButton BUGDelpoyButton;
    private JTextArea code;
    private JTextArea textArea2;
    private JScrollPane codePanel;
    private JScrollPane docPane;
    private JTextField taskNameTx;
    private JTextField authorField;
    @Autowired
    private DeploymentPackageService service;
    private boolean writeAuthor = false;
    
    public DeployForm() {
        setTitle("有建议发送给徐立78645574@qq.com");
        //设置作者
        authorField.setText(author);
        setContentPane(contentPane);
        setModal(true);
        //不能改变大小
        this.setResizable(false);
        getRootPane().setDefaultButton(demandReleasePackageBtn);
        buttonCancel.addActionListener(e -> onCancel());
        addBtnEvent(demandReleasePackageBtn, true);
        addBtnEvent(BUGDelpoyButton, false);
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
    
    /**
     * 退出调用的方法
     */
    private void onCancel() {
        if (writeAuthor) {
            File propertFile = new File(propertyFileName);
            try {
                Properties prop = new Properties();
                prop.setProperty(AUTHOR, authorField.getText());
                FileOutputStream fileOutputStream = new FileOutputStream(propertFile, false);//true表示追加打开
                prop.store(fileOutputStream, "作者");
            } catch (FileNotFoundException e) {
                log.error(e);
            } catch (IOException e) {
            }
        }
        dispose();
    }
    
    /**
     * 按钮添加实际
     *
     * @param button
     * @param b true：任务，false：BUG
     */
    private void addBtnEvent(JButton button, boolean b) {
        button.addActionListener(e -> {
            try {
                button.setEnabled(false);
                String authorText = authorField.getText();
                if (!StringUtils.equals(author, authorText)) {
                    writeAuthor = true;
                }
                //看作者是否变更，如果作者变更的话则保持
                DeploymentEntity entity = new DeploymentEntity(authorText, b, code.getText(), textArea2.getText(),
                                                               taskNameTx.getText());
                service.createFile(entity);
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                button.setEnabled(true);
            }
        });
    }
}
