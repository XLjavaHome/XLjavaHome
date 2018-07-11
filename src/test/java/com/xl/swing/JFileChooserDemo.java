package com.xl.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class JFileChooserDemo extends JFrame implements ActionListener {
    JFileChooser fc = new JFileChooser(); // 创建文件对话框对象
    JButton open, save;

    public JFileChooserDemo() {
        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());
        this.setTitle("文件对话框演示程序");
        open = new JButton("打开文件"); // 定义命令按钮
        save = new JButton("保存文件");
        open.addActionListener(this);// 为事件注册
        save.addActionListener(this);
        container.add(open); // 添加到内容窗格上
        container.add(save);
        this.show(true);
        this.setVisible(true);
        this.setSize(600, 450);
    }
    public static void main(String args[]) {
        JFileChooserDemo fcd = new JFileChooserDemo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource(); // 得到事件源
        if (button == open) {// 选择的是“打开”按钮
            int select = fc.showOpenDialog(this); // 显示打开文件对话框
            if (select == JFileChooser.APPROVE_OPTION) { // 选择的是否为确认
                File file = fc.getSelectedFile(); // 根据选择创建文件对象
                // 在屏幕上显示打开文件的文件名
                System.out.println("文件" + file.getName() + "被打开");
            } else {
                System.out.println("打开操作被取消");
            }
        }
        if (button == save) {//选择的是“保存”按钮
            int select = fc.showSaveDialog(this); //显示“保存”文件对话框
            if (select == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                System.out.println("文件" + file.getName() + "被保存");
            } else {
                System.out.println("保存操作被取消");
            }
        }
    }
}