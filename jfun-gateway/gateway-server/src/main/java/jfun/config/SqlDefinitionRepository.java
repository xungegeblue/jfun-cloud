package jfun.config;

import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author 谢镜勋
 * @Date 2019/4/24
 */
public class SqlDefinitionRepository implements RouteDefinitionRepository {
    public Flux<RouteDefinition> getRouteDefinitions() {
        return null;
    }

    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }
}
