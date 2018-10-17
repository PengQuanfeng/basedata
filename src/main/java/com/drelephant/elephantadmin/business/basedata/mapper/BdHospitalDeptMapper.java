package com.drelephant.elephantadmin.business.basedata.mapper;


import com.drelephant.elephantadmin.business.basedata.entity.BdHospitalDept;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import javax.annotation.Nonnull;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
  * 医疗科室表 Mapper 接口
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Mapper
public interface BdHospitalDeptMapper extends BaseMapper<BdHospitalDept> {
	List<BdHospitalDept> getLv1List();
	List<BdHospitalDept> getLv2List(@Nonnull @Param("lv1Code") String lv1Code);
	List<BdHospitalDept> getLv3List(@Nonnull @Param("lv2Code") String lv2Code);

	
}