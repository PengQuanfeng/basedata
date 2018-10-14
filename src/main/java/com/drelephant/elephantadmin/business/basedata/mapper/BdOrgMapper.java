package com.drelephant.elephantadmin.business.basedata.mapper;

import com.drelephant.elephantadmin.business.basedata.entity.BdOrg;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import javax.annotation.Nonnull;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Mapper
public interface BdOrgMapper extends BaseMapper<BdOrg> {
	int selectCompanyCode(String code);//查询公司编码是否存在
	/**
	 *查询公司名称是否存在 
	 * @param name
	 * @return
	 */
	int selectCompanyName(@Param("name")String name);
	int updateName(BdOrg bdOrg);//根据code更新公司名称
	/**
	 * 新增公司
	 * @param entity
	 */
	void insertBdOrg(BdOrg entity);
}