package com.drelephant.elephantadmin.business.basedata.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.drelephant.framework.base.common.R;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController; 
import com.drelephant.elephantadmin.business.basedata.entity.BdBusinessRegion;
import com.drelephant.elephantadmin.business.basedata.service.BdBusinessRegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Api(tags = "")
@RestController
@RequestMapping("bdBusinessRegion")
public class BdBusinessRegionController extends BaseController {
    @Autowired
    private BdBusinessRegionService bdBusinessRegionService;
/***********************业务区域信息设置新增***********************/
    
    @ApiOperation("业务区域信息新增")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "serviceCategoryName", value = "层级编码", required = true),
        @ApiImplicitParam(name = "lv1Code", value = "区域编码", required = true),
        @ApiImplicitParam(name = "lv1Name", value = "一级区域名称", required = true)       
    })
    @PostMapping("/saveRegion")
    public R saveRegion(@RequestBody @ApiParam("数据对象")BdBusinessRegion entity){
    	//确认同层级下的区域名称保持唯一
    	if(entity == null){
			return R.error("保存业务区域信息失败，参数无效!");
		}
        bdBusinessRegionService.inserRegion(entity);
        return R.ok().put("msg", "新增业务区域信息成功！");
    }
    @ApiOperation("更新区域信息")
    @PostMapping("/updateRegion")
    public R updateRegion(@ApiParam("数据对象")BdBusinessRegion data){
        return bdBusinessRegionService.updateRegion(data);
    }
    @ApiOperation("删除区域信息")
    @PostMapping("/deleteRegion")
    public R deleteRegion(@ApiParam("数据对象id")String id){
    	BdBusinessRegion m=new BdBusinessRegion();
    	m.setLv1Code(id);
        return bdBusinessRegionService.deleteOneRegion(m);
    }
    @ApiOperation("获取list")
    @PostMapping("/getListRegion")
    public R getListRegion(@ApiParam("当前页")int current,@ApiParam("分页大小")int pageSize){
        Page<BdBusinessRegion> page=new Page<>(current,pageSize);
        bdBusinessRegionService.getListRegion(page);
        return R.ok().put("list",page.getRecords()).put("total",page.getTotal());
    }
    @ApiOperation("单条区域信息")
    @PostMapping("/getOneRegion")
    public R getOneRegion(@ApiParam("区域编码")String lcodes,@ApiParam("层级")Integer level){
    	
    	BdBusinessRegion bd=bdBusinessRegionService.selectOneRegion(lcodes,level);
        return R.ok().put("list", bd);
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
    @ApiOperation("1级区域列表")
    @PostMapping("/getListLv1")
    public R getListLv1(){    	
        return bdBusinessRegionService.bdLv1();
    }
    @ApiOperation("2级区域列表")
    @PostMapping("/getListLv2")
    public R getListLv2(String lv1Code){    	
        return bdBusinessRegionService.bdLv2(lv1Code);
    }
}
