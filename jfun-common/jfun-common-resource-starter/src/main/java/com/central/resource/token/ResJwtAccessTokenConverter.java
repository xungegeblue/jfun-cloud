package com.central.resource.token;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: miv
 * @Date: 2019-06-21 04:48
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Slf4j
public class ResJwtAccessTokenConverter extends JwtAccessTokenConverter {
    public ResJwtAccessTokenConverter() {
        super();
        super.setAccessTokenConverter(new JwtUserAuthenticationConverter());
    }

    public class JwtUserAuthenticationConverter extends DefaultAccessTokenConverter {


        public JwtUserAuthenticationConverter() {
            super.setUserTokenConverter(new JwtfaultUserAuthenticationConverter());
        }


        public class JwtfaultUserAuthenticationConverter extends DefaultUserAuthenticationConverter {


            @Override
            public Map<String, ?> convertUserAuthentication(Authentication authentication) {
                Map<String, Object> response = new LinkedHashMap<String, Object>();
                response.put(USERNAME, authentication.getName());
                if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
                    response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
                }
                response.put("APP", "JFUN CLOUD");
                return response;
            }

            @Override
            public Authentication extractAuthentication(Map<String, ?> map) {
                return super.extractAuthentication(map);
            }
        }

    }
}
