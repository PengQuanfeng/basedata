package com.drelephant.elephantadmin.business.basedata.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.drelephant.framework.base.common.R;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController; 
import com.drelephant.elephantadmin.business.basedata.entity.BdBanner;
import com.drelephant.elephantadmin.business.basedata.service.BdBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * banner 前端控制器
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Api(tags = "banner")
@RestController
@RequestMapping("bdBanner")
public class BdBannerController extends BaseController {
    @Autowired
    private BdBannerService bdBannerService;
    @ApiOperation("获取list")
    @PostMapping("/list")
    public R getList(@ApiParam("当前页")int current,@ApiParam("分页大小")int pageSize){
        Page<BdBanner> page=new Page<>(current,pageSize);
        bdBannerService.selectPage(page);
        return R.ok().put("list",page.getRecords()).put("total",page.getTotal());
    }
    @ApiOperation("新增")
    @PostMapping("/add")
    public R save(@ApiParam("数据对象")BdBanner data){
        return bdBannerService.insert(data)?R.ok():R.error("保存错误");
    }
    @ApiOperation("删除")
    @PostMapping("/delete")
    public R delete(@ApiParam("数据对象id")String id){
        return bdBannerService.deleteById(id)?R.ok():R.error("删除错误");
    }
    @ApiOperation("更新")
    @PostMapping("/update")
    public R update(@ApiParam("数据对象")BdBanner data){
        return bdBannerService.updateById(data)?R.ok():R.error("更新错误");
    }
    @ApiOperation("通过ID获取一条数据")
    @PostMapping("/info")
    public R update(@ApiParam("数据对象id")String id){
        return R.ok().put("info",bdBannerService.selectById(id));
    }

}
