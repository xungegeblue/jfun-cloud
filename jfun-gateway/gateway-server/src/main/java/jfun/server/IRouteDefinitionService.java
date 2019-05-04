package jfun.server;

import jfun.entity.RouteDefinitionEntity;

import java.util.List;

/**
 * @Auther: miv
 * @Date: 2019-05-04 12:08
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
public interface IRouteDefinitionService {

    List<RouteDefinitionEntity> getAll();

    void insert(RouteDefinitionEntity resource);

    void update(RouteDefinitionEntity resource);

    void delete(Integer id);
}
