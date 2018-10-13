package com.drelephant.elephantadmin.business.basedata.service;

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
	
	Page<BdServiceConfig> queryServiceConfigInfo(int offset, int limit,String id);
}
