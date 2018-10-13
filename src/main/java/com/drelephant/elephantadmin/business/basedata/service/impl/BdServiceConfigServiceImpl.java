package com.drelephant.elephantadmin.business.basedata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.drelephant.elephantadmin.business.basedata.entity.BdServiceConfig;
import com.drelephant.elephantadmin.business.basedata.mapper.BdServiceConfigMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdServiceConfigService;

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

	@Override
    @Transactional
	public void saveServiceConfig(BdServiceConfig entity) {
		bdServiceConfigMapper.saveServiceConfig(entity);
	}

	@Override
    @Transactional
	public void updateBdServiceConfig(BdServiceConfig entity) {
		bdServiceConfigMapper.updateBdServiceConfig(entity);
	}

	@Override
	public Page<BdServiceConfig> queryServiceConfigInfo(int offset, int limit, String id) {
		// 构造分页实体
		Page<BdServiceConfig> page = new Page<BdServiceConfig>(offset, limit);
		List<BdServiceConfig> servicePriceList = bdServiceConfigMapper.queryServiceConfigInfo(page, id);
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
			map.put("SPWZ", "视频问诊");
			map.put("TWZX", "图文咨询");
		}else if(StringUtils.equals("SPWZ", type)){
			map.put("PTMZ", "普通门诊");
			map.put("ZKMZ", "专科门诊");
			map.put("TXMZ", "特需门诊");
		}else if(StringUtils.equals("TWZX", type)){
			map.put("SPWZ", "在线咨询");
			map.put("TWZX", "开药门诊");
			map.put("TWZX", "检查门诊");
		}
		return map;
	}
}
