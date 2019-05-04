package jfun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jfun.entity.RouteDefinitionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.springframework.web.bind.annotation.Mapping;


/**
 * @Author 谢镜勋
 * @Date 2019/4/26
 * 持久化路由信息
 */
@Mapper
public interface RouteMapper extends BaseMapper<RouteDefinitionEntity> {

}
