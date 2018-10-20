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
	void updateBdAreaRegion(BdAreaRegion entity);
	/**
	 * 根据省编码和市编码查询市名称和省
	 * @param provinceCode
	 * @param cityCode
	 * @return
	 */
	BdAreaRegion selectProviceNameFromCode(@Param("provinceCode")String provinceCode,@Param("cityCode")String cityCode);
	/**
	 * 根据省编码查询省名称
	 * @param provinceCode
	 * @return
	 */
	BdAreaRegion selectPName(@Param("provinceCode")String provinceCode);
	
	/**
	 * 更新 省份名称
	 * @param entity
	 */
	void updateProvinceName(BdAreaRegion entity);
	
	/**
	 * 更新 城市名称
	 * @param entity
	 */
	void updateCityName(BdAreaRegion entity);
	
	/**
	 * 更新 状态
	 * @param entity
	 */
	void updateStatusForLevel1(BdAreaRegion entity);
	
	/**
	 * 更新 状态
	 * @param entity
	 */
	void updateStatusForLevel2(BdAreaRegion entity);
	
	/**
	 * 获取 子级（包括 子级的子级）区域数量
	 * @param entity
	 * @return
	 */
	Integer getCountOfSubAreaRegions(BdAreaRegion entity);
	
}