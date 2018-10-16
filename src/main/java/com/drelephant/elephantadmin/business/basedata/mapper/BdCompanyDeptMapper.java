package com.drelephant.elephantadmin.business.basedata.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.drelephant.elephantadmin.business.basedata.entity.BdCompanyDept;

/**
 * <p>
  * 部门信息表 Mapper 接口
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Mapper
public interface BdCompanyDeptMapper extends BaseMapper<BdCompanyDept> {
	int selectDeptName(@Param("name")String name);

	int selectDeptNameForOtherCode(@Param("name") String name, @Param("code") String code);
	
	List<String> selectDeptCodes();
}