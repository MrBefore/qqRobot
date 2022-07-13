package com.robot.qq.service.impl;

import com.robot.qq.entity.CallbackMsg;
import com.robot.qq.enums.MsgTypeEnum;
import com.robot.qq.service.SearchService;
import com.robot.qq.service.SendToGroupService;
import com.robot.qq.service.SendToUserService;
import com.robot.qq.util.ImageUtil;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sunhao
 * @data 2022/6/24
 */
@Service
public class SearchServiceImpl implements SearchService {

    /**
     * 群号
     */
    private final String groupId = "934631369";

    /**
     * 测试群号
     */
    private final String test = "240956911";

    /**
     * QQ群关键字匹配规则
     */
    private final String REGEX_SEARCH = "^给我[个只堆].+";

    /**
     * 关键字前缀
     */
    private final String KEY_WORD_PREFIX = "给我[个只堆]";

    /**
     * 百度图片搜索结果url匹配规则
     */
    private final String REGEX_BAIDU_IMAGE = "\"thumbURL\":\"[a-zA-z]+://[^\\s\"]*";

    /**
     * url前缀
     */
    private final String URL_PREFIX = "\"thumbURL\":\"";

    @Autowired
    private SendToUserService toUserService;
    @Autowired
    private SendToGroupService toGroupService;

    /**
     * 发送图片
     *
     * @param message 回调信息
     */
    @Override
    public void search(CallbackMsg message) {
        if (Pattern.matches(REGEX_SEARCH, message.getMqMsg())) {
            String url = this.getImageFromHuashi(message.getMqMsg());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
            String now = sdf.format(new Date());
            if (message.getMqType().equals(MsgTypeEnum.MSG_TYPE_RRI_EDN.getCode()) || message.getMqType().equals(MsgTypeEnum.MSG_TYPE_GROUP.getCode())) {
                String fileUrl = "D:\\Photo\\baiduImages\\" + now + ".PNG";
                ImageUtil.saveImage(url, fileUrl);
                if (message.getMqType().equals(MsgTypeEnum.MSG_TYPE_RRI_EDN.getCode())) {
                    toUserService.sendPhotoToUser(message, fileUrl);
                }
                if (message.getMqType().equals(MsgTypeEnum.MSG_TYPE_GROUP.getCode())) {
                    if (message.getMqFromId().equals(groupId)) {
                        toGroupService.sendPhotoToGroup(message, fileUrl, groupId);
                    }
                    if (message.getMqFromId().equals(test)) {
                        toGroupService.sendPhotoToGroup(message, fileUrl, test);
                    }
                }
            }
        }
    }

    /**
     * 从rt.huashi6.com获取图片
     * @param mqMsg 关键词
     * @return 图片url
     */
    private String getImageFromHuashi(String mqMsg) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        Random random = new Random();
        // 关键字
        String keyWord = mqMsg.replaceAll(KEY_WORD_PREFIX, "");
//        String keyWord = "伊莉雅";
        String page = "1";
        HttpPost httpPost = new HttpPost("https://rt.huashi6.com/search/all");
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("word", keyWord));
        nameValuePairs.add(new BasicNameValuePair("index", page));
        UrlEncodedFormEntity urlEncodedFormEntity = null;
        try {
            urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpPost.setEntity(urlEncodedFormEntity);
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36");
        String html = null;
        try (CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {
            HttpEntity httpEntity = httpResponse.getEntity();
            html = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Pattern pagePattern = Pattern.compile("\"pageCount\":[0-9]*");
        assert html != null;
        Matcher matcher1 = pagePattern.matcher(html);
        int index = 1;
        // 获取总页数并随机选取一页
        if (matcher1.find()){
            try {
                page = matcher1.group().replace("\"pageCount\":", "");
                index = random.nextInt(Integer.parseInt(page));
            } catch (NumberFormatException e) {
                index = 1;
            }
        }
        // 再次发送请求获取图片
        nameValuePairs.clear();
        nameValuePairs.add(new BasicNameValuePair("index", index + ""));
        nameValuePairs.add(new BasicNameValuePair("word", keyWord));
        UrlEncodedFormEntity urlEncodedFormEntity1 = null;
        try {
            urlEncodedFormEntity1 = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpPost.setEntity(urlEncodedFormEntity1);
        try (CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {
            HttpEntity httpEntity = httpResponse.getEntity();
            html = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // url匹配规则
        Pattern pattern = Pattern.compile("\"originalPath\":\"[^\\s\"]*");
        assert html != null;
        Matcher matcher = pattern.matcher(html);
        List<String> urls = new ArrayList<>();
        while (matcher.find()) {
            urls.add(matcher.group().replace("\"originalPath\":\"", ""));
        }
        // 返回随机图片
        return  "https://img2.huashi6.com/" + urls.get(random.nextInt(urls.size() - 1));
    }

    /**
     * 从百度获取图片
     *
     * @param mqMsg 关键词
     * @return 图片url
     */
    private String getImage(String mqMsg) {
        // 存储Cookie
        BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        // 获取图片
        Random random = new Random();
        // 随机页数
        int page = random.nextInt(60);
        // 每页条数
        int size = 30;
        // 关键字
        String keyWord = mqMsg.replaceAll(KEY_WORD_PREFIX, "");
        HttpGet httpGet = new HttpGet("https://image.baidu.com/search/acjson?tn=resultjson_com&logid=7579204787814324805&ipn=rj&ct=201326592&is=&fp=result&fr=&word=" + keyWord + "&queryWord=" + keyWord
                + "&cl=2&lm=-1&ie=utf-8&oe=utf-8&adpicid=&st=-1&z=&ic=0&hd=&latest=&copyright=&s=&se=&tab=&width=&height=&face=0&istype=2&qc=&nc=1&expermode=&nojc=&isAsync=&pn=" + page + "&rn=" + size + "&gsm=3c&1656312479208=");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36");
        String html = null;
        try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
            HttpEntity httpEntity = httpResponse.getEntity();
            html = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 获取图片url列表
        Pattern pattern = Pattern.compile(REGEX_BAIDU_IMAGE);
        assert html != null;
        Matcher matcher = pattern.matcher(html);
        List<String> urls = new ArrayList<>();
        while (matcher.find()) {
            urls.add(matcher.group().replace(URL_PREFIX, ""));
        }
        // 返回随机图片
        return urls.get(random.nextInt(urls.size() - 1));
    }
}
