package com.drelephant.elephantadmin.business.basedata.service;

import com.drelephant.elephantadmin.business.basedata.entity.TemplateCases;
import com.drelephant.framework.base.common.R;

import io.swagger.annotations.ApiParam;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 电子病例模板表 服务类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public interface TemplateCasesService extends IService<TemplateCases> {
	/**
	 * 新增模板数据
	 * @param templateCases
	 * @return
	 */
	R saveTemp(TemplateCases templateCases);
	/**
	 * 根据模板编码 逻辑删除
	 * @param tmpCode
	 * @return
	 */
	R deleteOneTemp(String tmpCode);
	/**
	 * 根据模板编码 进行批量更新状态
	 * @param tmpCode
	 */
	R updateBatchTemp(String tmpCode,String status);
	/**
	 * 更新模板数据 
	 * @param templateCases
	 * @return
	 */
	R updateOneTemp(TemplateCases templateCases);
	/**
	 * 一级科室编码列表
	 */
	R getLv1List();
	/**
	 * 二级科室编码列表
	 * @param lv1DeptCode
	 * @return
	 */
	R getLv2List(String lv1DeptCode);
	/**
	 * 三级科室编码列表
	 * @param lv2DeptCode
	 * @return
	 */
	R getLv3List(String lv2DeptCode);
	/**
	 * 根据模板code
	 * @param tempCode
	 * @return
	 */
	TemplateCases selectOneTemp(String tempCode);
	/**
	 * 查询列表
	 * @param current
	 * @param pageSize
	 * @param lv1DeptCode
	 * @param lv2DeptCode
	 * @param tmpName
	 * @param templateType
	 * @param status
	 * @return
	 */
	Page<TemplateCases> getListTemp(Page<TemplateCases> page,int current,int pageSize,String lv1DeptCode,String lv2DeptCode,
			String tmpName,String templateType,String status);
}
