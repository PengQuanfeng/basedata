package com.drelephant.elephantadmin.business.basedata.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 大象健康日历
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@TableName("bd_health_calendar")
public class BdHealthCalendar implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 封面图片ID
     */
    @TableField("coverPicId")
    private String coverPicId;
    /**
     * 内容图片ID
     */
    @TableField("contentPicId")
    private String contentPicId;
    /**
     * 发布时间
     */

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("publishTime")
    private Date publishTime;
    /**
     * 创建时间
     */
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
    
	/**
	 * 不存储到数据库中
	 */
    @JsonFormat(timezone = "GMT+8", pattern = "y-M-d")
	@TableField(exist = false)
    private Date publishTime2;
    
    public Date getPublishTime2() {
		return publishTime2;
	}

	public void setPublishTime2(Date publishTime2) {
		this.publishTime2 = publishTime2;
	}

	/**
     * 标题
     */
    @TableField("title")
    private String title;


    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoverPicId() {
        return coverPicId;
    }

    public void setCoverPicId(String coverPicId) {
        this.coverPicId = coverPicId;
    }

    public String getContentPicId() {
        return contentPicId;
    }

    public void setContentPicId(String contentPicId) {
        this.contentPicId = contentPicId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
        this.publishTime2 = publishTime;
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
		return "BdHealthCalendar [id=" + id + ", coverPicId=" + coverPicId + ", contentPicId=" + contentPicId
				+ ", publishTime=" + publishTime + ", createTime=" + createTime + ", createUserCode=" + createUserCode
				+ ", createUserName=" + createUserName + ", updateTime=" + updateTime + ", updateUserCode="
				+ updateUserCode + ", updateUserName=" + updateUserName + ", version=" + version + ", status=" + status
				+ ", title=" + title + "]";
	}

    
}
