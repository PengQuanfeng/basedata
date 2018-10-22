package com.drelephant.elephantadmin.business.basedata.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
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
import springfox.documentation.annotations.ApiIgnore;

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

	@ApiOperation("查询服务类型(二级)列表")
	@GetMapping("/getServiceTypeList")
	public R getServiceTypeList(@ApiParam("服务类别编码") String serviceCategoryCode) {
		List<Map<String, String>> list = bdServiceConfigService.getServiceTypeList(serviceCategoryCode);
		
		return R.ok().put("list", list);
	}
	
	@ApiOperation("查询服务配置")
	@GetMapping("/list")
	public R list(@ApiParam("当前页") String current, @ApiParam("每页显示记录数") String pageSize) {
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
		Page<BdServiceConfig> page = bdServiceConfigService.queryServiceConfigInfo(offset, limit);
		return R.ok().put("list", page.getRecords()).put("total", page.getTotal());
	}
	
	@ApiOperation("获取 服务类别 列表")
	@GetMapping("/serviceType")
	public R getServiceCategoryList( ) {
		return R.ok().put("serviceType", bdServiceConfigService.getServiceCategoryList());
	}

    @ApiImplicitParams({
            @ApiImplicitParam(name = "serviceCategoryCode", value = "服务类别编码", required = true),
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
		entity.setServiceCode(uuid());
		
		return bdServiceConfigService.saveServiceConfig(entity);
	}
	
    private String uuid() {
    	return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "ID", required = true),
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
		if(entity == null){
			return R.error("编辑服务配置信息失败，参数无效!");
		}
		return bdServiceConfigService.updateBdServiceConfig(entity);
	}

	/**
	 * api- 根据 二级code in (str) 查询 list
	 * used by elephant-admin
	 * @param codeStr
	 * @return list
	 */
	@ApiIgnore
	@GetMapping("/listByCodes2InStrApi")
	public List<BdServiceConfig> listByCodes2InStrApi(String codeStr){
		if(StringUtils.isBlank(codeStr)){
			return Collections.emptyList();
		}
		Wrapper  w = Condition.create().in("serviceCode",codeStr).eq("status",Constans.ACTIVE).orderBy("createTime");
		return bdServiceConfigService.selectList(w);
	}
}
