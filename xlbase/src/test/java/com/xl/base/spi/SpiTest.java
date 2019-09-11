package com.xl.base.spi;

import java.util.Iterator;
import java.util.ServiceLoader;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-09-11
 * @time 23:57
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class SpiTest {
    @Test
    void name() {
        IOperation plus = new PlusOperationImpl();
        IOperation division = new DivisionOperationImpl();
        int numberA = 5;
        int numberB = 3;
        int operation1 = plus.operation(numberA, numberB);
        log.info(operation1);
        log.info(division.operation(numberA, numberB));
        ServiceLoader<IOperation> operations = ServiceLoader.load(IOperation.class);
        Iterator<IOperation> operationIterator = operations.iterator();
        while (operationIterator.hasNext()) {
            IOperation operation = operationIterator.next();
            log.info(operation.operation(numberA, numberB));
        }
    }
}
