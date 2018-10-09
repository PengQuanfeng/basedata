package com.drelephant.elephantadmin.business.basedata.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * banner
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public class BdBanner implements Serializable {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 类别
     */
	@TableField("bannerType")
	private String bannerType;
    /**
     * 文件ID
     */
	@TableField("fileId")
	private String fileId;
    /**
     * 排序字段
     */
	@TableField("orderNumber")
	private Integer orderNumber;
    /**
     * 文件类型
     */
	@TableField("fileType")
	private String fileType;
    /**
     * 备注
     */
	private String remark;
    /**
     * 链接地址
     */
	@TableField("linkAddress")
	private String linkAddress;
    /**
     * 图片地址
     */
	@TableField("picAddress")
	private String picAddress;
    /**
     * 链接开启状态 0否 1是
     */
	@TableField("isOpenLink")
	private String isOpenLink;
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

	public String getBannerType() {
		return bannerType;
	}

	public void setBannerType(String bannerType) {
		this.bannerType = bannerType;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLinkAddress() {
		return linkAddress;
	}

	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}

	public String getPicAddress() {
		return picAddress;
	}

	public void setPicAddress(String picAddress) {
		this.picAddress = picAddress;
	}

	public String getIsOpenLink() {
		return isOpenLink;
	}

	public void setIsOpenLink(String isOpenLink) {
		this.isOpenLink = isOpenLink;
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
		return "BdBanner{" +
			"id=" + id +
			", bannerType=" + bannerType +
			", fileId=" + fileId +
			", orderNumber=" + orderNumber +
			", fileType=" + fileType +
			", remark=" + remark +
			", linkAddress=" + linkAddress +
			", picAddress=" + picAddress +
			", isOpenLink=" + isOpenLink +
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
