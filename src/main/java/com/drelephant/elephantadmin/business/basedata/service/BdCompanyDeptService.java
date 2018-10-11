package com.drelephant.elephantadmin.business.basedata.service;

import com.drelephant.elephantadmin.business.basedata.entity.BdCompanyDept;
import com.drelephant.framework.base.common.R;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 部门信息表 服务类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public interface BdCompanyDeptService extends IService<BdCompanyDept> {
	List<BdCompanyDept> getCompanyList();//部门信息查询列表
	R addCompany(BdCompanyDept data);//部门信息录入
	R updateCompany(BdCompanyDept data);//修改部门信息
	R delectCompany(String code);//根据部门code单条删除
	R getListDept(int current,int pageSize);//分页查询得到部门信息列表
}
