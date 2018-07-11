package com.xl.util;

import org.junit.Test;

public class ZipUtilsTest {
    @Test
    public void uncompression() throws Exception {
        ZipUtils.uncompression(ResourceUtil.getResourceFile("zip/1.zip"), FileUtil.getDesktopPath());
    }
}