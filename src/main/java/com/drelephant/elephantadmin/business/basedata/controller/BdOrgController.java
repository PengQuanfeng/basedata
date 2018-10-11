package com.drelephant.elephantadmin.business.basedata.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.drelephant.framework.base.common.R;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController; 
import com.drelephant.elephantadmin.business.basedata.entity.BdOrg;
import com.drelephant.elephantadmin.business.basedata.service.BdOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("bdOrg")
public class BdOrgController extends BaseController {
    @Autowired
    private BdOrgService bdOrgService;
    @ApiOperation("获取list")
    @PostMapping("/list")
    public R getList(@ApiParam("当前页")int current,@ApiParam("分页大小")int pageSize){
        Page<BdOrg> page=new Page<>(current,pageSize);
        bdOrgService.selectPage(page);
        return R.ok().put("list",page.getRecords()).put("total",page.getTotal());
    }
    @ApiOperation("新增")
    @PostMapping("/add")
    public R save(@ApiParam("数据对象")BdOrg data){
        return bdOrgService.insert(data)?R.ok():R.error("保存错误");
    }
    @ApiOperation("删除")
    @PostMapping("/delete")
    public R delete(@ApiParam("数据对象id")String id){
        return bdOrgService.deleteById(id)?R.ok():R.error("删除错误");
    }
    @ApiOperation("更新")
    @PostMapping("/update")
    public R update(@ApiParam("数据对象")BdOrg data){
        return bdOrgService.updateById(data)?R.ok():R.error("更新错误");
    }
    @ApiOperation("通过ID获取一条数据")
    @PostMapping("/info")
    public R update(@ApiParam("数据对象id")String id){
        return R.ok().put("info",bdOrgService.selectById(id));
    }
    @ApiOperation("增加公司")
    @PostMapping("/saveCompay")
    public R saveCompay(@ApiParam("数据对象")String company){
        return bdOrgService.addCompany(company)?R.ok():R.error("保存错误");
    }
    @ApiOperation("更新公司名称")
    @PostMapping("/updateName")
    public R updateName(@ApiParam("数据对象")BdOrg data){
        return bdOrgService.updateCompany(data);
    }
    @ApiOperation("删除")
    @PostMapping("/deleteCode")
    public R deleteCode(@ApiParam("数据对象id")String id){
        return bdOrgService.deleteCode(id);
    }
    @ApiOperation("获取公司信息的list")
    @PostMapping("/admim/list")
    public R getListName(){
        return bdOrgService.selectCompay();
    }
    @ApiOperation("新增医院信息")
    @PostMapping("/saveHospital")
    public R saveHospital(@ApiParam("数据对象")BdOrg data){
		return bdOrgService.addHospital(data);   	
    }
    @ApiOperation("获取医院信息的list")
    @PostMapping("/getListHospital")
    public R getListHospital(@ApiParam("当前页")int current,@ApiParam("分页大小")int pageSize){
    	Page<BdOrg> page=new Page<>(current,pageSize);
    	bdOrgService.getListBdOrg(page);
        return R.ok().put("list", page.getRecords()).put("total",page.getTotal());
    }
    @ApiOperation("更新医院状态")
    @PostMapping("/updateHosStatus")
    public R updateHosStatus(@ApiParam("数据对象") BdOrg data){   	
		return bdOrgService.updateOneHosStatus(data);    	
    }
    @ApiOperation("单条删除医院状态")
    @PostMapping("/deleteOneHosStatus")
    public R deleteOneHosStatus(@ApiParam("数据对象")BdOrg data){
        return bdOrgService.deleteOneHosStatus(data);
    }
    
}
