package com.drelephant.elephantadmin.business.basedata.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.drelephant.elephantadmin.business.basedata.entity.BdWeightConfig;
import com.drelephant.elephantadmin.business.basedata.mapper.BdWeightConfigMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdWeightConfigService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;
import org.springframework.stereotype.Service;

/**
 * @author zhou.fan
 * @date 2018/10/17 17:37
 * @description
 */
@Service
public class BdWeightConfigServiceImpl extends ServiceImpl<BdWeightConfigMapper, BdWeightConfig> implements BdWeightConfigService {
    @Override
    public R save(BdWeightConfig bdWeightConfig) {
        int weight = bdWeightConfig.getMedicineDictionary() + bdWeightConfig.getStock() +
                bdWeightConfig.getOrderAvg() + bdWeightConfig.getMedicineOrderTotalPrice() +
                bdWeightConfig.getWarehouseCover() + bdWeightConfig.getLogisticsArriveDuration() +
                bdWeightConfig.getOrderAvg() +
                bdWeightConfig.getUserEvaluate() + bdWeightConfig.getLogisticsSendDuration();
        if (weight < Constans.HUNDRED) {
            return R.error("权重值不能超过100");
        }
        return insert(bdWeightConfig) ? R.ok() : R.error("保存失败，请稍后重试。");
    }

    @Override
    public BdWeightConfig getBySupplier(Integer supplierId) {
        EntityWrapper<BdWeightConfig> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("supplierId", supplierId);
        return selectOne(entityWrapper);
    }
}
