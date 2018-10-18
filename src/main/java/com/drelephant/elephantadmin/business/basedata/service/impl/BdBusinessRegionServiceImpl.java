package com.drelephant.elephantadmin.business.basedata.service.impl;

import com.drelephant.elephantadmin.business.basedata.entity.BdAreaRegion;
import com.drelephant.elephantadmin.business.basedata.entity.BdBusinessRegion;
import com.drelephant.elephantadmin.business.basedata.entity.BdHospitalDept;
import com.drelephant.elephantadmin.business.basedata.mapper.BdBusinessRegionMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdBusinessRegionService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	BdBusinessRegionMapper mBdBusinessRegionMapper;
	@Override
	public R inserRegion(BdBusinessRegion data) {	
		String lv1Code=data.getLv1Code();
		Integer level=data.getLevel();
		if(level==null){
			level=2;
		}
		if(StringUtils.isBlank(lv1Code)){
			return R.error().put("msg", "区域编码为空");
		}							
		//1级区域
		if(level==1){	
			int count=selectCount(Condition.create().eq("lv1Code", lv1Code).eq("level", level)
					.where("status !={0}",Constans.DELETED));
			if(count>0){
				return R.error().put("msg", "区域编码已经存在");
			}
			String lv1Name=data.getLv1Name();
			if(StringUtils.isBlank(lv1Name)){
				return R.error().put("msg", "区域名称为空");
			}		
			if(lv1Name.length()>20){
				return R.error().put("msg", "区域名称长度大于20");
			}
			int count1Name=selectCount(Condition.create().eq("level", level).eq("Lv1Name", lv1Name)
					.where("status !={0}", Constans.DELETED));
			if(count1Name>0){
				return R.error().put("msg", "同层级下名称重复");
			}
			data.setLv1Name(lv1Name);
			data.setLv1Code(lv1Code);
			data.setStatus(Constans.ACTIVE);
			data.setLv2Name("-");
		}else{
			String lv2Name=data.getLv2Name();
			if(StringUtils.isBlank(lv2Name)){
				return R.error().put("msg", "参数为空");
			}
			if(lv2Name.length()>20){
				return R.error().put("msg", "区域名称长度大于20");
			}
			int count2Name=selectCount(Condition.create().eq("level", level).eq("Lv2Name", lv2Name)
					.where("status !={0}", Constans.DELETED));
			if(count2Name>0){
				return R.error().put("msg", "同层级下名称重复");
			}
			BdBusinessRegion bd=selectOne(Condition.create().eq("lv1Code", lv1Code)
					.where("status !={0}", Constans.DELETED));
			if(bd==null){
				return R.error().put("msg", "参数错误");
			}
			data.setLv1Name(bd.getLv1Name());
			data.setLv1Code(lv1Code);
			data.setLv2Code(getRandom());
			data.setStatus(Constans.ACTIVE);
			data.setLv2Name(lv2Name);
			
		}
		boolean flag=insert(data);
		return flag?R.ok("新增成功"):R.error("插入失败");
	}

	@Override
	public R updateRegion(BdBusinessRegion data) {
		BdBusinessRegion mBdBusinessRegion=new BdBusinessRegion();
		boolean flag=false;
		String lv1Code=data.getLv1Code();
		Integer level=data.getLevel();
		if(StringUtils.isBlank(lv1Code)||level==null){
			return R.error("参数为空");
		}		
		BdBusinessRegion bd=selectOne(Condition.create().eq("lv1Code", lv1Code)
				.where("status !={0}", Constans.DELETED));
		if(level==1){
			String lv1Name=data.getLv1Name();
			if(StringUtils.isBlank(lv1Name)){
				return R.error().put("msg", "区域名称为空");
			}		
			if(lv1Name.length()>20){
				return R.error().put("msg", "区域名称长度大于20");
			} 
			int count1Name=selectCount(Condition.create().eq("level", level).eq("Lv1Name", lv1Name)
					.where("status !={0}", Constans.DELETED));
			if(count1Name>1){
				return R.error().put("msg", "同层级下名称重复");
			}
			mBdBusinessRegion.setLv1Name(lv1Name);
			flag=update(mBdBusinessRegion,Condition.create().eq("lv1Code", lv1Code).eq("level", level)
					.where("status !={0}", Constans.DELETED));
		}else{
			String lv2Name=data.getLv2Name();
			if(StringUtils.isBlank(lv2Name)){
				return R.error().put("msg", "参数为空");
			}
			if(lv2Name.length()>20){
				return R.error().put("msg", "区域名称长度大于20");
			}
			int count2Name=selectCount(Condition.create().eq("level", level).eq("Lv2Name", lv2Name)
					.where("status !={0}", Constans.DELETED));
			if(count2Name>0){
				return R.error().put("msg", "同层级下名称重复");
			}
			mBdBusinessRegion.setLv1Name(bd.getLv1Name());
			mBdBusinessRegion.setLv2Name(data.getLv2Name());
			flag=update(mBdBusinessRegion,Condition.create().eq("lv2Code", data.getLv2Code()));
		}
		
		return flag?R.ok():R.error("状态更新失败");
	}
