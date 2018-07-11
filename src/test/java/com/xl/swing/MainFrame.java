package com.xl.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    Container container;

    public MainFrame() {
        this.setTitle("欢迎使用图书管理系统 ");// 设置标题
        container = this.getContentPane(); // 获取默认的内容窗格
        container.setLayout(new BorderLayout()); // 设置布局格式
        JMenuBar menuBar = new JMenuBar(); // 创建菜单栏
        buildMainMenu(menuBar); // 最定义组建菜单的方法
        this.setJMenuBar(menuBar); // 把菜单栏挂到该窗口上
        this.show(true); // 显示窗口
        this.setSize(600, 450); // 设置窗口大小
    }

    /* 构建菜单，由于篇幅有限，创建三个菜单文件、图书信息查询和帮助菜单 */
    protected void buildMainMenu(JMenuBar menuBar) {
        JMenu fileMenu = new JMenu("文件(F)"); // 文件菜单的创建
        JMenuItem exitMenuItem = new JMenuItem("退出");
        /* 为事件注册，对ActionEvent事件作出处理 */
        exitMenuItem.addActionListener(new ExitActionListener());
        fileMenu.add(exitMenuItem); // 把退出菜单项添加到菜单上
        menuBar.add(fileMenu);// 文件菜单添加到菜单栏上
        /* 建立图书信息查询菜单* */
        JMenu libMenu = new JMenu("馆藏检索(B)");
        libMenu.setMnemonic(KeyEvent.VK_B);// 给图书检索菜单定义助记键
        JMenuItem libMenuItem = new JMenuItem("书目检索");
        JMenuItem myBorrowMenuItem = new JMenuItem("我的借阅");
        libMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));// 设定快捷键
        // 为事件注册，BookInLibraryActionListener为内部类
        libMenuItem.addActionListener(new BookInLibraryActionListener());
        myBorrowMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));// 设定快捷键
        myBorrowMenuItem.addActionListener(new MyBorrowActionListener());
        libMenu.add(libMenuItem); // 把菜单项添加到查询菜单上
        libMenu.add(myBorrowMenuItem);
        menuBar.add(libMenu); // 查询菜单添加到菜单栏上
        /* 帮助菜单的创建 */
        JMenu helpMenu = new JMenu("帮助(H)");
        helpMenu.setMnemonic(KeyEvent.VK_H);//给帮助菜单定义助记键
        JMenuItem aboutMenuItem = new JMenuItem("关于");
        aboutMenuItem.setMnemonic(KeyEvent.VK_A);
        aboutMenuItem.addActionListener(new AboutActionListener());// 为事件注册
        helpMenu.add(aboutMenuItem); // 把关于菜单项添加到帮助菜单上
        menuBar.add(helpMenu); // 帮助菜单添加到菜单栏上
    }

    public static void main(String[] args) {
        new MainFrame();
    }

    /* libMenuItem事件源的事件监听器，打开例8-16添加Jlist后的窗口 */
    class BookInLibraryActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            new JComboBoxDemo();
        }
    }

    /* 我的借阅的事件监听器，打开例8-16添加JTable后的窗口 */
    class MyBorrowActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            new JRadioButtonDemo();
        }
    }

    /* 帮助菜单中关于菜单项的事件监听者 */
    class AboutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            /* 暂时为空后加对话框 */
        }
    }

    /* 文件菜单中退出菜单项的事件监听者 */
    class ExitActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            dispose();
            System.exit(0);
        }
    }
}