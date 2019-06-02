package com.central.oauth2.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.central.common.constant.SecurityConstants;
import com.central.common.feign.UserService;
import com.central.common.model.Result;
import com.central.common.redis.template.RedisRepository;
import com.central.oauth2.exection.ValidateCodeException;
import com.central.oauth2.service.IValidateCodeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: miv
 * @Date: 2019-05-31 18:20
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Service
@Slf4j
public class ValidateCodeService implements IValidateCodeService {
    @Autowired
    private RedisRepository redisRepository;

    @Resource
    UserService userService;

    @Override
    public void saveImageCode(String deviceId, String imageCode) {
        redisRepository.setExpire(buildKey(deviceId),imageCode,SecurityConstants.DEFAULT_IMAGE_EXPIRE);
    }

    @Override
    public Result sendSmsCode(String mobile) {
        Object tempCode = redisRepository.get(buildKey(mobile));
        if(tempCode!=null){
            log.error("用户:{},验证码未失效:{}",mobile,tempCode);
            return Result.failed("验证码未失效，请失效后再次申请");
        }

        //通过手机号码查询用户是否存在,如果用户手机存在发送验证码


        String code = RandomUtil.randomNumbers(4);
        log.info("短信发送请求消息中心 -> 手机号:{} -> 验证码：{}", mobile, code);
        redisRepository.setExpire(buildKey(mobile), code, SecurityConstants.DEFAULT_IMAGE_EXPIRE);
        return Result.succeed("true");
    }

    @Override
    public String getCode(String deviceId) {
        return (String)redisRepository.get(buildKey(deviceId));
    }

    @Override
    public void remove(String deviceId) {
        redisRepository.del(buildKey(deviceId));
    }

    @Override
    public void validate(HttpServletRequest request) {
        String deviceId = request.getParameter("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new ValidateCodeException("请在请求参数中携带deviceId参数");
        }
        String code = this.getCode(deviceId);
        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request, "validCode");
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("请填写验证码");
        }

        if (code == null) {
            throw new ValidateCodeException("验证码不存在或已过期");
        }

        if (!StringUtils.equals(code, codeInRequest)) {
            throw new ValidateCodeException("验证码不正确");
        }

        this.remove(deviceId);

    }
    private String buildKey(String deviceId) {
        return SecurityConstants.DEFAULT_CODE_KEY + ":" + deviceId;
    }
}
