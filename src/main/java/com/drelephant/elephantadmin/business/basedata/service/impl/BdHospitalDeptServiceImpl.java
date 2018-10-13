package com.drelephant.elephantadmin.business.basedata.service.impl;

import com.drelephant.elephantadmin.business.basedata.entity.BdHospitalDept;
import com.drelephant.elephantadmin.business.basedata.entity.BdOrg;
import com.drelephant.elephantadmin.business.basedata.mapper.BdHospitalDeptMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdHospitalDeptService;
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
	public R insertHost(BdHospitalDept data) {
		BdHospitalDept mBdHospitalDept=new BdHospitalDept();
		String lv1Code=data.getLv1Code();
		Integer level=data.getLevel();
		int count=selectCount(Condition.create().eq("lv1Code", lv1Code));
		boolean flag=false;
		String status=data.getStatus();
		if(count==0&&level!=null){
			if(level==1){
				mBdHospitalDept.setLevel(level);
				mBdHospitalDept.setLv1Code(lv1Code);
				mBdHospitalDept.setLv1Name(data.getLv1Name());
				mBdHospitalDept.setLv2Code("-");
				mBdHospitalDept.setLv2Name("-");
				mBdHospitalDept.setLv3Code("-");
				mBdHospitalDept.setLv3Name("-");				
			}else if(level==2){
				mBdHospitalDept.setLevel(level);
				mBdHospitalDept.setLv1Code(lv1Code);
				mBdHospitalDept.setLv1Name(data.getLv1Name());
				mBdHospitalDept.setLv2Code(getRandom());
				mBdHospitalDept.setLv2Name(data.getLv2Name());
				mBdHospitalDept.setLv3Code("-");
				mBdHospitalDept.setLv3Name("-");	
			}else{
				mBdHospitalDept.setLevel(level);
				mBdHospitalDept.setLv1Code(lv1Code);
				mBdHospitalDept.setLv1Name(data.getLv1Name());
				mBdHospitalDept.setLv2Code(getRandom());
				mBdHospitalDept.setLv2Name(data.getLv2Name());
				mBdHospitalDept.setLv3Code(getRandom());
				mBdHospitalDept.setLv3Name(data.getLv3Name());								
			}
			if(StringUtils.isNotBlank(status)){
				mBdHospitalDept.setStatus(status);//状态
			}else{
				mBdHospitalDept.setStatus(Constans.ACTIVE);//初始有效
			}
			mBdHospitalDept.setRegulatoryCode("JG"+getRandom());//监管编码
			flag=insert(mBdHospitalDept);
		}		
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
		String lv1Code=data.getLv1Code();
		Integer level=data.getLevel();
		if(status!=null&&lv1Code!=null&&level!=null){
			if(level==1){
				mBdHospitalDept.setLv1Name(data.getLv1Name());
			}else if(level==2){
				mBdHospitalDept.setLv1Name(data.getLv1Name());
				mBdHospitalDept.setLv2Name(data.getLv2Name());
			}else{
				mBdHospitalDept.setLv1Name(data.getLv1Name());
				mBdHospitalDept.setLv2Name(data.getLv2Name());
				mBdHospitalDept.setLv3Name(data.getLv3Name());	
			}
			mBdHospitalDept.setStatus(status);				
			flag=update(mBdHospitalDept,Condition.create().eq("lv1Code", data.getLv1Code()));
		}
		return flag?R.ok():R.error("状态更新失败");
	}
	@Override
	public R deleteOneHost(BdHospitalDept data) {
		BdHospitalDept mBdHospitalDept=new BdHospitalDept();
		mBdHospitalDept.setStatus(Constans.DELETED);
		boolean flag=update(mBdHospitalDept,Condition.create().eq("lv1Code", data.getLv1Code()));
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
	public Page<BdHospitalDept> getListHost(Page<BdHospitalDept> page,String lv1Code,String lv2Code,
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
		if(StringUtils.isNotBlank(level)){
			con.eq("level", level);
		}
		if(StringUtils.isNotBlank(status)){
			con.eq("status", status);
		}else{
			con.where("status !={0}", Constans.DELETED);
		}	
		selectPage(page, con);
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
