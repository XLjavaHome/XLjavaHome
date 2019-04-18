package com.xl.swing.jframe;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JComboBoxDemo extends JFrame {
    protected JLabel selectionLabel;
    protected JComboBox fieldComboBox;
    protected JPanel topPanel;
    protected JButton retrievalButton;
    protected JTextField keywordText;
    protected String fieldSelected;
    protected JList bookListBox; //显示检索到的图书信息
    protected JButton detailsButton;//查看某本图书更详细信息的按钮
    protected JScrollPane bookScrollPane; // 把JList放入JScrollPane出现滚动菜单效果
    protected JPanel bottomPanel;//中间容器
    private Container container;

    public JComboBoxDemo() {
        container = this.getContentPane();
        selectionLabel = new JLabel("检索方式"); // 标签
        fieldComboBox = new JComboBox(); // 分类检索下拉列表
        fieldComboBox.addItem("请选择...");
        fieldComboBox.addItem("书名");
        fieldComboBox.addItem("ISBN号");
        fieldComboBox.addItem("作者");
        fieldComboBox.addItem("出版");
        //注册事件监听者FieldSelectedListener为内部类
        fieldComboBox.addItemListener(new FieldSelectedListener());
        keywordText = new JTextField("java", 20); // 显示检索关键字的文本框
        retrievalButton = new JButton("检索"); //提交命令按钮
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        keywordText.setSize(topPanel.getWidth() / 2, topPanel.getWidth());
        topPanel.add(selectionLabel);
        topPanel.add(fieldComboBox);
        topPanel.add(keywordText);
        topPanel.add(retrievalButton);
        //添加后面的代码JList显示检索内容
        this.add(BorderLayout.NORTH, topPanel);
        this.setTitle("图书管理系统图书查询");
        this.setSize(600, 450);
        this.setVisible(true);
        //为检索按钮添加事件监听者
        retrievalButton.addActionListener(new RetrievalActionListener());
        bookListBox = new JList();
        //设置列表一次只能选择一个，不允许多选
        bookListBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //为列表注册事件监听者，对用户选择改变进行处理
        bookListBox.addListSelectionListener(new BookSelectionListener());
        bookScrollPane = new JScrollPane(bookListBox);
        this.add(BorderLayout.CENTER, bookScrollPane);
        //添加显示某一本书详情的命令按钮
        detailsButton = new JButton("详细...");
        //detailsButton.addActionListener(new DetailsActionListener());
        detailsButton.setEnabled(false); //初始不可用，选择书后变为可用
        bottomPanel = new JPanel();
        bottomPanel.add(detailsButton);
        this.add(BorderLayout.SOUTH, bottomPanel);
    }

    public static void main(String[] arg) {
        new JComboBoxDemo();
    }

    class FieldSelectedListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                fieldSelected = (String) fieldComboBox.getSelectedItem();
            }
        }
    }

    class RetrievalActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            //此处实现数据库数据的检索，这里用特定信息代替相见13章
            Object data[] = {"Java耿祥义清华大学", "面向对象Java 张白一 西安电子"};
            bookListBox.setListData(data);
        }
    }

    class BookSelectionListener implements ListSelectionListener {
        //当列表的选择发生变化时，执行该方法里的内容
        @Override
        public void valueChanged(ListSelectionEvent event) {
            if (bookListBox.isSelectionEmpty()) { //判断是否选择了内容
                detailsButton.setEnabled(false);
            } else {
                detailsButton.setEnabled(true); //使”详细”按钮可用，以检索书的详细信息
            }
        }
    }
}