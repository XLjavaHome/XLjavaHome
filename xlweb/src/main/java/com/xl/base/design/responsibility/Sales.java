package com.xl.base.design.responsibility;

import lombok.extern.log4j.Log4j;

/*
 * 销售， 可以批准5%以内的折扣
 */
@Log4j
public class Sales extends PriceHandler {
    @Override
    public void processDiscount(float discount) {
        if (discount <= 0.05) {
            log.info(String.format("%s批准了折扣：%.2f%n", this.getClass().getName(), discount));
        } else {
            successor.processDiscount(discount);
        }
    }
}
