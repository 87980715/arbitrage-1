package com.shiyou.arbitrage.data.model;

import com.shiyou.arbitrage.common.PageParameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


public class Order extends PageParameter implements Serializable {
    /**
     * ID
     */
    @Id
    private Long id;

    /**
     * 注册时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 逻辑删除
     */
    private Integer flag;

    /**
     * 交易平台
     */
    private String platform;

    /**
     * 交易对
     */
    private String symbol;

    /**
     * 订单类型 buy sell
     */
    @Column(name = "order_type")
    private String orderType;

    /**
     * 订单类型 market limit
     */
    @Column(name = "order_method")
    private String orderMethod;

    /**
     * 委托价格
     */
    private Date price;

    /**
     * 委托数量
     */
    private Date amount;

    /**
     * 剩余数量
     */
    private Date remain;

    /**
     * 状态0 已撤销，1 已成交，2 部份成交，3未成交
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取注册时间
     *
     * @return create_time - 注册时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置注册时间
     *
     * @param createTime 注册时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取逻辑删除
     *
     * @return flag - 逻辑删除
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * 设置逻辑删除
     *
     * @param flag 逻辑删除
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * 获取交易平台
     *
     * @return platform - 交易平台
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * 设置交易平台
     *
     * @param platform 交易平台
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * 获取交易对
     *
     * @return symbol - 交易对
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * 设置交易对
     *
     * @param symbol 交易对
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * 获取订单类型 buy sell
     *
     * @return order_type - 订单类型 buy sell
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 设置订单类型 buy sell
     *
     * @param orderType 订单类型 buy sell
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取订单类型 market limit
     *
     * @return order_method - 订单类型 market limit
     */
    public String getOrderMethod() {
        return orderMethod;
    }

    /**
     * 设置订单类型 market limit
     *
     * @param orderMethod 订单类型 market limit
     */
    public void setOrderMethod(String orderMethod) {
        this.orderMethod = orderMethod;
    }

    /**
     * 获取委托价格
     *
     * @return price - 委托价格
     */
    public Date getPrice() {
        return price;
    }

    /**
     * 设置委托价格
     *
     * @param price 委托价格
     */
    public void setPrice(Date price) {
        this.price = price;
    }

    /**
     * 获取委托数量
     *
     * @return amount - 委托数量
     */
    public Date getAmount() {
        return amount;
    }

    /**
     * 设置委托数量
     *
     * @param amount 委托数量
     */
    public void setAmount(Date amount) {
        this.amount = amount;
    }

    /**
     * 获取剩余数量
     *
     * @return remain - 剩余数量
     */
    public Date getRemain() {
        return remain;
    }

    /**
     * 设置剩余数量
     *
     * @param remain 剩余数量
     */
    public void setRemain(Date remain) {
        this.remain = remain;
    }

    /**
     * 获取状态0 已撤销，1 已成交，2 部份成交，3未成交
     *
     * @return status - 状态0 已撤销，1 已成交，2 部份成交，3未成交
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态0 已撤销，1 已成交，2 部份成交，3未成交
     *
     * @param status 状态0 已撤销，1 已成交，2 部份成交，3未成交
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", flag=").append(flag);
        sb.append(", platform=").append(platform);
        sb.append(", symbol=").append(symbol);
        sb.append(", orderType=").append(orderType);
        sb.append(", orderMethod=").append(orderMethod);
        sb.append(", price=").append(price);
        sb.append(", amount=").append(amount);
        sb.append(", remain=").append(remain);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}