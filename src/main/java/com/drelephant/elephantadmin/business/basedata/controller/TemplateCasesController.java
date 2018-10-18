package com.drelephant.elephantadmin.business.basedata.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController;
import com.drelephant.elephantadmin.business.basedata.entity.TemplateCases;
import com.drelephant.elephantadmin.business.basedata.service.TemplateCasesService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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

/**
 * <p>
 * 电子病例模板表 前端控制器
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Api(tags = "电子病例模板表")
@RestController
@RequestMapping("templateCases")
public class TemplateCasesController extends BaseController {
    @Autowired
    private TemplateCasesService templateCasesService;

/*****************新增**************/
    @ApiImplicitParams({
		@ApiImplicitParam(name = "tmpName", value = "模板名称", required = true),
		@ApiImplicitParam(name = "lv1DeptCode", value = "一级科室编码"),
		@ApiImplicitParam(name = "lv2DeptCode", value = "二级科室编码"),
		@ApiImplicitParam(name = "lv3DeptCode", value = "三级科室编码"),
		@ApiImplicitParam(name = "chiefComplaint", value = "主诉", required = true),
		@ApiImplicitParam(name = "anamnesis", value = "既往史", required = true),
		@ApiImplicitParam(name = "illnessHistory", value = "现病史", required = true),
		@ApiImplicitParam(name = "status", value = "状态")
	})
    @ApiOperation("模板新增")
    @PostMapping("/saveTemp")
    public R saveTemp(@RequestBody @ApiParam("数据对象")TemplateCases data){
    	if(data==null){
    		return R.error().put("msg", "参数为空");
    	}
        return templateCasesService.saveTemp(data);
    }
    @ApiOperation("单条删除模板数据")
    @PostMapping("/deleteTemp")
    public R deleteTemp(@ApiParam(value="模板code",required=true)String tmpCode){
    	if(StringUtils.isBlank(tmpCode)){
    		return R.error().put("msg", "参数为空");
    	}
        return templateCasesService.deleteOneTemp(tmpCode);
    }
    @ApiOperation("获取1级科室列表")
    @GetMapping("/getLv1DeptList")
    public R getLv1DeptList(){
    	return templateCasesService.getLv1List();
    }
    @ApiOperation("获取2级科室列表")
    @GetMapping("/getLv2DeptList")
    public R getLv2DeptList(String lv1DeptCode){
    	return templateCasesService.getLv2List(lv1DeptCode);
    }
    @ApiOperation("获取3级科室列表")
    @GetMapping("/getLv3DeptList")
    public R getLv3DeptList(String lv2DeptCode){
    	return templateCasesService.getLv3List(lv2DeptCode);
    }
    @ApiOperation("获取模板类型下拉列表数据")
    @GetMapping("/getTempList")
    public R getTempList(){
    	List<Map<String, String>> list=new ArrayList<Map<String, String>>();
		addItem(list, Constans.TEMPTYPE, Constans.TEMPTYPE_D);
		addItem(list, Constans.PERSONAL, Constans.PERSONAL_P);
    	return R.ok().put("list", list);
    }
    @ApiOperation("获取状态下拉列表数据")
    @GetMapping("/getStatusList")
    public R getStatusList(){ 
    	List<Map<String, String>> list=new ArrayList<Map<String, String>>();
		addItem(list, Constans.ACTIVE, Constans.ACTIVE_A);
		addItem(list, Constans.INVALID, Constans.INVALID_I);
    	return R.ok().put("list", list);
    }
   
    @ApiOperation("批量更新状态")
    @PostMapping("/updateBatchTemp")
    public R updateBatchStatus(@ApiParam("模板编码")String tmpCode,@ApiParam("状态")String status){  
    	
    	return templateCasesService.updateBatchTemp(tmpCode, status);
    }
    @ApiOperation("单条更新模板数据")
    @PostMapping("/updateOneTemp")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "id", value = "id", required = true),
		@ApiImplicitParam(name = "tmpName", value = "模板名称", required = true),
		@ApiImplicitParam(name = "lv1DeptCode", value = "一级科室编码", required = true),
		@ApiImplicitParam(name = "lv2DeptCode", value = "二级科室编码", required = true),
		@ApiImplicitParam(name = "chiefComplaint", value = "主诉", required = true),
		@ApiImplicitParam(name = "anamnesis", value = "既往史", required = true),
		@ApiImplicitParam(name = "illnessHistory", value = "现病史", required = true),
		@ApiImplicitParam(name = "status", value = "状态")
	})
    public R updateOneTemp(@RequestBody @ApiParam("数据对象")TemplateCases data){
    	//如果模板类型是个人，则不用更新数据
    	return templateCasesService.updateOneTemp(data);
    }
    @ApiOperation("单条查询模板数据")
    @GetMapping("/getOneTemp")
    public R getOneTemp(@ApiParam(value="模板code",required=true)String tmpCode){
    	TemplateCases temp=templateCasesService.selectOneTemp(tmpCode);
    	return R.ok().put("list", temp);
    }
    @ApiOperation("批量查询模板数据")
    @GetMapping("/getListTemp")
    public R getListTemp(@ApiParam("当前页")String current,@ApiParam("分页大小")String pageSize,
    		@ApiParam("一级编码")String lv1DeptCode,@ApiParam("二级编码")String lv2DeptCode,
    		@ApiParam("模板名称")String tmpName,@ApiParam("模板类型")String templateType,@ApiParam("状态")String status){
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
    	//查询结果根据更新时间进行倒叙排列 DESC
    	Page<TemplateCases> page=new Page<>(offset,limit);
    	templateCasesService.getListTemp(page, offset, limit, lv1DeptCode, lv2DeptCode, tmpName, templateType, status);
    	return R.ok().put("list",page.getRecords()).put("total",page.getTotal());
    }
    private void addItem(List<Map<String, String>> list, String code, String name) {
    	Map<String, String> item = new HashMap<String, String>();
    	item.put("code", code);
    	item.put("name", name);
    	list.add(item);
    }
}
