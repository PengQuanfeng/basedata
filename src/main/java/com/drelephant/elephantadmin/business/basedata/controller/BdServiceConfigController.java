package com.drelephant.elephantadmin.business.basedata.controller;

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
import com.drelephant.elephantadmin.business.basedata.service.BdServiceConfigService;
import com.drelephant.framework.base.common.R;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 服务配置表 前端控制器
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Api(tags = "服务配置表")
@RestController
@RequestMapping("bdServiceConfig")
public class BdServiceConfigController extends BaseController {
	@Autowired
	private BdServiceConfigService bdServiceConfigService;

	@ApiOperation("查询服务配置")
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
		Page<BdServiceConfig> page = bdServiceConfigService.queryServiceConfigInfo(offset, limit, id);
		return R.ok().put("list", page.getRecords()).put("total", page.getTotal());
	}
	
	@ApiOperation("服务类型")
	@GetMapping("/serviceType")
	public R serviceType(@ApiParam("数据字典类型") String dataType ) { 
		return R.ok().put("serviceType", bdServiceConfigService.getServiceType(dataType));
	}

    @ApiImplicitParams({
            @ApiImplicitParam(name = "serviceCategoryCode", value = "服务类别编码", required = true),
            @ApiImplicitParam(name = "serviceCategoryName", value = "服务类别名称", required = true),
            @ApiImplicitParam(name = "serviceCode", value = "服务类型编码"),
            @ApiImplicitParam(name = "serviceName", value = "服务类型名称", required = true),
            @ApiImplicitParam(name = "isDocCanConfig", value = "医生服务配置", required = true),
            @ApiImplicitParam(name = "serviceNoticeDesc", value = "用户服务须知", required = true),
            @ApiImplicitParam(name = "isApCanConfig", value = "视频问诊/预约服务配置标识", required = true),
            @ApiImplicitParam(name = "isCostMandatory", value = "视频问诊/费用必选标识", required = true),
            @ApiImplicitParam(name = "urgentCost1", value = "视频问诊/加急费用1"),
            @ApiImplicitParam(name = "urgentCost2", value = "视频问诊/加急费用2"),
            @ApiImplicitParam(name = "urgentCost3", value = "视频问诊/加急费用3"),
            @ApiImplicitParam(name = "docReplyLimitCount", value = "图文咨询/医生回复限制", required = true),
            @ApiImplicitParam(name = "docSerTimeLength", value = "图文咨询/服务时长限制", required = true),
            @ApiImplicitParam(name = "isPrescription", value = "图文咨询/开具处方", required = true),
            @ApiImplicitParam(name = "isInspect", value = "图文咨询/开具检查", required = true),
            @ApiImplicitParam(name = "frequencyLimitCount", value = "图文咨询/次数限制", required = true),
    })
	@ApiOperation("新增服务配置")
	@PostMapping("/add")
	public R save(@RequestBody @ApiParam("服务配置信息") BdServiceConfig entity) {
		if(entity == null){
			return R.error("保存服务配置信息失败，参数无效!");
		}
		bdServiceConfigService.saveServiceConfig(entity);
		return R.ok().put("msg", "新增服务配置成功！");
	}
    @ApiImplicitParams({
        @ApiImplicitParam(name = "serviceCode", value = "服务类型编码"),
        @ApiImplicitParam(name = "serviceName", value = "服务类型名称", required = true),
        @ApiImplicitParam(name = "isDocCanConfig", value = "医生服务配置", required = true),
        @ApiImplicitParam(name = "serviceNoticeDesc", value = "用户服务须知", required = true),
        @ApiImplicitParam(name = "isApCanConfig", value = "视频问诊/预约服务配置标识", required = true),
        @ApiImplicitParam(name = "isCostMandatory", value = "视频问诊/费用必选标识", required = true),
        @ApiImplicitParam(name = "urgentCost1", value = "视频问诊/加急费用1"),
        @ApiImplicitParam(name = "urgentCost2", value = "视频问诊/加急费用2"),
        @ApiImplicitParam(name = "urgentCost3", value = "视频问诊/加急费用3"),
        @ApiImplicitParam(name = "docReplyLimitCount", value = "图文咨询/医生回复限制", required = true),
        @ApiImplicitParam(name = "docSerTimeLength", value = "图文咨询/服务时长限制", required = true),
        @ApiImplicitParam(name = "isPrescription", value = "图文咨询/开具处方", required = true),
        @ApiImplicitParam(name = "isInspect", value = "图文咨询/开具检查", required = true),
        @ApiImplicitParam(name = "frequencyLimitCount", value = "图文咨询/次数限制", required = true),
    })
	@ApiOperation("编辑服务配置")
	@PostMapping("/edit")
	public R edit(@RequestBody @ApiParam("编辑服务配置信息") BdServiceConfig entity) {
		if(entity != null){
			return R.error("编辑服务配置信息失败，参数无效!");
		}
		bdServiceConfigService.updateBdServiceConfig(entity);
		return R.ok().put("msg", "编辑服务配置成功！");
	}
}
