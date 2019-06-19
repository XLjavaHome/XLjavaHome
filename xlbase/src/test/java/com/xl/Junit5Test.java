package com.xl;

import org.junit.jupiter.api.*;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-19
 * @time 18:03
 * To change this template use File | Settings | File Templates.
 */
public class Junit5Test {
    /**
     * 不用写public
     */
    @Test
    void name() {
        System.out.println("hello World");
    }
    
    @Test
    void test1() {
        Assertions.assertEquals(2, 2 + 1);
    }
    
    @BeforeEach
    @DisplayName("每条用例开始时执行")
    void start() {
    }
    
    @AfterEach
    @DisplayName("每条用例结束时执行")
    void end() {
    }
    
    @Test
    void myFirstTest() {
        Assertions.assertEquals(2, 1 + 1);
    }
    
    @Test
    @DisplayName("描述测试用例╯°□°）╯")
    void testWithDisplayName() {
    }
    
    @Test
    @Disabled("这条用例暂时跑不过，忽略!")
    void myFailTest() {
        Assertions.assertEquals(1, 2);
    }
    
    @Test
    @DisplayName("运行一组断言")
    public void assertAllCase() {
        Assertions.assertAll("groupAssert", () -> Assertions.assertEquals(2, 1 + 1), () -> Assertions.assertTrue(1 > 0));
    }
    
    @Test
    @DisplayName("依赖注入1")
    public void testInfo(final TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());
    }
    
    /**
     * 可以只保留一个参数
     *
     * @param testReporter
     * @param testInfo
     */
    @Test
    @DisplayName("依赖注入2")
    public void testReporter(final TestReporter testReporter, final TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());
        testReporter.publishEntry("name", "Alex");
    }
}

