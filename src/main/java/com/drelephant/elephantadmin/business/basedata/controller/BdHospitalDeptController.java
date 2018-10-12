package com.drelephant.elephantadmin.business.basedata.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.drelephant.framework.base.common.R;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController; 
import com.drelephant.elephantadmin.business.basedata.entity.BdHospitalDept;
import com.drelephant.elephantadmin.business.basedata.entity.BdOrg;
import com.drelephant.elephantadmin.business.basedata.service.BdHospitalDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
    @ApiOperation("单条删除科室信息")
    @PostMapping("/deleteOneDept")
    public R deleteOneDept(@ApiParam("数据对象")BdHospitalDept data){
        return bdHospitalDeptService.deleteOneHost(data);
    }
    @ApiOperation("获取科室信息列表")
    @PostMapping("/getListDept")
    public R getListDept(@ApiParam("当前页")int current,@ApiParam("分页大小")int pageSize,@ApiParam("科室编码")String lv1Code,
    		@ApiParam("一级科室名称")String lv1Name,@ApiParam("二级科室名称")String lv2Name,
    		@ApiParam("层级")String level,@ApiParam("状态")String status){
        Page<BdHospitalDept> page=new Page<>(current,pageSize);
        bdHospitalDeptService.getListHost(page, lv1Code, lv1Name, lv2Name, level, status);
        return R.ok().put("list",page.getRecords()).put("total",page.getTotal());
    }
}
