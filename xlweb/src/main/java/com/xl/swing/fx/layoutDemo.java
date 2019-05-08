package com.xl.swing.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class layoutDemo extends Application {
    @Override
    public void start(Stage stage) {
        //demo1(stage);
        demo2(stage);
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);
        Text title = new Text("Data");
        title.setFont(Font.font("Amble CN", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);
        Text[] options = new Text[]{new Text(" Sales"), new Text(" Marketing"), new Text(" Distribution"), new Text(" Costs")};
        for (int i = 0; i < 4; i++) {
            vbox.getChildren().add(options[i]);
        }
        BorderPane border = new BorderPane();
        border.setLeft(vbox);
    }
    
    private void demo2(Stage stage) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699");
        Button buttonCurrent = new Button("Current");
        buttonCurrent.setPrefSize(100, 20);
        Button buttonProjected = new Button("Projected");
        buttonProjected.setPrefSize(100, 20);
        hbox.getChildren().addAll(buttonCurrent, buttonProjected);
        BorderPane border = new BorderPane();
        border.setTop(hbox);
        Scene scene = new Scene(border);
        stage.setScene(scene);
        stage.show();
    }
    
    private void demo1(Stage stage) {
        BorderPane layout = new BorderPane();
        layout.setTop(new Rectangle(200, 50, Color.DARKCYAN));
        layout.setBottom(new Rectangle(200, 50, Color.DARKCYAN));
        layout.setCenter(new Rectangle(100, 100, Color.MEDIUMAQUAMARINE));
        layout.setLeft(new Rectangle(50, 100, Color.DARKTURQUOISE));
        layout.setRight(new Rectangle(50, 100, Color.DARKTURQUOISE));
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
