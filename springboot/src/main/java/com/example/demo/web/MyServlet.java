package com.example.demo.web;

import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with 徐立. WebServlet注解默认同步 有个属性支持异步
 *
 * @author 徐立
 * @date 2019-08-31
 * @time 0:01
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(urlPatterns = "/my/Servlet", asyncSupported = true)
public class MyServlet extends HttpServlet {
    /**
     * asyncContext.complete() 放在里面和外面的区别 1.外面可以里面加载但是asyncContext里面的内容不会写到页面上
     * 2.放在里面要等待里面的方法执行完毕
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        AsyncContext asyncContext = req.startAsync();
        asyncContext.start(() -> {
            try {
                System.out.println("进入了");
                Thread.sleep(5000);
                String s = "hello aa World";
                System.out.println(s);
                resp.getWriter().println(s);
                //触发完成
                asyncContext.complete();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //触发完成
        //asyncContext.complete();
        String s2 = "this is two";
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(s2);
        System.out.println(s2);
    }
}
