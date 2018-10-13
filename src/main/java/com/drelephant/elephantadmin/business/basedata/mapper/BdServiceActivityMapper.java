package com.drelephant.elephantadmin.business.basedata.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.drelephant.elephantadmin.business.basedata.entity.BdServiceActivity;

/**
 * <p>
  * 服务活动记录 Mapper 接口
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Mapper
public interface BdServiceActivityMapper extends BaseMapper<BdServiceActivity> {

	int saveBdServiceActivity(BdServiceActivity entity);

	int updateBdServiceActivity(BdServiceActivity entity);
	
	int deleteActivityById(@Param("id") String id);
	
	List<BdServiceActivity> queryServiceActivityInfo(Pagination page, @Param("id") String id);
}