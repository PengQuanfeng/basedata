package com.drelephant.elephantadmin.business.basedata.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.drelephant.elephantadmin.business.basedata.entity.BdAreaRegion;
import com.drelephant.framework.base.common.R;

/**
 * <p>
 * 地区区域信息管理 服务类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public interface BdAreaRegionService extends IService<BdAreaRegion> {
	R insertBdAreaRegion(BdAreaRegion data);
	R updateStatus(BdAreaRegion data);
	R deleteBdAreaRegion(BdAreaRegion data);//单条更新地区信息状态
	//根据是否传入条件查询列表
	Page<BdAreaRegion> getListBdAreaRegion(Page<BdAreaRegion> page,String code,String provinceCode,
    		String cityCode,String countyCode,Integer lever,String status);
	//批量更新地区信息状态
	R updateBatchBdAreaRegion(String status,String codes);
	//查询表中层级
//	List<BdAreaRegion> getListLevel();
	
	/**
	 * 
	 * @return
	 */
	R getListLevel();
	
	/**
	 * 获取 区域树
	 * @return
	 */
	R getAreaRegionTree();
	
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
	
	/**
	 * 获取区县列表
	 * @return
	 */
	R getCountyList(String cityCode);
}
