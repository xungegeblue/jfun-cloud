package jfun.config;

import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 谢镜勋
 * @Date 2019/4/24
 */
@Component
public class SqlDefinitionRepository implements RouteDefinitionRepository {
    public Flux<RouteDefinition> getRouteDefinitions() {

        List<RouteDefinition> routeList = new ArrayList<>();

        RouteDefinition rd = getJfunRouteDefinitions();
        routeList.add(rd);
        return Flux.fromIterable(routeList);
    }
    //手动设置路由
    private RouteDefinition getJfunRouteDefinitions() {
        RouteDefinition rd = new RouteDefinition();
        URI uri = UriComponentsBuilder.fromHttpUrl("http://httpbin.org:80/get").build().toUri();
        rd.setId("test");
        rd.setUri(uri);


        List<PredicateDefinition> pds = new ArrayList<>();
        PredicateDefinition pd = new PredicateDefinition();
        Map<String,String> map = new HashMap<>();


        map.put("pattern","/foo");
        pd.setName("Path");
        pd.setArgs(map);

        pds.add(pd);
        rd.setPredicates(pds);
        return rd;
    }

    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }
}
