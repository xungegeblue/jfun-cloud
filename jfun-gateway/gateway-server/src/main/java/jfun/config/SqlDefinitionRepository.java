package jfun.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jfun.server.IRouteDefinitionService;
import jfun.util.GsonUtil;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author 谢镜勋
 * @Date 2019/4/24
 */
@Component
@Slf4j
public class SqlDefinitionRepository implements RouteDefinitionRepository {
    @Autowired
    IRouteDefinitionService iRouteDefinitionService;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {

        log.info("获取路由信息...");

        List<RouteDefinition> routeList = iRouteDefinitionService.getAll().stream().map(routeDefinitionEntity -> {
            RouteDefinition d = new RouteDefinition();

            d.setUri(URI.create(routeDefinitionEntity.getUri()));
            d.setId(routeDefinitionEntity.getId()+"");
            d.setOrder(routeDefinitionEntity.getOrders());

            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<FilterDefinition>>() {
            }.getType();

            List<FilterDefinition> filterDefinitions = gson.fromJson(routeDefinitionEntity.getFilters(), type);
            d.setFilters(filterDefinitions);

            type = new TypeToken<ArrayList<PredicateDefinition>>() {
            }.getType();
            List<PredicateDefinition> predicateDefinitions = gson.fromJson(routeDefinitionEntity.getPredicates(),type);
            d.setPredicates(predicateDefinitions);
            log.info("路由信息：\n {}", GsonUtil.prettyJSON(d));
            return d;
        }).collect(Collectors.toList());


        return Flux.fromIterable(routeList);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }
}
