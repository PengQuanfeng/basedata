package com.drelephant.elephantadmin.business.basedata.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.drelephant.elephantadmin.business.basedata.entity.BdServicePrice;

/**
 * <p>
 * 服务定价表 服务类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public interface BdServicePriceService extends IService<BdServicePrice> {

	void saveBdServicePrice(BdServicePrice entity);

	void updateBdServicePrice(BdServicePrice entity);
	
	Page<BdServicePrice> queryServicePriceInfo(int offset, int limit, String id);
}
