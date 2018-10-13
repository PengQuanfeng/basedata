package com.drelephant.elephantadmin.business.basedata.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.drelephant.framework.base.common.R;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController; 
import com.drelephant.elephantadmin.business.basedata.entity.BdHospitalDept;
import com.drelephant.elephantadmin.business.basedata.entity.BdOrg;
import com.drelephant.elephantadmin.business.basedata.service.BdHospitalDeptService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiOperation("获取list")
    @PostMapping("/list")
    public R getList(@ApiParam("当前页")int current,@ApiParam("分页大小")int pageSize){
        Page<BdHospitalDept> page=new Page<>(current,pageSize);
        bdHospitalDeptService.selectPage(page);
        return R.ok().put("list",page.getRecords()).put("total",page.getTotal());
    }
    @ApiOperation("新增")
    @PostMapping("/add")
    public R save(@ApiParam("数据对象")BdHospitalDept data){
        return bdHospitalDeptService.insert(data)?R.ok():R.error("保存错误");
    }
    @ApiOperation("删除")
    @PostMapping("/delete")
    public R delete(@ApiParam("数据对象id")String id){
        return bdHospitalDeptService.deleteById(id)?R.ok():R.error("删除错误");
    }
    @ApiOperation("更新")
    @PostMapping("/update")
    public R update(@ApiParam("数据对象")BdHospitalDept data){
        return bdHospitalDeptService.updateById(data)?R.ok():R.error("更新错误");
    }
    @ApiOperation("通过ID获取一条数据")
    @PostMapping("/info")
    public R update(@ApiParam("数据对象id")String id){
        return R.ok().put("info",bdHospitalDeptService.selectById(id));
    }
    /**********************新增接口方法***********************/
    @ApiOperation("新增科室信息")
    @PostMapping("/saveDept")
    public R saveDept(@ApiParam("数据对象")BdHospitalDept data){
        return bdHospitalDeptService.insertHost(data);
    }
    @ApiOperation("单条更新科室信息")
    @PostMapping("/updateOneDept")
    public R updateOneDept(@ApiParam("数据对象")BdHospitalDept data){
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
    public R deleteOneDept(@ApiParam("数据对象")BdHospitalDept data){
        return bdHospitalDeptService.deleteOneHost(data);
    }
    @ApiOperation("获取科室信息列表")
    @PostMapping("/getListDept")
    public R getListDept(@ApiParam("当前页")int current,@ApiParam("分页大小")int pageSize,@ApiParam("科室编码")String lv1Code,
    		@ApiParam("2级科室code")String lv2Code,@ApiParam("3级科室code")String lv3Code,
    		@ApiParam("层级")String level,@ApiParam("状态")String status){
        Page<BdHospitalDept> page=new Page<>(current,pageSize);
        bdHospitalDeptService.getListHost(page, lv1Code, lv2Code, lv3Code, level, status);
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
    @PostMapping("/getLv1NameList")
    public R getLv1NameList(){  	
    	return bdHospitalDeptService.getLv1List();
    }
    @ApiOperation("获取2级科室列表")
    @PostMapping("/getLv2NameList")
    public R getLv2NameList(String lv1Code){
    	return bdHospitalDeptService.getLv2List(lv1Code);
    }
    @ApiOperation("获取3级科室列表")
    @PostMapping("/getLv3NameList")
    public R getLv3NameList(String lv2Code){
    	return bdHospitalDeptService.getLv3List(lv2Code);
    }
}
