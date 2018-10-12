package com.drelephant.elephantadmin.business.basedata.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.drelephant.framework.base.common.R;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController; 
import com.drelephant.elephantadmin.business.basedata.entity.BdBusinessRegion;
import com.drelephant.elephantadmin.business.basedata.service.BdBusinessRegionService;
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
@RequestMapping("bdBusinessRegion")
public class BdBusinessRegionController extends BaseController {
    @Autowired
    private BdBusinessRegionService bdBusinessRegionService;
    @ApiOperation("获取list")
    @PostMapping("/list")
    public R getList(@ApiParam("当前页")int current,@ApiParam("分页大小")int pageSize){
        Page<BdBusinessRegion> page=new Page<>(current,pageSize);
        bdBusinessRegionService.selectPage(page);
        return R.ok().put("list",page.getRecords()).put("total",page.getTotal());
    }
    @ApiOperation("新增")
    @PostMapping("/add")
    public R save(@ApiParam("数据对象")BdBusinessRegion data){
        return bdBusinessRegionService.insert(data)?R.ok():R.error("保存错误");
    }
    @ApiOperation("删除")
    @PostMapping("/delete")
    public R delete(@ApiParam("数据对象id")String id){
        return bdBusinessRegionService.deleteById(id)?R.ok():R.error("删除错误");
    }
    @ApiOperation("更新")
    @PostMapping("/update")
    public R update(@ApiParam("数据对象")BdBusinessRegion data){
        return bdBusinessRegionService.updateById(data)?R.ok():R.error("更新错误");
    }
    @ApiOperation("通过ID获取一条数据")
    @PostMapping("/info")
    public R update(@ApiParam("数据对象id")String id){
        return R.ok().put("info",bdBusinessRegionService.selectById(id));
    }
/***********************业务区域信息设置新增***********************/
    @ApiOperation("业务区域信息新增")
    @PostMapping("/saveRegion")
    public R saveRegion(@ApiParam("数据对象")BdBusinessRegion data){
        return bdBusinessRegionService.inserRegion(data);
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
}
