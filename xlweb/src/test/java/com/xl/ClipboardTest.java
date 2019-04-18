package com.xl;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import org.junit.Test;

/**
 * Created with 徐立.剪切板测试
 *
 * @author: 徐立
 * @Date: 2018-12-03
 * @Time: 9:40
 * To change this template use File | Settings | File Templates.
 */
public class ClipboardTest {
    /**
     * 设置文本到剪切板
     */
    @Test
    public void demoTest() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection("hello World"), null);
    }
    
    /**
     * 获取剪切板的内容
     *
     * @throws IOException
     * @throws UnsupportedFlavorException
     */
    @Test
    public void getFromClipboard() throws IOException, UnsupportedFlavorException {
        Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            String s = (String) transferable.getTransferData(DataFlavor.stringFlavor);
            System.out.println(s);
        }
    }
}