/**
 * 可以根据区域编码
 */
	@Override
	public R deleteOneRegion(BdBusinessRegion data) {
		if(StringUtils.isBlank(data.getId())){
			return R.error().put("msg", "参数为空");
		}
		BdBusinessRegion mBdBusinessRegion=new BdBusinessRegion();
		mBdBusinessRegion.setStatus(Constans.DELETED);		 
		boolean flag=update(mBdBusinessRegion,Condition.create().eq("id", data.getId()));			 
		return flag?R.ok("删除成功"):R.error("删除失败");
	}
//TODO 搜索查询
	@Override
	public Page<BdBusinessRegion> getListRegion(Page<BdBusinessRegion> page,String code,String lv1Code,String lv2Code,String level) {
		Condition con=Condition.create();
		if(StringUtils.isNotBlank(lv1Code)){
			con.eq("lv1Code", lv1Code);
		}
		if(StringUtils.isNotBlank(lv2Code)){
			con.like("lv2Code", lv2Code);
		}
		if(StringUtils.isNotBlank(level)){
			con.eq("level", level);
		}
		//TODO 
		if(StringUtils.isNotBlank(code)){
			
		}
		con.where("status !={0}", Constans.DELETED);
		selectPage(page, con);
		return page;
	}
	public static String getRandom(){
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		return uuid;
	}
	/**
	 * 传入对应的区域编码 lv1Code 1级和2级
	 */
	@Override
	public BdBusinessRegion selectOneRegion(String codes,Integer level) {	
		Condition con=Condition.create();
		if(level==1){
			con.eq("lv1Code", codes);
			con.eq("level", 1);
		}else{
			con.eq("lv2Code", codes);
		}
		return selectOne(con);
	}

	@Override
	public R bdLv1() {
		List<BdBusinessRegion> list = mBdBusinessRegionMapper.getbdLv1List();
		List<Map<String, Object>> lv1s = new ArrayList<Map<String, Object>>();
		Map<String, Object> lv1 = null;
		for (BdBusinessRegion bdRegion : list) {
			lv1 = new HashMap<String, Object>();
			lv1.put("lv1Code", bdRegion.getLv1Code());
			lv1.put("lv1Name", bdRegion.getLv1Name());
			lv1s.add(lv1);
		}
		return R.ok().put("list", lv1s);
	}
	@Override
	public R bdLv2(String lv1Code) {
		List<BdBusinessRegion> list = mBdBusinessRegionMapper.getbdLv2List(lv1Code);
		List<Map<String, Object>> lv2s = new ArrayList<Map<String, Object>>();
		Map<String, Object> lv2 = null;
		for (BdBusinessRegion bdRegion : list) {
			lv2 = new HashMap<String, Object>();
			lv2.put("lv2Code", bdRegion.getLv2Code());
			lv2.put("lv2Name", bdRegion.getLv2Name());
			lv2s.add(lv2);
		}
		return R.ok().put("list", lv2s);
	}
}
