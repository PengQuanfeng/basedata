package com.drelephant.elephantadmin.business.basedata.service;

import com.drelephant.elephantadmin.business.basedata.entity.BdHospitalDept;
import com.drelephant.framework.base.common.R;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 医疗科室表 服务类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public interface BdHospitalDeptService extends IService<BdHospitalDept> {
	/**
	 * 
	 * @param data
	 * @param code 科室编码
	 * @return
	 */
	R insertHost(BdHospitalDept data);
	//单条更改科室信息
	R updateHost(BdHospitalDept data);
	/**
	 * 一级编码
	 * @param lv1Code
	 * @return
	 */
	BdHospitalDept selectOneDept(String lv1Code);
	//单条删除科室信息
	R deleteOneHost(String lv1Code);
	//批量修改科室信息状态
	R deleteBatchHostStatus(String lv1Code,String status);
	//查询科室信息列表
	Page<BdHospitalDept> getListHost(Page<BdHospitalDept> page,String lv1Code,String lv2Code,
			String lv3Code,String level,String status);

	/**
	 * 获取一级科室列表
	 * @return
	 */
	R getLv1List();
	
	/**
	 * 获取二级科室列表
	 * @return
	 */
	R getLv2List(String lv1Code);
	
	/**
	 * 获取三级科室列表
	 * @return
	 */
	R getLv3List(String lv2Code);
}
