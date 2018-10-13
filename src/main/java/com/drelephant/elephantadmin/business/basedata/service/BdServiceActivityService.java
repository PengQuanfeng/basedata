package com.drelephant.elephantadmin.business.basedata.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.drelephant.elephantadmin.business.basedata.entity.BdServiceActivity;

/**
 * <p>
 * 服务活动记录 服务类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public interface BdServiceActivityService extends IService<BdServiceActivity> {
	
	void saveBdServiceActivity(BdServiceActivity entity);

	void updateBdServiceActivity(BdServiceActivity entity);

	void deleteActivityById(String id);
	
	Page<BdServiceActivity> queryServiceActivityInfo(int offset, int limit, String id);
}
