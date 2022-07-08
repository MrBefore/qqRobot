package com.robot.qq.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robot.qq.entity.DataResult;
import com.robot.qq.entity.PushMessage;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Http工具类
 *
 * @author wangzk
 */
public final class HttpApiUtil {
    private static CloseableHttpClient httpClient;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static DataResult sendGet(String host, String port, String token, String function, Map<String, Object> params) {
        try {
            DataResult dataResult = null;
            httpClient = HttpClients.createDefault();
            StringBuilder sb = new StringBuilder();
            // 定义请求的参数http://localhost:7788/MyQQHTTPAPI
            sb.append("http://").append(host).append(":")
                    .append(port).append("/MyQQHTTPAPI");
            List<NameValuePair> nvps = new ArrayList<>();
            NameValuePair nvp = null;

            nvp = new BasicNameValuePair("function", function);
            nvps.add(nvp);
            nvp = new BasicNameValuePair("token", token);
            nvps.add(nvp);
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                Object value = entry.getValue();
                nvp = new BasicNameValuePair(entry.getKey(), value == null ? null : value.toString());
                nvps.add(nvp);
            }
            URI uri = new URIBuilder(sb.toString()).setParameters(nvps).build();

            HttpGet getRequest = new HttpGet(uri);
            CloseableHttpResponse resp = httpClient.execute(getRequest);
            // 判断返回状态是否为200
            if (resp.getStatusLine().getStatusCode() == 200) {
                String rs = EntityUtils.toString(resp.getEntity(), "UTF-8");
                dataResult = OBJECT_MAPPER.readValue(rs, DataResult.class);
                return dataResult;
            } else {
                throw new RuntimeException("失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("向插件发送请求失败！");
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static Map<String, Object> sendPost(String host, String port, PushMessage message) {
        try {
            String json = OBJECT_MAPPER.writeValueAsString(message);
            return sendPost(host, port, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("解析json出错");
        }

    }

    private static Map<String, Object> sendPost(String host, String port, String json) {
        try {
            Map<String, Object> dataResult = null;
            httpClient = HttpClients.createDefault();
            StringBuilder sb = new StringBuilder();
            // 定义请求的参数http://localhost:7788/MyQQHTTPAPI
            sb.append("http://").append(host).append(":")
                    .append(port).append("/MyQQHTTPAPI");
            HttpPost postRequest = new HttpPost(sb.toString());
            StringEntity entity = new StringEntity(json, Charset.forName("GBK"));
            // 发送Json格式的数据请求
            entity.setContentType("application/json");
            postRequest.setEntity(entity);
            String resp = httpClient.execute(postRequest, new BasicResponseHandler());
            dataResult = OBJECT_MAPPER.readValue(resp, Map.class);
            return dataResult;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("向插件发送请求失败！");
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
