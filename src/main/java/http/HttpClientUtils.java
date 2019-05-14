package http;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求工具
 * @author axiaogezi
 */
public class HttpClientUtils {
    /**
     * 请求配置
     */
    private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom()
            .setConnectTimeout(HttpConstants.CONNECT_TIMEOUT)
            .setConnectionRequestTimeout(HttpConstants.REQUEST_TIMEOUT)
            .setSocketTimeout(HttpConstants.SOCKET_TIMEOUT).build();

    /**
     * 发送Get请求
     * @param url
     * @param headers
     * @param params
     * @return
     * @throws Exception
     */
    public static HttpResult sendGet(String url, final Map<String,String> headers,
                                     final Map<String,Object> params) throws Exception {
        url = url + (null == params ? "" : assemblyParameter(params));
        URL URL = new URL(url);
        URI URI = new URI(URL.getProtocol(), URL.getHost(), URL.getPath(), URL.getQuery(), null);
        final HttpGet httpGet = new HttpGet(URI);
        httpGet.setConfig(REQUEST_CONFIG);
        if(null != headers){
            httpGet.setHeaders(assemblyHeader(headers));
        }
        final CloseableHttpClient client = HttpClients.createDefault();
        final HttpResponse response = client.execute(httpGet);
        if(null == response){
            throw new RuntimeException("send get connect response is null");
        }
        final HttpResult result = new HttpResult();
        result.setStatusCode(response.getStatusLine().getStatusCode());
        result.setHeaders(response.getAllHeaders());
        result.setHttpEntity(response.getEntity());
        return result;
    }
    public static HttpResult sendGet(String url, final Map<String, Object> params) throws Exception {
        return sendGet(url,null,params);
    }
    public static HttpResult sendGet(String url) throws Exception {
        return sendGet(url,null,null);
    }
    /**
     * 发送Post请求
     * @param url
     * @param headers
     * @param params
     * @param encoding
     * @return
     * @throws Exception
     */
    public static HttpResult sendPost(String url, final Map<String, String> headers, final Map<String, Object> params,
                                      final String encoding) throws Exception {
        URL URL = new URL(url);
        URI URI = new URI(URL.getProtocol(), URL.getHost(), URL.getPath(), URL.getQuery(), null);
        final HttpPost post = new HttpPost(URI);
        post.setConfig(REQUEST_CONFIG);
        final List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue().toString());
            paramList.add(pair);
        }
        post.setEntity(new UrlEncodedFormEntity(paramList, encoding));
        if (null != headers)
        {
            post.setHeaders(assemblyHeader(headers));
        }
        return sendPost(post);
    }
    public static HttpResult sendPost(String url, final Map<String, Object> params, final String encoding)
            throws Exception {
        return sendPost(url, null, params, encoding);
    }
    public static HttpResult sendPost(String url, final Map<String, Object> params)
            throws Exception {
        return sendPost(url, null, params, HttpConstants.DEFAULT_ENCODING);
    }

    /**
     * 发送post请求
     * @param url
     * @param data
     * @return
     * @throws Exception
     */
    public static HttpResult sendPost(String url,String data) throws Exception {
        URL URL = new URL(url);
        URI URI = new URI(URL.getProtocol(), URL.getHost(), URL.getPath(), URL.getQuery(), null);
        final HttpPost post = new HttpPost(URI);
        post.setConfig(REQUEST_CONFIG);
        StringEntity stringEntity = new StringEntity(data, ContentType.create("application/json","UTF-8"));
        post.setEntity(stringEntity);
        return sendPost(post);
    }

    /**
     * 发送Post请求
     * @param post
     * @return
     * @throws Exception
     */
    private static HttpResult sendPost(HttpPost post) throws Exception {
        final CloseableHttpClient client = HttpClients.createDefault();
        final HttpResponse response = client.execute(post);
        if (null == response) {
            throw new RuntimeException("send post connect response is null");
        }
        final HttpResult result = new HttpResult();
        result.setStatusCode(response.getStatusLine().getStatusCode());
        result.setHeaders(response.getAllHeaders());
        result.setHttpEntity(response.getEntity());
        return result;
    }

    /**
     * 装配请求头
     * @param headers
     * @return
     */
    private static Header[] assemblyHeader(final Map<String, String> headers) {
        Header[] allHeader = new BasicHeader[headers.size()];
        int i = 0;
        for (String str : headers.keySet()) {
            allHeader[i] = new BasicHeader(str, headers.get(str));
            i++;
        }
        return allHeader;
    }

    /**
     * 装配Get请求参数
     * @param parameters
     * @return
     */
    private static String assemblyParameter(final Map<String, Object> parameters) {
        StringBuilder sbud = new StringBuilder("?");
        for (String str : parameters.keySet()) {
            sbud.append(str).append("=").append(parameters.get(str)).append("&");
        }
        if (sbud.length() > 0) {
            sbud.deleteCharAt(sbud.length() - 1);
        }
        return sbud.toString();
    }

    public static void main(String[] args) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("wd", "1");
            Method sendGet = HttpClientUtils.class.getMethod("sendGet", String.class);
            HttpResult resultRef = (HttpResult)sendGet.invoke(HttpClientUtils.class,"http://www.baidu.com");
            HttpResult result = sendGet("http://www.baidu.com/s",map);
            System.out.println(resultRef.getResponseContent());
            System.out.println(result.getResponseContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
