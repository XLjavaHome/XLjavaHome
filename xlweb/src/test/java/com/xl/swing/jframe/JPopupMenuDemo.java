package com.xl.swing.jframe;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class JPopupMenuDemo extends JFrame {
    JPopupMenu popMenu = new JPopupMenu();

    public JPopupMenuDemo() {
        Container container = this.getContentPane();
        this.addMouseListener(new mouseLis());
        /* 创建4个菜单项，并添加到弹出式菜单上 */
        JMenuItem save = new JMenuItem("Save");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem exit = new JMenuItem("Exit");
        popMenu.add(save);
        popMenu.add(cut);
        popMenu.add(copy);
        popMenu.addSeparator(); // 添加一个分隔线
        popMenu.add(exit);
        this.show(true);
        this.setSize(600, 450);
    }

    public static void main(String[] args) {
        new JPopupMenuDemo();
    }

    class mouseLis extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) // 判断是否点击的是鼠标右键
            {
                popMenu.show(e.getComponent(), e.getX(), e.getY()); // 在当前位置显示
            }
        }
    }
}