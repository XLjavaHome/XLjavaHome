package com.xl.base;

import static cn.edu.hfut.dmic.webcollector.net.Proxys.*;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.异步任务
 *
 * @author: 徐立
 * @Date: 2018-04-24
 * @Time: 13:48
 * To change this template use File | Settings | File Templates.
 */
public class CompletableFutuTest {
    /**
     * 异步计算商品的价格.
     *
     * @param product 商品名称
     * @return 价格
     */
    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception e) {
                //否则就抛出异常,完成这次future操作
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }

    /**
     * 同步计算商品价格的方法
     *
     * @param product 商品名称
     * @return 价格
     */
    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 模拟计算,查询数据库等耗时
     */
    public static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    @Test
    public void demoTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> cale(50));
        System.out.println(future.get());
    }

    public static Integer cale(Integer para) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return para * para;
    }
}
