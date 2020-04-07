package com.xinyu.test_boot.global;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.xinyu.test_boot.global.GlobalConst.ErrorCode;

/**
 * 正常业务返回错误码
 * @author: yx
 * @date: 2020年1月13日 下午4:06:52
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String message;
	private Object body;
	private long timeStamp;

	public static Response ok() {
		Response r = new Response();
		r.setCode("200");
		r.setTimeStamp(System.currentTimeMillis());
		return r;
	}

	public static Response ok(Object body) {
		Response r = new Response();
		r.setCode("200");
		r.setBody(body);
		return r;
	}

	public static Response displayOk(Object body) {
		Response r = new Response();
		r.setCode("200");
		r.setMessage("ok");
		r.setBody(body);
		r.setTimeStamp(System.currentTimeMillis());
		return r;
	}

	public static Response error(String code, String message) {
		Response r = new Response();
		r.setCode(code);
		r.setMessage(message);
		return r;
	}
	
	public static Response error(ErrorCode message) {
		Response r = new Response();
		r.setCode(message.getCode());
		r.setMessage(message.getMsg());
		r.setTimeStamp(System.currentTimeMillis());
		return r;
	}

	public static Response contentOk(Object body) {
		Response r = new Response();
		r.setCode("200");
		r.setBody(body);
		return r;
	}

	/**
	 * 服务请求成功
	 * @param code 响应状态码
	 * @param message 响应提示信息
	 * @param data  响应消息体
	 * @return
	 */
	public static Response response(String code, String message, Object data) {
		Response r = new Response();
		r.setCode(code);
		r.setMessage(message);
		r.setBody(data);
		r.setTimeStamp(System.currentTimeMillis());
		return r;
	}

	public static Response responseCode(ErrorCode message, Object data) {
		Response r = new Response();
		r.setCode(message.getCode());
		r.setMessage(message.getMsg());
		r.setBody(data);
		r.setTimeStamp(System.currentTimeMillis());
		return r;
	}
	
	/**
	 * 服务异常
	 * 
	 * @param code
	 *            响应状态码
	 * @param message
	 *            响应提示信息
	 * @param data
	 *            响应消息体
	 * @return
	 */
	public static Response exception(String code, String message, Object data) {
		Response r = new Response();
		r.setCode(code);
		r.setBody(data);
		r.setMessage(message);
		r.setTimeStamp(System.currentTimeMillis());
		return r;
	}
}
