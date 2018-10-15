package com.drelephant.elephantadmin.business.basedata.mapper;

import java.util.List;

import javax.annotation.Nonnull;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.drelephant.elephantadmin.business.basedata.entity.BdAreaRegion;

/**
 * <p>
  * 地区区域信息管理 Mapper 接口
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Mapper
public interface BdAreaRegionMapper extends BaseMapper<BdAreaRegion> {
	List<Integer> listLevel();//查询层级
	
	List<BdAreaRegion> getAll();
	
	/**
	 * 获取省份列表
	 * @return
	 */
	List<BdAreaRegion> getProvinceList();
	
	/**
	 * 获取城市列表
	 * @return
	 */
	List<BdAreaRegion> getCityList(@Nonnull @Param("provinceCode") String provinceCode);
	
	/**
	 * 获取区县列表
	 * @return
	 */
	List<BdAreaRegion> getCountyList(@Nonnull @Param("cityCode") String cityCode);
	List<String> getProvinceName(String provinceCode);
	List<String> getCityName(String cityName);
	
	
}