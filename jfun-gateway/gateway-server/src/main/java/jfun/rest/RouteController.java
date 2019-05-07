package jfun.rest;

import jfun.entity.RouteDefinitionEntity;
import jfun.server.DynamicRouteService;
import jfun.server.impl.RouteDefinitionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.actuate.GatewayControllerEndpoint;
import org.springframework.cloud.gateway.route.RouteDefinition;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author 谢镜勋
 * @Date 2019/4/26
 * 管理路由
 */
@RestController
public class RouteController {

    @Autowired
    DynamicRouteService dynamicRouteService;

    @Autowired
    RouteDefinitionService routeDefinitionService;

    //添加路由
    @PostMapping("/route")
    public ResponseEntity create(@RequestBody RouteDefinitionEntity routeDefinition) {
        RouteDefinition rd = new RouteDefinition();
        Timestamp t = new Timestamp(new Date().getTime());

        routeDefinition.setCreatedTime(t);
        routeDefinition.setUpdatedTime(t);
        routeDefinition.setCreatedBy("system");
        routeDefinition.setUpdatedBy("system");

        BeanUtils.copyProperties(routeDefinition, rd);
//        GatewayControllerEndpoint a;
        dynamicRouteService.add(rd);
        routeDefinitionService.insert(routeDefinition);
        return ResponseEntity.ok().build();
    }

    //删除路由
    @DeleteMapping("/route/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        dynamicRouteService.delete(id);
        routeDefinitionService.delete(Integer.valueOf(id));
        return ResponseEntity.ok().build();
    }


    //查询路由
    @GetMapping("/route")
    public ResponseEntity get() {
        return ResponseEntity.ok(routeDefinitionService.getAll());
    }


    //更新路由
    @PutMapping("/route")
    public ResponseEntity update(@RequestBody RouteDefinitionEntity routeDefinition) {
        RouteDefinition rd = new RouteDefinition();
        BeanUtils.copyProperties(routeDefinition, rd);
        dynamicRouteService.update(rd);
        routeDefinitionService.update(routeDefinition);
        return ResponseEntity.ok().build();
    }

}
