package com.drelephant.elephantadmin.business.basedata.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.drelephant.framework.base.common.R;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController; 
import com.drelephant.elephantadmin.business.basedata.entity.BdCompanyDept;
import com.drelephant.elephantadmin.business.basedata.service.BdCompanyDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiOperation("获取list")
    @PostMapping("/list")
    public R getList(@ApiParam("当前页")int current,@ApiParam("分页大小")int pageSize){
        Page<BdCompanyDept> page=new Page<>(current,pageSize);
        bdCompanyDeptService.selectPage(page);
        return R.ok().put("list",page.getRecords()).put("total",page.getTotal());
    }
    @ApiOperation("新增")
    @PostMapping("/add")
    public R save(@ApiParam("数据对象")BdCompanyDept data){
        return bdCompanyDeptService.insert(data)?R.ok():R.error("保存错误");
    }
    @ApiOperation("删除")
    @PostMapping("/delete")
    public R delete(@ApiParam("数据对象id")String id){
        return bdCompanyDeptService.deleteById(id)?R.ok():R.error("删除错误");
    }
    @ApiOperation("更新")
    @PostMapping("/update")
    public R update(@ApiParam("数据对象")BdCompanyDept data){
        return bdCompanyDeptService.updateById(data)?R.ok():R.error("更新错误");
    }
    @ApiOperation("通过ID获取一条数据")
    @PostMapping("/info")
    public R update(@ApiParam("数据对象id")String id){
        return R.ok().put("info",bdCompanyDeptService.selectById(id));
    }
    @ApiOperation("添加部门")
    @PostMapping("/addCompany")
    public R addCompany(@ApiParam("数据对象")BdCompanyDept data){
    	return bdCompanyDeptService.addCompany(data);
    }
    @ApiOperation("编辑部门")
    @PostMapping("/updateDept")
    public R updateDept(@ApiParam("数据对象")BdCompanyDept data){
        return bdCompanyDeptService.updateCompany(data);
    }
    @ApiOperation("删除部门")
    @PostMapping("/deleteDept")
    public R deleteDept(@ApiParam("数据对象id")String id){
        return bdCompanyDeptService.delectCompany(id);
    }
    @ApiOperation("获取部门信息列表的list")
    @PostMapping("/getDeptList")
    public R getDeptList(@ApiParam(value="当前页",required = true)int current,@ApiParam(value="分页大小",required = true)int pageSize){        
        return bdCompanyDeptService.getListDept(current, pageSize);
    }
}
