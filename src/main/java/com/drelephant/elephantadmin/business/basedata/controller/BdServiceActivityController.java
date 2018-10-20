package com.drelephant.elephantadmin.business.basedata.controller;


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
import com.drelephant.elephantadmin.business.basedata.entity.BdServiceActivity;
import com.drelephant.elephantadmin.business.basedata.service.BdServiceActivityService;
import com.drelephant.elephantadmin.business.basedata.service.BdServiceConfigService;
import com.drelephant.framework.base.common.R;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 服务活动记录 前端控制器
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Api(tags = "服务活动记录")
@RestController
@RequestMapping("bdServiceActivity")
public class BdServiceActivityController extends BaseController {
    @Autowired
    private BdServiceActivityService bdServiceActivityService;
    
    @Autowired
    private BdServiceConfigService bdServiceConfigService;
    
    @ApiOperation("查询活动记录")
	@GetMapping("/list")
	public R list(@ApiParam("当前页") String current, @ApiParam("每页显示记录数") String pageSize, @ApiParam("id") String id) {
		int offset = 1;
		int limit = 1000;
		if (StringUtils.isNotBlank(current)) {
			// 当前记录数
			offset = Integer.parseInt(current);
		}
		if (StringUtils.isNotBlank(pageSize)) {
			// 每页限制数
			limit = Integer.parseInt(pageSize);
		}
		Page<BdServiceActivity> page = bdServiceActivityService.queryServiceActivityInfo(offset, limit, id);
		return R.ok().put("list", page.getRecords()).put("total", page.getTotal());
	}
    
    @ApiImplicitParams({
        @ApiImplicitParam(name = "serviceCategoryCode", value = "服务类别编码", required = true),
        @ApiImplicitParam(name = "serviceCode", value = "服务类型编码", required = true),
        @ApiImplicitParam(name = "price", value = "活动价格", required = true), 
        @ApiImplicitParam(name = "startTime", value = "开始时间", required = true),
        @ApiImplicitParam(name = "endTime", value = "结束时间", required = true),
    })
	@ApiOperation("新增活动记录")
	@PostMapping("/add")
	public R save(@RequestBody @ApiParam("活动记录") BdServiceActivity entity) {
		if(entity == null){
			return R.error("保存活动记录信息失败，参数无效!");
		}
		
		Map<String, String> map = bdServiceConfigService.getServiceName(entity.getServiceCode());
		entity.setServiceCategoryName(map.get("serviceCategoryName"));
		entity.setServiceName(map.get("serviceName"));
		
		bdServiceActivityService.saveBdServiceActivity(entity);
		return R.ok().put("msg", "新增活动记录成功！");
	}
    
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "ID", required = true),
        @ApiImplicitParam(name = "price", value = "活动价格", required = true), 
        @ApiImplicitParam(name = "startTime", value = "开始时间", required = true),
        @ApiImplicitParam(name = "endTime", value = "结束时间", required = true),
    })
	@ApiOperation("编辑活动记录")
	@PostMapping("/edit")
	public R edit(@RequestBody @ApiParam("编辑活动记录") BdServiceActivity entity) {
		if(entity == null){
			return R.error("编辑活动记录失败，参数无效!");
		}
		
		Map<String, String> map = bdServiceConfigService.getServiceName(entity.getServiceCode());
		entity.setServiceCategoryName(map.get("serviceCategoryName"));
		entity.setServiceName(map.get("serviceName"));
		
		bdServiceActivityService.updateBdServiceActivity(entity);
		return R.ok().put("msg", "编辑活动记录成功！");
	}
	@ApiOperation("删除活动记录")
	@PostMapping("/delete")
	public R delete( @ApiParam("id") String id) {
		if(StringUtils.isBlank(id)){
			return R.error("删除活动记录失败，参数无效!");
		}
		bdServiceActivityService.deleteActivityById(id);
		return R.ok().put("msg", "删除活动记录成功！");
	}

}
