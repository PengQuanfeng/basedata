package com.drelephant.elephantadmin.business.basedata.dto.response;

/**
 * @author zhou.fan
 * @date 2018/10/22 19:40
 * @description
 */
public class BdOrgAllOptionResponse {
    private String name;

    private String code;

    private String orgCategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrgCategory() {
        return orgCategory;
    }

    public void setOrgCategory(String orgCategory) {
        this.orgCategory = orgCategory;
    }
}
