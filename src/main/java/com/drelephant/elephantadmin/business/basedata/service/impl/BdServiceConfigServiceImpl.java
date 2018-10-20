package com.drelephant.elephantadmin.business.basedata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
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
import com.drelephant.framework.base.common.R;

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
	public R saveServiceConfig(BdServiceConfig entity) {
		Condition con=Condition.create();
		con.eq("serviceName", entity.getServiceName());
		con.where("status !={0}", Constans.DELETED);
		int count=selectCount(con);
		if(count>0){
			return R.error().put("msg", "服务类型名称已经存在");
		}
		
		entity.setStatus(Constans.ACTIVE);
		bdServiceConfigMapper.saveServiceConfig(entity);
		
		return R.ok();
	}

	@Override
    @Transactional
	public R updateBdServiceConfig(BdServiceConfig entity) {
		Condition con=Condition.create();
		con.eq("serviceName", entity.getServiceName());
		con.ne("id", entity.getId());
		con.where("status !={0}", Constans.DELETED);
		int count=selectCount(con);
		if(count>0){
			return R.error().put("msg", "服务类型名称已经存在");
		}
		
		bdServiceConfigMapper.updateBdServiceConfig(entity);
		return R.ok();
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

	/**
	 * 查询服务类型(二级)列表
	 */
	@Override
	public List<Map<String, String>> getServiceTypeList(String serviceCategoryCode) {
		List<Map<String, String>> serviceTypes = new ArrayList<Map<String, String>>();
		Map<String,String> item = null;
		List<BdServiceConfig> list=bdServiceConfigMapper.selectList(Condition.create().eq("serviceCategoryCode", serviceCategoryCode));
		for (BdServiceConfig bdServiceConfig : list) {
			item = new HashMap<String,String>();
			//
			item.put("code", bdServiceConfig.getServiceCode());
			item.put("name", bdServiceConfig.getServiceName());
			//
			serviceTypes.add(item);
		}
		return serviceTypes;
	}

	/**
	 * 获取服务名称(包括：服务类别名称 和 服务类型名称)
	 */
	@Override
	public Map<String, String> getServiceName(String serviceCode) {
		Map<String, String> map = new HashMap<String, String>();
		List<BdServiceConfig> list = bdServiceConfigMapper.selectList(Condition.create().eq("serviceCode", serviceCode));
		if (list.size() > 0) {
			BdServiceConfig bdServiceConfig = list.get(0);
			map.put("serviceName", bdServiceConfig.getServiceName());
			map.put("serviceCategoryName", bdServiceConfig.getServiceCategoryName());
		}
		return map;
	}
	
	/**
	 * 获取 服务类别 列表
	 */
	public List<Map<String, String>> getServiceCategoryList() {
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
