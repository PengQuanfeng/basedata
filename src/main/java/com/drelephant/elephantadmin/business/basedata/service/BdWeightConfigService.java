package com.drelephant.elephantadmin.business.basedata.service;

import com.baomidou.mybatisplus.service.IService;
import com.drelephant.elephantadmin.business.basedata.entity.BdWeightConfig;
import com.drelephant.framework.base.common.R;

/**
 * @author zhou.fan
 * @date 2018/10/17 17:35
 * @description
 */
public interface BdWeightConfigService extends IService<BdWeightConfig> {
    R save(BdWeightConfig bdWeightConfig);

    BdWeightConfig getBySupplier(Integer supplierId);
}
