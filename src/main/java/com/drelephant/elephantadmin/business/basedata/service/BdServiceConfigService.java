package com.drelephant.elephantadmin.business.basedata.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.drelephant.elephantadmin.business.basedata.entity.BdServiceConfig;

/**
 * <p>
 * 医疗服务配置表 服务类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public interface BdServiceConfigService extends IService<BdServiceConfig> {
	
	void saveServiceConfig(BdServiceConfig entity);

	void updateBdServiceConfig(BdServiceConfig entity);
	
	Map<String,String> getServiceType(String type);
	
	Page<BdServiceConfig> queryServiceConfigInfo(int offset, int limit);
	/*
	 *服务类型获取
	 */
	List<Map<String, String>> getServiceTypes();
}
