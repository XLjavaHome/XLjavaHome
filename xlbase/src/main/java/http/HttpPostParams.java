package http;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * Post参数封装
 *
 * @author Created by jz on 2017/10/24 14:49
 */
public class HttpPostParams extends AbstractHttpParams {
    @Override
    protected CloseableHttpResponse send(CloseableHttpClient httpClient, String base) throws Exception {
        List<NameValuePair> formParams = new ArrayList<>();
        for (String key : params.keySet()) {
            String value = params.get(key);
            formParams.add(new BasicNameValuePair(key, value));
        }
        HttpPost request = new HttpPost(base);
        RequestConfig.Builder custom = RequestConfig.custom();
        setConnectTime(custom);
        custom.setCookieSpec(CookieSpecs.STANDARD);
        RequestConfig localConfig = custom.build();
        request.setConfig(localConfig);
        request.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
        //内容为post
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        return httpClient.execute(request);
    }
    
    private void setConnectTime(RequestConfig.Builder custom) {
        //从连接池中获取连接的超时时间,HttpClient中的要用连接时尝试从连接池中获取，若是在等待了一定的时间后还没有获取到可用连接（比如连接池中没有空闲连接了）则会抛出获取连接超时异常
        custom.setConnectionRequestTimeout(1000);
        //与服务器连接超时时间：httpclient会创建一个异步线程用以创建socket连接，此处设置该socket的连接超时时间
        //指的是连接目标url的连接超时时间，即客服端发送请求到与目标url建立起连接的最大时间。如果在该时间范围内还没有建立起连接，则就抛出connectionTimeOut异常。
        custom.setConnectTimeout(1000);
        //socket读数据超时时间：从服务器获取响应数据的超时时间, 连接上一个url后，获取response的返回等待时间 ，即在与目标url建立连接后，等待放回response的最大时间，在规定时间内没有返回响应的话就抛出SocketTimeout。
        custom.setSocketTimeout(5000);
    }
}
