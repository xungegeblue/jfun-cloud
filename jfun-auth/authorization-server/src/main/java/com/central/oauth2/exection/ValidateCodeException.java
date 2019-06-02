package com.central.oauth2.exection;

import org.springframework.security.core.AuthenticationException;

/**
 * @Auther: miv
 * @Date: 2019-06-01 02:25
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
public class  ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}