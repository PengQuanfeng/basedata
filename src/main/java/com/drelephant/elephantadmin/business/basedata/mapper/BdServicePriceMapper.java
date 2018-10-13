package com.drelephant.elephantadmin.business.basedata.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.drelephant.elephantadmin.business.basedata.entity.BdServicePrice;

/**
 * <p>
  * 服务定价表 Mapper 接口
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Mapper
public interface BdServicePriceMapper extends BaseMapper<BdServicePrice> {

	int saveBdServicePrice(BdServicePrice entity);

	int updateBdServicePrice(BdServicePrice entity);
	
	List<BdServicePrice> queryServicePriceInfo(Pagination page, @Param("id") String id);
}