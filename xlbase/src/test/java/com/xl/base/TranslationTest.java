package com.xl.base;

import impl.GoogleTranslator;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-03
 * @time 22:51
 * To change this template use File | Settings | File Templates.
 */
public class TranslationTest {
    private String query = "项目经理";
    
    @Test
    public void Google() {
        GoogleTranslator googleTranslator = new GoogleTranslator();
        String name = googleTranslator.translation(query);
        System.out.println(name);
    }
    
    /**
     * 不适合程序员
     *
     * @throws IOException
     */
    @Test
    public void 有道() throws IOException {
        URL url = new URL("http://fanyi.youdao.com/openapi.do");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.addRequestProperty("encoding", "UTF-8");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        OutputStream os = connection.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write("keyfrom=fadabvaa&key=522071532&type=data&doctype=json&version=1.1&q=" + query);
        bw.flush();
        InputStream is = connection.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = br.readLine()) != null) {
            builder.append(line);
        }
        bw.close();
        osw.close();
        os.close();
        br.close();
        isr.close();
        is.close();
        System.out.println(builder.toString());
    }
}
