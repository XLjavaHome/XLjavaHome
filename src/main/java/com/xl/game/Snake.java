package com.xl.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Snake extends JFrame {
    /** */
    private static final long serialVersionUID = 1L;
    MyPaint mp = null;

    public Snake() {
        mp = new MyPaint();
        Thread t1 = new Thread(mp);// 启动mp线程
        t1.start();
        this.add(mp);// 加载面板
        this.addKeyListener(mp);// 加载按键监听
        this.setSize(300, 320);// 窗口大小
        this.setLocationRelativeTo(null); // 居中
        this.setTitle("贪吃蛇V1.0");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 点击关闭时，关闭程序
        this.setVisible(true);// 可见
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Snake she = new Snake();
    }
}

class MyPaint extends JPanel implements KeyListener, Runnable {
    /** */
    private static final long serialVersionUID = 1L;
    yidong yd = new yidong();

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, 300, 260);
        g.setColor(Color.yellow);
        g.fillOval(yd.swx, yd.swy, 20, 20);
        g.setColor(Color.red);
        g.fillOval(yd.x[0], yd.y[0], 20, 20);// 蛇头显示
        g.setColor(Color.blue);
        for (int i = 1; i < yd.sum - 1; i++) {// 蛇身体显示
            g.fillOval(yd.x[i], yd.y[i], 20, 20);
        }
        g.drawString("当前分数: " + yd.fenshu, 190, 275);
        g.drawString("速度等级: " + yd.lv, 10, 275);
        g.setColor(Color.ORANGE);
        if (yd.kaishi == 0) { // 当游戏结束时显示
            g.setFont(new Font("黑体", 1, 20));
            g.drawString("Game over", 90, 100);
            g.setFont(new Font("幼体", 1, 15));
            g.drawString("按Z键  继续游戏！", 80, 120);
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (yd.kaishi == 1) {// 当游戏进行时 可控制
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                if (yd.fx != 2) {
                    yd.fx = 8;
                }
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                if (yd.fx != 8) {
                    yd.fx = 2;
                }
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                if (yd.fx != 6) {
                    yd.fx = 4;
                }
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                if (yd.fx != 4) {
                    yd.fx = 6;
                }
            }
        } else {// 当游戏结束时 按Z 恢复游戏
            if (e.getKeyCode() == KeyEvent.VK_Z) {
                yd.sum = 4;
                yd.x[0] = 100;
                yd.y[0] = 100;
                yd.x[1] = 80;
                yd.y[1] = 100;
                yd.x[2] = 60;
                yd.y[2] = 100;
                yd.yanchi = 200;
                yd.kaishi = 1;
                yd.fx = 6;
                yd.nfx = 6;
                yd.sp = 20;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void run() { // 刷新面板线程
        Thread t2 = new Thread(yd);
        t2.start();
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }
    }
}

class yidong implements Runnable { // 蛇移动线程
    /**
     * 蛇的最大长度
     */
    int maxsum = 128;
    /**
     * 蛇的初始长度
     */
    int sum = 3;
    int[] x = new int[maxsum];
    int[] y = new int[maxsum];
    /**
     * 1:开始;0:结束
     */
    int kaishi = 1; // sum：代表蛇的总共长度是sum-1
    /**
     * 方向 fx：4=← 6=→ 8=↑ 2=↓ 其余：无移动
     */
    int fx = 1;
    int nfx, sp = 20, i = 0;
    /**
     * 延迟
     */
    int yanchi = 200;
    /**
     * 分数
     */
    int fenshu;
    /**
     * 食物的坐标
     */
    int swx = 0, swy = 0;
    /**
     * 食物
     */
    int shiwu = 0;
    String lv = "1";
    Random sj = new Random();

    @Override
    public void run() {
        x[0] = 100;
        y[0] = 100;// 初始化蛇坐标
        while (true) {
            try {
                Thread.sleep(yanchi);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (shiwu == 0) {// 随机产生食物
                swx = (sj.nextInt(10) + 1) * 20;// 随机0-20
                swy = (sj.nextInt(10) + 1) * 20;
                shiwu = 1;
            }
            if (kaishi == 1) {
                // 如果是开始,或者吃了食物分数为食物长度减去初始长度
                fenshu = sum * 100 - 400;
            }
            // 分数计算
            for (int i = sum - 1; i > 0; i--) {// 身体坐标刷新
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }
            if (fx == 6 && nfx != 4) {// 方向判断
                x[0] += sp;// 移动
                nfx = 6;
            } else if (fx == 4 && nfx != 6) {
                x[0] -= sp;
                nfx = 4;
            } else if (fx == 2 && nfx != 8) {
                y[0] += sp;
                nfx = 2;
            } else if (fx == 8 && nfx != 2) {
                y[0] -= sp;
                nfx = 8;
            } else {
                if (nfx == 6) {
                    x[0] += sp;
                }
                if (nfx == 4) {
                    x[0] -= sp;
                }
                if (nfx == 2) {
                    y[0] += sp;
                }
                if (nfx == 8) {
                    y[0] -= sp;
                }
            }
            if (x[0] > 270) {
                x[0] -= sp;
            }
            if (x[0] < 0) {
                x[0] += sp;
            }
            if (y[0] > 255) {
                y[0] -= sp;
            }
            if (y[0] < 0) {
                y[0] += sp;
            }
            if (x[0] > swx - 5 && x[0] < swx + 5 && y[0] > swy - 5 && y[0] < swy + 5) {
                sum++;// 增加长度
                shiwu = 0;
                if (sum == 10) {
                    yanchi = 180;
                    lv = "2";
                }// 加速
                if (sum == 20) {
                    yanchi = 160;
                    lv = "3";
                }
                if (sum == 30) {
                    yanchi = 150;
                    lv = "4";
                }
                if (sum == 40) {
                    yanchi = 140;
                    lv = "5";
                }
                if (sum == 50) {
                    yanchi = 130;
                    lv = "max";
                }
            }
            for (int i = sum - 1; i > 0; i--) {// 判断蛇是否吃到自己
                if (x[0] > x[i] - 5 && x[0] < x[i] + 5 && y[0] > y[i] - 5 && y[0] < y[i] + 5) {
                    sp = 0;
                    yanchi = 500;
                    kaishi = 0;
                }
            }
        }
    }
}
