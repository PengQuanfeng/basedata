package com.drelephant.elephantadmin.business.basedata.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController;
import com.drelephant.elephantadmin.business.basedata.entity.BdDictValue;
import com.drelephant.elephantadmin.business.basedata.service.BdDictValueService;
import com.drelephant.framework.base.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 数据字典表 前端控制器
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Api(tags = "数据字典表")
@RestController
@RequestMapping("bdDictValue")
public class BdDictValueController extends BaseController {
    @Autowired
    private BdDictValueService bdDictValueService;

    @ApiOperation("获取list")
    @PostMapping("/list")
    public R getList(@ApiParam("当前页") int current, @ApiParam("分页大小") int pageSize) {
        Page<BdDictValue> page = new Page<>(current, pageSize);
        bdDictValueService.selectPage(page);
        return R.ok().put("list", page.getRecords()).put("total", page.getTotal());
    }

    @ApiOperation("新增")
    @PostMapping("/add")
    public R save(@ApiParam("数据对象") BdDictValue data) {
        return bdDictValueService.insert(data) ? R.ok() : R.error("保存错误");
    }

    @ApiOperation("删除")
    @PostMapping("/delete")
    public R delete(@ApiParam("数据对象id") String id) {
        return bdDictValueService.deleteById(id) ? R.ok() : R.error("删除错误");
    }

    @ApiOperation("更新")
    @PostMapping("/update")
    public R update(@ApiParam("数据对象") BdDictValue data) {
        return bdDictValueService.updateById(data) ? R.ok() : R.error("更新错误");
    }

    @ApiOperation("通过ID获取一条数据")
    @PostMapping("/info")
    public R update(@ApiParam("数据对象id") String id) {
        return R.ok().put("info", bdDictValueService.selectById(id));
    }

    @ApiOperation("根据typeCode查询数据字典列表")
    @GetMapping("/selectValue")
    public R selectValue(@ApiParam("数据对象typeCode") String typeCode) {
        if (StringUtils.isBlank(typeCode)) {
            return R.error("参数为空");
        }
        List<BdDictValue> list = bdDictValueService.listValue(typeCode);
        return R.ok().put("list", list);
    }

    /**
     * api- selectByTypeCode
     *
     * @param typeCode typeCode
     * @return list
     */
    @GetMapping("/selectByTypeCodeApi")
    public List<BdDictValue> listByTypeCodeApi(@ApiParam("typeCode") String typeCode) {
        if (StringUtils.isBlank(typeCode)) {
            return Collections.emptyList();
        }
        //todo listValue 命名不准确.
        return bdDictValueService.listValue(typeCode);
    }

    /**
     * api- selectByCode
     *
     * @param code code
     * @return BdDictValue or null
     */
    @GetMapping("/selectByCodeApi")
    public BdDictValue selectByCodeApi(@ApiParam("code") String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        //noinspection unchecked
        return bdDictValueService.selectOne(Condition.create().eq("code", code).where("`status`!={0}", "DEL"));
    }

    /**
     * api- listByParentCode
     *
     * @param parentCode parentCode
     * @return List
     */
    @GetMapping("/listByParentCodeApi")
    public List<BdDictValue> listByParentCodeApi(@ApiParam("parentCode") String parentCode) {
        if (StringUtils.isBlank(parentCode)) {
            return Collections.emptyList();
        }
        final Wrapper wrapper = Condition.create().eq("parentCode", parentCode).where("`status`!={0}", "DEL");
        return bdDictValueService.selectList(wrapper);
    }


}
