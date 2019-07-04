package com.central.gateway.rest;

import com.central.gateway.server.DynamicRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.route.RouteDefinition;


import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 谢镜勋
 * @Date 2019/4/26
 * 管理路由
 */
@RestController
public class RouteController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    DynamicRouteService dynamicRouteService;

    @Autowired
    RouteDefinitionRepository repository;

    /**
     * 获取路由信息
     * @return
     */
    @GetMapping("route")
    public ResponseEntity data() {
        Object list = repository.getRouteDefinitions();
        return ResponseEntity.ok(list);
    }

    @PostMapping("route")
    public ResponseEntity create(@RequestBody RouteDefinition routeDefinition) {
        dynamicRouteService.add(routeDefinition);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("route")
    public ResponseEntity del(@RequestParam String id) {
        dynamicRouteService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("route")
    public ResponseEntity update(@RequestBody RouteDefinition routeDefinition) {
        dynamicRouteService.delete(routeDefinition.getId());
        dynamicRouteService.add(routeDefinition);
        return ResponseEntity.ok().build();
    }

}
