package com.drelephant.elephantadmin.business.basedata.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
/**
 * 快捷回复设置
 * @author HASEE
 *
 */
@TableName("bd_quick_reply")
public class BdQuickReply implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 服务类型编码
	 */
	@TableField("typeCode")
	private String typeCode;
	/**
	 * 服务类型名称
	 */
	@TableField("typeName")
	private String typeName;
	/**
	 * 内容
	 */
	@TableField("content")
	private String content;
	/**
	 * 备注
	 */
	@TableField("remark")
	private String remark;
	/**
	 * 排序
	 */
	@TableField("createTime")
	private int orderNumber;	
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
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
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
		return "BdQuickReply [id=" + id + ", typeCode=" + typeCode + ", typeName=" + typeName + ", content=" + content
				+ ", remark=" + remark + ", orderNumber=" + orderNumber + ", createTime=" + createTime
				+ ", createUserName=" + createUserName + ", createUserCode=" + createUserCode + ", updateTime="
				+ updateTime + ", updateUserName=" + updateUserName + ", updateUserCode=" + updateUserCode + ", status="
				+ status + ", version=" + version + "]";
	}
	
}
