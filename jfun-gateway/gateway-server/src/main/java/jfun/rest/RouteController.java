package jfun.rest;

import jfun.entity.RouteDefinitionEntity;
import jfun.server.DynamicRouteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;


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
    DynamicRouteService dynamicRouteService;

    //添加路由
    @PostMapping("/route")
    public ResponseEntity create(@RequestBody RouteDefinitionEntity routeDefinition) {
        RouteDefinition rd = new RouteDefinition();
        BeanUtils.copyProperties(routeDefinition, rd);
        dynamicRouteService.add(rd);

        return ResponseEntity.ok().build();
    }

    //删除路由
    @DeleteMapping("/route/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        dynamicRouteService.delete(id);
        return ResponseEntity.ok().build();
    }


    //查询路由
    @GetMapping("/route")
    public ResponseEntity get(@RequestBody RouteDefinitionEntity routeDefinition) {
        return ResponseEntity.ok().build();
    }


    //更新路由
    @PutMapping("/route")
    public ResponseEntity update(@RequestBody RouteDefinitionEntity routeDefinition) {
        return ResponseEntity.ok().build();
    }

}
