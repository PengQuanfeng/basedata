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
import com.drelephant.elephantadmin.business.basedata.entity.BdHospitalDept;
import com.drelephant.elephantadmin.business.basedata.service.BdHospitalDeptService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 医疗科室表 前端控制器
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Api(tags = "医疗科室表")
@RestController
@RequestMapping("bdHospitalDept")
public class BdHospitalDeptController extends BaseController {
    @Autowired
    private BdHospitalDeptService bdHospitalDeptService;

    /**********************新增接口方法***********************/
    @ApiOperation("新增科室信息")
    @ApiImplicitParams({
//		@ApiImplicitParam(name = "code", value = "科室编码", required = true),
		@ApiImplicitParam(name = "lv1Code", value = "一级科室编码"),
		@ApiImplicitParam(name = "lv1Name", value = "一级科室名称新增一级时输入"),
		@ApiImplicitParam(name = "lv2Code", value = "二级科室编码"),
		@ApiImplicitParam(name = "lv2Name", value = "二级科室名称新增二级时输入"),
		@ApiImplicitParam(name = "lv3Code", value = "二级科室编码"),
		@ApiImplicitParam(name = "lv3Name", value = "二级科室名称新增三级时输入"),
		@ApiImplicitParam(name = "regulatoryCode", value = "监管编码", required = true),
		@ApiImplicitParam(name = "level", value = "层级"),
		@ApiImplicitParam(name = "status", value = "状态ACT(启用)/INV(禁用)")
		
	})
    @PostMapping("/saveDept")
    public R saveDept(@RequestBody @ApiParam("数据对象")BdHospitalDept data){
    	//code 接收前台传递的科室编码，1级时作为1级编码，2级时为2级编码，3级时为3级编码
//    	if(StringUtils.isBlank(code)){
//    		return R.error().put("msg", "科室编码不能为空");
//    	}
    	if(data==null){
    		return R.error().put("msg", "参数为空");
    	}
        return bdHospitalDeptService.insertHost(data);
    }
    @ApiOperation("单条更新科室信息")
    @PostMapping("/updateOneDept")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "ID"),
		@ApiImplicitParam(name = "lv1Name", value = "一级科室名称"),
		@ApiImplicitParam(name = "lv2Name", value = "二级科室名称"),
		@ApiImplicitParam(name = "lv3Name", value = "二级科室名称"),
		@ApiImplicitParam(name = "level", value = "层级"),
		@ApiImplicitParam(name = "status", value = "状态ACT(启用)/INV(禁用)")
		
	})
    public R updateOneDept(@RequestBody @ApiParam("数据对象")BdHospitalDept data){
    	if(data==null){
    		return R.error().put("msg", "参数为空");
    	}
        return bdHospitalDeptService.updateHost(data);
    }
    @ApiOperation("单条查看科室信息")
    @PostMapping("/selectOneDept")
    public R selectOneDept(@ApiParam("一级科室编码")String lv1Code){
    	BdHospitalDept bd=bdHospitalDeptService.selectOneDept(lv1Code);
        return R.ok().put("list", bd);
    }
    @ApiOperation("单条删除科室信息")
    @PostMapping("/deleteOneDept")
    public R deleteOneDept(@ApiParam(value="科室编码",required=true)String lv1Code){
        return bdHospitalDeptService.deleteOneHost(lv1Code);
    }
    @ApiOperation("获取科室信息列表")
    @GetMapping("/getListDept")
    public R getListDept(@ApiParam("科室编码")String code, @ApiParam("当前页")String current,@ApiParam("分页大小")String pageSize,@ApiParam("科室编码")String lv1Code,
    		@ApiParam("2级科室code")String lv2Code,@ApiParam("3级科室code")String lv3Code,
    		@ApiParam("层级")String level,@ApiParam("状态")String status){
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
        Page<BdHospitalDept> page=new Page<>(offset,limit);
        bdHospitalDeptService.getListHost(page, code, lv1Code, lv2Code, lv3Code, level, status);
        return R.ok().put("list",page.getRecords()).put("total",page.getTotal());
    }
    
    @ApiOperation("层级下拉数据")
    @GetMapping("/getListLevel")
    public R getListLevel(){
    	//首先定义一个 集合用来存储返回数据
    	List<Map<String, Object>> levels = new ArrayList<Map<String, Object>>();
    	//定义一个空的Map集合
		Map<String, Object> level = null;
		//用来存放下拉数据的List集合
		List<Integer> list=new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		//遍历存放下拉数据的集合
    	for (Integer integer : list) {
    		level = new HashMap<String, Object>();
    		//数据放到map集合中
    		level.put("level", integer);
    		//将map集合数据放入到返回的集合中
    		levels.add(level);
		}
    	return R.ok().put("list", levels);
    }
    @ApiOperation("获取状态下拉列表数据")
    @GetMapping("/getStatusList")
    public R getStatusList(){
    	List<Map<String,Object>> statuss=new ArrayList<Map<String,Object>>();
    	Map<String,Object> map=null;
    	List<String> status=new ArrayList<String>();
    	status.add(Constans.ACTIVE);
    	status.add(Constans.INVALID);
    	for (String str : status) {
			map=new HashMap<String,Object>();
			map.put("status", str);
			statuss.add(map);
		} 
    	return R.ok().put("list", statuss);
    }
    @ApiOperation("获取1级科室列表")
    @GetMapping("/getLv1NameList")
    public R getLv1NameList(){  	
    	return bdHospitalDeptService.getLv1List();
    }
    @ApiOperation("获取2级科室列表")
    @GetMapping("/getLv2NameList")
    public R getLv2NameList(String lv1Code){
    	return bdHospitalDeptService.getLv2List(lv1Code);
    }
    @ApiOperation("获取3级科室列表")
    @GetMapping("/getLv3NameList")
    public R getLv3NameList(String lv2Code){
    	return bdHospitalDeptService.getLv3List(lv2Code);
    }
}
