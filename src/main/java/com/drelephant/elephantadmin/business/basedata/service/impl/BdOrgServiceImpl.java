package com.drelephant.elephantadmin.business.basedata.service.impl;

import com.drelephant.elephantadmin.business.basedata.entity.BdOrg;
import com.drelephant.elephantadmin.business.basedata.mapper.BdCompanyDeptMapper;
import com.drelephant.elephantadmin.business.basedata.mapper.BdOrgMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdOrgService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
public class BdOrgServiceImpl extends ServiceImpl<BdOrgMapper, BdOrg> implements BdOrgService {
	@Autowired
	BdOrgMapper bdOrgMapper;
	@Autowired
	BdCompanyDeptMapper mBdCompanyDeptMapper;
	@Override
	public boolean addCompany(String name) {
		BdOrg mBdOrg=new BdOrg();
		mBdOrg.setName(name);
		mBdOrg.setCode("1001");//替换
		mBdOrg.setOrgNature(Constans.AUTIT_ORGNATURE);	
		mBdOrg.setUpdateTime(new Date());
		mBdOrg.setCreateTime(new Date());
		mBdOrg.setCreateUserCode("admin");
		mBdOrg.setCreateUserName("admin");
		mBdOrg.setUpdateUserCode("admin");
		mBdOrg.setUpdateUserName("admin");
		String code="123";
		//已经存在的公司编码不允许插入
		int i=bdOrgMapper.selectCompanyCode(code);
		if(code=="123"){
			bdOrgMapper.insert(mBdOrg);
			return true;
		}		
		return false;
	}
	
	@Override
	public R updateCompany(BdOrg data) {
		// TODO Auto-generated method stub
		BdOrg bdOrg=new BdOrg();
		bdOrg.setName(data.getName());
		boolean flag=update(bdOrg,Condition.create().eq("code", data.getCode()));
		
		return flag?R.ok():R.error("更新失败");
	}
	@Override
	public R deleteCode(String code) {
		int deptCount=mBdCompanyDeptMapper.selectCount(Condition.create().eq("companyCode", code));
		boolean flag=false;
		if(deptCount==0){
			flag=delete(Condition.create().eq("code", code));
		}		
		return flag?R.ok():R.error("部门信息不为空，禁止删除公司信息");
	}
	@Override
	public R selectCompay() {	
		List<BdOrg> list=selectList(Condition.create().eq("orgNature", "company"));
		for (BdOrg bdOrg : list) {
			String code=bdOrg.getCode();
			int count=mBdCompanyDeptMapper.selectCount(Condition.create().eq("companyCode", code));
			bdOrg.setDeptCount(count);
		}				 
		return R.ok().put("list", list);
	}	
	/**
	 * 公司编码生成的通用方法
	 * @return
	 */
	public static String getRandom(){	
		return UUID.randomUUID().toString();
	}

	@Override
	public R addHospital(BdOrg data) {
		// TODO Auto-generated method stub
		BdOrg bdOrg=new BdOrg();
		boolean flag=false;
		int count=selectCount(Condition.create().eq("code", data.getCode()));			
		if(count==0){
			bdOrg.setCode(data.getCode());//自定义医院编码
			bdOrg.setName(data.getName());//医院名称
			bdOrg.setOrgNature(data.getOrgNature());
			bdOrg.setProvinceCode("Pro100010");//省份编码
			bdOrg.setProvinceName(data.getProvinceName());
			bdOrg.setCityCode("CITY100010");//城市编码
			bdOrg.setCityName(data.getCityName());
			bdOrg.setHospitalLevel(data.getHospitalLevel());
			String status=data.getStatus();
			if(status!=null){
				bdOrg.setStatus(data.getStatus());
			}else{
				bdOrg.setStatus(Constans.ACTIVE);
			}
		}						
		flag=insert(bdOrg);
		return flag?R.ok():R.error("插入失败");
	}

	@Override
	public Page<BdOrg> getListBdOrg(Page<BdOrg> page) {
		Condition con=Condition.create();
		con.eq("orgNature", Constans.AUTIT_HOSPITAL);
		con.where("status !={0}", Constans.DELETED);
		selectPage(page, con);
		return page;
	}

	@Override
	public R updateOneHosStatus(BdOrg data) {
		BdOrg bdOrg=new BdOrg();
		bdOrg.setStatus(data.getStatus());
		boolean flag=update(bdOrg,Condition.create().eq("code", data.getCode()));
		return flag?R.ok():R.error("状态更新失败");
	}

	@Override
	public R deleteOneHosStatus(BdOrg data) {
		BdOrg bdOrg=new BdOrg();
		bdOrg.setStatus(Constans.DELETED);
		boolean flag=update(bdOrg,Condition.create().eq("code", data.getCode()));
		return flag?R.ok():R.error("删除失败");
	}
}
