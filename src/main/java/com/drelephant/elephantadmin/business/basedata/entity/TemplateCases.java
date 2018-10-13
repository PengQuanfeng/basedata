package com.drelephant.elephantadmin.business.basedata.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 电子病例模板表
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@TableName("template_cases")
public class TemplateCases implements Serializable {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 模板名称
     */
	@TableField("tmpName")
	private String tmpName;
    /**
     * 模板编码
     */
	@TableField("tmpCode")
	private String tmpCode;
    /**
     * 状态
     */
	private String status;
    /**
     * 模板类型
     */
	@TableField("templateType")
	private String templateType;
    /**
     * 医生姓名
     */
	@TableField("doctorName")
	private String doctorName;
    /**
     * 医生编码
     */
	@TableField("doctorCode")
	private String doctorCode;
    /**
     * 一级科室编码
     */
	@TableField("lv1DeptCode")
	private String lv1DeptCode;
    /**
     * 一级科室名称
     */
	@TableField("lv1DeptName")
	private String lv1DeptName;
    /**
     * 二级科室编码
     */
	@TableField("lv2DeptCode")
	private String lv2DeptCode;
    /**
     * 二级科室名称
     */
	@TableField("lv2DeptName")
	private String lv2DeptName;
    /**
     * 主诉
     */
	@TableField("chiefComplaint")
	private String chiefComplaint;
    /**
     * 现病史
     */
	@TableField("illnessHistory")
	private String illnessHistory;
    /**
     * 既往史
     */
	private String anamnesis;
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


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTmpName() {
		return tmpName;
	}

	public void setTmpName(String tmpName) {
		this.tmpName = tmpName;
	}

	public String getTmpCode() {
		return tmpCode;
	}

	public void setTmpCode(String tmpCode) {
		this.tmpCode = tmpCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorCode() {
		return doctorCode;
	}

	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}

	public String getLv1DeptCode() {
		return lv1DeptCode;
	}

	public void setLv1DeptCode(String lv1DeptCode) {
		this.lv1DeptCode = lv1DeptCode;
	}

	public String getLv1DeptName() {
		return lv1DeptName;
	}

	public void setLv1DeptName(String lv1DeptName) {
		this.lv1DeptName = lv1DeptName;
	}

	public String getLv2DeptCode() {
		return lv2DeptCode;
	}

	public void setLv2DeptCode(String lv2DeptCode) {
		this.lv2DeptCode = lv2DeptCode;
	}

	public String getLv2DeptName() {
		return lv2DeptName;
	}

	public void setLv2DeptName(String lv2DeptName) {
		this.lv2DeptName = lv2DeptName;
	}

	public String getChiefComplaint() {
		return chiefComplaint;
	}

	public void setChiefComplaint(String chiefComplaint) {
		this.chiefComplaint = chiefComplaint;
	}

	public String getIllnessHistory() {
		return illnessHistory;
	}

	public void setIllnessHistory(String illnessHistory) {
		this.illnessHistory = illnessHistory;
	}

	public String getAnamnesis() {
		return anamnesis;
	}

	public void setAnamnesis(String anamnesis) {
		this.anamnesis = anamnesis;
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

	@Override
	public String toString() {
		return "TemplateCases{" +
			"id=" + id +
			", tmpName=" + tmpName +
			", tmpCode=" + tmpCode +
			", status=" + status +
			", templateType=" + templateType +
			", doctorName=" + doctorName +
			", doctorCode=" + doctorCode +
			", lv1DeptCode=" + lv1DeptCode +
			", lv1DeptName=" + lv1DeptName +
			", lv2DeptCode=" + lv2DeptCode +
			", lv2DeptName=" + lv2DeptName +
			", chiefComplaint=" + chiefComplaint +
			", illnessHistory=" + illnessHistory +
			", anamnesis=" + anamnesis +
			", createTime=" + createTime +
			", createUserCode=" + createUserCode +
			", createUserName=" + createUserName +
			", updateTime=" + updateTime +
			", updateUserCode=" + updateUserCode +
			", updateUserName=" + updateUserName +
			", version=" + version +
			"}";
	}
}
