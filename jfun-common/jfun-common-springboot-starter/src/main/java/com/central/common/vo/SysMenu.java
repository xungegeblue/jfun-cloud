package com.central.common.vo;

import lombok.Data;

/**
 * @Auther: miv
 * @Date: 2019-05-20 20:13
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Data
public class SysMenu {
    private static final long serialVersionUID = 749360940290141180L;

    private Long parentId;
    private String name;
    private String css;
    private String url;
    private String path;
    private Integer sort;
    private Integer type;
    private Boolean hidden;
    /**
     * 请求的类型
     */
    private String pathMethod;
}
