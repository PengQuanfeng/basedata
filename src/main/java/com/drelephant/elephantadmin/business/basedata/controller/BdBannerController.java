package com.drelephant.elephantadmin.business.basedata.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController;
import com.drelephant.elephantadmin.business.basedata.entity.BdBanner;
import com.drelephant.elephantadmin.business.basedata.service.BdBannerService;
import com.drelephant.framework.base.common.R;
import io.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    /**
     * 新增接口.
     *
     * @param data data
     * @return R
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileId", value = "图片文件id", required = true),
            @ApiImplicitParam(name = "fileName", value = "fileName,本地地址", required = true),
            @ApiImplicitParam(name = "isOpenLink", value = "是否开启链接, Y/N", required = true),
            @ApiImplicitParam(name = "linkAddress", value = "链接地址", required = true),
            @ApiImplicitParam(name = "picAddress", value = "图片地址", required = true),
            @ApiImplicitParam(name = "remark", value = "备注", required = true)
    })
    @ApiOperation("新增首页图片信息")
    @PostMapping("/saveBaner")
    public R add(@RequestBody BdBanner data) {
        if (data == null) {
            return R.error("数据无效");
        }
        return bdBannerService.insertRecAndCheckCount10(data);
    }

    /**
     * 更新.
     *
     * @param data
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "picAddress", value = "本地路径", required = true),
            @ApiImplicitParam(name = "isOpenLink", value = "是否开启链接", required = true),
            @ApiImplicitParam(name = "linkAddress", value = "链接地址"),
            @ApiImplicitParam(name = "remark", value = "备注"),
            @ApiImplicitParam(name = "id", value = "id", required = true)
    })
    @ApiOperation("更新单条首页图片信息")
    @PostMapping("/updateOneBaner")
    public R updateOneBaner(@RequestBody @ApiParam("数据对象") BdBanner data) {
        if (data == null) {
            return R.error().put("msg", "参数为空,更新失败");
        }
        return bdBannerService.updateBdBander(data);
    }

    /**
     * 删除.
     *
     * @param map
     * @return
     */
    @ApiOperation("删除单条首页图片信息")
    @PostMapping("/deleteOneBaner")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true)
    })
    public R deleteOneBaner(@RequestBody Map<String, String> map) {
        if (StringUtils.isBlank(map.get("id"))) {
            return R.error("id不能为空");
        }
        //进行逻辑删除
        return bdBannerService.updateStatus(map.get("id"));
    }

    /**
     * 列表查询.
     *
     * @return
     */
    @ApiOperation("列表查询")
    @GetMapping("/getListBanner")
    public R getListBanner() {
        //最多10张图片，未要求排序
        return R.ok().put("list", bdBannerService.listAllACT());
    }

    //todo 逻辑需要更新
    @ApiOperation("上移")
    @PostMapping("/moveUp")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true)
    })
    public R moveUp(@RequestBody Map<String, String> map) {
        if (StringUtils.isBlank(map.get("id"))) {
            return R.error("id不能为空");
        }
        return bdBannerService.moveUp(map.get("id"));
    }

    //todo 逻辑需要更新
    @ApiOperation("下移")
    @PostMapping("/moveDown")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true)
    })
    public R moveDown(@RequestBody Map<String, String> map) {
        if (StringUtils.isBlank(map.get("id"))) {
            return R.error("id不能为空");
        }
        return bdBannerService.moveDown(map.get("id"));
    }

    @ApiOperation("单条查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "本条数据对应的id")
    })
    @GetMapping("/getOnetBanner")
    public R getOnetBanner(String id) {
        if (StringUtils.isBlank(id)) {
            return R.error().put("msg", "参数为空");
        }
        return R.ok().put("data", bdBannerService.selectOne(Condition.create().eq("id", id)));
    }
}
