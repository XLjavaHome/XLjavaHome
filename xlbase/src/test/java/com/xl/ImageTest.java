package com.xl;

import com.xl.util.FileUtil;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-08-04
 * @time 21:18
 * To change this template use File | Settings | File Templates.
 */
public class ImageTest {
    @Test
    void readTest() throws IOException {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("no.jpg");
        BufferedImage read = ImageIO.read(resourceAsStream);
        File tempFile = FileUtil.getTempFile(".jpg");
        ImageIO.write(read, "png", tempFile);
        FileUtil.openParent(tempFile);
    }
}
