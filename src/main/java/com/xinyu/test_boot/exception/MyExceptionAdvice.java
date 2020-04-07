package com.xinyu.test_boot.exception;

import java.util.StringJoiner;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.expression.ParseException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xinyu.test_boot.global.GlobalConst.ErrorCode;
import com.xinyu.test_boot.global.Response;

@ControllerAdvice
public class MyExceptionAdvice {
	
	private final Logger logger = LoggerFactory.getLogger(MyExceptionAdvice.class);
	
	@Autowired
    protected MessageSource messageSource;

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Response defaultException(HttpServletRequest request, Exception ex){

		String code = ErrorCode.SYSTEM_ERROR.getCode();
        String result = null;
        if (ex instanceof HttpMessageNotReadableException) {
            code = ErrorCode.PARAM_ERROR.getCode();
        } else if (ex instanceof ParseException) {
        	code = ErrorCode.PARAM_ERROR.getCode();
        } else if (ex instanceof NumberFormatException) {
        	code = ErrorCode.PARAM_ERROR.getCode();
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            code = ErrorCode.HTTP_METHOD_NOT_SURPPORT.getCode();
        }
        String msg = null;
        if (ex instanceof BusinessException) {
            msg = ((BusinessException) ex).getErrorMessage();
            code = ((BusinessException) ex).getErrorCode();
            final Object data = ((BusinessException) ex).getResult();
            if (data != null) {
                if (data instanceof String) {
                    result = (String) data;
                }
            }
        } else if (ex instanceof SystemException) {
            msg = ((SystemException) ex).getErrorMessage();
            code = ((SystemException) ex).getErrorCode();
        } else if (ex instanceof MethodArgumentNotValidException) {
            // 参数错误
            code = ErrorCode.PARAM_ERROR.getCode();
            msg = getParseErr(ex);
        } else {
        	code = ErrorCode.UNKNOWN_ERROR.getCode();
        }
//        // 业务异常
//        else if (ex instanceof BizException) {
//            BizException bizException = (BizException) ex;
//            msg = bizException.getMessage();
//            code = bizException.getErrorCode().getCode();
//
//        } 
        if (msg == null) {
            msg = getMsgByException(code, ex, messageSource);
        }
        logger.error("code: " + code + "  msg: " + msg, ex);
        return Response.exception(code, msg, result);
	}
	
	/**
     * 异常解析
     *
     * @param ex 异常信息
     * @return 整理后字符串
     */
    private String getParseErr(Exception ex) {
        String result;
        StringJoiner sj = new StringJoiner(",");
        final BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
        for (ObjectError err : bindingResult.getAllErrors()) {
            if (err instanceof FieldError) {
                // err.getObjectName()，对象名;((FieldError) err).getField()，字段名;
                sj.add(((FieldError) err).getField() + err.getDefaultMessage());
            } else {
                sj.add(ex.getMessage());
            }
        }
        result = sj.toString();
        return result;
    }
	
	private String getMsgByException(String code, Throwable ex, MessageSource messageSource) {
        try {
            return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
        }

        return "";
    }

}