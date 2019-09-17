package com.xl.deploy.gui;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AwtDemo {
    public static void main(String[] args) {
        // 无法关闭的原因：没有添加效果，事件监听机制
        Frame f = new Frame("标题栏"); // 构造一个最初不可见的 Frame
        // 新实例,new完后具备默认的管理器，成为边界式管理器
        f.setTitle("Title");
        f.setSize(500, 400); // 500是横坐标，100走的纵坐标 空值大小
        f.setLocation(300, 200); // 因为总是出现在左上角，现在调正。
        // 给按钮设置窗体
        f.setLayout(new FlowLayout());
        // 添加按钮
        Button b = new Button("我是一个按钮");
        // 添加按钮
        f.add(b); // 没指定就居中填充，整个就是一个按钮
        //给按钮添加时间,可以不分代码顺序前后
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 1;
                System.out.println(i);
            }
        });
        // 匿名内部类
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) // 打开时显示的
            {
                System.out.println("我被打开了");
            }
            
            @Override
            public void windowClosing(WindowEvent e) // 关的动作
            {
                System.out.println("我关");
                System.exit(0);
            }
            
            @Override
            public void windowActivated(WindowEvent e) // 前置时触发此行为
            {
                System.out.println("active");
            }
        });
        f.setVisible(true); // public void setVisible(boolean b)如为 true，则使Window 可见，否则隐藏 Window。
        // 主线程没关闭，只要开启图形化界面，就会多一个线程。
    }
}

/*
 * class MyWin implements WindowListener { //覆盖7个方法，可我只用到关闭的动作
 * //其他动作都没有用到，可是缺必须复写 }
 */

