package com.xl.deploy.gui;

/*
 练习-列出指定目录内容

 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class MyWindow {
    private Frame f; // 可以直接继承Frame,就可以直接调用里面的方法了
    private TextField tf; // 文本框
    private Button but; // 按钮
    private TextArea ta; // 是显示文本的多行区域
    private Dialog d; // Dialog有两个，一个文本提示，一个确定按钮
    private Label lab;
    private Button okBut;
    
    MyWindow() {
        init();
    }
    
    public void init() // 起使
    {
        f = new Frame("显示指定路径下的文件");
        f.setBounds(300, 100, 600, 500); // 出现该Frame距离屏幕左边的距离，距离上边的距离。文本横坐标长度，纵坐标长度
        f.setLayout(new FlowLayout()); // 设置布局管理，流布局，默认是边界布局，如果不指认东南西北就全填充
        // tf = new TextField(30); //文本长度
        // 觉得上面短了
        tf = new TextField(60);
        but = new Button("转到"); // 按钮的名称
        // ta = new TextArea(15,40); //文本区域
        ta = new TextArea(25, 70);
        d = new Dialog(f, "提示信息-self", true); // 模式true
        // 不能添加到Frame窗体中,如果为false，可以绕过对话框，继续操作窗体
        d.setBounds(400, 200, 200, 150); // 对话框的位置
        d.setLayout(new FlowLayout());
        lab = new Label(); // 初始化还不知道啥信息
        okBut = new Button("确定"); // 添加确定按钮
        d.add(lab);
        d.add(okBut);
        f.add(tf);
        f.add(but);
        f.add(ta);
        myEvent();// 事件
        f.setVisible(true); // 显示出来
    }
    
    private void myEvent() {
        okBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d.setVisible(false);
            }
        });
        d.addWindowListener(new WindowAdapter() // 点击关闭触发的事件
        {
            @Override
            public void windowClosing(WindowEvent e) // 设置关闭事件
            {
                d.setVisible(false); // 关×不能关掉，就把对话框取消
            }
        });
        f.addWindowListener(new WindowAdapter() // 点击关闭触发的事件
        {
            @Override
            public void windowClosing(WindowEvent e) // 设置关闭事件
            {
                System.exit(0);
            }
        });
        // 确定事件源是文本框
        but.addActionListener(new ActionListener() // 点击转到按钮触发的事件
        {
            // 获取文本区域
            @Override
            public void actionPerformed(ActionEvent e) {
                // 输入目录
                showDir();
            }
        });
        // 不想点击按钮，按回车就行 tf文本框
        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    showDir();
                }
            }
        });
        /*
         * //演示 String text = tf.getText(); tf.setText(""); //将上面清空。 //设置文本区域的内容
         * ta.setText(text); //将字符串在文本区域中显示 // System.out.print(text);
         */
        // 封装一下功能
    }
    
    private void showDir() {
        String dirPath = tf.getText();
        File dir = new File(dirPath); // 封装成对象
        if (dir.exists() && dir.isDirectory()) // 如果是目录并且存在
        {
            ta.setText(""); // 把以前的清空
            String[] names = dir.list();
            for (String name : names) {
                // ta.setText(name+"\r\n"); //输出，但是这样会覆盖！
                ta.append(name + "\r\n"); // 追加文本
            }
        } else {
            // 窗口提示的错误信息内容
            String info = "您输入的信息：" + dirPath + "是错误的。请重新输入";
            lab.setText(info); // 讲信息传入到文本框
            d.setVisible(true); // 让对话框显示
        }
    }
    
    public static void main(String[] args) {
        new MyWindow();
    }
}
