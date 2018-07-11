package com.xl.swing;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;
import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-07-02
 * @Time: 9:53
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class ApplicationTest extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@NotNull Stage primaryStage) throws Exception {
        primaryStage.setTitle("这是title");
        Button btn = new Button();
        btn.setText("BUTTON按钮的内容");
        btn.setOnAction(event -> log.info("BUTTON按钮的事件"));
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        log.info("这是stop");
        super.stop();
    }
}
