package com.xl.swing.fx.label;/**
 * Created with 徐立.
 *
 * @author 徐立
 * @Date: 2019-01-26
 * @Time: 15:46
 * To change this template use File | Settings | File Templates.
 */

import com.xl.swing.fx.XLBaseApplication;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LabelFX extends XLBaseApplication {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        //空标签
        Label label1 = new Label();
        //有文本的标签
         Label label2 = new Label("Search"); //有文本有图像 Image image = new Image(getClass().
        // getResourceAsStream("labels.jpg")); Label label3 = new Label("Search", new ImageView(image));
        super.start(primaryStage);
    }
}
