package com.drelephant.elephantadmin.business.basedata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.drelephant.elephantadmin.business.basedata.entity.BdDictValue;
import com.drelephant.elephantadmin.business.basedata.entity.BdServiceConfig;
import com.drelephant.elephantadmin.business.basedata.mapper.BdDictValueMapper;
import com.drelephant.elephantadmin.business.basedata.mapper.BdServiceConfigMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdServiceConfigService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;

/**
 * <p>
 * 医疗服务配置表 服务实现类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Service
public class BdServiceConfigServiceImpl extends ServiceImpl<BdServiceConfigMapper, BdServiceConfig>
		implements BdServiceConfigService {

	@Autowired
	BdServiceConfigMapper bdServiceConfigMapper;
	
	@Autowired
	BdDictValueMapper bdDictValueMapper;
	@Override
    @Transactional
	public void saveServiceConfig(BdServiceConfig entity) {
		entity.setStatus(Constans.ACTIVE);
		bdServiceConfigMapper.saveServiceConfig(entity);
	}

	@Override
    @Transactional
	public void updateBdServiceConfig(BdServiceConfig entity) {
		bdServiceConfigMapper.updateBdServiceConfig(entity);
	}

	@Override
	public Page<BdServiceConfig> queryServiceConfigInfo(int offset, int limit) {
		// 构造分页实体
		Page<BdServiceConfig> page = new Page<BdServiceConfig>(offset, limit);
		List<BdServiceConfig> servicePriceList = bdServiceConfigMapper.queryServiceConfigInfo(page);
		if (CollectionUtils.isNotEmpty(servicePriceList)) {
			page.setRecords(servicePriceList);
			return page;
		}
		return page;
	}

	@Override
	public Map<String, String> getServiceType(String type) {
		Map<String,String> map = new HashMap<String,String>();
		//TODO:后续改用读数据字典方式
		if(StringUtils.equals("FWLB", type)){
			map.put("SPWZ", Constans.FWLB_SPWZ_NAME);
			map.put("TWZX", Constans.FWLB_TWZX_NAME);
		}else if(StringUtils.equals("SPWZ", type)){
			map.put("PTMZ", Constans.SPWZ_PTMZ_NAME);
			map.put("ZKMZ", Constans.SPWZ_ZKMZ_NAME);
			map.put("TXMZ", Constans.SPWZ_TXMZ_NAME);
		}else if(StringUtils.equals("TWZX", type)){
			map.put("ZXZX", Constans.TWZX_ZXZX_NAME);
			map.put("KYMZ", Constans.TWZX_KYMZ_NAME);
			map.put("JCMZ", Constans.TWZX_JCMZ_NAME);
		}
		return map;
	}
	public List<Map<String, String>> getServiceTypes() {
		List<Map<String, String>> serviceTypes = new ArrayList<Map<String, String>>();
		Map<String,String> item = null;
		List<BdDictValue> list=bdDictValueMapper.selectList(Condition.create().eq("typeCode", "FWLB"));
		for (BdDictValue bdDictValue : list) {
			item = new HashMap<String,String>();
			//
			item.put("code", bdDictValue.getCode());
			item.put("name", bdDictValue.getName());
			//
			serviceTypes.add(item);
		}
		return serviceTypes;
	}
}
