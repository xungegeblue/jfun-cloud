package com.central.zuul.exection;

/**
 * @Auther: miv
 * @Date: 2019-06-10 16:17
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description: 无效token
 */

import com.central.common.model.Result;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws ServletException {
        Map<String, Object> map = new HashMap<String, Object>();
        Throwable cause = authException.getCause();

        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        try {
            Gson gson = new Gson();
            if (cause instanceof InvalidTokenException) {

                response.sendError(HttpStatus.UNAUTHORIZED.value());
            } else {
                response.sendError(HttpStatus.UNAUTHORIZED.value());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
