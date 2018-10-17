package com.drelephant.elephantadmin.business.basedata.service;

import com.drelephant.elephantadmin.business.basedata.dto.reqeuest.BdSupplierRequest;
import com.drelephant.elephantadmin.business.basedata.entity.BdSupplier;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 供应商信息表 服务类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public interface BdSupplierService extends IService<BdSupplier> {

    boolean save(BdSupplierRequest data);
}
