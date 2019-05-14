package http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;

/**
 * 请求返回结果
 * @author axiaogezi
 */
public class HttpResult {
    protected static Logger log = LoggerFactory.getLogger(HttpResult.class);
    /**
     * 请求返回状态码
     */
    private int statusCode;
    /**
     * 头部信息
     */
    private HashMap<String, Header> headerAll;
    /**
     * 请求响应返回实体
     */
    private HttpEntity httpEntity;

    /**
     * 获取结果状态码
     *
     * @return
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * 设置结果状态码
     *
     * @param statusCode
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * 获取结果头部信息
     *
     * @return
     */
    public HashMap<String, Header> getHeaders() {
        return headerAll;
    }

    /**
     * 设置结果头部信息
     *
     * @param headers
     */
    public void setHeaders(Header[] headers) {
        headerAll = new HashMap<String, Header>(16);
        for (Header header : headers) {
            headerAll.put(header.getName(), header);
        }
    }

    /**
     * 获取响应结果
     *
     * @return
     */
    public HttpEntity getHttpEntity() {
        return httpEntity;
    }

    /**
     * 设置响应结果
     *
     * @param httpEntity
     */
    public void setHttpEntity(HttpEntity httpEntity) {
        this.httpEntity = httpEntity;
    }

    /**
     * 得到返回值
     * @param encode
     * @return
     */
    public String getResponseContent(String encode) {
        String resultStr = "";
        try {
            resultStr = EntityUtils.toString(this.httpEntity,encode);
        } catch (IOException e) {
            log.error("get http response content error", e);
        }finally {
            try {
                EntityUtils.consume(this.httpEntity);
            } catch (IOException e) {
                log.error("close http entity error", e);
            }
        }
        return resultStr;
    }
    public String getResponseContent() {
        return getResponseContent(HttpConstants.DEFAULT_ENCODING);
    }
}
