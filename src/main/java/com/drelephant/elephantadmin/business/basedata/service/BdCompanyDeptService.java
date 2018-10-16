package com.drelephant.elephantadmin.business.basedata.service;

import com.baomidou.mybatisplus.service.IService;
import com.drelephant.elephantadmin.business.basedata.entity.BdCompanyDept;
import com.drelephant.framework.base.common.R;

/**
 * <p>
 * 部门信息表 服务类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public interface BdCompanyDeptService extends IService<BdCompanyDept> {
	R addDept(BdCompanyDept data);//部门信息录入
	R updateDept(BdCompanyDept data);//修改部门信息
	R deleteDept(String id);//根据部门code单条删除
	R getListDept(String companyCode, int current,int pageSize);//分页查询得到部门信息列表
	R getDept(String code);
}
