package com.robot.qq.controller;

import com.robot.qq.entity.CallbackMsg;
import com.robot.qq.entity.ReqResult;
import com.robot.qq.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;


/**
 * 从百度图库搜索图片
 *
 * @author user
 */
@RequestMapping("/search")
@RestController
public class ImageSearchController {

    @Autowired
    private SearchService searchService;

    /**
     * 根据关键词搜索图片
     *
     * @param message 回调信息
     * @param request request请求
     * @return 统一出参
     * @throws Exception Exception
     */
    @PostMapping("/image")
    public ReqResult searchImage(@RequestBody CallbackMsg message, HttpServletRequest request) throws Exception {
        message.setMqMsg(URLDecoder.decode(message.getMqMsg(), "utf-8"));
        searchService.search(message);
        return new ReqResult(1);
    }
}
