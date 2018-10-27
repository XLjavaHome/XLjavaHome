package com.xl.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatGUI extends JFrame implements ActionListener {
    private final Container container;
    private final JButton send; // 发送按钮
    private final JTextArea chatContent; // 聊天内容区
    private final JTextField sentence; // 聊天信息栏

    private ChatGUI() {
        setLocation(300, 200);
        super.setTitle("聊天窗口");// 设置窗口标题
        this.setSize(400, 300); // 设置窗口大小
        container = this.getContentPane(); // 获得主窗口的内容面板
        container.setLayout(new BorderLayout()); // 设置布局方式为BorderLayout
        JScrollPane centerPanel = new JScrollPane(); // 创建用于存放聊天记录的滚动面板
        chatContent = new JTextArea(); // 创建存放聊天内容的多行文本输入区对象
        centerPanel.setViewportView(chatContent); // 将文本区放入滚动面板
        // 将滚动面板放到主窗口内容面板中央
        container.add(centerPanel, BorderLayout.CENTER);
        chatContent.setEditable(false); // 将文本区置为不可编辑状态
        // 创建底部面板对象，存放聊天标签、聊天栏、发送按钮
        JPanel bottomPanel = new JPanel();
        sentence = new JTextField(20); // 创建文本输入行对象，存放每次的聊天内容
        send = new JButton("发送"); // 创建按钮对象
        bottomPanel.add(new JLabel("聊天信息")); // 将标签添加到底部面板
        bottomPanel.add(sentence); // 把聊天内容栏添加到底部面板上
        bottomPanel.add(send); // 把发送按钮添加到底部面板上
        container.add(bottomPanel, BorderLayout.SOUTH);// 将底部面板放在主窗口的底部
        send.addActionListener(this); // 注册发送按钮的动作事件
        sentence.addActionListener(this); // 注册聊天栏的动作事件
        this.setVisible(true); // 显示主窗口
        EventQueue.invokeLater(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println(i);
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭窗口时退出系统
    }

    public static void main(String[] arg) {
        new ChatGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = sentence.getText(); // 获取聊天栏里的聊天信息
        if (str != null && !"".equals(str)) // 如果聊天内容不为空，则发送信息
        {
            chatContent.append("本人:  " + str + "\n");// 将本人发送的聊天信息追加到聊天内容区
        } else {
            chatContent.append("聊天信息不能为空\n");
        }
        sentence.setText(""); // 清空聊天栏里的内容
    }
}