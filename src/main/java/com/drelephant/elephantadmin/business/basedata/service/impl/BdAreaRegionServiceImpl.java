package com.drelephant.elephantadmin.business.basedata.service.impl;

import com.drelephant.elephantadmin.business.basedata.entity.BdAreaRegion;
import com.drelephant.elephantadmin.business.basedata.mapper.BdAreaRegionMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdAreaRegionService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 地区区域信息管理 服务实现类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Service
public class BdAreaRegionServiceImpl extends ServiceImpl<BdAreaRegionMapper, BdAreaRegion> implements BdAreaRegionService {

	@Override
	public R insertBdAreaRegion(BdAreaRegion data) {
		// TODO Auto-generated method stub
		BdAreaRegion mBdAreaRegion=new BdAreaRegion();
		int count=selectCount(Condition.create().eq("code", data.getCode()));
		boolean flag=false;
		if(count==0){
			mBdAreaRegion.setCode(data.getCode());
			mBdAreaRegion.setProvinceCode(data.getProvinceCode());
			mBdAreaRegion.setProvinceName(data.getProvinceName());
			mBdAreaRegion.setCityCode(data.getCityCode());
			mBdAreaRegion.setCityName(data.getCityName());
			mBdAreaRegion.setCountyCode(data.getCountyCode());
			mBdAreaRegion.setCountyName(data.getCountyName());
			mBdAreaRegion.setLevel(data.getLevel());//层级
			//mBdAreaRegion.setLayerInfo(data.getLayerInfo());//层级信息
			String status=data.getStatus();
			if(StringUtils.isNotBlank(status)){
				mBdAreaRegion.setStatus(status);//状态
			}else{
				mBdAreaRegion.setStatus(Constans.ACTIVE);//初始有效
			}						
			flag=insert(mBdAreaRegion);
		}	
		return flag?R.ok():R.error("保存失败") ;
	}

	@Override
	public R updateStatus(BdAreaRegion data) {
		// TODO Auto-generated method stub
		BdAreaRegion mBdAreaRegion=new BdAreaRegion();
		BdAreaRegion mBdAreaRegion1=selectOne(Condition.create().eq("code", data.getCode()));
		Integer lever=mBdAreaRegion1.getLevel();
		if(lever==1){
			mBdAreaRegion.setProvinceName(data.getProvinceName());
			mBdAreaRegion.setProvinceCode(data.getProvinceCode());
			mBdAreaRegion.setStatus(data.getStatus());
		}else if(lever==2){
			mBdAreaRegion.setProvinceName(data.getProvinceName());
			mBdAreaRegion.setProvinceCode(data.getProvinceCode());
			mBdAreaRegion.setCityCode(data.getCityCode());
			mBdAreaRegion.setCityName(data.getCityName());
			mBdAreaRegion.setStatus(data.getStatus());
		}else{
			mBdAreaRegion.setProvinceName(data.getProvinceName());
			mBdAreaRegion.setProvinceCode(data.getProvinceCode());
			mBdAreaRegion.setCityCode(data.getCityCode());
			mBdAreaRegion.setCityName(data.getCityName());
			mBdAreaRegion.setCountyCode(data.getCountyCode());
			mBdAreaRegion.setCountyName(data.getCountyName());
			mBdAreaRegion.setStatus(data.getStatus());
		}
		boolean flag=update(mBdAreaRegion,Condition.create().eq("code", data.getCode()));
		return flag?R.ok():R.error("更新失败");
	}

	@Override
	public R deleteBdAreaRegion(BdAreaRegion data) {
		BdAreaRegion mBdAreaRegion=new BdAreaRegion();
		mBdAreaRegion.setStatus(Constans.DELETED);
		boolean flag=update(mBdAreaRegion,Condition.create().eq("code", data.getCode()));
		return flag?R.ok():R.error("删除失败");
	}

	@Override
	public Page<BdAreaRegion> getListBdAreaRegion(Page<BdAreaRegion> page, String code, String provinceName, String CityName,
			String CountyName, Integer lever, String status) {
				
		Condition con=Condition.create();
		if(StringUtils.isNotBlank(code)){
			con.eq("code", code);
		}
		if(StringUtils.isNotBlank(provinceName)){
			con.eq("provinceName", provinceName);
		}
		if(StringUtils.isNotBlank(CityName)){
			con.eq("CityName", CityName);
		}
		if(StringUtils.isNotBlank(CountyName)){
			con.eq("CountyName", CountyName);
		}
		if(lever !=null){
			con.eq("lever", lever);
		}
		if(StringUtils.isNotBlank(status)){			
			con.eq("status", status);
		}else{
			con.where("status !={0}", Constans.DELETED);//.last(" limit 1")
		}				
		selectPage(page,con);			
		return page;
	}

	@Override
	public R updateBatchBdAreaRegion(String status,String codes) {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		String[] str=null;
		if(StringUtils.isNotBlank(codes)){
			str=codes.trim().split(",");
			for(int i=0;i<str.length;i++){				
				list.add(str[i]);
			}
		}
		boolean flag=false;
		if(list!=null && !list.isEmpty()){
			BdAreaRegion mBdAreaRegion=new BdAreaRegion();			
			for(int j=0;j<list.size();j++){
				mBdAreaRegion.setStatus(status);
				flag=update(mBdAreaRegion,Condition.create().eq("code", list.get(j)));
			}
		}
		return flag?R.ok():R.error();
	}


	
}
