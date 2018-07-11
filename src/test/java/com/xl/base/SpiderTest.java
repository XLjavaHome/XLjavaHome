package com.xl.base;

import org.junit.Test;
import org.tinygroup.parser.filter.QuickNameFilter;
import org.tinygroup.tinyspider.Spider;
import org.tinygroup.tinyspider.Watcher;
import org.tinygroup.tinyspider.impl.PrintProcessor;
import org.tinygroup.tinyspider.impl.SpiderImpl;
import org.tinygroup.tinyspider.impl.WatcherImpl;

/**
 * Created with IntelliJ IDEA. 网页爬虫测试
 *
 * @author: 徐立
 * @Date: 2018-04-17
 * @Time: 9:42
 * To change this template use File | Settings | File Templates.
 */
public class SpiderTest {
    @Test
    public void demoTest() {
        Spider Spider = new SpiderImpl();
        Watcher watcher = new WatcherImpl();
        watcher.addProcessor(new PrintProcessor());
        QuickNameFilter nodeFilter = new QuickNameFilter();
        nodeFilter.setNodeName("div");
        nodeFilter.setIncludeAttribute("class", "qbody");
        watcher.setNodeFilter(nodeFilter);
        Spider.addWatcher(watcher);
        Spider.processUrl("http://www.oschina.net/question?catalog=1");
    }
}
