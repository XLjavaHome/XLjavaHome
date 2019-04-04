package com.xl;

import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-04-04
 * @time 16:58
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class InstanceofTest {
    class A {
    }
    
    class B extends A {
    }
    
    @Test
    public void instanceofTest() {
        boolean x = "11" instanceof String;
        log.info(x);
        log.info(String.class.isInstance("22"));
    }
    
    @Test
    public void name() {
        B b = new B();
        A a = new A();
        A ba = new B();
        boolean x = b instanceof B;
        log.info(x);
        boolean x3 = b instanceof A;
        log.info(x3);
        boolean x2 = b instanceof Object;
        log.info(x2);
        boolean x1 = null instanceof Object;
        log.info(x1);
        log.info(b.getClass().isInstance(b));
        log.info(b.getClass().isInstance(a));
        log.info(a.getClass().isInstance(ba));
        log.info(b.getClass().isInstance(ba));
        log.info(b.getClass().isInstance(null));
        log.info(A.class.isInstance(a));
        log.info(A.class.isInstance(b));
        log.info(A.class.isInstance(ba));
        log.info(B.class.isInstance(a));
        log.info(B.class.isInstance(b));
        log.info(B.class.isInstance(ba));
        log.info(Object.class.isInstance(b));
        log.info(Object.class.isInstance(null));
    }
}
