package com.central.user.vo;

/**
 * @Author 谢镜勋
 * @Date 2019/3/26
 */
public class Page extends com.baomidou.mybatisplus.extension.plugins.pagination.Page {
    private long page;

    /**
     * mybatis的当前页是从1开始的
     * @param page
     */
    public void setPage(long page) {
        this.page=page+1;
        this.setCurrent(this.page);
    }
    public long getPage(){
        return this.page;
    }
}
