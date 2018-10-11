package com.drelephant.elephantadmin.business.basedata.service.impl;

import com.drelephant.elephantadmin.business.basedata.entity.BdCompanyDept;
import com.drelephant.elephantadmin.business.basedata.mapper.BdCompanyDeptMapper;
import com.drelephant.elephantadmin.business.basedata.mapper.BdOrgMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdCompanyDeptService;
import com.drelephant.framework.base.common.R;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门信息表 服务实现类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Service
public class BdCompanyDeptServiceImpl extends ServiceImpl<BdCompanyDeptMapper, BdCompanyDept> implements BdCompanyDeptService {
	@Autowired
	BdCompanyDeptMapper bdCompanyDeptMapper;
	@Autowired
	BdOrgMapper bdOrgMapper;
	@Override
	public List<BdCompanyDept> getCompanyList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R addCompany(BdCompanyDept data) {
		BdCompanyDept mBdCompanyDept=new BdCompanyDept();		
		int count=bdOrgMapper.selectCount(Condition.create().eq("code", data.getCompanyCode()));
		boolean flag=false;
		if(count>0){
			mBdCompanyDept.setName(data.getName());
			//需替换，部门编码生成规则
			mBdCompanyDept.setCode(getRandom());
			mBdCompanyDept.setCompanyCode(data.getCompanyCode());
			mBdCompanyDept.setCompanyName(data.getCompanyName());
			mBdCompanyDept.setDeptEmail(data.getDeptEmail());
			//bdCompanyDeptMapper.insert(entity)
			flag=insert(mBdCompanyDept);
		}		
		return flag?R.ok():R.error("公司不存在");
	}	
	@Override
	public R updateCompany(BdCompanyDept data) {
		// TODO Auto-generated method stub
		BdCompanyDept mBdCompanyDept=new BdCompanyDept();
		mBdCompanyDept.setName(data.getName());
		boolean flag=update(mBdCompanyDept,Condition.create().eq("code", data.getCode()));
		return flag?R.ok():R.error("更新异常");
	}

	@Override
	public R delectCompany(String code) {
		// TODO Auto-generated method stub
		boolean flag=delete(Condition.create().eq("code", code));
		return flag?R.ok():R.error("删除失败");
	}
	public static  String getRandom(){
		Random  r=new Random ();
		return "BM"+r.nextInt();
	}

	@Override
	public R getListDept(int current, int pageSize) {
		Page<BdCompanyDept> page=new Page<>(current,pageSize);
		selectPage(page, Condition.create());
		//List<BdCompanyDept> list=selectList(Condition.create());
		return R.ok().put("list",page.getRecords()).put("total",page.getTotal());
	}
}
