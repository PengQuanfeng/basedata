package com.drelephant.elephantadmin.business.basedata.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.drelephant.elephantadmin.business.basedata.entity.BdBanner;

/**
 * <p>
  * banner Mapper 接口
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Mapper
public interface BdBannerMapper extends BaseMapper<BdBanner> {
	/**
	 * 查询最大的排序号
	 * @return
	 */
	Integer maxOrderNumber();
	/**
	 * 根据id得到当前的排序字段
	 * @param id
	 * @return
	 */
	int orderNum(String id);
	int getId(int orderNumber);
	int getMinOrder();
	
	/**
	 * 获取所有的BdBanner
	 * @return
	 */
	List<BdBanner> getAll();
	
	/**
	 * 更新 排序号
	 * @param id
	 * @param orderNumber
	 */
	void updateOrderNumber(String id, int orderNumber);
}