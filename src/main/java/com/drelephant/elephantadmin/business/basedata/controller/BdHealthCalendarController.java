package com.drelephant.elephantadmin.business.basedata.controller;

import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController;
import com.drelephant.elephantadmin.business.basedata.entity.BdHealthCalendar;
import com.drelephant.elephantadmin.business.basedata.service.BdHealthCalendarService;
import com.drelephant.framework.base.common.R;
import io.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
     * 新增 图片
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", required = true),
            @ApiImplicitParam(name = "contentPicId", value = "图片ID", required = true),
            @ApiImplicitParam(name = "contentPicPath", value = "contentPicPath", required = true),
            @ApiImplicitParam(name = "publishTime", value = "发布时间", required = true)
    })
    @ApiOperation("新增")
    @PostMapping("/save")
    public R add(@RequestBody @ApiParam("数据对象") BdHealthCalendar entity) {
       return  bdHealthCalendarService.saveBdHealthCalendar(entity);
    }

    /**
     * 删除选中的图片信息
     */
    @ApiOperation("删除")
    @PostMapping("/deletePic")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true)
    })
    public R deletePic(@RequestBody Map<String, String> map) {
        String id = map.get("id");
        if (StringUtils.isBlank(id)) {
            return R.error("ID不能为空");
        }
        boolean flag = bdHealthCalendarService.deleteBdHealthCalendar(id);
        return flag ? R.ok().put("msg", "删除成功") : R.error("删除失败");
    }

    /**
     * 获取 当天日期
     *
     * @return 当天日期
     */
    @GetMapping("/getToday")
    public R getToday() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return R.ok().put("data", format.format(new Date()));
    }

    /**
     * 查询月列表数据。
     *
     * @param dateStr like : 2018-10-01
     *                返回数据为
     * @return 已经上传过的日期集合
     */
    @GetMapping("/listMonth")
    public R listMonth(@ApiParam("某天(格式:2018-10-01)") String dateStr) {
        return R.ok().put("list", bdHealthCalendarService.selectListMonth(dateStr));
    }

    /**
     * 某天所在周（包括几号日期与星期）的排期信息
     *
     * @param dateStr like : 2018-10-01
     * @return
     */
    @GetMapping("/listWeek")
    public R listWeek(@ApiParam("某天(格式:2018-10-01)") String dateStr) {
        return R.ok().put("list", bdHealthCalendarService.selectListWeek(dateStr));

    }

    /**
     * 更新保存.
     *
     * @param data
     * @return
     */
    @ApiOperation("编辑保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true),
            @ApiImplicitParam(name = "title", value = "title", required = true),
            @ApiImplicitParam(name = "contentPicId", value = "contentPicId", required = true),
            @ApiImplicitParam(name = "contentPicPath", value = "contentPicPath", required = true),
    })
    @PostMapping("/update")
    public R update(@RequestBody BdHealthCalendar data) {
        return bdHealthCalendarService.updateRecord(data);
    }

}
