package com.central.gateway.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.central.gateway.server.DynamicRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * @author: miv
 * @Date: 2019-05-20 15:06
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description: 网关的配置可用通过REST接口，或者是通过nacos的指定文件
 */
@Slf4j
@Component
@Configuration
public class DynamicRouteServiceImplByNacos {
    @Autowired
    private DynamicRouteService dynamicRouteService;

    /**
     * nacos地址
     */
    public String nacosAddr = "127.0.0.1:8848";

    public DynamicRouteServiceImplByNacos() {
        dynamicRouteByNacosListener("gateway-init", "DEFAULT_GROUP");
    }

    private void dynamicRouteByNacosListener(String dataId, String group) {
        try {

            log.info("nacos监听，nacos地址为：{},配置文件groud:{},data:{}",nacosAddr,group,dataId);
            ConfigService configService = NacosFactory.createConfigService(nacosAddr);
            String content = configService.getConfig(dataId, group, 5000);
            System.out.println(content);
            configService.addListener(dataId, group, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    RouteDefinition definition = JSON.parseObject(configInfo, RouteDefinition.class);
                    dynamicRouteService.update(definition);
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        } catch (NacosException e) {
            log.error("网关nacos异常:{}", e.toString());
        }
    }

}
