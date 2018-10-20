package com.drelephant.elephantadmin.business.basedata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.drelephant.elephantadmin.business.basedata.entity.BdHospitalDept;
import com.drelephant.elephantadmin.business.basedata.mapper.BdHospitalDeptMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdHospitalDeptService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;

/**
 * <p>
 * 医疗科室表 服务实现类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Service
public class BdHospitalDeptServiceImpl extends ServiceImpl<BdHospitalDeptMapper, BdHospitalDept> implements BdHospitalDeptService {
	@Autowired
	BdHospitalDeptMapper bdHospitalDeptMapper;
	
	@Override
	public R insertHost(BdHospitalDept entity) {
		// 默认层级三级
		Integer level=entity.getLevel();
		if(level==null){
			level=3;
			entity.setLevel(level);
		}
		//
		String code = entity.getCode();
		if(StringUtils.isBlank(code)){
			return R.error().put("msg", "科室编码不能为空");
		}
		//
		String regulatoryCode=entity.getRegulatoryCode();
		if(StringUtils.isBlank(regulatoryCode)){
			return R.error().put("msg", "监管编码不能为空");
		}
		if(StringUtils.isNotBlank(entity.getStatus())){
			entity.setStatus(entity.getStatus());//状态
		}else{
			entity.setStatus(Constans.ACTIVE);//初始有效
		}
		if(level==1){
			int sLv1Code=selectCount(Condition.create().eq("lv1Code", code).where("status !={0}", 
					Constans.DELETED).eq("level", level));
			if(sLv1Code>0){
				return R.error().put("msg", "科室编码已存在");
			}
			String lv1Name=entity.getLv1Name();
			if(StringUtils.isBlank(lv1Name)){
				return R.error().put("msg", "科室名称为空");
			}
			if(lv1Name.length()>20){
				return R.error().put("msg", "科室名称大于20");
			}
			int sLv1Name=selectCount(Condition.create().eq("lv1Name", lv1Name).where("status !={0}", Constans.DELETED).eq("level", 1));
			if(sLv1Name>0){
				return R.error().put("msg", "科室名称已存在");
			}
			entity.setLv1Name(lv1Name);
			entity.setLv1Code(code);
			entity.setRegulatoryCode(regulatoryCode);
			entity.setLevel(level);
		}else if(level==2){
			int sLv2Code=selectCount(Condition.create().eq("lv2Code", code).where("status !={0}", 
					Constans.DELETED).eq("level", level));
			if(sLv2Code>0){
				return R.error().put("msg", "科室编码已存在");
			}
			String lv2Name=entity.getLv2Name();
			if(StringUtils.isBlank(lv2Name)){
				return R.error().put("msg", "科室名称为空");
			}
			if(lv2Name.length()>20){
				return R.error().put("msg", "科室名称大于20");
			}
			int sLv2Name=selectCount(Condition.create().eq("lv2Name", lv2Name).where("status !={0}", Constans.DELETED).eq("level", 2));
			if(sLv2Name>0){
				return R.error().put("msg", "科室名称已存在");
			}
			entity.setLv2Name(lv2Name);
			entity.setLv2Code(code);
			entity.setRegulatoryCode(regulatoryCode);
			entity.setLevel(level);
			
			List<BdHospitalDept> list = bdHospitalDeptMapper.selectList(Condition.create().eq("lv1Code", entity.getLv1Code()));
			if (list.size() > 0) {
				BdHospitalDept bdHospitalDept = list.get(0);
				entity.setLv1Name(bdHospitalDept.getLv1Name());
			}
		}else{
			int sLv3Code=selectCount(Condition.create().eq("lv3Code", code).where("status !={0}", 
					Constans.DELETED).eq("level", level));
			if(sLv3Code>0){
				return R.error().put("msg", "科室编码已存在");
			}
			String lv3Name=entity.getLv3Name();
			if(StringUtils.isBlank(lv3Name)){
				return R.error().put("msg", "科室名称为空");
			}
			if(lv3Name.length()>20){
				return R.error().put("msg", "科室名称大于20");
			}
			int sLv3Name=selectCount(Condition.create().eq("lv3Name", lv3Name).where("status !={0}", Constans.DELETED).eq("level", 3));
			if(sLv3Name>0){
				return R.error().put("msg", "科室名称已存在");
			}
			entity.setLv3Name(lv3Name);
			entity.setLv3Code(code);
			entity.setRegulatoryCode(regulatoryCode);
			entity.setLevel(level);
			
			List<BdHospitalDept> list = bdHospitalDeptMapper.selectList(Condition.create().eq("lv1Code", entity.getLv1Code()));
			if (list.size() > 0) {
				BdHospitalDept bdHospitalDept = list.get(0);
				entity.setLv1Name(bdHospitalDept.getLv1Name());
			}
			//
			list = bdHospitalDeptMapper.selectList(Condition.create().eq("lv2Code", entity.getLv2Code()));
			if (list.size() > 0) {
				BdHospitalDept bdHospitalDept = list.get(0);
				entity.setLv2Name(bdHospitalDept.getLv2Name());
			}
		}
		boolean flag=insert(entity);
		return flag?R.ok():R.error("新增失败");
	}
	public static String getRandom(){
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		return uuid;
	}
	public static void main(String[] args) {
//		System.out.println(getRandom());
	}
	@Override
	
	public R updateHost(BdHospitalDept data) {
		BdHospitalDept mBdHospitalDept=new BdHospitalDept();
		boolean flag=false;
		String status=data.getStatus();
		Integer level=data.getLevel();
		if(status != null && level != null){
			if(level==1){
				mBdHospitalDept.setLv1Name(data.getLv1Name());
				//
				BdHospitalDept mBdHospitalDept0=new BdHospitalDept();
				mBdHospitalDept0.setLv1Name(data.getLv1Name());
				update(mBdHospitalDept0, Condition.create().eq("lv1Code", data.getLv1Code()));
			}else if(level==2){
				List<BdHospitalDept> list = bdHospitalDeptMapper.selectList(Condition.create().eq("lv1Code", data.getLv1Code()));
				if (list.size() > 0) {
					BdHospitalDept bdHospitalDept = list.get(0);
					mBdHospitalDept.setLv1Name(bdHospitalDept.getLv1Name());
				}
				//
				mBdHospitalDept.setLv2Name(data.getLv2Name());
				//
				BdHospitalDept mBdHospitalDept0=new BdHospitalDept();
				mBdHospitalDept0.setLv2Name(data.getLv2Name());
				update(mBdHospitalDept0, Condition.create().eq("lv2Code", data.getLv2Name()));
			}else{
				List<BdHospitalDept> list = bdHospitalDeptMapper.selectList(Condition.create().eq("lv1Code", data.getLv1Code()));
				if (list.size() > 0) {
					BdHospitalDept bdHospitalDept = list.get(0);
					mBdHospitalDept.setLv1Name(bdHospitalDept.getLv1Name());
				}
				//
				list = bdHospitalDeptMapper.selectList(Condition.create().eq("lv2Code", data.getLv2Code()));
				if (list.size() > 0) {
					BdHospitalDept bdHospitalDept = list.get(0);
					mBdHospitalDept.setLv2Name(bdHospitalDept.getLv2Name());
				}
				//
				mBdHospitalDept.setLv3Name(data.getLv3Name());	
			}
			mBdHospitalDept.setStatus(status);
			flag=update(mBdHospitalDept, Condition.create().eq("id", data.getId()));
		}
		return flag?R.ok():R.error("状态更新失败");
	}
	@Override
	public R deleteOneHost(String lv1Code) {
		if(StringUtils.isBlank(lv1Code)){
			return R.error().put("msg", "参数为空");
		}
		BdHospitalDept mBdHospitalDept=new BdHospitalDept();
		mBdHospitalDept.setStatus(Constans.DELETED);
		boolean flag=update(mBdHospitalDept,Condition.create().eq("lv1Code", lv1Code));
		return flag?R.ok():R.error("删除失败");
	}
	@Override
	public R deleteBatchHostStatus(String lv1Code, String status) {
		List<String> list=new ArrayList<String>();
		String[] str=null;
		if(StringUtils.isNotBlank(lv1Code)){
			str=lv1Code.trim().split(",");
			for(int i=0;i<str.length;i++){				
				list.add(str[i]);
			}
		}
		boolean flag=false;
		if(list!=null && !list.isEmpty()){
			BdHospitalDept mBdHospitalDept=new BdHospitalDept();			
			for(int j=0;j<list.size();j++){
				mBdHospitalDept.setStatus(status);
				flag=update(mBdHospitalDept,Condition.create().eq("lv1Code", list.get(j)));
			}
		}
		return flag?R.ok():R.error("删除失败");
	}
	@Override
	public Page<BdHospitalDept> getListHost(Page<BdHospitalDept> page,String code,String lv1Code,String lv2Code,
			String lv3Code,String level,String status) {
		Condition con=Condition.create();
		if(StringUtils.isNotBlank(lv1Code)){
			con.eq("lv1Code", lv1Code);
		}
		if(StringUtils.isNotBlank(lv2Code)){
			con.eq("lv2Code", lv2Code);
		}
		if(StringUtils.isNotBlank(lv3Code)){
			con.eq("lv3Code", lv3Code);
		}
		if(StringUtils.isNotBlank(code)){
			con.or("(lv1Code like {0} or lv2Code like {1} or lv3Code like {2})", "%" + code + "%", "%" + code + "%", "%" + code + "%");
		}
		if(StringUtils.isNotBlank(level)){
			con.eq("level", level);
		}
		if(StringUtils.isNotBlank(status)){
			con.eq("status", status);
		}else{
			con.where("status !={0}", Constans.DELETED);
		}	
		selectPage(page, con);
		//
		List<BdHospitalDept> list = page.getRecords();
		for (BdHospitalDept item : list) {
			if (item.getLevel() == 1) {
				item.setCode(item.getLv1Code());
			} else if (item.getLevel() == 2) {
				item.setCode(item.getLv2Code());
			} else if (item.getLevel() == 3) {
				item.setCode(item.getLv3Code());
			}
		}
		//
		return page;
	}
	@Override
	public R getLv1List() {
		List<BdHospitalDept> list=bdHospitalDeptMapper.getLv1List();
		List<Map<String, Object>> lv1s = new ArrayList<Map<String, Object>>();
		Map<String, Object> lv1 = null;
		for (BdHospitalDept bdHospitalDept : list) {
			lv1= new HashMap<String, Object>();
			lv1.put("lv1Code", bdHospitalDept.getLv1Code());
			lv1.put("lv1Name", bdHospitalDept.getLv1Name());
			lv1s.add(lv1);
		}
		return R.ok().put("list", lv1s);
	}
	
	@Override
	public R batchUpdateHospitalDeptStatus(String status, String ids) {
		if(StringUtils.isBlank(ids)){
			return R.error().put("msg", "ID列表（逗号分隔）不能为空");
		}
    	//
		if(StringUtils.isBlank(status)){
			return R.error().put("msg", "状态不能为空");
		}
		//
		if (!Constans.ACTIVE.equals(status) && !Constans.INVALID.equals(status)) {
			return R.error().put("msg", "状态取值不正确。正确取值为: " + Constans.ACTIVE + " 或 " + Constans.INVALID);
		}
		//
		List<String> list=new ArrayList<String>();
		String[] str=null;
		if(StringUtils.isNotBlank(ids)){
			str=ids.trim().split(",");
			for(int i=0;i<str.length;i++){				
				list.add(str[i]);
			}
		}
		boolean flag=false;
		if(list!=null && !list.isEmpty()){
			BdHospitalDept mBdHospitalDept=new BdHospitalDept();			
			for(int j=0;j<list.size();j++){
				String id = list.get(j);
				//
				Condition con=Condition.create();
				con.eq("id", id);
				BdHospitalDept bdHospitalDeptOne = selectOne(con);
				if(bdHospitalDeptOne==null){
					return R.error().put("msg", "根据ID(" + id + ")找不到相关的记录");
				}
				//
				if (bdHospitalDeptOne.getLevel() == 1) {
					bdHospitalDeptOne.setStatus(status);
					bdHospitalDeptMapper.updateStatusForLevel1(bdHospitalDeptOne);
				} else if (bdHospitalDeptOne.getLevel() == 2) {
					bdHospitalDeptOne.setStatus(status);
					bdHospitalDeptMapper.updateStatusForLevel2(bdHospitalDeptOne);
				}
				//
				bdHospitalDeptOne.setStatus(status);
				flag=update(bdHospitalDeptOne, Condition.create().eq("id", id));
				
			}
		}
		return flag?R.ok().put("msg", "成功"):R.error().put("msg", "失败");
	}
	
	@Override
	public R getLv2List(String lv1Code) {
		List<BdHospitalDept> list=bdHospitalDeptMapper.getLv2List(lv1Code);
		List<Map<String, Object>> lv2s = new ArrayList<Map<String, Object>>();
		Map<String, Object> lv2 = null;
		for (BdHospitalDept bdHospitalDept : list) {
			lv2= new HashMap<String, Object>();
			lv2.put("lv2Code", bdHospitalDept.getLv2Code());
			lv2.put("lv2Name", bdHospitalDept.getLv2Name());
			lv2s.add(lv2);
		}
		return R.ok().put("list", lv2s);
	}
	@Override
	public R getLv3List(String lv2Code) {
		List<BdHospitalDept> list=bdHospitalDeptMapper.getLv3List(lv2Code);
		List<Map<String, Object>> lv3s = new ArrayList<Map<String, Object>>();
		Map<String, Object> lv3 = null;
		for (BdHospitalDept bdHospitalDept : list) {
			lv3= new HashMap<String, Object>();
			lv3.put("lv3Code", bdHospitalDept.getLv3Code());
			lv3.put("lv3Name", bdHospitalDept.getLv3Name());
			lv3s.add(lv3);
		}
		return R.ok().put("list", lv3s);
	}
	@Override
	public BdHospitalDept selectOneDept(String lv1Code) {
		
		return selectOne(Condition.create().eq("lv1Code", lv1Code));
	}
}
