package com.drelephant.elephantadmin.business.basedata.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController;
import com.drelephant.elephantadmin.business.basedata.entity.BdDictType;
import com.drelephant.elephantadmin.business.basedata.service.BdDictTypeService;
import com.drelephant.framework.base.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据字典类型表 前端控制器
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Api(tags = "数据字典类型表")
@RestController
@RequestMapping("bdDictType")
public class BdDictTypeController extends BaseController {
    @Autowired
    private BdDictTypeService bdDictTypeService;

    @ApiOperation("获取list")
    @PostMapping("/list")
    public R getList(@ApiParam("当前页") int current, @ApiParam("分页大小") int pageSize) {
        Page<BdDictType> page = new Page<>(current, pageSize);
        bdDictTypeService.selectPage(page);
        return R.ok().put("list", page.getRecords()).put("total", page.getTotal());
    }

    @ApiOperation("通过ID获取一条数据")
    @PostMapping("/info")
    public R update(@ApiParam("数据对象id") String id) {
        return R.ok().put("info", bdDictTypeService.selectById(id));
    }

}
