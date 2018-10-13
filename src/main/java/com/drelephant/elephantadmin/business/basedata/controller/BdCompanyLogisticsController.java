package com.drelephant.elephantadmin.business.basedata.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.drelephant.framework.base.common.R;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController; 
import com.drelephant.elephantadmin.business.basedata.entity.BdCompanyLogistics;
import com.drelephant.elephantadmin.business.basedata.service.BdCompanyLogisticsService;
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
 * 物流公司信息表 前端控制器
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Api(tags = "物流公司信息表")
@RestController
@RequestMapping("bdCompanyLogistics")
public class BdCompanyLogisticsController extends BaseController {
    @Autowired
    private BdCompanyLogisticsService bdCompanyLogisticsService;
    @ApiOperation("获取list")
    @PostMapping("/list")
    public R getList(@ApiParam("当前页")int current,@ApiParam("分页大小")int pageSize){
        Page<BdCompanyLogistics> page=new Page<>(current,pageSize);
        bdCompanyLogisticsService.selectPage(page);
        return R.ok().put("list",page.getRecords()).put("total",page.getTotal());
    }
    @ApiOperation("新增")
    @PostMapping("/add")
    public R save(@ApiParam("数据对象")BdCompanyLogistics data){
        return bdCompanyLogisticsService.insert(data)?R.ok():R.error("保存错误");
    }
    @ApiOperation("删除")
    @PostMapping("/delete")
    public R delete(@ApiParam("数据对象id")String id){
        return bdCompanyLogisticsService.deleteById(id)?R.ok():R.error("删除错误");
    }
    @ApiOperation("更新")
    @PostMapping("/update")
    public R update(@ApiParam("数据对象")BdCompanyLogistics data){
        return bdCompanyLogisticsService.updateById(data)?R.ok():R.error("更新错误");
    }
    @ApiOperation("通过ID获取一条数据")
    @PostMapping("/info")
    public R update(@ApiParam("数据对象id")String id){
        return R.ok().put("info",bdCompanyLogisticsService.selectById(id));
    }
/**********************新增接口方法***************************/
    @ApiOperation("获取物流信息list")
    @PostMapping("/getListLogistics")
    public R getListLogistics(@ApiParam("当前页")int current,@ApiParam("分页大小")int pageSize,
    		@ApiParam("公司名称")String name,@ApiParam("状态")String status){
        Page<BdCompanyLogistics> page=new Page<>(current,pageSize);
        bdCompanyLogisticsService.getListLogis(page,name,status);
        return R.ok().put("list",page.getRecords()).put("total",page.getTotal());
    }
    @ApiOperation("更新公司物流状态")
    @PostMapping("/updateLogistics")
    public R updateLogistics(@ApiParam("数据对象")BdCompanyLogistics data){
        return bdCompanyLogisticsService.updateLogisStatus(data);
    }
    @ApiOperation("获取物流字典状态下拉列表数据")
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
}
