package com.drelephant.elephantadmin.business.basedata.dto.reqeuest;

import com.drelephant.elephantadmin.business.basedata.entity.BdSupplier;

/**
 * @author zhou.fan
 * @date 2018/10/17 14:07
 * @description 新增供应商
 */
public class BdSupplierRequest extends BdSupplier {
    /**
     * 密码
     */
    private String password;
    /**
     * 确认密码
     */
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
