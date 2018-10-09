package com.drelephant.elephantadmin.business.basedata.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 地区区域信息管理
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public class BdAreaRegion implements Serializable {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 编码
     */
	private String code;
    /**
     * 省编码
     */
	@TableField("provinceCode")
	private String provinceCode;
    /**
     * 省名称
     */
	@TableField("provinceName")
	private String provinceName;
    /**
     * 市编码
     */
	@TableField("cityCode")
	private String cityCode;
    /**
     * 市名称
     */
	@TableField("cityName")
	private String cityName;
    /**
     * 区县编码
     */
	@TableField("countyCode")
	private String countyCode;
    /**
     * 区县名称
     */
	@TableField("countyName")
	private String countyName;
    /**
     * 层级
     */
	private Integer level;
    /**
     * 层级信息
     */
	@TableField("layerInfo")
	private String layerInfo;
	@TableField("createTime")
	private Date createTime;
	@TableField("createUserName")
	private String createUserName;
	@TableField("createUserCode")
	private String createUserCode;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getLayerInfo() {
		return layerInfo;
	}

	public void setLayerInfo(String layerInfo) {
		this.layerInfo = layerInfo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getCreateUserCode() {
		return createUserCode;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
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
		return "BdAreaRegion{" +
			"id=" + id +
			", code=" + code +
			", provinceCode=" + provinceCode +
			", provinceName=" + provinceName +
			", cityCode=" + cityCode +
			", cityName=" + cityName +
			", countyCode=" + countyCode +
			", countyName=" + countyName +
			", level=" + level +
			", layerInfo=" + layerInfo +
			", createTime=" + createTime +
			", createUserName=" + createUserName +
			", createUserCode=" + createUserCode +
			", updateTime=" + updateTime +
			", updateUserName=" + updateUserName +
			", updateUserCode=" + updateUserCode +
			", status=" + status +
			", version=" + version +
			"}";
	}
}
