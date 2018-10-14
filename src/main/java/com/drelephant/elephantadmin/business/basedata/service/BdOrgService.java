package com.drelephant.elephantadmin.business.basedata.service;

import com.drelephant.elephantadmin.business.basedata.entity.BdOrg;
import com.drelephant.framework.base.common.R;

import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public interface BdOrgService extends IService<BdOrg> {
	void addCompany(BdOrg entity);//公司信息插入
	R updateCompany(BdOrg data);//更新公司名称
	R deleteCode(String code);
	R selectCompay();
	//新增医院字典
	R addHospital(BdOrg data);
	//查询医院信息列表
	Page<BdOrg> getListBdOrg(Page<BdOrg> page,String code,String provinceCode,String cityCode,String name
			,String hospitalLevel,String status);
	//单条修改医院状态
	R updateOneHosStatus(BdOrg data);
	//单条删除医院状态
	R deleteOneHosStatus(BdOrg data);
	//批量更新医院状态
	R deleteBatchHosStatus(String status,String code);
	
	/**
	 * 获取省份列表
	 * @return
	 */
	R getProvinceList();
	
	/**
	 * 获取城市列表
	 * @return
	 */
	R getCityList(String provinceCode);
}
