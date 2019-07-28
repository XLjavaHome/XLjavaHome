package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.1.在src/main/resources下新建一个banner.txt文档
 * 2.通过http://patorjk.com/software/taag网站生成需要的字符，将字符拷贝到步骤1所创建的txt文档中，比如我这里为Hello Sang！生成字符，如下：
 *
 * @author: 徐立
 * @Date: 2018-07-08
 * @Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@RestController
public class XLSpringbootApplication {
    @Value(value = "${book.author}")
    private String bookAuthor;
    @Value("${book.name}")
    private String bookName;
    @Value("${book.pinyin}")
    private String bookPinYin;
    
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(XLSpringbootApplication.class, args);
    }
}
