package com.xl.internet;

import com.xl.enumsupport.CharacterEnum;
import com.xl.util.FileUtil;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import org.junit.Test;

public class URLTest {
    private String s = "dse中国sdf";
    
    @Test
    public void decode() throws UnsupportedEncodingException {
        String s = " %7B%22hiddentext%22%3A%22%22%7D";
        s =
                "%7B%22inputClass%22%3A%22autocomplete_input%22%2C%22resultsClass%22%3A%22autocomplete_style1%22%2C%22loadingClass%22%3A%22autocomplete_loading%22%2C%22minChars%22%3A1%2C%22delay%22%3A50%2C%22initialValue%22%3A%22%22%2C%22matchCase%22%3Afalse%2C%22matchSubset%22%3Atrue%2C%22matchContains%22%3Afalse%2C%22cacheLength%22%3A30%2C%22max%22%3A100%2C%22dataType%22%3A%22json%22%2C%22mustMatch%22%3Afalse%2C%22selectFirst%22%3Atrue%2C%22autoFill%22%3Afalse%2C%22width%22%3A200%2C%22multiple%22%3Afalse%2C%22multipleSeparator%22%3A%22%2C%20%22%2C%22showOnFocus%22%3Afalse%2C%22scroll%22%3Atrue%2C%22scrollHeight%22%3A150%2C%22clazz%22%3A%22com.sinitek.sirm.org.web.action.CandidateControlAction%22%2C%22data%22%3Anull%7D\n"
                + "param: %7B%22__schemeCode%22%3A%22%22%2C%22prefix%22%3A%22dxmjl%22%2C%22path%22%3A%22%2Fsirmpm%2Fproject%2Faddproject.jsp%22%2C%22unitlimitflag%22%3A%22%22%2C%22mode%22%3A%22%22%2C%22multi%22%3A%22%22%2C%22deptid%22%3A%22%22%2C%22teamid%22%3A%22%22%2C%22positionid%22%3A%22%22%2C%22showposition%22%3A%22false%22%7D";
        String decode = URLDecoder.decode(s, CharacterEnum.UTF8.getValue());
        System.out.println(decode);
    }
    
    @SuppressWarnings("deprecation")
    @Test
    public void test() throws UnsupportedEncodingException {
        //encode(s) 默认采取项目编码，这个项目是utf-8
        String _s = java.net.URLEncoder.encode(s, CharacterEnum.UTF8.getValue());
        System.out.println(_s);
        _s = java.net.URLEncoder.encode(s, CharacterEnum.UTF8.getValue());
        System.out.println(_s);
        s = URLDecoder.decode(_s);
        System.out.println(s);
        s = URLDecoder.decode(_s, CharacterEnum.UTF8.getValue());
        System.out.println(s);
        s = URLDecoder.decode(_s, CharacterEnum.GBK.getValue());
        System.out.println(s);
    }
    
    /**
     * 多次转码的值是一样的
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void 两次转码() throws UnsupportedEncodingException {
        String encode = URLDecoder.decode(s, CharacterEnum.UTF8.getValue());
        System.out.println(encode);
        System.out.println(URLDecoder.decode(encode, CharacterEnum.UTF8.getValue()));
    }
    
    @Test
    public void download() throws IOException {
        String url = "https://ii.hywly.com/a/1/1076/42.jpg";
        url = "http://www.baidu.com/img/baidu_sylogo1.gif";
        URL url1 = new URL(url);
        System.out.println(url1.getPath());
        InputStream inputStream = url1.openStream();
        File tempFile = FileUtil.getTempFile(".gif");
        FileUtil.write(tempFile, inputStream);
        FileUtil.openParent(tempFile);
    }
    
    @Test
    public void download2() {
        try {
            //1.定位网络图片路径
            String imgPath = "https://ii.hywly.com/a/1/1076/42.jpg";
            URL url = new URL(imgPath);
            //2.建立与网络图片的连接,获取该图片的输入流
            URLConnection connection = url.openConnection();
            File file = FileUtil.getTempFile("qq.gif");
            InputStream inputStream = connection.getInputStream();
            //3.在本地建一个图片路径,接收与存储网络图片
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                //4.通过字节数组循环读取网络图片到本地
                byte[] bs = new byte[8016];
                int len = 0;
                while ((len = inputStream.read(bs)) != -1) {
                    outputStream.write(bs, 0, len);
                }
                //5.关闭流
                inputStream.close();
            }
            System.out.println("图片下载成功!");
        } catch (IOException e) {
            System.out.println("图片下载失败!");
            e.printStackTrace();
        }
    }
}
