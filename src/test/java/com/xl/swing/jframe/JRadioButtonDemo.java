package com.xl.swing.jframe;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JRadioButtonDemo extends JFrame {
    protected JPanel topPanel;
    protected JScrollPane bookInLibScrollPane;// 存放借阅信息的面板
    protected JTable borrowInfoTable; //显示借阅信息的表格
    private Container container;

    public JRadioButtonDemo() {
        container = this.getContentPane();//获取内容窗格
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        // 设置边框文本提示信息
        topPanel.setBorder(BorderFactory.createTitledBorder("借阅查询选项"));
        JRadioButton currBorrowButton = new JRadioButton("当前借阅");
        JRadioButton oldBorrowButton = new JRadioButton("历史借阅");
        topPanel.add(currBorrowButton); //添加组件到面板容器
        topPanel.add(oldBorrowButton); //添加组件到面板容器
        // 注册事件监听程序，对ActionEvent事件作出处理
        currBorrowButton.addActionListener(new CurrentBorrowInfoListener());
        oldBorrowButton.addActionListener(new OldBorrowInfoListener());
        /** 将2个RadioButton对象放进ButtonGroup中，以实现二选一 */
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(currBorrowButton);
        buttonGroup1.add(oldBorrowButton);
        this.add(BorderLayout.NORTH, topPanel); //把面板容器添加到内容窗格上
        this.setTitle("图书管理系统我的借阅"); //设置标题
        this.setSize(600, 450);//设置大小
        this.setVisible(true);//设置可见性
    }

    public static void main(String[] arg) {
        new JRadioButtonDemo();
    }

    class CurrentBorrowInfoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            // 把当前借阅信息从数据库取出，以表格的形式显示出来，代码实现见13章
            Vector allBorrowInfoVector = new Vector();// 存放所有的行的内容向量
            Vector rowVector1 = new Vector();// 存放第一行内容的向量
            //为第一行的内容向量设定值，实际应从数据库读取信息放入向量，这里为测试数据
            rowVector1.add("java程序设计");
            rowVector1.add("耿祥义");
            rowVector1.add("清华大学出版社");
            rowVector1.add("09-09-08");
            rowVector1.add("09-12-08");
            rowVector1.add("");
            rowVector1.add("0");
            rowVector1.add("0");
            allBorrowInfoVector.add(rowVector1);//把第一行向量添加
            rowVector1 = new Vector();// 存放第二行向量
            rowVector1.add("java");
            rowVector1.add("张白一");
            rowVector1.add("清华大学出版社");
            rowVector1.add("09-10-10");
            rowVector1.add("10-01-10");
            rowVector1.add("");
            rowVector1.add("0");
            rowVector1.add("0");
            allBorrowInfoVector.add(rowVector1);
            /*静态数据以上向量可以通过二维数组代替*/
            /*Object borrowinf[][]={{"java程序设计","耿祥义","清华大学出版社", "09-09-08", "09-12-08", "","0","0"},{"java","张白一","清华大学出版社","09-10-10","10-01-10","","0","0"}};*/
            Vector borrowHead = new Vector(); // 存储表头信息的向量
            borrowHead.add("书名");
            borrowHead.add("作者");
            borrowHead.add("出版");
            borrowHead.add("借阅日期");
            borrowHead.add("应还日期");
            borrowHead.add("归还日期");
            borrowHead.add("超期天数");
            borrowHead.add("罚款金额");
            /*静态数据以上向量可以通过一维数组代替*/
            /* Object tableHead[]={"书名","作者","出版","借阅日期","应还日期","归还日期","超期天数", "罚款金额"};*/
            // 生成具有内容和表头的表格
            // borrowInfoTable = new JTable(borrowinf, tableHead); //以数组数据生成表格
            borrowInfoTable = new JTable(allBorrowInfoVector, borrowHead);//以向量数据生成表格
            borrowInfoTable.setEnabled(false);//设置表格是不可编辑的，只显示信息
            borrowInfoTable.setPreferredScrollableViewportSize(new Dimension(0, 120));
            bookInLibScrollPane = new JScrollPane();
            bookInLibScrollPane.setViewportView(borrowInfoTable); //放置到滚动面板
            //设置提示信息
            bookInLibScrollPane.setBorder(BorderFactory.createTitledBorder("借阅信息"));
            add(BorderLayout.CENTER, bookInLibScrollPane); //添加到内容窗格上
            validate();//刷新窗口
        }
    }

    class OldBorrowInfoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            // 把历史借阅信息从数据库取出，以表格的形式显示出来，代码实现见13章
        }
    }
}