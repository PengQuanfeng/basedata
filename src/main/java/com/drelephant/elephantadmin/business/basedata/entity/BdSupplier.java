package com.drelephant.elephantadmin.business.basedata.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 供应商信息表
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public class BdSupplier implements Serializable {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 供应商名称
     */
	private String name;
    /**
     * 供应商编码
     */
	private String code;
    /**
     * 供应商类型编码-字典
     */
	@TableField("typeCode")
	private String typeCode;
    /**
     * 联系电话
     */
	@TableField("phoneNumber")
	private String phoneNumber;
    /**
     * 地址
     */
	private String address;
    /**
     * 供应商登录帐号
     */
	@TableField("providerUserCode")
	private String providerUserCode;
    /**
     * 是否允许拆单 1是 2 否
     */
	@TableField("isAllowSplitOrder")
	private String isAllowSplitOrder;
    /**
     * 权重规则 1手动设置 2自动计算
     */
	@TableField("weightType")
	private String weightType;
    /**
     * 手动权重值
     */
	@TableField("weightSetupValue")
	private Integer weightSetupValue;
    /**
     * 自动权重值
     */
	@TableField("weightAutoValue")
	private Integer weightAutoValue;
    /**
     * 更新频率,单位天
     */
	@TableField("weightUpdateFreq")
	private Integer weightUpdateFreq;
    /**
     * 统一利率
     */
	@TableField("unifiedInterestRate")
	private BigDecimal unifiedInterestRate;
    /**
     * 运费价格
     */
	@TableField("freightPrice")
	private BigDecimal freightPrice;
    /**
     * 包邮价格
     */
	@TableField("freeShippingPrice")
	private BigDecimal freeShippingPrice;
    /**
     * 仓储覆盖,省编码,逗号分隔字符串
     */
	@TableField("storageRangeStr")
	private String storageRangeStr;
	@TableField("createTime")
	private Date createTime;
	@TableField("createUserCode")
	private String createUserCode;
	@TableField("createUserName")
	private String createUserName;
	@TableField("updateTime")
	private Date updateTime;
	@TableField("updateUserCode")
	private String updateUserCode;
	@TableField("updateUserName")
	private String updateUserName;
	private String version;
	private String status;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProviderUserCode() {
		return providerUserCode;
	}

	public void setProviderUserCode(String providerUserCode) {
		this.providerUserCode = providerUserCode;
	}

	public String getIsAllowSplitOrder() {
		return isAllowSplitOrder;
	}

	public void setIsAllowSplitOrder(String isAllowSplitOrder) {
		this.isAllowSplitOrder = isAllowSplitOrder;
	}

	public String getWeightType() {
		return weightType;
	}

	public void setWeightType(String weightType) {
		this.weightType = weightType;
	}

	public Integer getWeightSetupValue() {
		return weightSetupValue;
	}

	public void setWeightSetupValue(Integer weightSetupValue) {
		this.weightSetupValue = weightSetupValue;
	}

	public Integer getWeightAutoValue() {
		return weightAutoValue;
	}

	public void setWeightAutoValue(Integer weightAutoValue) {
		this.weightAutoValue = weightAutoValue;
	}

	public Integer getWeightUpdateFreq() {
		return weightUpdateFreq;
	}

	public void setWeightUpdateFreq(Integer weightUpdateFreq) {
		this.weightUpdateFreq = weightUpdateFreq;
	}

	public BigDecimal getUnifiedInterestRate() {
		return unifiedInterestRate;
	}

	public void setUnifiedInterestRate(BigDecimal unifiedInterestRate) {
		this.unifiedInterestRate = unifiedInterestRate;
	}

	public BigDecimal getFreightPrice() {
		return freightPrice;
	}

	public void setFreightPrice(BigDecimal freightPrice) {
		this.freightPrice = freightPrice;
	}

	public BigDecimal getFreeShippingPrice() {
		return freeShippingPrice;
	}

	public void setFreeShippingPrice(BigDecimal freeShippingPrice) {
		this.freeShippingPrice = freeShippingPrice;
	}

	public String getStorageRangeStr() {
		return storageRangeStr;
	}

	public void setStorageRangeStr(String storageRangeStr) {
		this.storageRangeStr = storageRangeStr;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserCode() {
		return createUserCode;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUserCode() {
		return updateUserCode;
	}

	public void setUpdateUserCode(String updateUserCode) {
		this.updateUserCode = updateUserCode;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BdSupplier{" +
			"id=" + id +
			", name=" + name +
			", code=" + code +
			", typeCode=" + typeCode +
			", phoneNumber=" + phoneNumber +
			", address=" + address +
			", providerUserCode=" + providerUserCode +
			", isAllowSplitOrder=" + isAllowSplitOrder +
			", weightType=" + weightType +
			", weightSetupValue=" + weightSetupValue +
			", weightAutoValue=" + weightAutoValue +
			", weightUpdateFreq=" + weightUpdateFreq +
			", unifiedInterestRate=" + unifiedInterestRate +
			", freightPrice=" + freightPrice +
			", freeShippingPrice=" + freeShippingPrice +
			", storageRangeStr=" + storageRangeStr +
			", createTime=" + createTime +
			", createUserCode=" + createUserCode +
			", createUserName=" + createUserName +
			", updateTime=" + updateTime +
			", updateUserCode=" + updateUserCode +
			", updateUserName=" + updateUserName +
			", version=" + version +
			", status=" + status +
			"}";
	}
}
