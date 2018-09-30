package com.xl.base;

import java.util.UUID;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

@Log4j
public class IDTest {
    /**
     * uuid
     */
    @Test
    public void UUIDTest() {
        for (int i = 0; i < 1000; i++) {
            UUID u = UUID.randomUUID();
            System.out.println(u.toString());
        }
    }

    /**
     * 生成唯一的id
     */
    @Test
    public void idTest() {
        IdWorker worker = new IdWorker(1, 1);
        for (int i = 0; i < 30; i++) {
            System.out.println(worker.nextId());
        }
    }
}

