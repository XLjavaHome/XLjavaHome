package com.xl.swing.jframe;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

//该类作为事件监听者，需要实现对应的接口
public class JTextFieldDemo extends JFrame implements ActionListener {
    private JLabel lb2;
    private JTextField t1, t2;

    public JTextFieldDemo() {
        this.setLayout(new FlowLayout()); //设置布局管理
        JLabel lb1 = new JLabel("请输入一个正整数：");
        lb2 = new JLabel("1到该数的和为：");// 创建标签对象字符串为提示信息
        t1 = new JTextField(10);// 创建输入文本框，最多显示10个字符
        t2 = new JTextField(10);
        this.add(lb1); // 将组件添加到窗口上
        this.add(t1);
        this.add(lb2);
        this.add(t2);
        t1.addActionListener(this);// 为文本框注册ActionEvent事件监听器
        // 为窗口注册窗口事件监听程序，监听器以匿名类的形式进行
        this.addWindowListener(new WindowAdapter() {// 匿名类开始
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            } // 窗口关闭
        });// 匿名类结束
        this.setTitle("图书管理系统JTextField示例");//设置窗体标题
        this.setSize(600, 450);//设置窗口大小
        this.setVisible(true);//设置窗体的可见性
    }

    public static void main(String[] arg) {
        new JTextFieldDemo();
    }

    @Override
    public void actionPerformed(ActionEvent e) { // ActionListener接口中方法的实现
        // getText()获取文本框输入的内容，转换为整型数值
        int n = Integer.parseInt(t1.getText());
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum = sum + i;
        }
        t2.setText(String.valueOf(sum)); // 修改文本框输出内容
    }
}