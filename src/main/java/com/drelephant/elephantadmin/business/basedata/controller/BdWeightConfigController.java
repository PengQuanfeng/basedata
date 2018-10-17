package com.drelephant.elephantadmin.business.basedata.controller;

import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController;
import com.drelephant.elephantadmin.business.basedata.entity.BdWeightConfig;
import com.drelephant.elephantadmin.business.basedata.service.BdWeightConfigService;
import com.drelephant.framework.base.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhou.fan
 * @date 2018/10/17 17:34
 * @description
 */
@Api("供应商权重配置")
@RestController
@RequestMapping("weightConfig")
public class BdWeightConfigController extends BaseController {
    @Autowired
    private BdWeightConfigService bdWeightConfigService;

    /**
     * 增加权重
     *
     * @param bdWeightConfig
     * @return
     */
    @PostMapping("/save")
    public R save(@RequestBody @ApiParam("数据对象") BdWeightConfig bdWeightConfig) {
        if (null == bdWeightConfig.getSupplierId()) {
            return R.error("供应商id不能为空");
        }
        return bdWeightConfigService.save(bdWeightConfig);
    }

    /**
     * 根据供应商id查询权重
     *
     * @param supplierId 供应商id
     * @return
     */
    @GetMapping("/getBySupplier")
    public R getBySupplier(@ApiParam("供应商id") @RequestParam("supplierId") Integer supplierId) {
        BdWeightConfig bdWeightConfig = bdWeightConfigService.getBySupplier(supplierId);
        return R.ok().put("data", bdWeightConfig);
    }
}
