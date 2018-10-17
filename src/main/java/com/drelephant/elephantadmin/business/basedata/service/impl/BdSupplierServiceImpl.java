package com.drelephant.elephantadmin.business.basedata.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.drelephant.elephantadmin.business.basedata.dto.reqeuest.BdSupplierRequest;
import com.drelephant.elephantadmin.business.basedata.entity.BdSupplier;
import com.drelephant.elephantadmin.business.basedata.mapper.BdSupplierMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdSupplierService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 供应商信息表 服务实现类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Service
public class BdSupplierServiceImpl extends ServiceImpl<BdSupplierMapper, BdSupplier> implements BdSupplierService {

    @Override
    public boolean save(BdSupplierRequest data) {
        BdSupplier supplier = new BdSupplier();
        BeanUtils.copyProperties(data,supplier);
        //TODO 调用认证中心注册商户


        return insert(supplier);
    }
}
