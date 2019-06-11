package com.central.oauth2.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @Auther: miv
 * @Date: 2019-06-10 05:49
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Setter
@Getter
public class AuthMessage extends OAuth2Exception {
    int code;
    String msg;

    public AuthMessage(String msg) {
        super(msg);
    }
}
