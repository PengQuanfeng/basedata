package com.drelephant.elephantadmin.business.basedata.service.impl;

import com.drelephant.elephantadmin.business.basedata.entity.BdAreaRegion;
import com.drelephant.elephantadmin.business.basedata.entity.BdOrg;
import com.drelephant.elephantadmin.business.basedata.mapper.BdCompanyDeptMapper;
import com.drelephant.elephantadmin.business.basedata.mapper.BdOrgMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdOrgService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;
import com.drelephant.framework.core.swagger.SwaggerConfigurationProerties.Contact;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
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
			BdOrg bdOrg=new BdOrg();
			bdOrg.setStatus(Constans.DELETED);
			Condition con=Condition.create();
			con.eq("code", code);
			update(bdOrg,con);
		}		
		return flag?R.ok():R.error("部门信息不为空，禁止删除公司信息");
	}
	@Override
	public R selectCompay() {	
		Condition con=Condition.create();
		con.eq("orgNature", Constans.AUTIT_ORGNATURE);
		con.where("status !={0}", Constans.DELETED);//未删除状态下的公司
		List<BdOrg> list=selectList(con);
		for (BdOrg bdOrg : list) {
			String code=bdOrg.getCode();
			Condition ccon=Condition.create();
			ccon.eq("companyCode", code);
			con.where("status !={0}", Constans.DELETED);//未删除状态下的部门个数
			int count=mBdCompanyDeptMapper.selectCount(ccon);
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
	/**
	 * 查询列表需要修改--增加搜索条件
	 */
	@Override
	public Page<BdOrg> getListBdOrg(Page<BdOrg> page,String code,String provinceName,String cityName,String name
			,String hospitalLevel,String status) {
		Condition con=Condition.create();
		con.eq("orgNature", Constans.AUTIT_HOSPITAL);//类型为医院
		if(StringUtils.isNotBlank(code)){
			con.eq("code", code);
		}
		if(StringUtils.isNotBlank(provinceName)){
			con.eq("provinceName", provinceName);
		}
		if(StringUtils.isNotBlank(cityName)){
			con.eq("cityName", cityName);
		}
		if(StringUtils.isNotBlank(name)){
			con.eq("name", name);
		}
		if(StringUtils.isNotBlank(hospitalLevel)){
			con.eq("hospitalLevel", hospitalLevel);
		}
		if(StringUtils.isNotBlank(status)){
			con.eq("status", status);
		}else{
			con.where("status !={0}", Constans.DELETED);
		}				
		selectPage(page, con);
		return page;
	}
	/**
	 * 
	 */
	@Override
	public R updateOneHosStatus(BdOrg data) {
		BdOrg bdOrg=new BdOrg();
		boolean flag=false;
		String status=data.getStatus();
		String cityName=data.getCityName();
		String provinceName=data.getProvinceName();
		String name=data.getName();
		String hospitalLevel=data.getHospitalLevel();
		if(status!=null&&cityName!=null&&provinceName!=null&&name!=null&&hospitalLevel!=null){
			bdOrg.setStatus(status);
			bdOrg.setCityName(cityName);
			bdOrg.setProvinceName(data.getProvinceName());
			bdOrg.setName(name);
			bdOrg.setHospitalLevel(hospitalLevel);
			flag=update(bdOrg,Condition.create().eq("code", data.getCode()));
		}
		
		return flag?R.ok():R.error("参数为空，状态更新失败");
	}

	@Override
	public R deleteOneHosStatus(BdOrg data) {
		BdOrg bdOrg=new BdOrg();
		bdOrg.setStatus(Constans.DELETED);
		boolean flag=update(bdOrg,Condition.create().eq("code", data.getCode()));
		return flag?R.ok():R.error("删除失败");
	}

	@Override
	public R deleteBatchHosStatus(String status, String code) {
		List<String> list=new ArrayList<String>();
		String[] str=null;
		if(StringUtils.isNotBlank(code)){
			str=code.trim().split(",");
			for(int i=0;i<str.length;i++){				
				list.add(str[i]);
			}
		}
		boolean flag=false;
		if(list!=null && !list.isEmpty()){
			BdOrg bdOrg=new BdOrg();			
			for(int j=0;j<list.size();j++){
				bdOrg.setStatus(status);
				flag=update(bdOrg,Condition.create().eq("code", list.get(j)));
			}
		}
		return flag?R.ok():R.error();
	}
	
}