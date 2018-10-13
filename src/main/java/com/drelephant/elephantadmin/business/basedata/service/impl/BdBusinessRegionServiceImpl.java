package com.drelephant.elephantadmin.business.basedata.service.impl;

import com.drelephant.elephantadmin.business.basedata.entity.BdBusinessRegion;
import com.drelephant.elephantadmin.business.basedata.entity.BdHospitalDept;
import com.drelephant.elephantadmin.business.basedata.mapper.BdBusinessRegionMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdBusinessRegionService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Service
public class BdBusinessRegionServiceImpl extends ServiceImpl<BdBusinessRegionMapper, BdBusinessRegion> implements BdBusinessRegionService {

	@Override
	public R inserRegion(BdBusinessRegion data) {
		BdBusinessRegion mBdBusinessRegion=new BdBusinessRegion();
		String lv1Code=data.getLv1Code();
		Integer level=data.getLevel();
		int count=selectCount(Condition.create().eq("lv1Code", lv1Code));
		int count1Name=selectCount(Condition.create().eq("level", level).eq("Lv1Name", data.getLv1Name()));
		boolean flag=false;
		//1级区域下1级区域name不能重复
		if(count==0&&level==1&&count1Name==0){			
			mBdBusinessRegion.setLevel(level);
			mBdBusinessRegion.setLv1Code(lv1Code);
			mBdBusinessRegion.setLv1Name(data.getLv1Name());
			mBdBusinessRegion.setLv2Code("-");
			mBdBusinessRegion.setLv2Name("-");
			mBdBusinessRegion.setStatus(Constans.ACTIVE);
			flag=insert(mBdBusinessRegion);
		}
		int count2Name=selectCount(Condition.create().eq("level", level).eq("Lv2Name", data.getLv2Name()));
		if(count==0&&level==2&&count2Name==0){
			mBdBusinessRegion.setLevel(level);
			mBdBusinessRegion.setLv1Code(lv1Code);
			mBdBusinessRegion.setLv1Name(data.getLv1Name());
			mBdBusinessRegion.setLv2Code(getRandom());
			mBdBusinessRegion.setLv2Name(data.getLv2Name());
			mBdBusinessRegion.setStatus(Constans.ACTIVE);
			flag=insert(mBdBusinessRegion);
		}		
		return flag?R.ok():R.error("插入失败");
	}

	@Override
	public R updateRegion(BdBusinessRegion data) {
		BdBusinessRegion mBdBusinessRegion=new BdBusinessRegion();
		boolean flag=false;
		String lv1Code=data.getLv1Code();
		Integer level=data.getLevel();		
		if(level==1){
			mBdBusinessRegion.setLv1Name(data.getLv1Name());
			flag=update(mBdBusinessRegion,Condition.create().eq("lv1Code", data.getLv1Code()));
		}else{
			mBdBusinessRegion.setLv1Name(data.getLv1Name());
			mBdBusinessRegion.setLv2Name(data.getLv2Name());
			flag=update(mBdBusinessRegion,Condition.create().eq("lv1Code", data.getLv1Code()));
		}
		return flag?R.ok():R.error("状态更新失败");
	}

	@Override
	public R deleteOneRegion(BdBusinessRegion data) {
		BdBusinessRegion mBdBusinessRegion=new BdBusinessRegion();
		mBdBusinessRegion.setStatus(Constans.DELETED);
		boolean flag=update(mBdBusinessRegion,Condition.create().eq("lv1Code", data.getLv1Code()));
		return flag?R.ok():R.error("删除失败");
	}

	@Override
	public Page<BdBusinessRegion> getListRegion(Page<BdBusinessRegion> page) {
		Condition con=Condition.create();
		con.where("status !={0}", Constans.DELETED);
		selectPage(page, con);
		return page;
	}
	public static String getRandom(){
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		return uuid;
	}

	@Override
	public BdBusinessRegion selectOneRegion(String lv1Code) {		
		return selectOne(Condition.create().eq("lv1Code", lv1Code));
	}
}
