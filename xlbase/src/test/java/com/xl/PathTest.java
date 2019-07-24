package com.xl;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-24
 * @time 17:24
 * To change this template use File | Settings | File Templates.
 */
public class PathTest {
    @Test
    void name() {
        Path path = Paths.get("/home/percy/IdeaProjects/StreamDemo/src/com/percy/God Had to Be Fair");
        System.out.println(path);
    }
}
