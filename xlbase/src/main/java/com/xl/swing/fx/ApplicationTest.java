package com.xl.swing.fx;

import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created with IntelliJ IDEA. <br/>1.解析第一行选择的目录 讲所有文本文件内容读取，记录文件名 <br/>2.解析第二行选择的目录，符合相同的文件 <br/>3.输出到第三行选择的目录
 *
 * @author 徐立
 * @Date: 2018-07-02
 * @Time: 9:53 To change this template use File | Settings | File Templates.
 */
@Log4j
public class ApplicationTest extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    private Button firstButon = new Button();
    private Button secondButon = new Button();
    private Button thirdButon = new Button();
    private TextField firstTextField = new TextField();
    private TextField secondTextField = new TextField();
    private TextField thirdTextField = new TextField();
    
    @Override
    public void start(@NotNull Stage stage) throws Exception {
        stage.setTitle("提取文本文件的所有文件 by徐立");
        //初始化元素
        initText();
        //初始化事件
        initAction(stage);
        //布局
        initLayout(stage);
        stage.show();
    }
    
    /**
     * 布局
     *
     * @param primaryStage
     */
    private void initLayout(Stage primaryStage) {
        StackPane root = new StackPane();
        firstTextField.setMaxWidth(200D);
        root.getChildren().add(firstButon);
        root.getChildren().add(secondButon);
        root.getChildren().add(thirdButon);
        root.getChildren().add(firstTextField);
        root.getChildren().add(secondTextField);
        root.getChildren().add(thirdTextField);
        primaryStage.setScene(new Scene(root, 300, 250));
    }
    
    private void initText() {
        firstButon.setText("选择目录");
        secondButon.setText("请选择提取文件的目录");
        thirdButon.setText("选择目录");
    }
    
    private void initAction(final Stage primaryStage) {
        firstButon.setOnAction(event -> {
            //初始化文件选择
            //fileSelected(stage);
            //目录选择器
            DirectoryChooser folderChooser = new DirectoryChooser();
            folderChooser.setTitle("选择目录");
            File selectedFile = folderChooser.showDialog(primaryStage);
            firstTextField.setText(selectedFile.getAbsolutePath());
            //方法打开一个可以多选的文件窗口
            //List<File> files = fc.showOpenMultipleDialog(stage);
        });
    }
    
    /**
     * 文件选择
     *
     * @param primaryStage
     */
    private void fileSelected(Stage primaryStage) {
        //1.解析第一行选择的文件
        FileChooser fc = new FileChooser();
        //设置过滤
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File f = fc.showOpenDialog(primaryStage);
    }
    
    @Override
    public void stop() throws Exception {
        log.info("这是stop");
        super.stop();
    }
}
