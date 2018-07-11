package com.xl.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

//该类作为事件监听者，需要实现对应的ItemListener接口
public class JCheckBoxDemo extends JFrame implements ItemListener {
    int red = 0, green = 0, blue = 0; // 三种颜色分量的值
    private JLabel lb1; // 作为调色板，根据所选颜色不同它的颜色随之变化
    private JCheckBox ckb1, ckb2, ckb3; // 代表红、绿、蓝三色是否选中的复选框
    private Container container;

    public JCheckBoxDemo() {
        container = this.getContentPane();
        ckb1 = new JCheckBox("红色"); // 创建复选框对象，字符串为提示信息
        ckb2 = new JCheckBox("绿色");
        ckb3 = new JCheckBox("蓝色");
        lb1 = new JLabel(); // 创建空标签作为调色板
        lb1.setMaximumSize(new Dimension(150, 200)); // 设置尺寸
        container.add(lb1, BorderLayout.CENTER); // 把组件添加到窗口上
        JPanel p1 = new JPanel();
        p1.add(ckb1);
        p1.add(ckb2);
        p1.add(ckb3);
        // 为组件注册事件监听程序，对ItemEvent事件进行处理
        ckb1.addItemListener(this);
        ckb2.addItemListener(this);
        ckb3.addItemListener(this);
        if (1 == 1) {
            System.out.println("111");
        }
        container.add(p1, BorderLayout.SOUTH);
        // 为窗口注册窗口事件监听程序，监听器以匿名类的形式进行
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭窗口时退出系统
        this.setTitle("图书管理系统JCheckBox示例");
        this.setSize(600, 450);
        this.setVisible(true);
    }

    public static void main(String[] arg) {
        new JCheckBoxDemo();
    }

    // ItemListener接口中方法的实现按钮的状态发生改变时该方法将会被调用
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getItem() == ckb1) // 判断事件源
        {
            if (e.getStateChange() == ItemEvent.SELECTED) // 判断组件到底有没有被选中
            {
                red = 255;
            } else {
                red = 0;
            }
        }
        if (e.getItem() == ckb2) {
            if (ckb2.isSelected()) //判断组件是否选中，选中为true否则为false
            {
                green = 255;
            } else {
                green = 0;
            }
        }
        if (e.getSource() == ckb3) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                blue = 255;
            } else {
                blue = 0;
            }
        }
        lb1.setOpaque(true);// 设置标签为不透明，使标签的颜色显示出来
        lb1.setBackground(new Color(red, green, blue));// 设置标签的背景颜色
    }
}