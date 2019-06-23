package com.central.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.ConstructorArgs;

/**
 * @Auther: miv
 * @Date: 2019-06-21 16:26
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description: 权限对应的URL+Method就是资源
 */
@Data
@NoArgsConstructor
public class Resource {

    private String name;
    private String url;
    private String method;
    private String description;
}
