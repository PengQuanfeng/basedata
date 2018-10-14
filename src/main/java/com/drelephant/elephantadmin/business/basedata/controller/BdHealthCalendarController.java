package com.drelephant.elephantadmin.business.basedata.controller;


import com.drelephant.framework.base.common.R;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController; 
import com.drelephant.elephantadmin.business.basedata.entity.BdHealthCalendar;
import com.drelephant.elephantadmin.business.basedata.service.BdHealthCalendarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 大象健康日历 前端控制器
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Api(tags = "大象健康日历")
@RestController
@RequestMapping("bdHealthCalendar")
public class BdHealthCalendarController extends BaseController {
    @Autowired
    private BdHealthCalendarService bdHealthCalendarService;

    /**
     * 图片上传时保存信息
     */
    @ApiImplicitParams({
        @ApiImplicitParam(name = "coverPicId", value = "封面图片ID", required = true),       
        @ApiImplicitParam(name = "contentPicId", value = "内容图片ID", required = true),
        @ApiImplicitParam(name = "publishTime", value = "发布时间", required = true)       
    })
    @ApiOperation("上传接口")
	@PostMapping("/upload")
	public R fileUpload(BdHealthCalendar entity){
    	if(entity != null){
			return R.error("上传失败，参数无效!");
		}
    	bdHealthCalendarService.saveBdHealthCalendar(entity);
    	return R.ok().put("msg", "上传成功");
	}
	/**
	 * 删除选中的图片信息
	 */
    @ApiOperation("删除")
	@PostMapping("/deletePic")
	public R deletePic(@ApiParam("id") String id){
		if(StringUtils.isBlank(id)){
			return R.error("删除失败，参数无效!");
		}
		bdHealthCalendarService.deleteBdHealthCalendar(id);
    	return R.ok().put("msg", "删除成功");
	}
    /*****待写接口***********/
    /**
     * 左侧查询出当月中上传图片的天数信息列表
     * @return
     */
    public void getListDate(@ApiParam("传入的时间")String date){
    	
    }
}
