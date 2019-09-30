package com.example.demo.web.listener;

import lombok.extern.log4j.Log4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-09-30
 * @time 15:52
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class MyListener implements ApplicationListener {
    int count;
    
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        Object source = applicationEvent.getSource();
        count++;
        log.info(count);
    }
}
