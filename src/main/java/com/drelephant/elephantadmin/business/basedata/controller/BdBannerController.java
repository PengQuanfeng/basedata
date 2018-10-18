package com.drelephant.elephantadmin.business.basedata.controller;


import com.drelephant.framework.base.common.R;
import com.baomidou.mybatisplus.mapper.Condition;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController; 
import com.drelephant.elephantadmin.business.basedata.entity.BdBanner;
import com.drelephant.elephantadmin.business.basedata.service.BdBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
    @ApiImplicitParams({
		@ApiImplicitParam(name = "picAddress", value = "本地路径", required = true),
		@ApiImplicitParam(name = "isOpenLink", value = "是否开启链接0/1" ,required=true),
		@ApiImplicitParam(name = "linkAddress", value = "链接地址" ),
		@ApiImplicitParam(name = "remark", value = "备注" )
	})
    @ApiOperation("新增首页图片信息")
    @PostMapping("/saveBaner")
    public R saveBaner(@RequestBody @ApiParam("数据对象")BdBanner entity){
    	if(entity == null){
			return R.error("新增首页图片信息失败，参数无效!");
		}
    	bdBannerService.insertBdBander(entity);
        return R.ok().put("msg", "新增服务配置成功！");
    }
//    @ApiOperation("查询单条首页图片信息")
//    @GetMapping("/getOneBaner")
//    public R getOneBaner(@ApiParam("id列")String id){
//    	BdBanner bd=bdBannerService.selectOneBd(id);
//        return R.ok().put("list", bd);
//    }
    @ApiImplicitParams({
		@ApiImplicitParam(name = "picAddress", value = "本地路径", required = true),
		@ApiImplicitParam(name = "isOpenLink", value = "是否开启链接" , required = true),
		@ApiImplicitParam(name = "linkAddress", value = "链接地址" ),
		@ApiImplicitParam(name = "remark", value = "备注" ),
		@ApiImplicitParam(name = "id", value = "id",required=true)
	})
    @ApiOperation("更新单条首页图片信息")
    @PostMapping("/updateOneBaner")
    public R updateOneBaner(@RequestBody @ApiParam("数据对象")BdBanner data){
    	if(data==null){
    		return R.error().put("msg", "参数为空,更新失败");
    	}
        return bdBannerService.updateBdBander(data);
    }   
    @ApiOperation("删除单条首页图片信息")
    @PostMapping("/deleteOneBaner")
    public R deleteOneBaner(@ApiParam(name="id",value="数据列id",required=true)String  id){
    	if(StringUtils.isBlank(id)){
			return R.error("删除活动记录失败，参数无效!");
		}
    	//进行逻辑删除   	
        return bdBannerService.updateStatus(id);
    }
    @ApiOperation("列表查询")
    @GetMapping("/getListBanner")
    public R getListBanner(){
    	//最多10张图片，未要求排序    	    	
        return R.ok().put("list", bdBannerService.getListBd());
    }    
    @ApiOperation("上移")
    @PostMapping("/moveUp")
    public R moveUp(@ApiParam(name="id",value="字段id列")String id){
    	return bdBannerService.moveUp(id);
    }
    
    @ApiOperation("下移")
    @PostMapping("/moveDown")
    public R moveDown(@ApiParam(name="id",value="字段id列")String id){
    	return bdBannerService.moveDown(id);
    }
 //TODO 增加单条查询的接口
    @ApiOperation("单条查询接口")
    @ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "本条数据对应的id")
	})
    @GetMapping("/getOnetBanner")
    //@RequestBody Map<String, String> map
    public R getOnetBanner(String id){  	
    	//String id=map.get("id");
    	if(StringUtils.isBlank(id)){
    		return R.error().put("msg", "参数为空");
    	}   	
        return R.ok().put("data", bdBannerService.selectOne(Condition.create().eq("id", id)));
    } 
}
