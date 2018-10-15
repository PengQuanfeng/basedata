package com.drelephant.elephantadmin.business.basedata.service.impl;

import com.drelephant.elephantadmin.business.basedata.entity.BdBanner;
import com.drelephant.elephantadmin.business.basedata.mapper.BdBannerMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdBannerService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * banner 服务实现类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Service
public class BdBannerServiceImpl extends ServiceImpl<BdBannerMapper, BdBanner> implements BdBannerService {
	@Autowired
	BdBannerMapper bdBannerMapper;
	@Override
	public void insertBdBander(BdBanner entity) {
		int count=bdBannerMapper.maxOrderNumber();	
		entity.setOrderNumber(count+1);//排序字段		
		entity.setStatus(Constans.ACTIVE);
		bdBannerMapper.saveBanner(entity);
	}

	@Override
	public R updateBdBander(BdBanner bdBanner) {
		BdBanner bd=new BdBanner();
		bd.setPicAddress(bdBanner.getPicAddress());
		bd.setIsOpenLink(bdBanner.getIsOpenLink());
		bd.setRemark(bdBanner.getRemark());
		bd.setLinkAddress(bdBanner.getLinkAddress());		
		boolean flag=update(bd,Condition.create().eq("id", bdBanner.getId()));
		return flag?R.ok():R.error("更新失败");
	}

	@Override
	public BdBanner selectOneBd(String id) {
		return selectOne(Condition.create().eq("id", id));
	}

	@Override
	public List<BdBanner> getListBd() {		
		Condition con=Condition.create();
//		con.where(" status !={0}", Constans.DELETED);
		con.notIn("status", Constans.DELETED);
		con.orderBy("orderNumber", false);
		return selectList(con);
	}

	@Override
	public R updateStatus(String id) {
		BdBanner bd=new BdBanner();
		bd.setStatus(Constans.DELETED);
		boolean flag=update(bd,Condition.create().eq("id", id));
		return flag?R.ok():R.error("删除失败");
	}
	
	@Override
	public R moveUp(String id) {
		List<BdBanner> list = bdBannerMapper.getAll();
		int orderNumber = 0;
		BdBanner banner = null;
		for (int i = 0; i < list.size(); i++) {
			banner = list.get(i);
			if (banner.getId().equals(id)) {
				orderNumber = banner.getOrderNumber();
				if (i == 0) { // 当前记录是排在最前面的记录
					break;
				} else {
					bdBannerMapper.updateOrderNumber(id, orderNumber - 1); // 前移1个号码
					
					// 前一个BdBanner后移1个号码
					BdBanner prBanner = list.get(i - 1);
					bdBannerMapper.updateOrderNumber(prBanner.getId(), prBanner.getOrderNumber() + 1);
				}
			}
		}
		//
		return R.ok();
	}
	
	@Override
	public R moveDown(String id) {
		List<BdBanner> list = bdBannerMapper.getAll();
		int orderNumber = 0;
		BdBanner banner = null;
		for (int i = 0; i < list.size(); i++) {
			banner = list.get(i);
			if (banner.getId().equals(id)) {
				orderNumber = banner.getOrderNumber();
				if (i == (list.size() - 1)) { // 当前记录是排在最后面的记录
					break;
				} else {
					bdBannerMapper.updateOrderNumber(id, orderNumber + 1); // 后移1个号码
					
					// 下一个BdBanner前移1个号码
					BdBanner nextBanner = list.get(i + 1);
					bdBannerMapper.updateOrderNumber(nextBanner.getId(), nextBanner.getOrderNumber() - 1);
				}
			}
		}
		//
		return R.ok();
	}	
}
