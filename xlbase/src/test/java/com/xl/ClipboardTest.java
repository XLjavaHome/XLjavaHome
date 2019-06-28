package com.xl;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.剪切板
 *
 * @author 徐立
 * @date 2019-06-28
 * @time 14:10
 * To change this template use File | Settings | File Templates.
 */
public class ClipboardTest {
    @Test
    void name() {
        Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Clipboard systemSelection = Toolkit.getDefaultToolkit().getSystemSelection();
    }
}
