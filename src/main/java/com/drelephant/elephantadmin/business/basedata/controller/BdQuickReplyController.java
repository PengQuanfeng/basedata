package com.drelephant.elephantadmin.business.basedata.controller;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController;
import com.drelephant.elephantadmin.business.basedata.entity.BdQuickReply;
import com.drelephant.elephantadmin.business.basedata.entity.BdServiceConfig;
import com.drelephant.elephantadmin.business.basedata.service.BdQuickReplyService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * <p>
 * 快捷回复设置   前端控制器
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Api(tags = "快捷回复设置")
@RestController
@RequestMapping("bdQuickReply")
public class BdQuickReplyController extends BaseController{
	@Autowired
	private BdQuickReplyService bdQuickReplyService;
	@ApiOperation("服务类型")
	@GetMapping("/serviceTypes")
	public R serviceType() { 
		return R.ok().put("list",bdQuickReplyService.getServiceTypes());
	}
	@ApiOperation("新增快捷回复")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "typeCode", value = "服务类型编码", required = true),
		@ApiImplicitParam(name = "typeName", value = "服务类型名称"),
		@ApiImplicitParam(name = "content", value = "回复内容", required = true),
		@ApiImplicitParam(name = "remark", value = "备注")
	})	
	@PostMapping("/add")
	public R save(@RequestBody @ApiParam("快捷回复设置参数") BdQuickReply entity){
		if(entity == null){
			return R.error("保存快捷回复设置信息失败，参数无效!");
		}
		bdQuickReplyService.saveQuickReply(entity);
		return R.ok().put("msg", "新增服务配置成功！");
	}
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "对应id", required = true),
		@ApiImplicitParam(name = "content", value = "回复内容", required = true),
		@ApiImplicitParam(name = "remark", value = "备注")
	})
	@ApiOperation("编辑快捷回复")
	@PostMapping("/update")
	public R update(@RequestBody @ApiParam("快捷回复设置参数")BdQuickReply entity){
		if(entity == null){
			return R.error("编辑快捷回复设置信息失败，参数无效!");
		}
		bdQuickReplyService.updateQuickReply(entity);
		return R.ok().put("msg", "编辑服务配置成功！");
	}
	@ApiOperation("查询快捷回复")
	@GetMapping("/list")
	public R list(@ApiParam("当前页")@RequestParam(value="current" ,defaultValue="1" ,required=false) String current, 
			@ApiParam("每页显示记录数")@RequestParam(value="pageSize" ,defaultValue="1000" ,required=false) String pageSize
			,@ApiParam("服务类型编码")@RequestParam(value="typeCode" ,defaultValue="ZXZX"  ,required=false)String typeCode) {//
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
		Page<BdQuickReply> page = bdQuickReplyService.queryQuickReplyInfo(offset, limit,typeCode);//
		return R.ok().put("list", page.getRecords()).put("total", page.getTotal());
	}
	@ApiOperation("删除快捷回复")
	@PostMapping("/delete")
	public R edit( @ApiParam(value="id",required=true) String id) {
		if(StringUtils.isBlank(id)){
			return R.error("删除快捷回复失败，参数无效!");
		}
		bdQuickReplyService.deleteQuickReplyById(id);
		return R.ok().put("msg", "删除活动记录成功！");
	}
	//TODO 根据排序字段进行上移和下移
	@ApiOperation("上移")
    @PostMapping("/moveUp")
	@ApiImplicitParam(name = "id", value = "id", required = true)		
    public R moveUp(@ApiParam("id列")String id){
    	return bdQuickReplyService.moveUp(id);
    }
    
    @ApiOperation("下移")
    @PostMapping("/moveDown")
    @ApiImplicitParam(name = "id", value = "id", required = true)
    public R moveDown(@ApiParam("id列")String id){
    	return bdQuickReplyService.moveDown(id);
    }
}
