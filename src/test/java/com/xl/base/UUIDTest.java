package com.xl.base;

import java.util.UUID;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

@Log4j
public class UUIDTest {
    @Test
    public void demoTest() {
        for (int i = 0; i < 1000; i++) {
            UUID u = UUID.randomUUID();
            log.info(u.toString());
        }
    }
}
