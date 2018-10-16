package com.drelephant.elephantadmin.business.basedata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.drelephant.elephantadmin.business.basedata.entity.BdAreaRegion;
import com.drelephant.elephantadmin.business.basedata.mapper.BdAreaRegionMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdAreaRegionService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;

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
	@Autowired
	BdAreaRegionMapper mBdAreaRegionMapper;
	@Override
	@Transactional
	public R insertBdAreaRegion(BdAreaRegion data) {
		// TODO Auto-generated method stub
		BdAreaRegion mBdAreaRegion=new BdAreaRegion();
		Condition con=Condition.create();
		con.eq("code", data.getCode());
		con.where("status !={0}", Constans.DELETED);
		int count=selectCount(con);
		boolean flag=false;
		String provinceCode=data.getProvinceCode();
		String cityCode=data.getCityCode();
		List<String> list=mBdAreaRegionMapper.getProvinceName(provinceCode);
		List<String> listCity=mBdAreaRegionMapper.getCityName(cityCode);
		int level=data.getLevel();
		mBdAreaRegion.setCode(data.getCode());
		mBdAreaRegion.setLevel(level);		
		if(level==1){
			mBdAreaRegion.setProvinceName(list.get(0));
			mBdAreaRegion.setProvinceCode(provinceCode);
		}else if(level==2){
			mBdAreaRegion.setProvinceCode(provinceCode);
			mBdAreaRegion.setProvinceName(list.get(0));
			mBdAreaRegion.setCityCode(cityCode);
			mBdAreaRegion.setCityName(listCity.get(0));
		}else {
			mBdAreaRegion.setProvinceCode(provinceCode);
			mBdAreaRegion.setProvinceName(list.get(0));
			mBdAreaRegion.setCityCode(cityCode);
			mBdAreaRegion.setCityName(listCity.get(0));
			mBdAreaRegion.setCountyCode(getRandom());
			mBdAreaRegion.setCountyName(data.getCountyName());
		}
		String status=data.getStatus();
		if(StringUtils.isNotBlank(status)){
			mBdAreaRegion.setStatus(status);//状态
		}else{
			mBdAreaRegion.setStatus(Constans.ACTIVE);//初始有效
		}
		if(count==0){											
			flag=insert(mBdAreaRegion);
			return R.ok().put("msg", "保存行政地区成功！");
		}	
		return flag?R.ok():R.error("保存失败");
	}

	@Override
	@Transactional
	public R updateStatus(BdAreaRegion entity) {				
		if (StringUtils.isBlank(entity.getCode())) {
			return R.error("编码为空");
		}
		if (entity.getLevel()==null) {
			entity.setLevel(1);			
		}
		Condition con=Condition.create();
		con.eq("code", entity.getCode());
		boolean flag=update(entity,con);
		//mBdAreaRegionMapper.updateBdAreaRegion(entity);
		return flag?R.ok():R.error();
		
	}

	@Override
	@Transactional
	public R deleteBdAreaRegion( String id) {
		if(StringUtils.isBlank(id)){
			return R.error("参数为空");
		}
		BdAreaRegion mBdAreaRegion=new BdAreaRegion();
		mBdAreaRegion.setStatus(Constans.DELETED);
		Condition con=Condition.create();
		con.eq("id", id);
		boolean flag=update(mBdAreaRegion,con);	
		return flag?R.ok().put("msg", "删除成功"):R.error().put("msg", "删除失败");
	}

	@Override
	public Page<BdAreaRegion> getListBdAreaRegion(Page<BdAreaRegion> page,String code,String provinceCode,
    		String cityCode,String countyCode,Integer level,String status) {
				
		Condition con=Condition.create();
		if(StringUtils.isNotBlank(code)){
			con.eq("code", code);
		}
		if(StringUtils.isNotBlank(provinceCode)){
			con.eq("provinceCode", provinceCode);
		}
		if(StringUtils.isNotBlank(cityCode)){
			con.eq("cityCode", cityCode);
		}
		if(StringUtils.isNotBlank(countyCode)){
			con.eq("countyCode", countyCode);
		}
		if(level !=null){
			con.eq("level", level);
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
		return flag?R.ok().put("msg", "成功"):R.error().put("msg", "失败");
	}

	@Override
	public R getListLevel() {
		List<Integer> list=mBdAreaRegionMapper.listLevel();
		return R.ok().put("list", list);
	}

	@Override
	public R getAreaRegionTree() {
		List<BdAreaRegion> list = mBdAreaRegionMapper.getAll();
		//
		List<String> provinceCodes = new ArrayList<String>();
		List<BdAreaRegion> provinceList = new ArrayList<BdAreaRegion>();
		String provinceCode = null;
		for (BdAreaRegion bdAreaRegion : list) {
			provinceCode = bdAreaRegion.getProvinceCode();
			if (!provinceCodes.contains(provinceCode)) {
				provinceCodes.add(provinceCode);
				//
				provinceList.add(bdAreaRegion);
			}
		}
		//
		List<Map<String, Object>> provinces = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> cities = null;
		Map<String, Object> province = null;
		for (BdAreaRegion bdAreaRegion : provinceList) {
			provinceCode = bdAreaRegion.getProvinceCode();
			cities = collectCities(provinceCode, list);
			//
			province = new HashMap<String, Object>();
			province.put("provinceCode", provinceCode);
			province.put("provinceName", bdAreaRegion.getProvinceName());
			province.put("cities", cities);
			//
			provinces.add(province);
		}
		
		return R.ok().put("list", provinces);
	}
	
	private List<Map<String, Object>> collectCities(String provinceCode, List<BdAreaRegion> list) {
		List<Map<String, Object>> cities = new ArrayList<Map<String, Object>>();
		List<String> cityCodes = new ArrayList<String>();
		//
		Map<String, Object> city = null;
		String cityCode = null;
		List<Map<String, Object>> counties = null;
		for (BdAreaRegion bdAreaRegion : list) {
			cityCode = bdAreaRegion.getCityCode();
			if (!cityCodes.contains(cityCode)) {
				city = new HashMap<String, Object>();
				//
				city.put("cityCode", cityCode);
				city.put("cityName", bdAreaRegion.getCityName());
				//
				counties = collectCounties(cityCode, list);
				city.put("counties", counties);
				//
				cities.add(city);
			}
		}
		//
		return cities;
	}
	
	private List<Map<String, Object>> collectCounties(String cityCode, List<BdAreaRegion> list) {
		List<Map<String, Object>> counties = new ArrayList<Map<String, Object>>();
		List<String> countyCodes = new ArrayList<String>();
		//
		Map<String, Object> county = null;
		String countyCode = null;
		for (BdAreaRegion bdAreaRegion : list) {
			countyCode = bdAreaRegion.getCountyCode();
			if (!countyCodes.contains(countyCode)) {
				county = new HashMap<String, Object>();
				//
				county.put("countyCode", countyCode);
				county.put("countyName", bdAreaRegion.getCountyName());
				//
				counties.add(county);
			}
		}
		//
		return counties;
	}
	
	/**
	 * 获取省份列表
	 * @return
	 */
	@Override
	public R getProvinceList() {
		List<BdAreaRegion> list = mBdAreaRegionMapper.getProvinceList();
		//
		List<Map<String, Object>> provinces = new ArrayList<Map<String, Object>>();
		Map<String, Object> province = null;
		for (BdAreaRegion bdAreaRegion : list) {
			province = new HashMap<String, Object>();
			province.put("provinceCode", bdAreaRegion.getProvinceCode());
			province.put("provinceName", bdAreaRegion.getProvinceName());
			//
			provinces.add(province);
		}
		//
		return R.ok().put("list", provinces);
	}
	
	/**
	 * 获取城市列表
	 * @return
	 */
	@Override
	public R getCityList(String provinceCode) {
		List<BdAreaRegion> list = mBdAreaRegionMapper.getCityList(provinceCode);
		//
		List<Map<String, Object>> cities = new ArrayList<Map<String, Object>>();
		Map<String, Object> city = null;
		for (BdAreaRegion bdAreaRegion : list) {
			city = new HashMap<String, Object>();
			//
			city.put("cityCode", bdAreaRegion.getCityCode());
			city.put("cityName", bdAreaRegion.getCityName());
			//
			cities.add(city);
		}
		//
		return R.ok().put("list", cities);
	}
	
	/**
	 * 获取区县列表
	 * @return
	 */
	@Override
	public R getCountyList(String cityCode) {
		List<BdAreaRegion> list = mBdAreaRegionMapper.getCountyList(cityCode);
		//
		List<Map<String, Object>> counties = new ArrayList<Map<String, Object>>();
		Map<String, Object> county = null;
		for (BdAreaRegion bdAreaRegion : list) {
			county = new HashMap<String, Object>();
			//
			county.put("countyCode", bdAreaRegion.getCountyCode());
			county.put("countyName", bdAreaRegion.getCountyName());
			//
			counties.add(county);
		}
		//
		return R.ok().put("list", counties);
	}
	/**
	 * 区县编码
	 * @return
	 */
	public static String getRandom(){
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		return uuid;
	}
}
