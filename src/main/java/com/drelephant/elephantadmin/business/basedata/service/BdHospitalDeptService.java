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
	//新增科室信息字典
	R insertHost(BdHospitalDept data);
	//单条更改科室信息
	R updateHost(BdHospitalDept data);
	//单条删除科室信息
	R deleteOneHost(BdHospitalDept data);
	//批量修改科室信息状态
	R deleteBatchHostStatus(String lv1Code,String status);
	//查询科室信息列表
	Page<BdHospitalDept> getListHost(Page<BdHospitalDept> page,String lv1Code,String lv1Name,
			String lv2Name,String level,String status);
}
