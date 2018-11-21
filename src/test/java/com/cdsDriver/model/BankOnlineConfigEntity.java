package com.cdsDriver.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 银行基础信息上下线配置信息
 * 2017-08-15
 * zhangzhigang
 */
public class BankOnlineConfigEntity implements Serializable{
    private static final long serialVersionUID = -8463470247829676404L;

    public static final String ALIAS_ID="主键ID";
    public static final String ALIAS_BANK_ID="银行基础配置表ID";
    public static final String ALIAS_ONLINE_TIME="上线时间";
    public static final String ALIAS_OFFLINE_TIME="下线时间";
    public static final String ALIAS_STATUS="状态";
    public static final String ALIAS_DEL_FLAG="删除标识";
    public static final String ALIAS_CREATE_TIME="创建时间";
    public static final String ALIAS_MODIFE_TIME="修改时间";

    private Integer id;
    private Integer bankId;
    private Date onlineTime;
    private Date offlineTime;
    private Integer status;
    private Integer delFlag;
    private Date createTime;
    private Date modifeTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifeTime() {
        return modifeTime;
    }

    public void setModifeTime(Date modifeTime) {
        this.modifeTime = modifeTime;
    }

    @Override
    public String toString() {
        return "BankOnlineConfigEntity{" +
                "id=" + id +
                ", bankId=" + bankId +
                ", onlineTime=" + onlineTime +
                ", offlineTime=" + offlineTime +
                ", status=" + status +
                ", delFlag=" + delFlag +
                ", createTime=" + createTime +
                ", modifeTime=" + modifeTime +
                '}';
    }
}
