package com.drelephant.elephantadmin.business.basedata.service.impl;

import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.drelephant.elephantadmin.business.basedata.entity.BdCompanyDept;
import com.drelephant.elephantadmin.business.basedata.mapper.BdCompanyDeptMapper;
import com.drelephant.elephantadmin.business.basedata.mapper.BdOrgMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdCompanyDeptService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;

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
	public R addDept(BdCompanyDept data) {
		if (StringUtils.isBlank(data.getCompanyCode())) {
			return R.error("公司编号为空");
		}
		
		String name = data.getName();
		if (StringUtils.isBlank(name)) {
			return R.error("部门名称为空");
		}
		name = name.trim();
		if (name.length() > 20) {
			return R.error("部门名称长度超过20");
		}
		//
		int nameCount = bdCompanyDeptMapper.selectDeptName(name);
		if (nameCount > 0) {
			return R.error("部门名称已被使用过");
		}
		
		//
		List<String> codes = bdCompanyDeptMapper.selectDeptCodes();
		int order = 0;
		for (String code : codes) {
			if (StringUtils.isNotBlank(code)) {
				code = code.trim();
				if (code.startsWith("BM") && code.length() > 2) {
					try {
						int tempOrder = Integer.parseInt(code.substring(2));
						if (tempOrder > order) {
							order = tempOrder;
						}
					} catch (Exception e) {}
				}
			}
		}
		String code = "BM" + StringUtils.leftPad(Integer.toString(order + 1), 4, "0"); // 公司编号：BM+0000，从0001自增长
		BdCompanyDept mBdCompanyDept=new BdCompanyDept();		
		mBdCompanyDept.setCode(code);
		
		String companyName = bdOrgMapper.getCompanyName(data.getCompanyCode());
		if(StringUtils.isNotBlank(companyName)){
			mBdCompanyDept.setName(data.getName());
			mBdCompanyDept.setCompanyCode(data.getCompanyCode());
			mBdCompanyDept.setCompanyName(companyName);
			mBdCompanyDept.setDeptEmail(data.getDeptEmail());
			mBdCompanyDept.setStatus(Constans.ACTIVE);
			boolean flag = insert(mBdCompanyDept);
			return flag?R.ok():R.error("新增部门失败");
		} else {
			return R.error("公司不存在");
		}
	}
	
	@Override
	public R updateDept(BdCompanyDept data) {
		if (StringUtils.isBlank(data.getCode())) {
			return R.error("部门编号为空");
		}
		
		String name = data.getName();
		if (StringUtils.isBlank(name)) {
			return R.error("部门名称为空");
		}
		name = name.trim();
		if (name.length() > 20) {
			return R.error("部门名称长度超过20");
		}
		//
		int nameCount = bdCompanyDeptMapper.selectDeptNameForOtherCode(name, data.getCode());
		if (nameCount > 0) {
			return R.error("部门名称已被使用过");
		}
		
		BdCompanyDept mBdCompanyDept=new BdCompanyDept();
		mBdCompanyDept.setName(data.getName());
		boolean flag=update(mBdCompanyDept,Condition.create().eq("code", data.getCode()));
		return flag?R.ok():R.error("更新异常");
	}

	@Override
	public R deleteDept(String code) {
		BdCompanyDept item = new BdCompanyDept();
		item.setStatus(Constans.DELETED);
		Condition con = Condition.create();
		con.eq("code", code);
		boolean flag= update(item, con);
		
		return flag?R.ok():R.error("删除失败");
	}
	public static  String getRandom(){
		Random  r=new Random ();
		return "BM"+r.nextInt();
	}

	@Override
	public R getListDept(String companyCode, int current, int pageSize) {
		if (StringUtils.isBlank(companyCode)) {
			return R.error("公司编号为空");
		}
		
		Page<BdCompanyDept> page=new Page<>(current,pageSize, "code");
		selectPage(page, Condition.create().eq("status", Constans.ACTIVE).eq("companyCode", companyCode));

		return R.ok().put("list",page.getRecords()).put("total",page.getTotal());
	}

	@Override
	public R getDept(String code) {
		if (StringUtils.isBlank(code)) {
			return R.error("部门编号为空");
		}
		
		BdCompanyDept dept = this.selectOne(Condition.create().eq("code", code));

		return R.ok().put("dept", dept);
	}
}
