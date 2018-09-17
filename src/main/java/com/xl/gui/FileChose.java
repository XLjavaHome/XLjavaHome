package com.xl.gui;

import com.xl.util.Constant;
import com.xl.util.FileUtil;
import com.xl.util.GUIUtil;
import com.xl.util.StringUtil;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import lombok.extern.log4j.Log4j;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-05-17
 * @Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class FileChose {
    private JButton fileBtn;
    private JTextField textField1;
    private JButton okBtn;
    private JPanel jPanel1;
    private JProgressBar progressBar;
    private JTextArea errorMsgTextArea;
    private JPanel errorMsgJPanel;
    private JFileChooser fc;

    public FileChose() throws HeadlessException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        init();
    }

    private void init() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        jPanel1.setSize(600, 600);
        //文件目录选择器
        fc = new JFileChooser();
        //设置进度条上是否显示进度具体进度如50%
        progressBar.setStringPainted(true);
        hidden();
        addActionEvent();
    }

    private void hidden() {
        progressBar.setVisible(false);
        errorMsgTextArea.setVisible(false);
        errorMsgJPanel.setVisible(false);
    }

    private void addActionEvent() {
        textField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                log.info(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                log.info(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                log.info(e);
            }
        });
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        fileBtn.addActionListener(e -> {
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            // 显示打开文件对话框
            int select = fc.showOpenDialog(jPanel1);
            if (select == JFileChooser.APPROVE_OPTION) { // 选择的是否为确认
                File directory = fc.getSelectedFile();
                textField1.setText(directory.getAbsolutePath());
            }
        });
        okBtn.addActionListener(e -> {
            progressBar.setString(null);
            String filePath = textField1.getText();
            if (StringUtil.isNotEmpty(filePath)) {
                List<String> errorMsg = new ArrayList<String>();
                progressBar.setVisible(true);
                progressBar.setStringPainted(true);
                new Thread(() -> {
                    //不包含该目录下的文件
                    List<File> files = FileUtil.queryAllNotDirectoryLowFile(filePath);
                    //需要-1才可以
                    progressBar.setMaximum(files.size());
                    for (int i = 0; i < files.size(); i++) {
                        File file = files.get(i);
                        File target = new File(filePath, file.getName());
                        progressBar.setValue(i + 1);
                        if (!target.exists()) {
                            try {
                                FileUtil.copyFile(file, target);
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            String message = "文件" + target.getAbsolutePath() + "已经存在";
                            errorMsg.add(message);
                            log.error(message);
                        }
                    }
                    progressBar.setString("复制完成！");
                    if (errorMsg.size() > 0) {
                        errorMsgTextArea.setText(StringUtil.join(errorMsg, Constant.NEWLINE));
                        errorMsgTextArea.setVisible(true);
                    } else {
                        errorMsgTextArea.setVisible(false);
                        errorMsgTextArea.setText(null);
                    }
                    //JOptionPane.showMessageDialog(null, "复制成功！", "信息", JOptionPane.WARNING_MESSAGE);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            hidden();
                        }
                    }, 5000L);
                    try {
                        FileUtil.open(new File(filePath));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }).start();
            }
        });
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        Font font = new Font("SimSun", Font.PLAIN, 12);
        UIManager.put("OptionPane.font", font);
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.buttonFont", font);
        JFrame frame = new JFrame("将一个目录下的所有文件复制到这个目录下");
        frame.setContentPane(new FileChose().jPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(250, 250);
        GUIUtil.makeCenter(frame);
        frame.setVisible(true);
    }
}
