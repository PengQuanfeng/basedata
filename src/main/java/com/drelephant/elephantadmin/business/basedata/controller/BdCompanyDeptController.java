package com.drelephant.elephantadmin.business.basedata.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController;
import com.drelephant.elephantadmin.business.basedata.entity.BdCompanyDept;
import com.drelephant.elephantadmin.business.basedata.service.BdCompanyDeptService;
import com.drelephant.framework.base.common.R;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 部门信息表 前端控制器
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Api(tags = "部门信息表")
@RestController
@RequestMapping("bdCompanyDept")
public class BdCompanyDeptController extends BaseController {
    @Autowired
    private BdCompanyDeptService bdCompanyDeptService;
    
	@ApiImplicitParams({
		@ApiImplicitParam(name = "companyCode", value = "公司编码", required = true),
		@ApiImplicitParam(name = "name", value = "部门名称", required = true),
		@ApiImplicitParam(name = "deptEmail", value = "负责人邮箱", required = true)
	})
    @ApiOperation("添加部门")
    @PostMapping("/addDept")
    public R addDept(@RequestBody @ApiParam("数据对象")BdCompanyDept data){
    	return bdCompanyDeptService.addDept(data);
    }
    
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "部门编码", required = true),
		@ApiImplicitParam(name = "name", value = "部门名称", required = true),
		@ApiImplicitParam(name = "deptEmail", value = "负责人邮箱", required = true)
	})
    @ApiOperation("编辑部门")
    @PostMapping("/updateDept")
    public R updateDept(@RequestBody @ApiParam("数据对象") BdCompanyDept data){
        return bdCompanyDeptService.updateDept(data);
    }
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "部门编码", required = true),
	})
    @ApiOperation("删除部门")
    @PostMapping("/deleteDept")
    public R deleteDept(@ApiParam("部门编码") String code){
        return bdCompanyDeptService.deleteDept(code);
    }
    
    @ApiOperation("获取部门信息列表的list")
    @GetMapping("/getDeptList")
    public R getDeptList(
    		@ApiParam(value="公司编码",required = true) String companyCode, 
    		@ApiParam(value="当前页",required = true) int current, 
    		@ApiParam(value="分页大小",required = true) int pageSize){        
        return bdCompanyDeptService.getListDept(companyCode, current, pageSize);
    }
    
    @ApiOperation("获取部门信息")
    @GetMapping("/getDept")
    public R getDept(
    		@ApiParam(value="部门编码",required = true) String code){        
        return bdCompanyDeptService.getDept(code);
    }
}
