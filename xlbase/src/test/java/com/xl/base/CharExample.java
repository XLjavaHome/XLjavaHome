package com.xl.base;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-04
 * @time 9:22
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class CharExample {
    volatile static String message;
    /**
     * currentHashMap解决重复消费？
     */
    private static Map<String, String> map = new ConcurrentHashMap();
    
    public static void main(String[] args) {
        /**
         * 等待对方输入消息,并显示
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 收线程
        executorService.submit(() -> {
            while (true) {
                if (map.isEmpty()) {
                    Thread.sleep(2000);
                    continue;
                }
                Iterator<String> iterator = map.keySet().iterator();
                while (iterator.hasNext()) {
                    String next = iterator.next();
                    log.info("我收到了:{}", next);
                    iterator.remove();
                }
                message = "";
            }
        });
        executorService.submit(() -> {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String message = scanner.next();
                map.put(message, message);
            }
        });
        log.info("start");
    }
}
