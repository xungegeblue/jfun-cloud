package com.central.gateway.server;

import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author: miv
 * @Date: 2019-05-20 12:25
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Service
public class DynamicRouteService implements ApplicationEventPublisherAware {

    @Resource
    private RouteDefinitionWriter routeDefinitionWriter;

    private ApplicationEventPublisher publisher;

    private void notifyChanged() {
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    /**
     * 增加路由
     */
    public void add(RouteDefinition definition) {
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        notifyChanged();
    }


    /**
     * 更新路由
     */
    public void update(RouteDefinition definition) {
        this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        notifyChanged();
    }

    /**
     * 删除路由
     */
    public void delete(String id) {
        this.routeDefinitionWriter.delete(Mono.just(id));
        notifyChanged();
    }


}
