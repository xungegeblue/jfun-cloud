package jfun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author 谢镜勋
 * @Date 2019/4/26
 * 路由配置的持久化对象
 */
@TableName(value = "gateway_routes")
@Data
public class RouteDefinitionEntity {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    @TableField(value = "route_id")
    String routeId;
    String uri;


    String predicates;
    String filters;
    Integer orders;
    String description;
    String status;
    @TableField(value = "created_time")
    Timestamp createdTime;

    @TableField(value = "updated_time")
    Timestamp updatedTime;

    @TableField(value = "created_by")
    String createdBy;
    @TableField(value = "updated_by")
    String updatedBy;
}
