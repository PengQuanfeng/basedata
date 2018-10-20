package com.drelephant.elephantadmin.business.basedata.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.drelephant.framework.base.common.R;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController;
import com.drelephant.elephantadmin.business.basedata.entity.BdBusinessRegion;
import com.drelephant.elephantadmin.business.basedata.service.BdBusinessRegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 * 前端控制器
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

    /***********************业务区域信息设置新增***********************/

    @ApiOperation("业务区域信息新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "level", value = "层级", required = true),
            @ApiImplicitParam(name = "lv1Code", value = "区域编码", required = true),
            @ApiImplicitParam(name = "lv1Name", value = "一级区域名称"),
            @ApiImplicitParam(name = "lv2Name", value = "二级区域名称")
    })
    @PostMapping("/saveRegion")
    public R saveRegion(@RequestBody @ApiParam("数据对象") BdBusinessRegion entity) {
        //确认同层级下的区域名称保持唯一
        if (entity == null) {
            return R.error("保存业务区域信息失败，参数无效!");
        }

        return bdBusinessRegionService.inserRegion(entity);
    }

    @ApiOperation("更新区域信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "level", value = "层级", required = true),
            @ApiImplicitParam(name = "lv1Code", value = "区域编码", required = true),
            @ApiImplicitParam(name = "lv2Name", value = "二级区域名称")
    })
    @PostMapping("/updateRegion")
    public R updateRegion(@RequestBody @ApiParam("数据对象") BdBusinessRegion data) {
        return bdBusinessRegionService.updateRegion(data);
    }

    /**
     * 删除业务区域.
     *
     * @param data like {id:记录id}
     * @return R
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "数据记录id",required = true)
    })
    @ApiOperation("删除区域信息")
    @PostMapping("/deleteRegion")
    public R deleteRegion(@RequestBody  Map<String,String> data) {
        String id = data.get("id");
        if(StringUtils.isBlank(id)){
            return R.error("数据记录id为空");
        }
        return bdBusinessRegionService.deleteLogicById(id);
    }

    @ApiOperation("获取list")
    @GetMapping("/getListRegion")
    public R getListRegion(@ApiParam(value = "当前页") String current, @ApiParam(value = "分页大小") String pageSize,
                           @ApiParam(value = "编码/1级or2级") String code,
                           @ApiParam(value = "区域编码") String lv1Code, @ApiParam(value = "2级区域编码") String lv2Code,
                           @ApiParam(value = "层级") String level) {
        int offset = 1;
        int limit = 1000;
        if (StringUtils.isNotBlank(current)) {
            // 当前记录数
            offset = Integer.parseInt(current);
        }
        if (StringUtils.isNotBlank(pageSize)) {
            // 每页限制数
            limit = Integer.parseInt(pageSize);
        }
        Page<BdBusinessRegion> page = new Page<>(offset, limit);
        bdBusinessRegionService.getListRegion(page, code, lv1Code, lv2Code, level);
        return R.ok().put("list", page.getRecords()).put("total", page.getTotal());
    }

    @ApiOperation("单条区域信息")
    @GetMapping("/getOneRegion")
    public R getOneRegion(@ApiParam(value = "区域编码", required = true) String lcodes, @ApiParam(value = "层级", required = true) Integer level) {

        BdBusinessRegion bd = bdBusinessRegionService.selectOneRegion(lcodes, level);
        return R.ok().put("list", bd);
    }

    @ApiOperation("层级下拉数据")
    @GetMapping("/getListLevel")
    public R getListLevel() {
        //首先定义一个 集合用来存储返回数据
        List<Map<String, Object>> levels = new ArrayList<Map<String, Object>>();
        //定义一个空的Map集合
        Map<String, Object> level = null;
        //用来存放下拉数据的List集合
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        //遍历存放下拉数据的集合
        for (Integer integer : list) {
            level = new HashMap<String, Object>();
            //数据放到map集合中
            level.put("level", integer);
            //将map集合数据放入到返回的集合中
            levels.add(level);
        }
        return R.ok().put("list", levels);
    }

    @ApiOperation("1级区域列表")
    @GetMapping("/getListLv1")
    public R getListLv1() {
        return bdBusinessRegionService.bdLv1();
    }

    @ApiOperation("2级区域列表")
    @GetMapping("/getListLv2")
    public R getListLv2(@ApiParam(value = "区域编码", required = true) String lv1Code) {
        return bdBusinessRegionService.bdLv2(lv1Code);
    }
}
