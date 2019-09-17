package com.xl.deploy.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

/*
 菜单面可以添加菜单条又可以添加菜单，Menu extends MenuItem,Menu add(MenuItem)
 */
public class MyMenuDemo {
    private Frame f;
    private Menu FileMenu; // 文件菜单
    private MenuBar mb, bar; // 菜单条
    private MenuItem openItem, saveItem, closeItem; // 打开，保存，关闭条目
    private FileDialog openDia, saveDia; // 设置对话框用与打开文件，保存文件
    private TextArea ta; // 添加文本区域用于打开选择的文件
    private File file;
    
    MyMenuDemo() {
        init();
    }
    
    public void init() {
        f = new Frame("myWindow");
        f.setBounds(300, 100, 600, 600);
        // f.setLayout(new FlowLayout()); // 流式布局,添加文本框的话很难看，用默认的
        // Print.print(f.getExtendeState()); //返回int类型获取此窗体的状态。0
        bar = new MenuBar();// 初始化菜单条
        // m = new Menu("文件");
        ta = new TextArea();
        FileMenu = new Menu("文件"); // 初始化菜单
        openItem = new MenuItem("打开");
        saveItem = new MenuItem("保存");
        closeItem = new MenuItem("退出");
        FileMenu.add(openItem);
        FileMenu.add(saveItem);
        FileMenu.add(closeItem);
        bar.add(FileMenu); // 将菜单条与菜单关联
        f.setMenuBar(bar);// 将菜单条添加进框架
        openDia = new FileDialog(f, "我要打开", FileDialog.LOAD); // 后面的模式不写也可以，默认的就是打开
        saveDia = new FileDialog(f, "我要保存", FileDialog.SAVE); // 保存
        f.add(ta);
        myEvent();
        f.setVisible(true);
        // 自己测试
        // Print.print(closeItem.isEnabled());// 是否启用了菜单项
        // Print.print(closeItem.paramString()); // 返回此状态的字符串
    }
    
    private void myEvent() {
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (file == null) {
                    saveDia.setVisible(true); // 显示保存,可以不显示，当文件不存在时显示
                    String dirPath = saveDia.getDirectory();// 获取存储的路径
                    String fileName = saveDia.getFile();// 获取存储的文件名
                    if (dirPath == null || fileName == null) {
                        return;
                    }
                    file = new File(dirPath, fileName);
                }
                try {
                    BufferedWriter bufw = new BufferedWriter(new FileWriter(file));
                    String text = ta.getText(); //将文本区域的文本传进text
                    bufw.write(text);
                    //	bufw.flush();
                    bufw.close();
                } catch (IOException ex) {
                    throw new RuntimeException("写入失败！");
                }
            }
        });
        openItem.addActionListener(new ActionListener() // 点击打开的事件
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDia.setVisible(true);
                String dirPath = openDia.getDirectory();// 获得打开文件的路径
                String fileName = openDia.getFile();
                System.out.println(dirPath + "  " + fileName);
                // 如果没选择文件就会出空指针异常，目录有，文件没有。所以得判断
                if (dirPath == null || fileName == null) {
                    return;
                }
                ta.setText(""); // 每次打开都清空之前的文本
                file = new File(dirPath, fileName);
                try {
                    BufferedReader bufr = new BufferedReader(new FileReader(file));
                    String line = null;
                    while ((line = bufr.readLine()) != null) {
                        ta.append(line + "\r\n"); // 将读取的文件添加到文本中
                    }
                    bufr.close();
                } catch (IOException ew) {
                    throw new RuntimeException("读取失败");
                }
            }
        });
        closeItem.addActionListener(new ActionListener() // 点击菜单退出触发的事件
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    
    public static void main(String[] args) {
        new MyMenuDemo();
    }
}
