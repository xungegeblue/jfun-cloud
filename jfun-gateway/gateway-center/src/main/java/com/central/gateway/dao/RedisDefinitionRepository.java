package com.central.gateway.dao;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 谢镜勋
 * @Date 2019/4/24
 */
@Slf4j
@Component
public class RedisDefinitionRepository implements RouteDefinitionRepository {


    public static final String GATEWAY_ROUTES = "geteway_routes";

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {

        log.info("获取路由信息...");
        List<RouteDefinition> routeDefinitions = new ArrayList<>();
        redisTemplate.opsForHash().values(GATEWAY_ROUTES).stream().forEach(routeDefinition -> {
            routeDefinitions.add(JSON.parseObject(routeDefinition.toString(), RouteDefinition.class));
        });
        return Flux.fromIterable(routeDefinitions);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        log.info("保存路由:{}", route.toString());
        return route
                .flatMap(routeDefinition -> {
                    redisTemplate.opsForHash().put(GATEWAY_ROUTES, routeDefinition.getId(),
                            JSON.toJSONString(routeDefinition));
                    return Mono.empty();
                });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        log.info("删除路由:{}", routeId);
        return routeId.flatMap(id -> {
            if (redisTemplate.opsForHash().hasKey(GATEWAY_ROUTES, id)) {
                redisTemplate.opsForHash().delete(GATEWAY_ROUTES, id);
                return Mono.empty();
            }
            return Mono.defer(() -> Mono.error(new NotFoundException("RouteDefinition not found: " + routeId)));
        });
    }


}
