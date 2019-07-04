package com.central.resource.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: miv
 * @Date: 2019-06-10 16:19
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description: 权限不足
 */
@Component("customAccessDeniedHandler")
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    /**
     * 参考https://www.cnblogs.com/bndong/p/10275430.html
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        try {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
