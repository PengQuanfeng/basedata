package com.drelephant.elephantadmin.business.basedata.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.drelephant.elephantadmin.business.basedata.controller.base.BaseController;
import com.drelephant.elephantadmin.business.basedata.dto.reqeuest.BdSupplierRequest;
import com.drelephant.elephantadmin.business.basedata.entity.BdSupplier;
import com.drelephant.elephantadmin.business.basedata.service.BdSupplierService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;

/**
 * <p>
 * 供应商信息表 前端控制器
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Api(tags = "供应商信息表")
@RestController
@RequestMapping("bdSupplier")
public class BdSupplierController extends BaseController {
    @Autowired
    private BdSupplierService bdSupplierService;

    @ApiOperation("获取list")
    @PostMapping("/list")
    public R getList(@ApiParam("当前页") int current, @ApiParam("分页大小") int pageSize) {
        Page<BdSupplier> page = new Page<>(current, pageSize);
        bdSupplierService.selectPage(page);
        return R.ok().put("list", page.getRecords()).put("total", page.getTotal());
    }


    @ApiOperation("新增")
    @PostMapping("/add")
    public R save(@ApiParam("数据对象") BdSupplierRequest data) {
        // 暂定供应商类型为 1 商户  ， 2 线上
        if (StringUtils.equals(data.getTypeCode(), Constans.SUPPLIER_TYPE_CODE_MERCHANT)) {
            if (StringUtils.isBlank(data.getProviderUserCode()) ||
                    StringUtils.isBlank(data.getPassword()) ||
                    StringUtils.isBlank(data.getConfirmPassword())) {
                return R.error("请填写账号密码");
            }
            if (StringUtils.equals(data.getPassword(), data.getConfirmPassword())) {
                return R.error("密码不一致");
            }
        }
        String checkMsg = checkParam(data);
        if (StringUtils.isNotBlank(checkMsg)) {
            return R.error(checkMsg);
        }
        return bdSupplierService.save(data) ? R.ok() : R.error("保存错误");
    }

    private String checkParam(BdSupplier data) {
        String msg = null;

        if (StringUtils.isBlank(data.getName())) {
            msg = "供应商名称不为能空";
        }
        Integer weightSetupValue = data.getWeightSetupValue();
        if (StringUtils.equals(data.getWeightType(), Constans.SUPPLIER_WEIGHT_TYPE_MANUAL)) {
            if (weightSetupValue < BigDecimal.ZERO.intValue() || weightSetupValue > Constans.HUNDRED) {
                msg = ("手动权重比较在0-100之间");
            }
        }

        if (null == data.getFreightPrice() || data.getFreightPrice().compareTo(BigDecimal.ZERO) > -1) {
            msg = ("运费价格必须大于0");
        }

        if (null == data.getFreeShippingPrice() || data.getFreeShippingPrice().compareTo(BigDecimal.ZERO) > -1) {
            msg = ("包邮价格必须大于0");
        }

        if (StringUtils.isBlank(data.getStorageRangeStr())) {
            msg = ("至少提供一个仓库");
        }
        return msg;
    }


    @ApiOperation("删除")
    @PostMapping("/delete")
    public R delete(@ApiParam("数据对象id") String id) {
        return bdSupplierService.deleteById(id) ? R.ok() : R.error("删除错误");
    }

    @ApiOperation("更新")
    @PostMapping("/update")
    public R update(@ApiParam("数据对象") BdSupplier data) {
        String checkMsg = checkParam(data);
        if (StringUtils.isNotBlank(checkMsg)) {
            return R.error(checkMsg);
        }
        return bdSupplierService.updateById(data) ? R.ok() : R.error("更新错误");
    }

    @ApiOperation("通过ID获取一条数据")
    @PostMapping("/info")
    public R update(@ApiParam("数据对象id") String id) {
        return R.ok().put("info", bdSupplierService.selectById(id));
    }

}
