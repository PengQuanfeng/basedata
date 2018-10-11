package com.drelephant.elephantadmin.business.basedata.mapper;

import com.drelephant.elephantadmin.business.basedata.entity.BdOrg;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
	int updateName(BdOrg bdOrg);//根据code更新公司名称
}