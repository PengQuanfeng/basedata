package com.drelephant.elephantadmin.business.basedata.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 服务活动记录
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public class BdServiceActivity implements Serializable {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 服务类别名称(一级)
     */
	@TableField("serviceCategoryName")
	private String serviceCategoryName;
    /**
     * 服务类别编码(一级)
     */
	@TableField("serviceCategoryCode")
	private String serviceCategoryCode;
    /**
     * 服务类型名称(二级)
     */
	@TableField("serviceName")
	private String serviceName;
    /**
     * 服务类型编码(二级)
     */
	@TableField("serviceCode")
	private String serviceCode;
    /**
     * 生效日期
     */
	@TableField("startTime")
	private Date startTime;
    /**
     * 生效结束日期
     */
	@TableField("endTime")
	private Date endTime;
    /**
     * 价格
     */
	private BigDecimal price;
    /**
     * 生效标识
     */
	@TableField("takeEffectCount")
	private String takeEffectCount;
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

	public String getServiceCategoryName() {
		return serviceCategoryName;
	}

	public void setServiceCategoryName(String serviceCategoryName) {
		this.serviceCategoryName = serviceCategoryName;
	}

	public String getServiceCategoryCode() {
		return serviceCategoryCode;
	}

	public void setServiceCategoryCode(String serviceCategoryCode) {
		this.serviceCategoryCode = serviceCategoryCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getTakeEffectCount() {
		return takeEffectCount;
	}

	public void setTakeEffectCount(String takeEffectCount) {
		this.takeEffectCount = takeEffectCount;
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
		return "BdServiceActivity{" +
			"id=" + id +
			", serviceCategoryName=" + serviceCategoryName +
			", serviceCategoryCode=" + serviceCategoryCode +
			", serviceName=" + serviceName +
			", serviceCode=" + serviceCode +
			", startTime=" + startTime +
			", endTime=" + endTime +
			", price=" + price +
			", takeEffectCount=" + takeEffectCount +
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
