package com.drelephant.elephantadmin.business.basedata.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController;
import com.drelephant.elephantadmin.business.basedata.entity.BdAreaRegion;
import com.drelephant.elephantadmin.business.basedata.service.BdAreaRegionService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 地区区域信息管理 前端控制器
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Api(tags = "地区区域信息管理")
@RestController
@RequestMapping("bdAreaRegion")
public class BdAreaRegionController extends BaseController {
    @Autowired
    private BdAreaRegionService bdAreaRegionService;
 /*******************新增接口地址***************/  
    
    @ApiImplicitParams({
        @ApiImplicitParam(name = "level", value = "层级", required = true),
        @ApiImplicitParam(name = "code", value = "编码", required = true),
        @ApiImplicitParam(name = "provinceCode", value = "省编码") ,
        @ApiImplicitParam(name = "cityCode", value = "市编码") ,
        @ApiImplicitParam(name = "countyCode", value = "区县编码") ,
        @ApiImplicitParam(name = "status", value = "状态") 
    })
    @ApiOperation("行政地区新增")
    @PostMapping("/saveAdmin")
    public R saveAdmin(@RequestBody @ApiParam("数据对象")BdAreaRegion entity){
    	if(entity == null){
			return R.error("保存行政地区失败，参数无效!");
		}
    	
        return bdAreaRegionService.insertBdAreaRegion(entity);
    }
    @ApiOperation("更新状态")
    @PostMapping("/updateStatus")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "provinceCode", value = "省编码") ,
        @ApiImplicitParam(name = "cityCode", value = "市编码") ,
        @ApiImplicitParam(name = "countyCode", value = "区县编码") ,
        @ApiImplicitParam(name = "status", value = "状态") 
    })
    public R updateStatus(@ApiParam("数据对象") @RequestBody BdAreaRegion entity){
    	if(entity == null){
			return R.error("修改行政地区失败，参数无效!");
		}
    	bdAreaRegionService.updateStatus(entity);
        return R.ok().put("msg", "更新状态成功！");
    }
    @ApiOperation("单条删除")
    @PostMapping("/deleteCode")
    public R deleteCode(@ApiParam(value = "id", required = true) String id){  	
        return bdAreaRegionService.deleteBdAreaRegion(id);
    } 
    @ApiOperation("批量更新地区状态")
    @PostMapping("/updateBatchCode")
    public R updateBatchCode(@ApiParam(value="是否启用")String status,@ApiParam("地区编码")String codes ){
    	if(StringUtils.isNotBlank(status)){
    		if(status.equals(Constans.ACTIVE)){
    			status=Constans.ACTIVE;
    		}else{
    			status=Constans.INVALID;
    		}
    	}
        return bdAreaRegionService.updateBatchBdAreaRegion(status,codes);
    }
    
//    @ApiOperation("获取行政地区tree")
//    @PostMapping("/getAreaRegionTree")
    public R getAreaRegionTree(){  
    	return bdAreaRegionService.getAreaRegionTree();
    }
    
    @ApiOperation("获取省份列表")
    @GetMapping("/getProvinceList")
    public R getProvinceList(){
    	return bdAreaRegionService.getProvinceList();
    }
    
    @ApiOperation("获取城市列表")
    @GetMapping("/getCityList")
    public R getCityList(@ApiParam("省份编码")String provinceCode){
    	return bdAreaRegionService.getCityList(provinceCode);
    }
    
    @ApiOperation("获取区县列表")
    @GetMapping("/getCountyList")
    public R getCountyList(@ApiParam("城市编码")String cityCode){
    	return bdAreaRegionService.getCountyList(cityCode);
    }
    
    @ApiOperation("列表数据")   
    @GetMapping("/getListAdmin")
    public R getListAdmin(@ApiParam(value="当前页")@RequestParam(value="current" ,defaultValue="1" ,required=false)String current,
    		@RequestParam(value="pageSize" ,defaultValue="1000" ,required=false)@ApiParam("分页大小") String pageSize,
    		@ApiParam("地区编码")String code,@ApiParam("省份code")String provinceCode,
    		@ApiParam("城市code")String cityCode,@ApiParam("区县code")String countyCode,
    		@ApiParam("层级")  Integer level,@ApiParam("状态")String status ){ 
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
		
    	Page<BdAreaRegion> page=new Page<>(offset,limit);
    	bdAreaRegionService.getListBdAreaRegion(page, code, provinceCode, cityCode, countyCode, level, status);
        return R.ok().put("list",page.getRecords()).put("total",page.getTotal());
    }
    @ApiOperation("层级下拉数据")
    @GetMapping("/getListLevel")
    public R getListLevel(){  
    	List<Map<String, Object>> levels = new ArrayList<Map<String, Object>>();
		Map<String, Object> level = null;
		List<Integer> list=new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
    	for (Integer integer : list) {
    		level = new HashMap<String, Object>();
    		level.put("level", integer);
    		levels.add(level);
		}
    	return R.ok().put("list", levels);
    }
    @ApiOperation("状态下拉数据")
    @GetMapping("/getListStatus")
    public R getListStatus(){  
    	List<Map<String, Object>> statuss = new ArrayList<Map<String, Object>>();
		Map<String, Object> status = null;
		List<String> list=new ArrayList<String>();
		list.add(Constans.ACTIVE);
		list.add(Constans.INVALID);
    	for (String str : list) {
    		status = new HashMap<String, Object>();
    		status.put("status", str);
    		statuss.add(status);
		}
    	return R.ok().put("list", statuss);
    }
}
