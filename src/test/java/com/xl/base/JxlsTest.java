package com.xl.base;

import com.xl.util.FileUtil;
import com.xl.util.ResourceUtil;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: 徐立
 * Date: 2017/10/18
 * Time: 14:33
 * To change this template use File | Settings | File Templates.
 */
public class JxlsTest {
    /**
     * @throws IOException
     * @throws InvalidFormatException
     */
    @Test
    public void excelTest() throws IOException, InvalidFormatException {
        long l1 = System.currentTimeMillis();
        int pagesize = 100;
        //获取模板
        String path = "jxls/jxlsExcel.xls";
        InputStream is = ResourceUtil.getResourceInputStream(path);
        //总的数据
        Map<String, Object> map = new HashMap<String, Object>();
        //listmap
        List<Map<String, String>> lm = createdata();
        //添加数据
        map.put("list", lm);
        //每个sheet的数据
        ArrayList<List> objects = new ArrayList<List>();
        //sheet的名称的集合
        List<String> listSheetNames = new ArrayList<String>();
        int sheeenum = 0;
        if (lm.size() % pagesize == 0) {
            sheeenum = lm.size() / pagesize;
        } else {
            sheeenum = (lm.size() / pagesize) + 1;
        }
        for (int i = 1; i <= sheeenum; i++) {
            listSheetNames.add(i + "");
            //获取当前的条目
            List<Map<String, String>> childlm = createdata();
            childlm = getpagingdata(lm, i, pagesize);
            objects.add(childlm);
        }
        //调用引擎生成excel报表
        XLSTransformer transformer = new XLSTransformer();
        Workbook workbook = transformer.transformMultipleSheetsList(is, objects, listSheetNames, "list", new HashMap(), 0);
        long l = System.currentTimeMillis();
        workbook.write(new FileOutputStream(FileUtil.createTempFile("jxls/out.xls")));
        workbook.close();
        long l2 = System.currentTimeMillis();
        long l3 = l2 - l1;
        System.out.println("使用时间" + l3 + "毫秒");
    }

    private List<Map<String, String>> createdata() {
        List<Map<String, String>> lm = new ArrayList<Map<String, String>>();
        for (int i = 0; i < 1002; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", "name" + i);
            if (i % 2 == 0) {
                map.put("sex", "男");
            } else {
                map.put("sex", "女");
            }
            map.put("age", i + "");
            map.put("address", "address" + i);
            lm.add(map);
        }
        return lm;
    }

    private List getpagingdata(List<Map<String, String>> list, int page, int pagesize) {
        List<Map<String, String>> childlm = new ArrayList<Map<String, String>>();
        //总值
        int totle = list.size();
        //计算下标
        //开始下标:（page-1)*pagesize
        int start = (page - 1) * pagesize;
        //结束下标:（page-1)*pagesize+pagesize,注意结尾
        int end = (page - 1) * pagesize + pagesize;
        if (end > totle) {
            int i = totle - end;
            end = (end + i) - 1;
        }
        for (int i = start; i < end; i++) {
            Map<String, String> map = list.get(i);
            childlm.add(map);
        }
        return childlm;
    }
}
