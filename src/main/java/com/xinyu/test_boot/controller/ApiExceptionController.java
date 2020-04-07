package com.xinyu.test_boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xinyu.test_boot.exception.BusinessException;
import com.xinyu.test_boot.global.Response;
import com.xinyu.test_boot.helper.GsonUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义异常
 * @author: yx
 * @date: 2020年1月13日 下午3:54:03
 */
@Slf4j
@RestController
@RequestMapping("/exception")
public class ApiExceptionController {

	@GetMapping(value = "/test1/{type}")
	public Response giftCheckList(@PathVariable Integer type) {
		log.info("测试自定义异常：type={},",GsonUtil.toJson(type));
		if(type == null || type == 0) {
			throw new BusinessException("300", "null error");
		}
		return Response.ok();
	}

}