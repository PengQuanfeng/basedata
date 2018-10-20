package com.drelephant.elephantadmin.business.basedata.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.drelephant.elephantadmin.business.basedata.entity.BdServiceConfig;
import com.drelephant.elephantadmin.business.basedata.entity.BdServicePrice;
import com.drelephant.elephantadmin.business.basedata.mapper.BdServiceConfigMapper;
import com.drelephant.elephantadmin.business.basedata.mapper.BdServicePriceMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdServicePriceService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;

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
	
	@Autowired
	BdServiceConfigMapper bdServiceConfigMapper;
	
	@Override
    @Transactional
	public R saveBdServicePrice(BdServicePrice entity) {
		entity.setStatus(Constans.ACTIVE);
		
		BdServicePrice con2 = new BdServicePrice();
		con2.setServiceCategoryCode(entity.getServiceCategoryCode());
		con2.setServiceCode(entity.getServiceCode());
		BdServicePrice one = bdServicePriceMapper.selectOne(con2);
		if (one == null) {
			BdServiceConfig con = new BdServiceConfig();
			con.setServiceCategoryCode(entity.getServiceCategoryCode());
			con.setServiceCode(entity.getServiceCode());
			BdServiceConfig one0 = bdServiceConfigMapper.selectOne(con);
			if (one0 != null) {
				entity.setServiceCategoryName(one0.getServiceCategoryName());
				entity.setServiceName(one0.getServiceName());
			} else {
				return R.error("根据serviceCategoryCode(" + entity.getServiceCategoryCode() + ")和serviceCode(" + entity.getServiceCode() + ")无法找到 服务定价 信息");
			}
			//
			bdServicePriceMapper.saveBdServicePrice(entity);
		} else {
			one.setPlatformFloatNumber(entity.getPlatformFloatNumber());
			one.setPlatformPriceMax(entity.getPlatformPriceMax());
			one.setPlatformPriceMin(entity.getPlatformPriceMin());
			one.setPlatformUnifiedPrice(entity.getPlatformUnifiedPrice());
			one.setActivityPrice(entity.getActivityPrice());
			//
			bdServicePriceMapper.updateById(one);
		}
		
		return R.ok();
	}

	@Override
    @Transactional
	public R updateBdServicePrice(BdServicePrice entity) {
		BdServicePrice con2 = new BdServicePrice();
		con2.setServiceCategoryCode(entity.getServiceCategoryCode());
		con2.setServiceCode(entity.getServiceCode());
		BdServicePrice one = bdServicePriceMapper.selectOne(con2);
		if (one == null) {
			return R.error("根据serviceCategoryCode(" + entity.getServiceCategoryCode() + ")和serviceCode(" + entity.getServiceCode() + ")无法找到 医疗服务配置 信息");
		} else {
			one.setPlatformFloatNumber(entity.getPlatformFloatNumber());
			one.setPlatformPriceMax(entity.getPlatformPriceMax());
			one.setPlatformPriceMin(entity.getPlatformPriceMin());
			one.setPlatformUnifiedPrice(entity.getPlatformUnifiedPrice());
			one.setActivityPrice(entity.getActivityPrice());
			//
			bdServicePriceMapper.updateById(one);
		}
		
		return R.ok();
	}

	@Override
	public Page<BdServicePrice> queryServicePriceInfo(int offset, int limit) {
		// 构造分页实体
		Page<BdServicePrice> page = new Page<BdServicePrice>(offset, limit);
		List<BdServicePrice> servicePriceList = bdServicePriceMapper.queryServicePriceInfo(page);
		if (CollectionUtils.isNotEmpty(servicePriceList)) {
			page.setRecords(servicePriceList);
			return page;
		}
		return page;
	}
	
}
