package com.drelephant.elephantadmin.business.basedata.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
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
import com.drelephant.elephantadmin.business.basedata.entity.BdAreaRegion;
import com.drelephant.elephantadmin.business.basedata.entity.BdOrg;
import com.drelephant.elephantadmin.business.basedata.mapper.BdAreaRegionMapper;
import com.drelephant.elephantadmin.business.basedata.mapper.BdCompanyDeptMapper;
import com.drelephant.elephantadmin.business.basedata.mapper.BdOrgMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdOrgService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;

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
	
	@Autowired
	BdAreaRegionMapper mBdAreaRegionMapper;
	
	@Override
	public R addCompany(BdOrg entity) {
		String name = entity.getName();
		if (StringUtils.isBlank(name)) {
			return R.error("公司名称为空");
		}
		name = name.trim();
		if (name.length() > 30) {
			return R.error("公司名称长度超过30字符");
		}
		//
		int nameCount = bdOrgMapper.selectCompanyName(name);
		if (nameCount > 0) {
			return R.error("公司名称已被使用过");
		}
		
		BdOrg mBdOrg=new BdOrg();
		mBdOrg.setName(name);
		//
		List<String> codes = bdOrgMapper.selectCompanyCodes();
		int order = 0;
		for (String code : codes) {
			if (StringUtils.isNotBlank(code)) {
				code = code.trim();
				if (code.startsWith("GS") && code.length() > 2) {
					try {
						int tempOrder = Integer.parseInt(code.substring(2));
						if (tempOrder > order) {
							order = tempOrder;
						}
					} catch (Exception e) {}
				}
			}
		}
		String code = "GS" + StringUtils.leftPad(Integer.toString(order + 1), 4, "0"); // 公司编号：GS+0000，从0001自增长
		mBdOrg.setCode(code);
		//
		mBdOrg.setOrgNature(Constans.ORG_NATURE_COMPANY);//机构性质
		mBdOrg.setStatus(Constans.ACTIVE);//初始状态

		bdOrgMapper.insertBdOrg(mBdOrg);
		return R.ok().put("msg", "新增公司成功！");
	}
	
	@Override
	public R updateCompany(BdOrg data) {
		if (StringUtils.isBlank(data.getCode())) {
			return R.error("公司编号为空");
		}
		
		String name = data.getName();
		if (StringUtils.isBlank(name)) {
			return R.error("公司名称为空");
		}
		name = name.trim();
		if (name.length() > 20) {
			return R.error("公司名称长度超过20");
		}
		//
		int nameCount = bdOrgMapper.selectCompanyNameForOtherCode(name, data.getCode());
		if (nameCount > 0) {
			return R.error("公司名称已被使用过");
		}
		
		BdOrg bdOrg=new BdOrg();
		bdOrg.setName(data.getName());
		boolean flag=update(bdOrg,Condition.create().eq("code", data.getCode()));
		
		return flag?R.ok():R.error("更新失败");
	}
	@Override
	public R deleteCompany(String code) {
		int deptCount=mBdCompanyDeptMapper.selectCount(Condition.create().eq("companyCode", code));
		boolean flag=false;
		if(deptCount==0){
			BdOrg bdOrg=new BdOrg();
			bdOrg.setStatus(Constans.DELETED);
			Condition con=Condition.create();
			con.eq("code", code);
			flag=update(bdOrg,con);
		}
		
		return flag?R.ok():R.error("部门信息不为空，禁止删除公司信息");
	}
	@Override
	public R selectCompay() {
		Condition con = Condition.create();
		con.eq("orgNature", Constans.ORG_NATURE_COMPANY);
		con.where("status !={0}", Constans.DELETED);//未删除状态下的公司
		List<BdOrg> list = selectList(con);
		
		// 按 编码降序排序
		java.util.Collections.sort(list, new Comparator<BdOrg>() {
			@Override
			public int compare(BdOrg o1, BdOrg o2) {
				return o1.getCode().compareTo(o2.getCode());
			}
		});
		
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
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		return uuid;
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
			bdOrg.setProvinceCode(data.getProvinceCode());//省份编码
			bdOrg.setProvinceName(data.getProvinceName());
			bdOrg.setCityCode(data.getCityCode());//城市编码
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
	public Page<BdOrg> getListBdOrg(Page<BdOrg> page,String code,String provinceCode,String cityCode,String name
			,String hospitalLevel,String status) {
		Condition con=Condition.create();
		con.eq("orgNature", Constans.ORG_NATURE_HOSPITAL);//类型为医院
		if(StringUtils.isNotBlank(code)){
			con.eq("code", code);
		}
		if(StringUtils.isNotBlank(provinceCode)){
			con.eq("provinceCode", provinceCode);
		}
		if(StringUtils.isNotBlank(cityCode)){
			con.eq("cityCode", cityCode);
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

	@Override
	public R getProvinceList() {
		List<BdAreaRegion> list = mBdAreaRegionMapper.getProvinceList();
		List<Map<String, Object>> provinces = new ArrayList<Map<String, Object>>();
		Map<String, Object> province = null;
		for (BdAreaRegion bdOrg : list) {
			province = new HashMap<String, Object>();
			province.put("provinceCode", bdOrg.getProvinceCode());
			province.put("provinceName", bdOrg.getProvinceName());
			provinces.add(province);
		}
		return R.ok().put("list", provinces);
	}

	@Override
	public R getCityList(String provinceCode) {
		List<BdAreaRegion> list = mBdAreaRegionMapper.getCityList(provinceCode);
		List<Map<String, Object>> provinces = new ArrayList<Map<String, Object>>();
		Map<String, Object> province = null;
		for (BdAreaRegion bdOrg : list) {
			province = new HashMap<String, Object>();
			province.put("cityCode", bdOrg.getCityCode());
			province.put("cityName", bdOrg.getCityName());
			provinces.add(province);
		}
		return R.ok().put("list", provinces);
	}
	
}
