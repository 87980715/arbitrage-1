package com.shiyou.arbitrage.common;

import tk.mybatis.mapper.entity.IDynamicTableName;

import javax.persistence.Transient;

/**
 * Description: 分页
 * Author: ZhangYX
 * Date:  2017/9/22
 */
public class PageParameter implements IDynamicTableName {

    @Transient
    private Integer pageNum;
    @Transient
    private Integer pageSize;
    @Transient
    private String dynamicTableName;

    @Override
    public String getDynamicTableName() {
        return dynamicTableName;
    }

    public void setDynamicTableName(String dynamicTableName) {
        this.dynamicTableName = dynamicTableName;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
