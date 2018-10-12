package com.drelephant.elephantadmin.business.basedata.mapper;

import com.drelephant.elephantadmin.business.basedata.entity.BdAreaRegion;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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
	List<BdAreaRegion> listLevel();//查询层级
}