package com.drelephant.elephantadmin.business.basedata.dto.reqeuest;

/**
 * @Auther: zhou.fan
 * @Date: 2018/10/17 10:51
 * @Description:
 */
public class DbOrgRequest {
    /** 机构名称 */
    private String name;
    /** 机构类型 */
    private String orgCategory;
    private Integer pageNo;
    private Integer pageSize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgCategory() {
        return orgCategory;
    }

    public void setOrgCategory(String orgCategory) {
        this.orgCategory = orgCategory;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
