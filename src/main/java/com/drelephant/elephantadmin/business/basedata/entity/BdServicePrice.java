package com.drelephant.elephantadmin.business.basedata.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 服务定价表
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@TableName("bd_service_price")
public class BdServicePrice implements Serializable {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 服务类别编码(一级)
     */
	@TableField("serviceCategoryCode")
	private String serviceCategoryCode;
    /**
     * 服务类别名称(一级)
     */
	@TableField("serviceCategoryName")
	private String serviceCategoryName;
    /**
     * 服务类型编码(二级)
     */
	@TableField("serviceCode")
	private String serviceCode;
    /**
     * 服务类型名称(二级)
     */
	@TableField("serviceName")
	private String serviceName;
    /**
     * 平台统一定价
     */
	@TableField("platformUnifiedPrice")
	private BigDecimal platformUnifiedPrice;
    /**
     * 活动价格
     */
	@TableField("activityPrice")
	private BigDecimal activityPrice;
    /**
     * 平台自主定价min
     */
	@TableField("platformPriceMin")
	private BigDecimal platformPriceMin;
    /**
     * 平台自主价格max
     */
	@TableField("platformPriceMax")
	private BigDecimal platformPriceMax;
    /**
     * 平台上浮比例(%)
     */
	@TableField("platformFloatNumber")
	private String platformFloatNumber;
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

	public String getServiceCategoryCode() {
		return serviceCategoryCode;
	}

	public void setServiceCategoryCode(String serviceCategoryCode) {
		this.serviceCategoryCode = serviceCategoryCode;
	}

	public String getServiceCategoryName() {
		return serviceCategoryName;
	}

	public void setServiceCategoryName(String serviceCategoryName) {
		this.serviceCategoryName = serviceCategoryName;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public BigDecimal getPlatformUnifiedPrice() {
		return platformUnifiedPrice;
	}

	public void setPlatformUnifiedPrice(BigDecimal platformUnifiedPrice) {
		this.platformUnifiedPrice = platformUnifiedPrice;
	}

	public BigDecimal getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(BigDecimal activityPrice) {
		this.activityPrice = activityPrice;
	}

	public BigDecimal getPlatformPriceMin() {
		return platformPriceMin;
	}

	public void setPlatformPriceMin(BigDecimal platformPriceMin) {
		this.platformPriceMin = platformPriceMin;
	}

	public BigDecimal getPlatformPriceMax() {
		return platformPriceMax;
	}

	public void setPlatformPriceMax(BigDecimal platformPriceMax) {
		this.platformPriceMax = platformPriceMax;
	}

	public String getPlatformFloatNumber() {
		return platformFloatNumber;
	}

	public void setPlatformFloatNumber(String platformFloatNumber) {
		this.platformFloatNumber = platformFloatNumber;
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
		return "BdServicePrice{" +
			"id=" + id +
			", serviceCategoryCode=" + serviceCategoryCode +
			", serviceCategoryName=" + serviceCategoryName +
			", serviceCode=" + serviceCode +
			", serviceName=" + serviceName +
			", platformUnifiedPrice=" + platformUnifiedPrice +
			", activityPrice=" + activityPrice +
			", platformPriceMin=" + platformPriceMin +
			", platformPriceMax=" + platformPriceMax +
			", platformFloatNumber=" + platformFloatNumber +
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
