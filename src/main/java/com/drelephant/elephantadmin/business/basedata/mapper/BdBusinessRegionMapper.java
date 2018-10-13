package com.drelephant.elephantadmin.business.basedata.mapper;

import com.drelephant.elephantadmin.business.basedata.entity.BdBusinessRegion;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Mapper
public interface BdBusinessRegionMapper extends BaseMapper<BdBusinessRegion> {
	/**
	 * 一级区域编码下拉数据
	 * @return
	 */
	List<BdBusinessRegion> getbdLv1List();
	/**
	 * 二级区域编码下拉数据
	 * @return
	 */
	List<BdBusinessRegion> getbdLv2List(String lv1Code);
}