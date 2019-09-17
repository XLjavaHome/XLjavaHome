package com.xl.deploy.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Created by 立 on 7/25 0025.
 */
class SwingDemo extends JApplet {
    @Override
    public void init() {
        Container contentPane = getContentPane();
         /*原文
    Icon icon = new ImageIcon("swing.gif","An animated GIF of Duke on a swing");
    JLabel label = new JLabel("Swing!",icon,SwingConstants.CENTER);
    */
        //修改后
        JLabel label = new JLabel("Swing!",
                                  new ImageIcon(getImage(getCodeBase(), "swing.gif"), "An animated GIF of Duke on a swing"),
                                  SwingConstants.CENTER);
        contentPane.add(label, BorderLayout.CENTER);
    }
}
