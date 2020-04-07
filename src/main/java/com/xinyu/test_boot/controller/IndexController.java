package com.xinyu.test_boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xinyu.test_boot.annotation.RequestLimit;
import com.xinyu.test_boot.global.Response;

/**
 * 接口防刷
 * @author: yx
 * @date: 2020年1月13日 下午3:54:03
 */
@RestController
@RequestMapping("/index")
@RequestLimit(maxCount = 5,second = 1)
public class IndexController {

    /**
     * @RequestLimit 修饰在方法上，优先使用其参数
     * @return
     */
    @GetMapping("/test1")
    @RequestLimit(second=5, maxCount=5, needLogin=true)
    public Response test(){
        //TODO ...
        return Response.ok();
    }

    /**
     * @RequestLimit 修饰在类上，用的是类的参数
     * @return
     */
    @GetMapping("/test2")
    public Response test2(){
        //TODO ...
        return Response.ok();
    }
}