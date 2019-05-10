package com.xl.swing.fx;

import com.sun.istack.internal.NotNull;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @Date: 2019-01-26
 * @Time: 15:49
 * To change this template use File | Settings | File Templates.
 */
public abstract class XLBaseApplication extends Application {
    @Override
    public void start(@NotNull Stage stage) {
        stage.setTitle("提取文本文件的所有文件 by徐立");
        stage.show();
    }
}
