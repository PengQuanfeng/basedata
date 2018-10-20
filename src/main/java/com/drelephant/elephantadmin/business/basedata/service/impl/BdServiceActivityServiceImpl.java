package com.drelephant.elephantadmin.business.basedata.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.drelephant.elephantadmin.business.basedata.entity.BdServiceActivity;
import com.drelephant.elephantadmin.business.basedata.mapper.BdServiceActivityMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdServiceActivityService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;

/**
 * <p>
 * 服务活动记录 服务实现类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Service
public class BdServiceActivityServiceImpl extends ServiceImpl<BdServiceActivityMapper, BdServiceActivity> implements BdServiceActivityService {

	@Autowired
	BdServiceActivityMapper bdServiceActivityMapper;
	
	@Override
    @Transactional
	public void saveBdServiceActivity(BdServiceActivity entity) {
		entity.setStatus(Constans.ACTIVE);
		bdServiceActivityMapper.saveBdServiceActivity(entity);
	}

	@Override
    @Transactional
	public void updateBdServiceActivity(BdServiceActivity entity) {
		bdServiceActivityMapper.updateBdServiceActivity(entity);
	}

	@Override
	public Page<BdServiceActivity> queryServiceActivityInfo(int offset, int limit, String id) {
		// 构造分页实体
		Page<BdServiceActivity> page = new Page<BdServiceActivity>(offset, limit);
		List<BdServiceActivity> servicePriceList = bdServiceActivityMapper.queryServiceActivityInfo(page, id);
		if (CollectionUtils.isNotEmpty(servicePriceList)) {
			page.setRecords(servicePriceList);
			return page;
		}
		return page;
	}

	@Override
	public void deleteActivityById(String id) {
		bdServiceActivityMapper.deleteActivityById(id);
		
	}
	
}
