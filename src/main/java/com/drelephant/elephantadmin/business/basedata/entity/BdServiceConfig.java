package com.drelephant.elephantadmin.business.basedata.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 医疗服务配置表
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public class BdServiceConfig implements Serializable {

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
     * 医生服务配置标识
     */
	@TableField("isDocCanConfig")
	private String isDocCanConfig;
    /**
     * 用户服务须知
     */
	@TableField("serviceNoticeDesc")
	private String serviceNoticeDesc;
    /**
     * 医生回复限制(条)
     */
	@TableField("docReplyLimitCount")
	private Integer docReplyLimitCount;
    /**
     * 服务时长限制(小时)
     */
	@TableField("docSerTimeLength")
	private String docSerTimeLength;
    /**
     * 开处方标识
     */
	@TableField("isPrescription")
	private String isPrescription;
    /**
     * 开检查标识
     */
	@TableField("isInspect")
	private String isInspect;
    /**
     * 次数限制(次)
     */
	@TableField("frequencyLimitCount")
	private Integer frequencyLimitCount;
    /**
     * 费用必选标识
     */
	@TableField("isCostMandatory")
	private String isCostMandatory;
    /**
     * 加急费用1
     */
	@TableField("urgentCost1")
	private BigDecimal urgentCost1;
    /**
     * 加急费用2
     */
	@TableField("urgentCost2")
	private BigDecimal urgentCost2;
    /**
     * 加急费用3
     */
	@TableField("urgentCost3")
	private BigDecimal urgentCost3;
    /**
     * 医生服务配置标识2
     */
	@TableField("isDocApCanConfig")
	private String isDocApCanConfig;
    /**
     * 预约服务配置标识
     */
	@TableField("isApCanConfig")
	private String isApCanConfig;
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

	public String getIsDocCanConfig() {
		return isDocCanConfig;
	}

	public void setIsDocCanConfig(String isDocCanConfig) {
		this.isDocCanConfig = isDocCanConfig;
	}

	public String getServiceNoticeDesc() {
		return serviceNoticeDesc;
	}

	public void setServiceNoticeDesc(String serviceNoticeDesc) {
		this.serviceNoticeDesc = serviceNoticeDesc;
	}

	public Integer getDocReplyLimitCount() {
		return docReplyLimitCount;
	}

	public void setDocReplyLimitCount(Integer docReplyLimitCount) {
		this.docReplyLimitCount = docReplyLimitCount;
	}

	public String getDocSerTimeLength() {
		return docSerTimeLength;
	}

	public void setDocSerTimeLength(String docSerTimeLength) {
		this.docSerTimeLength = docSerTimeLength;
	}

	public String getIsPrescription() {
		return isPrescription;
	}

	public void setIsPrescription(String isPrescription) {
		this.isPrescription = isPrescription;
	}

	public String getIsInspect() {
		return isInspect;
	}

	public void setIsInspect(String isInspect) {
		this.isInspect = isInspect;
	}

	public Integer getFrequencyLimitCount() {
		return frequencyLimitCount;
	}

	public void setFrequencyLimitCount(Integer frequencyLimitCount) {
		this.frequencyLimitCount = frequencyLimitCount;
	}

	public String getIsCostMandatory() {
		return isCostMandatory;
	}

	public void setIsCostMandatory(String isCostMandatory) {
		this.isCostMandatory = isCostMandatory;
	}

	public BigDecimal getUrgentCost1() {
		return urgentCost1;
	}

	public void setUrgentCost1(BigDecimal urgentCost1) {
		this.urgentCost1 = urgentCost1;
	}

	public BigDecimal getUrgentCost2() {
		return urgentCost2;
	}

	public void setUrgentCost2(BigDecimal urgentCost2) {
		this.urgentCost2 = urgentCost2;
	}

	public BigDecimal getUrgentCost3() {
		return urgentCost3;
	}

	public void setUrgentCost3(BigDecimal urgentCost3) {
		this.urgentCost3 = urgentCost3;
	}

	public String getIsDocApCanConfig() {
		return isDocApCanConfig;
	}

	public void setIsDocApCanConfig(String isDocApCanConfig) {
		this.isDocApCanConfig = isDocApCanConfig;
	}

	public String getIsApCanConfig() {
		return isApCanConfig;
	}

	public void setIsApCanConfig(String isApCanConfig) {
		this.isApCanConfig = isApCanConfig;
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
		return "BdServiceConfig{" +
			"id=" + id +
			", serviceCategoryCode=" + serviceCategoryCode +
			", serviceCategoryName=" + serviceCategoryName +
			", serviceCode=" + serviceCode +
			", serviceName=" + serviceName +
			", isDocCanConfig=" + isDocCanConfig +
			", serviceNoticeDesc=" + serviceNoticeDesc +
			", docReplyLimitCount=" + docReplyLimitCount +
			", docSerTimeLength=" + docSerTimeLength +
			", isPrescription=" + isPrescription +
			", isInspect=" + isInspect +
			", frequencyLimitCount=" + frequencyLimitCount +
			", isCostMandatory=" + isCostMandatory +
			", urgentCost1=" + urgentCost1 +
			", urgentCost2=" + urgentCost2 +
			", urgentCost3=" + urgentCost3 +
			", isDocApCanConfig=" + isDocApCanConfig +
			", isApCanConfig=" + isApCanConfig +
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
