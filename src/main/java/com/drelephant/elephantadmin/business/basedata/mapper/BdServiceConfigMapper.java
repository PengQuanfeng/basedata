package com.drelephant.elephantadmin.business.basedata.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.drelephant.elephantadmin.business.basedata.entity.BdServiceConfig;

/**
 * <p>
  * 医疗服务配置表 Mapper 接口
 * </p>
 * @author com.drelephant
 * @since 2018-10-09
 */
@Mapper
public interface BdServiceConfigMapper extends BaseMapper<BdServiceConfig> {

	int saveServiceConfig(BdServiceConfig entity);

	int updateBdServiceConfig(BdServiceConfig entity);
	
	List<BdServiceConfig> queryServiceConfigInfo(Pagination page, @Param("id") String id);
}