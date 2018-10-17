package com.drelephant.elephantadmin.business.basedata.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.drelephant.framework.base.common.R;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController; 
import com.drelephant.elephantadmin.business.basedata.entity.BdCompanyLogistics;
import com.drelephant.elephantadmin.business.basedata.service.BdCompanyLogisticsService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;

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
 
/**********************新增接口方法***************************/
    @ApiOperation("获取物流信息list")
    @GetMapping("/getListLogistics")
    public R getListLogistics(@ApiParam("当前页")String current,@ApiParam("分页大小")String pageSize,
    		@ApiParam("公司名称")String name,@ApiParam("状态")String status){
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
        Page<BdCompanyLogistics> page=new Page<>(offset,limit);
        bdCompanyLogisticsService.getListLogis(page,name,status);
        return R.ok().put("list",page.getRecords()).put("total",page.getTotal());
    }
    @ApiOperation("更新公司物流状态")
    @PostMapping("/updateLogistics")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "status", value = "状态"),
		@ApiImplicitParam(name = "code", value = "编码")
	})
    public R updateLogistics(@RequestBody @ApiParam("数据对象")BdCompanyLogistics data){
    	if(data==null){
    		return R.error().put("msg", "参数为空");
    	}
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
