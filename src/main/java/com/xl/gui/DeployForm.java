package com.xl.gui;

import com.xl.service.DeploymentPackageService;
import com.xl.service.impl.DeploymentPackageServiceImpl;
import com.xl.util.GUIUtil;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.*;

/**
 * Created with 徐立.生成部署目录
 *
 * @author 徐立
 * @version 1.0 2019-04-12 17:17
 * To change this template use File | Settings | File Templates.
 * @date 2019-04-12
 * @time 17:17
 */
public class DeployForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JButton demandReleasePackage;
    private JButton BUG部署包Button;
    private JTextArea code;
    private JTextArea textArea2;
    private DeploymentPackageService service = new DeploymentPackageServiceImpl();
    public DeployForm() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(demandReleasePackage);

        buttonCancel.addActionListener(e -> onCancel());
        demandReleasePackage.addActionListener(e -> {
            try {
                service.createFile(true,code,textArea2);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        BUG部署包Button.addActionListener(e -> {
            try {
                service.createFile(false,code,textArea2);
            } catch (IOException e1) {
                 e1.printStackTrace();
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(e -> {
            onCancel();
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
    
    private void onCancel() {
        dispose();
    }
    
    public static void main(String[] args) {
        DeployForm dialog = new DeployForm();
        dialog.pack();
        GUIUtil.makeCenter(dialog);
        dialog.setVisible(true);
    }
}
