package com.drelephant.elephantadmin.business.basedata.service.impl;

import com.drelephant.elephantadmin.business.basedata.entity.BdCompanyLogistics;
import com.drelephant.elephantadmin.business.basedata.mapper.BdCompanyLogisticsMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdCompanyLogisticsService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 物流公司信息表 服务实现类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Service
public class BdCompanyLogisticsServiceImpl extends ServiceImpl<BdCompanyLogisticsMapper, BdCompanyLogistics> implements BdCompanyLogisticsService {

	@Override
	public Page<BdCompanyLogistics> getListLogis(Page<BdCompanyLogistics> page,
			String name,String status) {
		Condition con=Condition.create();
		if(StringUtils.isNotBlank(name)){
			//TODO 
			con.like("name", name);
//			con.eq("name", name);
		}
		if(StringUtils.isNotBlank(status)){
			con.eq("status", status);
		}else{
			con.where("status !={0}", Constans.DELETED);
		}
		selectPage(page,con);
		return page;
	}

	@Override
	public R updateLogisStatus(BdCompanyLogistics data) {
		BdCompanyLogistics mBdCompanyLogistics=new BdCompanyLogistics();		
		boolean flag=false;
		String status=data.getStatus();
		if(StringUtils.isBlank(status)){
			return R.error().put("msg", "状态未选择，更新失败");
		}
		mBdCompanyLogistics.setStatus(data.getStatus());
		flag=update(mBdCompanyLogistics,Condition.create().eq("code", data.getCode()));		
		return flag?R.ok():R.error("状态更新失败");
	}
	
}
