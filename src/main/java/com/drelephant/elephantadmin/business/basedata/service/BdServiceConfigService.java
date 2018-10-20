package com.drelephant.elephantadmin.business.basedata.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.drelephant.elephantadmin.business.basedata.entity.BdServiceConfig;
import com.drelephant.framework.base.common.R;

/**
 * <p>
 * 医疗服务配置表 服务类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public interface BdServiceConfigService extends IService<BdServiceConfig> {
	
	R saveServiceConfig(BdServiceConfig entity);

	R updateBdServiceConfig(BdServiceConfig entity);
	
	public List<Map<String, String>> getServiceTypeList(String serviceCategoryCode);
	
	Page<BdServiceConfig> queryServiceConfigInfo(int offset, int limit);
	/*
	 * 获取 服务类别 列表
	 */
	List<Map<String, String>> getServiceCategoryList();
	
	/**
	 * 获取服务名称(包括：服务类别名称 和 服务类型名称)
	 */
	Map<String, String> getServiceName(String serviceCode);
}
