package com.xl.gui;
/**
 * Created with IntelliJ IDEA.
 *
 * @author 徐立
 * @Date: 2018-05-18
 * @Time: 11:28
 * To change this template use File | Settings | File Templates.
 */

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

/**
 * Created with IntelliJ IDEA.进度条
 *
 * @author 徐立
 * @Date: 2018-05-18
 * @Time: 14:30
 * To change this template use File | Settings | File Templates.
 */
public class JProgressBarDemo extends JFrame {
    public JProgressBarDemo() {
        this.setTitle("进度条的使用");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 250, 100);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(contentPane);
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JProgressBar progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBar.setValue(i);
                }
                progressBar.setString("升级完成！");
            }
        }.start();
        contentPane.add(progressBar);
        this.setVisible(true);
    }


    public static void main(String[] args) {
        JProgressBarDemo example = new JProgressBarDemo();
    }
}
