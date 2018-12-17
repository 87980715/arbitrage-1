package com.shiyou.arbitrage.data.model;

import com.shiyou.arbitrage.common.PageParameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public class Symbol extends PageParameter implements Serializable {
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
     * 交易对名称
     */
    private String name;

    /**
     * 类型： 币币 spot, 期货 futures
     */
    private String type;

    /**
     * 交易区
     */
    private String area;

    /**
     * 价格小数
     */
    @Column(name = "price_decimal")
    private Integer priceDecimal;

    /**
     * 数量小数
     */
    @Column(name = "amount_decimal")
    private Integer amountDecimal;

    /**
     * 可否交易
     */
    private Boolean tradeable;

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
     * 获取交易对名称
     *
     * @return name - 交易对名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置交易对名称
     *
     * @param name 交易对名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取类型： 币币 spot, 期货 futures
     *
     * @return type - 类型： 币币 spot, 期货 futures
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型： 币币 spot, 期货 futures
     *
     * @param type 类型： 币币 spot, 期货 futures
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取交易区
     *
     * @return area - 交易区
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置交易区
     *
     * @param area 交易区
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取价格小数
     *
     * @return price_decimal - 价格小数
     */
    public Integer getPriceDecimal() {
        return priceDecimal;
    }

    /**
     * 设置价格小数
     *
     * @param priceDecimal 价格小数
     */
    public void setPriceDecimal(Integer priceDecimal) {
        this.priceDecimal = priceDecimal;
    }

    /**
     * 获取数量小数
     *
     * @return amount_decimal - 数量小数
     */
    public Integer getAmountDecimal() {
        return amountDecimal;
    }

    /**
     * 设置数量小数
     *
     * @param amountDecimal 数量小数
     */
    public void setAmountDecimal(Integer amountDecimal) {
        this.amountDecimal = amountDecimal;
    }

    /**
     * 获取可否交易
     *
     * @return tradeable - 可否交易
     */
    public Boolean getTradeable() {
        return tradeable;
    }

    /**
     * 设置可否交易
     *
     * @param tradeable 可否交易
     */
    public void setTradeable(Boolean tradeable) {
        this.tradeable = tradeable;
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
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", area=").append(area);
        sb.append(", priceDecimal=").append(priceDecimal);
        sb.append(", amountDecimal=").append(amountDecimal);
        sb.append(", tradeable=").append(tradeable);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}