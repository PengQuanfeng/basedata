package com.drelephant.elephantadmin.business.basedata.controller;


import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController;
import com.drelephant.elephantadmin.business.basedata.entity.BdHealthCalendar;
import com.drelephant.elephantadmin.business.basedata.service.BdHealthCalendarService;
import com.drelephant.framework.base.common.R;
import io.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public R fileUpload(BdHealthCalendar entity) {
        if (entity != null) {
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
    public R deletePic(@ApiParam("id") String id) {
        if (StringUtils.isBlank(id)) {
            return R.error("删除失败，参数无效!");
        }
        bdHealthCalendarService.deleteBdHealthCalendar(id);
        return R.ok().put("msg", "删除成功");
    }
    /*****待写接口***********/
    /**
     * 查询月列表数据。
     * dateStr like : 2018-10-01
     * 返回数据为已经上传过的日期集合。
     *
     * @return
     */
    @GetMapping("/listMonth")
    public R listMonth(@ApiParam("传入的时间") String dateStr) {
        return R.ok().put("list", bdHealthCalendarService.selectListMonth(dateStr));
    }

    /**
     * 查询周列表数据。
     * dateStr like : 2018-10-01
     * 返回数据为一周数据集合.
     *
     * @param dateStr
     * @return
     */
    @GetMapping("/listWeek")
    public R listWeek(String dateStr) {
        return R.ok().put("list", bdHealthCalendarService.selectListWeek(dateStr));
    }

}
