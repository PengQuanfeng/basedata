package com.drelephant.elephantadmin.business.basedata.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController;
import com.drelephant.elephantadmin.business.basedata.entity.BdServiceConfig;
import com.drelephant.elephantadmin.business.basedata.entity.BdServicePrice;
import com.drelephant.elephantadmin.business.basedata.service.BdServiceConfigService;
import com.drelephant.elephantadmin.business.basedata.service.BdServicePriceService;
import com.drelephant.framework.base.common.R;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 服务定价 前端控制器
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Api(tags = "服务定价")
@RestController
@RequestMapping("bdServicePrice")
public class BdServicePriceController extends BaseController {
	@Autowired
	private BdServicePriceService bdServicePriceService;
	
	@Autowired
	private BdServiceConfigService bdServiceConfigService;

	@ApiOperation("查询服务定价")
	@GetMapping("/list")
	public R list(@ApiParam("当前页") String current, @ApiParam("每页显示记录数") String pageSize) {
		int offset = 1;
		int limit = 100000;
		if (StringUtils.isNotBlank(current)) {
			// 当前记录数
			offset = Integer.parseInt(current);
		}
		if (StringUtils.isNotBlank(pageSize)) {
			// 每页限制数
			limit = Integer.parseInt(pageSize);
		}
		
		Page<BdServicePrice> page = bdServicePriceService.queryServicePriceInfo(0, 100000);
		Map<String, BdServicePrice> map = new HashMap<String, BdServicePrice>(); // key - serviceCategoryCode * serviceCode, value - BdServicePrice
		for (BdServicePrice bdServicePrice : page.getRecords()) {
			map.put(bdServicePrice.getServiceCategoryCode() + "*" + bdServicePrice.getServiceCode(), bdServicePrice);
		}

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Page<BdServiceConfig> page1 = bdServiceConfigService.queryServiceConfigInfo(offset, limit);
		Map<String, Object> item = null;
		for (BdServiceConfig bdServiceConfig : page1.getRecords()) {
			item = new HashMap<String, Object>();
			//
			item.put("serviceCategoryName", bdServiceConfig.getServiceCategoryName());
			item.put("serviceName", bdServiceConfig.getServiceName());
			//
			BdServicePrice bdServicePrice = map.get(bdServiceConfig.getServiceCategoryCode() + "*" + bdServiceConfig.getServiceCode());
			if (bdServicePrice != null) {
				item.put("platformUnifiedPrice", bdServicePrice.getPlatformUnifiedPrice());
				item.put("activityPrice", bdServicePrice.getActivityPrice());
				item.put("platformPriceMin", bdServicePrice.getPlatformPriceMin());
				item.put("platformPriceMax", bdServicePrice.getPlatformPriceMax());
				item.put("platformFloatNumber", bdServicePrice.getPlatformFloatNumber());
				item.put("updateTime", bdServicePrice.getUpdateTime());
			}
			item.put("id", bdServiceConfig.getId());
			item.put("serviceCategoryCode", bdServiceConfig.getServiceCategoryCode());
			item.put("serviceCode", bdServiceConfig.getServiceCode());
			//
			list.add(item);
		}
		return R.ok().put("list", list).put("total", page.getTotal());
	}
	
	@ApiImplicitParams({
        @ApiImplicitParam(name = "serviceCategoryCode", value = "服务类别编码(一级)", required = true),
        @ApiImplicitParam(name = "serviceCode", value = "服务类型编码(二级)", required = true),
        @ApiImplicitParam(name = "platformUnifiedPrice", value = "平台统一原价", required = true),
        @ApiImplicitParam(name = "platformPriceMin", value = "自主定价范围-起始值" ),
        @ApiImplicitParam(name = "platformPriceMax", value = "自主定价范围-结束值"),
        @ApiImplicitParam(name = "platformFloatNumber", value = "平台上浮比例"),
    })
	@ApiOperation("新增服务定价")
	@PostMapping("/add")
	public R save(@RequestBody @ApiParam("服务定价信息") BdServicePrice entity) {
		if(entity == null){
			return R.error("保存服务定价信息失败，参数无效!");
		}
		if(entity.getPlatformUnifiedPrice() == null){ 
			return R.error().put("msg", "平台统一原价为空");
		}
		bdServicePriceService.saveBdServicePrice(entity);
		return R.ok().put("msg", "新增服务定价成功！");
	}
	
	@ApiImplicitParams({
        @ApiImplicitParam(name = "serviceCategoryCode", value = "服务类别编码(一级)", required = true),
        @ApiImplicitParam(name = "serviceCode", value = "服务类型编码(二级)", required = true),
        @ApiImplicitParam(name = "platformUnifiedPrice", value = "平台统一原价", required = true),
        @ApiImplicitParam(name = "platformPriceMin", value = "自主定价范围-起始值" ),
        @ApiImplicitParam(name = "platformPriceMax", value = "自主定价范围-结束值"),
        @ApiImplicitParam(name = "platformFloatNumber", value = "平台上浮比例"),
    })
	@ApiOperation("编辑服务定价")
	@PostMapping("/edit")
	public R edit(@RequestBody @ApiParam("编辑服务定价信息") BdServicePrice entity) {
		if(entity == null){
			return R.error("编辑服务定价信息失败，参数无效!");
		}
		if(entity.getPlatformUnifiedPrice() == null){ 
			return R.error().put("msg", "平台统一原价为空");
		}
		bdServicePriceService.updateBdServicePrice(entity);
		return R.ok().put("msg", "编辑服务定价成功！");
	}
}
