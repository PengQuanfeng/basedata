package com.drelephant.elephantadmin.business.basedata.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 数据字典表
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@TableName("bd_dict_value")
public class BdDictValue implements Serializable {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 编码
     */
	private String code;
    /**
     * 名称
     */
	private String name;
    /**
     * 类型编码
     */
	@TableField("typeCode")
	private String typeCode;
    /**
     * 值
     */
	private String value;
    /**
     * 父级编码
     */
	@TableField("parentCode")
	private String parentCode;
    /**
     * 描述
     */
	private String description;
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
	private String version;
	private String status;


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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return "BdDictValue{" +
			"id=" + id +
			", code=" + code +
			", name=" + name +
			", typeCode=" + typeCode +
			", value=" + value +
			", parentCode=" + parentCode +
			", description=" + description +
			", createTime=" + createTime +
			", createUserName=" + createUserName +
			", createUserCode=" + createUserCode +
			", updateTime=" + updateTime +
			", updateUserName=" + updateUserName +
			", updateUserCode=" + updateUserCode +
			", version=" + version +
			", status=" + status +
			"}";
	}
}
