package com.central.oauth2.rest;

import com.central.common.model.Result;
import com.central.oauth2.constants.FromLoginConstant;
import com.central.oauth2.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: miv
 * @Date: 2019-06-02 15:29
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description: 权限认知相关接口
 */
@Slf4j
@Controller
@SessionAttributes({"authorizationRequest"})
public class Oauth2Controller {


    private SecurityProperties securityProperties = new SecurityProperties();
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    //登陆页面
    @RequestMapping(FromLoginConstant.AFTER_LOGING_PAGE)
    public String login(Model model) {
        model.addAttribute("loginProcessUrl",FromLoginConstant.LOGIN_PROCESSING_URL);
        return securityProperties.getOauthLogin().getOauthLogin();
    }

    @RequestMapping(FromLoginConstant.LOGIN_PAGE)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public Result requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (null != savedRequest) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是:" + targetUrl);
            redirectStrategy.sendRedirect(request, response, FromLoginConstant.AFTER_LOGING_PAGE);
        }
        //如果访问的是接口资源
        return Result.failed("访问的服务需要身份认证，请引导用户到登录页");
    }


    /**
     * 自定义授权页面，注意：一定要在类上加@SessionAttributes({"authorizationRequest"})
     *
     * @param model   model
     * @param request request
     * @return String
     * @throws Exception Exception
     */
    @RequestMapping("/oauth/confirm_access")
    public String getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {

        AuthorizationRequest rq = (AuthorizationRequest) model.get("authorizationRequest");
        model.put("clientId", rq.getClientId());
        model.put("scopeList", rq.getScope());
        return securityProperties.getOauthLogin().getOauthGrant();
    }

}
