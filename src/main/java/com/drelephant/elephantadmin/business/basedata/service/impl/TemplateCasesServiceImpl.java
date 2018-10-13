package com.drelephant.elephantadmin.business.basedata.service.impl;



import com.drelephant.elephantadmin.business.basedata.entity.BdHospitalDept;
import com.drelephant.elephantadmin.business.basedata.entity.TemplateCases;
import com.drelephant.elephantadmin.business.basedata.mapper.BdHospitalDeptMapper;
import com.drelephant.elephantadmin.business.basedata.mapper.TemplateCasesMapper;
import com.drelephant.elephantadmin.business.basedata.service.TemplateCasesService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 电子病例模板表 服务实现类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Service
public class TemplateCasesServiceImpl extends ServiceImpl<TemplateCasesMapper, TemplateCases> implements TemplateCasesService {
	@Autowired
	BdHospitalDeptMapper bdHospitalDeptMapper;
	@Override
	public R saveTemp(TemplateCases templateCases) {
		String tmpName=templateCases.getTmpName();
		int count=selectCount(Condition.create().eq("TmpName", tmpName).where("status !={0}", Constans.DELETED));
		boolean flag=false;
		if(count==0){
			TemplateCases mtemplateCases=new TemplateCases();
			mtemplateCases.setTmpName(tmpName);
			mtemplateCases.setTmpCode(getRandom());	//模板编码	
			//科室编码查询科室名称
			mtemplateCases.setLv1DeptCode(templateCases.getLv1DeptCode());
			mtemplateCases.setLv1DeptName(templateCases.getLv1DeptName());
			mtemplateCases.setLv2DeptCode(templateCases.getLv2DeptCode());
			mtemplateCases.setLv2DeptName(templateCases.getLv2DeptName());
			mtemplateCases.setChiefComplaint(templateCases.getChiefComplaint());
			mtemplateCases.setIllnessHistory(templateCases.getIllnessHistory());
			mtemplateCases.setAnamnesis(templateCases.getAnamnesis());
			mtemplateCases.setTemplateType(Constans.TEMPTYPE);		
			mtemplateCases.setDoctorName("-");
			String status=templateCases.getStatus();
			if(StringUtils.isNotBlank(status)){
				mtemplateCases.setStatus(status);//状态
			}else{
				mtemplateCases.setStatus(Constans.ACTIVE);//初始有效
			}
			flag=insert(mtemplateCases);
		}
		
		return flag?R.ok():R.error("插入失败");
	}
	public static String getRandom(){
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		return uuid;
	}
	@Override
	public R deleteOneTemp(String tmpCode) {
		TemplateCases tem=new TemplateCases();
		tem.setStatus(Constans.DELETED);
		boolean flag=update(tem,Condition.create().eq("tmpCode", tmpCode));
		return flag?R.ok():R.error("删除失败");
	}
	@Override
	public R updateBatchTemp(String tmpCode, String status) {
		List<String> list=new ArrayList<String>();
		String[] str=null;
		if(StringUtils.isNotBlank(tmpCode)){
			str=tmpCode.trim().split(",");
			for(int i=0;i<str.length;i++){				
				list.add(str[i]);
			}
		}
		boolean flag=false;
		if(list!=null && !list.isEmpty()){
			TemplateCases tem=new TemplateCases();
			for (int i = 0; i < str.length; i++) {
				tem.setStatus(status);
				flag=update(tem,Condition.create().eq("tmpCode", list.get(i)));
			}
		}
		return flag?R.ok():R.error("更新失败");
				
	}
	public R getLv1List() {
		List<BdHospitalDept> list=bdHospitalDeptMapper.getLv1List();
		List<Map<String, Object>> lv1s = new ArrayList<Map<String, Object>>();
		Map<String, Object> lv1 = null;
		for (BdHospitalDept bdHospitalDept : list) {
			lv1= new HashMap<String, Object>();
			lv1.put("lv1DeptCode", bdHospitalDept.getLv1Code());
			lv1.put("lv1DeptName", bdHospitalDept.getLv1Name());
			lv1s.add(lv1);
		}
		return R.ok().put("list", lv1s);
	}
	public R getLv2List(String lv1DeptCode) {
		List<BdHospitalDept> list=bdHospitalDeptMapper.getLv2List(lv1DeptCode);
		List<Map<String, Object>> lv2s = new ArrayList<Map<String, Object>>();
		Map<String, Object> lv2 = null;
		for (BdHospitalDept bdHospitalDept : list) {
			lv2= new HashMap<String, Object>();
			lv2.put("lv2DeptCode", bdHospitalDept.getLv2Code());
			lv2.put("lv2DeptName", bdHospitalDept.getLv2Name());
			lv2s.add(lv2);
		}
		return R.ok().put("list", lv2s);
	}
	@Override
	public TemplateCases selectOneTemp(String tempCode) {		
		return selectOne(Condition.create().eq("tmpCode", tempCode));
	}
	@Override
	public R updateOneTemp(TemplateCases data) {
		TemplateCases temp=new TemplateCases();
		boolean flag=false;
		String status=data.getStatus();
		int count=selectCount(Condition.create().eq("tmpName", data.getTmpName()));
		if(status!=null&&count==0){
			temp.setStatus(status);
			temp.setChiefComplaint(data.getChiefComplaint());
			temp.setLv1DeptCode(data.getLv1DeptCode());
			temp.setLv1DeptName(data.getLv1DeptName());
			temp.setLv2DeptCode(data.getLv2DeptCode());
			temp.setLv2DeptName(data.getLv2DeptName());
			temp.setAnamnesis(data.getAnamnesis());
			temp.setIllnessHistory(data.getIllnessHistory());			
			temp.setTmpName(data.getTmpName());
			flag=update(temp,Condition.create().eq("tmpCode", data.getTmpCode()));
		}		
		return flag?R.ok():R.error("更新错误");
	}
	@Override
	public Page<TemplateCases> getListTemp(Page<TemplateCases> page,int current, int pageSize, String lv1DeptCode, String lv2DeptCode,
			String tmpName, String templateType, String status) {
		Condition con=Condition.create();
		if(StringUtils.isNotBlank(lv1DeptCode)){
			con.eq("lv1DeptCode", lv1DeptCode);
		}
		if(StringUtils.isNotBlank(lv2DeptCode)){
			con.eq("lv2DeptCode", lv2DeptCode);
		}
		if(StringUtils.isNotBlank(tmpName)){
			//con.eq("lv3Code", tmpName);
			con.like("tmpName", tmpName);
		}
		if(StringUtils.isNotBlank(templateType)){
			con.eq("templateType", templateType);
		}
		if(StringUtils.isNotBlank(status)){
			con.eq("status", status);
		}else{
			con.where("status !={0}", Constans.DELETED);
		}	
		con.orderBy("updateTime", false);//降序排列
		selectPage(page, con);
		return page;
	}
	
}