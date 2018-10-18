package com.drelephant.elephantadmin.business.basedata.service;

import com.drelephant.elephantadmin.business.basedata.entity.BdBusinessRegion;
import com.drelephant.framework.base.common.R;

import java.util.List;

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
public interface BdBusinessRegionService extends IService<BdBusinessRegion> {
	//业务区域数据新增
	R inserRegion(BdBusinessRegion mBdBusinessRegion);
	//业务区域数据修改
	R updateRegion(BdBusinessRegion mBdBusinessRegion);
	//业务区域数据删除
	R deleteOneRegion(BdBusinessRegion mBdBusinessRegion);
	//业务区域数据列表查询
	Page<BdBusinessRegion> getListRegion(Page<BdBusinessRegion> page,String code,String lv1Code,String lv2Code,String level);
	/**
	 * 单条业务区域信息
	 * @param lv1Code
	 * @return
	 */
	BdBusinessRegion selectOneRegion(String codes,Integer level);
	/**
	 * 一级区域编码下拉数据
	 * @return
	 */
	R bdLv1();
	/**
	 * 二级区域编码下拉数据
	 * @return
	 */
	R bdLv2(String lv1Code);
}
