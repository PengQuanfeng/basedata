package com.drelephant.elephantadmin.business.basedata.mapper;

import com.drelephant.elephantadmin.business.basedata.entity.BdBanner;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
	
}