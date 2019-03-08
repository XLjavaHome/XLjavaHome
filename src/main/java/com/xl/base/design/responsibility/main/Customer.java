package com.xl.base.design.responsibility.main;

import com.xl.base.design.responsibility.PriceHandler;
import com.xl.base.design.responsibility.PriceHandlerFactory;
import java.util.Random;
import lombok.Data;
import lombok.extern.log4j.Log4j;

/**
 * Created with 徐立.
 *
 * @author: 徐立
 * @Date: 2019-01-12
 * @Time: 19:06
 * To change this template use File | Settings | File Templates.
 */
@Data
@Log4j
public class Customer {
    private PriceHandler priceHandler;
    
    public void setPriceHandler(PriceHandler priceHandler) {
        this.priceHandler = priceHandler;
    }
    
    public void requestDiscount(float discount) {
        priceHandler.processDiscount(discount);
    }
    
    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.setPriceHandler(PriceHandlerFactory.createPriceHandler());
        Random rand = new Random();
        for (int i = 1; i <= 100; i++) {
            customer.requestDiscount(rand.nextFloat());
        }
    }
}
