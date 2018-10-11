package com.drelephant.elephantadmin.business.basedata.service;

import com.drelephant.elephantadmin.business.basedata.entity.BdAreaRegion;
import com.drelephant.framework.base.common.R;

import io.swagger.annotations.ApiParam;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

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
	Page<BdAreaRegion> getListBdAreaRegion(Page<BdAreaRegion> page,String code,String provinceName,
    		String CityName,String CountyName,Integer lever,String status);
	R updateBatchBdAreaRegion(String status,String codes);//批量更新地区信息状态
}
