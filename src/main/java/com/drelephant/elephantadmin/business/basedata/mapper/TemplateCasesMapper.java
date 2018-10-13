package com.drelephant.elephantadmin.business.basedata.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.drelephant.elephantadmin.business.basedata.entity.TemplateCases;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
  * 电子病例模板表 Mapper 接口
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Mapper
public interface TemplateCasesMapper extends BaseMapper<TemplateCases> {

}