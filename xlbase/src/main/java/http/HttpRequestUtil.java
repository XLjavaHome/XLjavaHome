package http;

import com.xl.util.CharacterUtil;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Http请求工具类
 */
@Log4j
public class HttpRequestUtil {
    static boolean proxySet = false;
    static String proxyHost = "127.0.0.1";
    static int proxyPort = 8087;
    
    /**
     * 编码
     *
     * @param source
     * @return
     */
    public static String urlEncode(String source, String encode) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "0";
        }
        return result;
    }
    
    /**
     * 发起http请求获取返回结果
     * 通过在 URL 上调用 openConnection 方法创建连接对象。
     * 处理设置参数和一般请求属性。
     * 使用 connect 方法建立到远程对象的实际连接。
     * 远程对象变为可用。远程对象的头字段和内容变为可访问。
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static StringBuffer httpRequest(URL url) throws IOException {
        return httpRequest(url, RequestMethod.GET);
    }
    
    /**
     * @param url
     * @param requestMethod 请求方式
     * @return
     * @throws IOException
     */
    public static StringBuffer httpRequest(URL url, RequestMethod requestMethod) throws IOException {
        StringBuffer buffer = new StringBuffer();
        //创建URL
        //连接
        HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
        // 设置是否向httpUrlConnection输出，如果是个是post请求，参数要放在
        // http正文内，因此需要设为true, 默认情况下是false;
        httpUrlConnection.setDoOutput(true);
        //false就抛异常 读不到设置是否从httpUrlConnection读入，默认情况下是true;
        httpUrlConnection.setDoInput(true);
        // Post 请求不能使用缓存
        httpUrlConnection.setUseCaches(false);
        httpUrlConnection.setRequestMethod(requestMethod.name());
        httpUrlConnection.setUseCaches(true);
        // 设定传送的内容类型是可序列化的java对象
        // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
        //必须在connect前完成设置
        httpUrlConnection.connect();
        // TODO 2019/6/23 23:15 徐立 通过url确定编码还要改下
        Charset urlEncode = CharacterUtil.getURLEncode(url);
        // 将返回的输入流转换成字符串
        try (InputStream inputStream = httpUrlConnection.getInputStream(); BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream, urlEncode))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
        } catch (IOException e) {
            log.error(e);
        }
        httpUrlConnection.disconnect();
        return buffer;
    }
    
    /**
     * 发送http请求取得返回的输入流
     *
     * @param requestUrl 请求地址
     * @return InputStream
     */
    public static InputStream httpRequestIO(String requestUrl) {
        InputStream inputStream = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();
            // 获得返回的输入流
            inputStream = httpUrlConn.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }
    
    public static void main(String[] args) {
        //demo:代理访问
        String url = "http://api.adf.ly/api.php";
        String para = "key=youkeyid&youuid=uid&advert_type=int&domain=adf.ly&url=http://somewebsite.com";
        String sr = HttpRequestUtil.sendPost(url, para, true);
        System.out.println(sr);
    }
    
    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param isproxy 是否使用代理模式
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param, boolean isproxy) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = null;
            if (isproxy) {//使用代理模式
                @SuppressWarnings("static-access") Proxy proxy = new Proxy(Proxy.Type.DIRECT.HTTP,
                        new InetSocketAddress(proxyHost, proxyPort));
                conn = (HttpURLConnection) realUrl.openConnection(proxy);
            } else {
                conn = (HttpURLConnection) realUrl.openConnection();
            }
            // 打开和URL之间的连接
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");    // POST方法
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8);
            // 发送请求参数
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    @Test
    public void testGet() {
        String url = "https://translate.google.cn/translate_a/single?client=webapp&sl=auto&tl=en&hl=zh-CN&dt=at&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&dt=t&dt=gt&otf=1&ssel=0&tsel=3&kc=1&tk=20235.446978&q=%E5%BC%80%E5%8F%91%E4%BB%BB%E5%8A%A1";
        String s = sendGet(url, null);
        System.out.println(s);
    }
    
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url 发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        return result;
    }
}
