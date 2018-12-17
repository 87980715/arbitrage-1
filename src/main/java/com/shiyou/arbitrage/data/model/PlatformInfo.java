package com.shiyou.arbitrage.data.model;

import com.shiyou.arbitrage.common.PageParameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "platform_info")
public class PlatformInfo extends PageParameter implements Serializable {
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
     * API地址
     */
    @Column(name = "api_url")
    private String apiUrl;

    /**
     * SOCKET地址
     */
    @Column(name = "socket_url")
    private String socketUrl;

    /**
     * API key
     */
    @Column(name = "api_key")
    private String apiKey;

    /**
     * API secrect
     */
    @Column(name = "api_secrect")
    private String apiSecrect;

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
     * 获取API地址
     *
     * @return api_url - API地址
     */
    public String getApiUrl() {
        return apiUrl;
    }

    /**
     * 设置API地址
     *
     * @param apiUrl API地址
     */
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    /**
     * 获取SOCKET地址
     *
     * @return socket_url - SOCKET地址
     */
    public String getSocketUrl() {
        return socketUrl;
    }

    /**
     * 设置SOCKET地址
     *
     * @param socketUrl SOCKET地址
     */
    public void setSocketUrl(String socketUrl) {
        this.socketUrl = socketUrl;
    }

    /**
     * 获取API key
     *
     * @return api_key - API key
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * 设置API key
     *
     * @param apiKey API key
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * 获取API secrect
     *
     * @return api_secrect - API secrect
     */
    public String getApiSecrect() {
        return apiSecrect;
    }

    /**
     * 设置API secrect
     *
     * @param apiSecrect API secrect
     */
    public void setApiSecrect(String apiSecrect) {
        this.apiSecrect = apiSecrect;
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
        sb.append(", apiUrl=").append(apiUrl);
        sb.append(", socketUrl=").append(socketUrl);
        sb.append(", apiKey=").append(apiKey);
        sb.append(", apiSecrect=").append(apiSecrect);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}