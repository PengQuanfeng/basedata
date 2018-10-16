package com.drelephant.elephantadmin.business.basedata.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.drelephant.elephantadmin.business.basedata.entity.BdOrg;

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
	String getCompanyName(String code);
	
	int selectCompanyCode(String code);//查询公司编码是否存在
	/**
	 *查询公司名称是否存在 
	 * @param name
	 * @return
	 */
	int selectCompanyName(@Param("name")String name);

	int selectCompanyNameForOtherCode(@Param("name") String name, @Param("code") String code);	
	
	int updateName(BdOrg bdOrg);//根据code更新公司名称
	/**
	 * 新增公司
	 * @param entity
	 */
	void insertBdOrg(BdOrg entity);
	/**
	 * 删除公司
	 */
	void deleteBdOrg(@Param("id")String id);
	
	/**
	 * 查询公司编码列表
	 * @return
	 */
	List<String> selectCompanyCodes();
}