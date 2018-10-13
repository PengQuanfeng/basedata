package com.drelephant.elephantadmin.business.basedata.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.drelephant.elephantadmin.business.basedata.entity.BdServicePrice;
import com.drelephant.elephantadmin.business.basedata.mapper.BdServicePriceMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdServicePriceService;

/**
 * <p>
 * 服务定价表 服务实现类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Service
public class BdServicePriceServiceImpl extends ServiceImpl<BdServicePriceMapper, BdServicePrice> implements BdServicePriceService {
	
	@Autowired
	BdServicePriceMapper bdServicePriceMapper;
	
	@Override
    @Transactional
	public void saveBdServicePrice(BdServicePrice entity) {
		bdServicePriceMapper.saveBdServicePrice(entity);
	}

	@Override
    @Transactional
	public void updateBdServicePrice(BdServicePrice entity) {
		bdServicePriceMapper.updateBdServicePrice(entity);
	}

	@Override
	public Page<BdServicePrice> queryServicePriceInfo(int offset, int limit, String id) {
		// 构造分页实体
		Page<BdServicePrice> page = new Page<BdServicePrice>(offset, limit);
		List<BdServicePrice> servicePriceList = bdServicePriceMapper.queryServicePriceInfo(page, id);
		if (CollectionUtils.isNotEmpty(servicePriceList)) {
			page.setRecords(servicePriceList);
			return page;
		}
		return page;
	}
	
}
