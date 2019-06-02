package com.central.oauth2.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Auther: miv
 * @Date: 2019-06-02 19:47
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@ConfigurationProperties(prefix = "system")
@Data
public class SecurityProperties {


    private OauthPageProperties oauthLogin = new OauthPageProperties();

}