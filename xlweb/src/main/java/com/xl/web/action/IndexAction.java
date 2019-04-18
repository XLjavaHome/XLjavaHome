package com.xl.web.action;

import com.xl.entity.PageLoadContext;
import com.xl.entity.User;
import com.xl.util.FileUtil;
import com.xl.util.MethodUtil;
import com.xl.web.webInterface.PageLoadInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2017-12-29
 * @Time: 17:37
 * To change this template use File | Settings | File Templates.
 */
@RequestMapping("/index")
@Log4j
@Controller
public class IndexAction implements PageLoadInterface {
    private int num;
    
    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    public @ResponseBody
    User getShopInJSON(@PathVariable String name) {
        User user = new User();
        user.setName(name);
        System.out.println(user);
        return user;
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String addBlog(ModelMap modelMap) {
        MethodUtil.getMethodName();
        return "suceess";
    }
    
    @Override
    public void loadpage(PageLoadContext context) {
        List<String> exclutDirectoryName = excluDirectory();
        String path = context.getRequest().getSession().getServletContext().getRealPath("");
        List<File> fileList = FileUtil.queryAll(path, "jsp");
        fileList.addAll(FileUtil.queryAll(path, "src/src/main/resources/html"));
        //左边是名称，右边是路径
        Map<String, String> pathMap = new LinkedHashMap<String, String>();
        pathMap.put("springMVC", "/springController/spring.action");
        pathMap.put("springMVCXL", "/springController/这是名称.action");
        for (File file : fileList) {
            File parentFile = file.getParentFile();
            if (parentFile.isDirectory()) {
                if (exclutDirectoryName.contains(parentFile.getName())) {
                    continue;
                }
            }
            StringBuffer relaPath = new StringBuffer(file.getAbsolutePath().substring(path.length()));
            if (relaPath.indexOf("/") != 0) {
                relaPath.insert(0, "/");
            }
            pathMap.put(relaPath.toString().replace("\\", "/"), relaPath.toString().replace("\\", "/"));
        }
        context.getRequest().setAttribute("filePath", pathMap);
    }
    
    private void test11() {
        System.out.println("11");
    }
    
    /**
     * 不包含的目錄
     *
     * @return
     */
    private List<String> excluDirectory() {
        List<String> exclutDirectoryName = new ArrayList<>();
        exclutDirectoryName.add("css");
        exclutDirectoryName.add("images");
        exclutDirectoryName.add("include");
        exclutDirectoryName.add("js");
        exclutDirectoryName.add("static");
        exclutDirectoryName.add("WEB-INF");
        return exclutDirectoryName;
    }
}
