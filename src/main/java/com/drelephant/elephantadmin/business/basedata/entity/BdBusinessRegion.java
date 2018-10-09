package com.drelephant.elephantadmin.business.basedata.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public class BdBusinessRegion implements Serializable {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 当前层级
     */
	private Integer level;
    /**
     * 一级区域编码
     */
	@TableField("lv1Code")
	private String lv1Code;
    /**
     * 一级区域名称
     */
	@TableField("lv1Name")
	private String lv1Name;
    /**
     * 二级区域编码
     */
	@TableField("lv2Code")
	private String lv2Code;
    /**
     * 二级区域名称
     */
	@TableField("lv2Name")
	private String lv2Name;
    /**
     * 层级信息
     */
	@TableField("layerLevel")
	private String layerLevel;
	@TableField("createTime")
	private Date createTime;
	@TableField("createUserCode")
	private String createUserCode;
	@TableField("createUserName")
	private String createUserName;
	@TableField("updateTime")
	private Date updateTime;
	@TableField("updateUserName")
	private String updateUserName;
	@TableField("updateUserCode")
	private String updateUserCode;
	private String status;
	private String version;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getLv1Code() {
		return lv1Code;
	}

	public void setLv1Code(String lv1Code) {
		this.lv1Code = lv1Code;
	}

	public String getLv1Name() {
		return lv1Name;
	}

	public void setLv1Name(String lv1Name) {
		this.lv1Name = lv1Name;
	}

	public String getLv2Code() {
		return lv2Code;
	}

	public void setLv2Code(String lv2Code) {
		this.lv2Code = lv2Code;
	}

	public String getLv2Name() {
		return lv2Name;
	}

	public void setLv2Name(String lv2Name) {
		this.lv2Name = lv2Name;
	}

	public String getLayerLevel() {
		return layerLevel;
	}

	public void setLayerLevel(String layerLevel) {
		this.layerLevel = layerLevel;
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

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public String getUpdateUserCode() {
		return updateUserCode;
	}

	public void setUpdateUserCode(String updateUserCode) {
		this.updateUserCode = updateUserCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "BdBusinessRegion{" +
			"id=" + id +
			", level=" + level +
			", lv1Code=" + lv1Code +
			", lv1Name=" + lv1Name +
			", lv2Code=" + lv2Code +
			", lv2Name=" + lv2Name +
			", layerLevel=" + layerLevel +
			", createTime=" + createTime +
			", createUserCode=" + createUserCode +
			", createUserName=" + createUserName +
			", updateTime=" + updateTime +
			", updateUserName=" + updateUserName +
			", updateUserCode=" + updateUserCode +
			", status=" + status +
			", version=" + version +
			"}";
	}
}
