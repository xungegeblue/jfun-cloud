package com.central.resource.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: miv
 * @Date: 2019-06-13 16:48
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
//@RestControllerAdvice
//public class ExceptionHandlerAdvice {
//
//    /**
//     * IllegalArgumentException异常处理返回json
//     * 状态码:400
//     *
//     * @param exception
//     * @return
//     */
//    @ExceptionHandler({IllegalArgumentException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Map<String, Object> badRequestException(IllegalArgumentException exception) {
//        Map<String, Object> data = new HashMap<>();
//        data.put("resp_code", HttpStatus.BAD_REQUEST.value());
//        data.put("resp_msg", exception.getMessage());
//
//        return data;
//    }
//
//    /**
//     * AccessDeniedException异常处理返回json
//     * 状态码:403
//     *
//     * @param exception
//     * @return
//     */
//    @ExceptionHandler({AccessDeniedException.class})
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public Map<String, Object> badMethodExpressException(AccessDeniedException exception) {
//        Map<String, Object> data = new HashMap<>();
//        data.put("resp_code", HttpStatus.FORBIDDEN.value());
//        data.put("resp_msg", exception.getMessage());
//
//        return data;
//    }
//}
