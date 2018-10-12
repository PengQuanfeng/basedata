package com.drelephant.elephantadmin.business.basedata.service;

import com.drelephant.elephantadmin.business.basedata.entity.BdCompanyLogistics;
import com.drelephant.framework.base.common.R;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 物流公司信息表 服务类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public interface BdCompanyLogisticsService extends IService<BdCompanyLogistics> {
	//查询物流信息数据
	Page<BdCompanyLogistics> getListLogis(Page<BdCompanyLogistics> page,String name,String status);
	//修改物流公司状态
	R updateLogisStatus(BdCompanyLogistics data);
}
