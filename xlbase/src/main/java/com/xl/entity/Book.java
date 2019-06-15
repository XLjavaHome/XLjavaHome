package com.xl.entity;

import java.io.Serializable;
import lombok.Data;
import org.junit.Test;

@Data
public class Book implements Serializable {
    private String name;
    private String author;
    private String price; //因为有元,所以直接设置成String
    /**
     * boolean类型获取是is
     */
    private boolean hasRight;

    @Test
    public void sysoTest() {
        Book b = new Book();
        b.setAuthor("111");
        ClassLoaderTest.print(b);
        System.out.println(b.isHasRight());
        b.setHasRight(true);
        System.out.println(b.isHasRight());
    }
}
