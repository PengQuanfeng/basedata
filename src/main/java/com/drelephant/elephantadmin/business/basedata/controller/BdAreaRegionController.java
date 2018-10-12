package com.drelephant.elephantadmin.business.basedata.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.drelephant.framework.base.common.R;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController; 
import com.drelephant.elephantadmin.business.basedata.entity.BdAreaRegion;
import com.drelephant.elephantadmin.business.basedata.service.BdAreaRegionService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiOperation("获取list")
    @PostMapping("/list")
    public R getList(@ApiParam("当前页")int current,@ApiParam("分页大小")int pageSize){
        Page<BdAreaRegion> page=new Page<>(current,pageSize);
        bdAreaRegionService.selectPage(page);
        return R.ok().put("list",page.getRecords()).put("total",page.getTotal());
    }
    @ApiOperation("新增")
    @PostMapping("/add")
    public R save(@ApiParam("数据对象")BdAreaRegion data){
        return bdAreaRegionService.insert(data)?R.ok():R.error("保存错误");
    }
    @ApiOperation("删除")
    @PostMapping("/delete")
    public R delete(@ApiParam("数据对象id")String id){
        return bdAreaRegionService.deleteById(id)?R.ok():R.error("删除错误");
    }
    @ApiOperation("更新")
    @PostMapping("/update")
    public R update(@ApiParam("数据对象")BdAreaRegion data){
        return bdAreaRegionService.updateById(data)?R.ok():R.error("更新错误");
    }
    @ApiOperation("通过ID获取一条数据")
    @PostMapping("/info")
    public R update(@ApiParam("数据对象id")String id){
        return R.ok().put("info",bdAreaRegionService.selectById(id));
    }
    
    @ApiOperation("行政地区新增")
    @PostMapping("/saveAdmin")
    public R saveAdmin(@ApiParam("数据对象")BdAreaRegion data){
        return bdAreaRegionService.insertBdAreaRegion(data);
    }
    @ApiOperation("更新状态")
    @PostMapping("/updateStatus")
    public R updateStatus(@ApiParam("数据对象")BdAreaRegion data){
        return bdAreaRegionService.updateStatus(data);
    }
    @ApiOperation("单条删除")
    @PostMapping("/deleteCode")
    public R deleteCode(@ApiParam("数据对象id")BdAreaRegion data){
        return bdAreaRegionService.deleteBdAreaRegion(data);
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
    @ApiOperation("获取行政地区list")
    @PostMapping("/getListAdmin")
    public R getListAdmin(@ApiParam("当前页")int current,@ApiParam("分页大小")int pageSize,
    		@ApiParam("地区编码")String code,@ApiParam("省份")String provinceName,
    		@ApiParam("城市")String cityName,@ApiParam("区县")String countyName,
    		@ApiParam("层级")Integer lever,@ApiParam("状态")String status ){  
    	Page<BdAreaRegion> page=new Page<>(current,pageSize);
    	bdAreaRegionService.getListBdAreaRegion(page, code, provinceName, cityName, countyName, lever, status);
        return R.ok().put("list",page.getRecords()).put("total",page.getTotal());
    }
    @ApiOperation("层级下拉数据")
    @PostMapping("/getListLevel")
    public R getListLevel(){  
//    	List<String> list=new ArrayList<String>();
//    	list.add("1");
//    	list.add("2");
//    	list.add("3");
//    	List<BdAreaRegion> bd=bdAreaRegionService.getListLevel();
//    	bd.get(1).getLevel();
    	List<Map<Integer,String>> lm=new ArrayList<Map<Integer,String>>();
    	Map<Integer,String> map=new HashMap<Integer,String>();
    	map.put(1, "1");
    	map.put(2, "2");
    	lm.add(map);
//    	bdAreaRegionService.getListLevel();
    	return R.ok().put("level", map);
    }
}
