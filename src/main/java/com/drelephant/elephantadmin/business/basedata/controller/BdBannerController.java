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
    @ApiOperation("新增首页图片信息")
    @PostMapping("/saveBaner")
    public R saveBaner(@ApiParam("数据对象")BdBanner data){
        return bdBannerService.insertBdBander(data);
    }
    @ApiOperation("查询单条首页图片信息")
    @PostMapping("/getOneBaner")
    public R getOneBaner(@ApiParam("id列")String id){
    	BdBanner bd=bdBannerService.selectOneBd(id);
        return R.ok().put("list", bd);
    }
    @ApiOperation("更新单条首页图片信息")
    @PostMapping("/updateOneBaner")
    public R updateOneBaner(@ApiParam("数据对象")BdBanner data){
        return bdBannerService.updateBdBander(data);
    }
    @ApiOperation("删除单条首页图片信息")
    @PostMapping("/deleteOneBaner")
    public R deleteOneBaner(@ApiParam("id")String  id){
    	//进行逻辑删除   	
        return bdBannerService.updateStatus(id);
    }
    @ApiOperation("列表查询")
    @PostMapping("/getListBanner")
    public R getListBanner(){
    	//最多10张图片，未要求排序    	
    	
        return R.ok().put("list", bdBannerService.getListBd());
    }

/************待写接口*********************/
    @ApiOperation("上移下移字段排序")
    @PostMapping("/updateOrderNumberBd")
    public R updateOrderNumberBd(@ApiParam("id")String id,@ApiParam("排序字段")int orderNumber){
    	
        return R.ok();
    }
 
}
