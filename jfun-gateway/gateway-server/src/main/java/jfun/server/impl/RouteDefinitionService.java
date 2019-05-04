package jfun.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jfun.dao.RouteMapper;
import jfun.entity.RouteDefinitionEntity;
import jfun.server.IRouteDefinitionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: miv
 * @Date: 2019-05-04 12:09
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Service
public class RouteDefinitionService extends ServiceImpl<RouteMapper, RouteDefinitionEntity> implements IRouteDefinitionService {

    @Override
    public List<RouteDefinitionEntity> getAll() {
        List<RouteDefinitionEntity> list = baseMapper.selectList(null);
        return list;
    }

    @Override
    public void insert(RouteDefinitionEntity resource) {
        baseMapper.insert(resource);
    }

    @Override
    public void update(RouteDefinitionEntity resource) {
        baseMapper.updateById(resource);
    }

    @Override
    public void delete(Integer id) {
        baseMapper.deleteById(id);
    }

}
