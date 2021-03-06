package com.drelephant.elephantadmin.business.basedata.service.impl;


import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.drelephant.elephantadmin.business.basedata.entity.BdHospitalDept;
import com.drelephant.elephantadmin.business.basedata.entity.TemplateCases;
import com.drelephant.elephantadmin.business.basedata.mapper.BdHospitalDeptMapper;
import com.drelephant.elephantadmin.business.basedata.mapper.TemplateCasesMapper;
import com.drelephant.elephantadmin.business.basedata.service.TemplateCasesService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.framework.base.common.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.*;

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
        String tmpName = templateCases.getTmpName();
        if (StringUtils.isBlank(tmpName)) {
            return R.error("模板名不能为空");
        }
        int count = selectCount(Condition.create().eq("TmpName", tmpName).where("status !={0}", Constans.DELETED));
        if (count > 0) {
            return R.error().put("msg", "模板名称已存在");
        }
        if (tmpName.length() > 20) {
            return R.error().put("msg", "模板名称长度超过20");
        }
        templateCases.setTmpName(tmpName);
        templateCases.setTemplateType(Constans.TEMPTYPE);
        String chiefComplaint = templateCases.getChiefComplaint();
        String illnessHistory = templateCases.getIllnessHistory();
        String anamnesis = templateCases.getAnamnesis();
        if (StringUtils.isBlank(chiefComplaint) || StringUtils.isBlank(illnessHistory) || StringUtils.isBlank(anamnesis)) {
            return R.error().put("msg", "参数为空");
        }
        if (chiefComplaint.length() > 200) {
            return R.error().put("msg", "输入字符长度大于200");
        }
        if (illnessHistory.length() > 200) {
            return R.error().put("msg", "输入字符长度大于200");
        }
        if (anamnesis.length() > 200) {
            return R.error().put("msg", "输入字符长度大于200");
        }
        templateCases.setChiefComplaint(chiefComplaint);
        templateCases.setIllnessHistory(illnessHistory);
        templateCases.setAnamnesis(anamnesis);
        String lv1DeptCode = templateCases.getLv1DeptCode();
        String lv2DeptCode = templateCases.getLv2DeptCode();
        String lv3DeptCode = templateCases.getLv3DeptCode();
        if (StringUtils.isNotBlank(lv1DeptCode)) {
            //一级科室编码
            BdHospitalDept bdHospitE = new BdHospitalDept();
            bdHospitE.setLv1Code(lv1DeptCode);
            bdHospitE.setLevel(1);
            BdHospitalDept bdHospit = bdHospitalDeptMapper.selectOne(bdHospitE);
            templateCases.setLv1DeptCode(lv1DeptCode);
            templateCases.setLv1DeptName(bdHospit.getLv1Name());
        }
        if (StringUtils.isNotBlank(lv2DeptCode)) {
            //一级科室编码
            BdHospitalDept bdHospitE = new BdHospitalDept();
            bdHospitE.setLv1Code(lv1DeptCode);
            bdHospitE.setLevel(1);
            BdHospitalDept bdHospit = bdHospitalDeptMapper.selectOne(bdHospitE);
            //二级科室编码
            BdHospitalDept bdHospitE2 = new BdHospitalDept();
            bdHospitE2.setLv2Code(lv2DeptCode);
            bdHospitE2.setLevel(2);
            BdHospitalDept bdHospit2 = bdHospitalDeptMapper.selectOne(bdHospitE2);
            templateCases.setLv1DeptCode(lv1DeptCode);
            templateCases.setLv2DeptCode(lv2DeptCode);
            templateCases.setLv1DeptName(bdHospit.getLv1Name());
            templateCases.setLv2DeptName(bdHospit2.getLv2Name());
        }
        if (StringUtils.isNotBlank(lv3DeptCode)) {
            //一级科室编码
            BdHospitalDept bdHospitE = new BdHospitalDept();
            bdHospitE.setLv1Code(lv1DeptCode);
            bdHospitE.setLevel(1);
            BdHospitalDept bdHospit = bdHospitalDeptMapper.selectOne(bdHospitE);
            //二级科室编码
            BdHospitalDept bdHospitE2 = new BdHospitalDept();
            bdHospitE2.setLv2Code(lv2DeptCode);
            bdHospitE2.setLevel(2);
            BdHospitalDept bdHospit2 = bdHospitalDeptMapper.selectOne(bdHospitE2);
            //三级科室编码
            BdHospitalDept bdHospitE3 = new BdHospitalDept();
            bdHospitE3.setLevel(3);
            bdHospitE3.setLv3Code(lv3DeptCode);
            BdHospitalDept bdHospit3 = bdHospitalDeptMapper.selectOne(bdHospitE3);
            templateCases.setLv2DeptName(bdHospit2.getLv2Name());
            templateCases.setLv1DeptName(bdHospit.getLv1Name());
            templateCases.setLv3DeptName(bdHospit3.getLv3Name());
            templateCases.setLv1DeptCode(lv1DeptCode);
            templateCases.setLv2DeptCode(lv2DeptCode);
            templateCases.setLv3DeptCode(lv3DeptCode);
        }
        templateCases.setTmpCode(getRandom());    //模板编码
        templateCases.setDoctorName("-");
        String status = templateCases.getStatus();
        if (StringUtils.isNotBlank(status)) {
            templateCases.setStatus(status);//状态
        } else {
            templateCases.setStatus(Constans.ACTIVE);//初始有效
        }
        boolean flag = insert(templateCases);
        return flag ? R.ok("新增成功") : R.error("插入失败");
    }

    public static String getRandom() {
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uuid;
    }

    @Override
    public R deleteOneTemp(String tmpCode) {
        TemplateCases tem = new TemplateCases();
        tem.setStatus(Constans.DELETED);
        boolean flag = update(tem, Condition.create().eq("tmpCode", tmpCode));
        return flag ? R.ok() : R.error("删除失败");
    }


    @Transactional
    @Override
    public R updateBatchStatusByCodeList(@Nonnull List<String> tmpCodeList, @Nonnull String status) {
        if (tmpCodeList.isEmpty()) {
            return R.error("tmpCodeList 不能为空");
        }
        //不存在操作失败的状况, 直接成功 .
        tmpCodeList.forEach(v -> {
            TemplateCases entity = new TemplateCases();
            entity.setStatus(status);
            update(entity, Condition.create().eq("tmpCode", v));
        });
        return R.ok();

    }

    public R getLv1List() {
        List<BdHospitalDept> list = bdHospitalDeptMapper.getLv1List();
        List<Map<String, Object>> lv1s = new ArrayList<Map<String, Object>>();
        Map<String, Object> lv1 = null;
        for (BdHospitalDept bdHospitalDept : list) {
            lv1 = new HashMap<String, Object>();
            lv1.put("lv1DeptCode", bdHospitalDept.getLv1Code());
            lv1.put("lv1DeptName", bdHospitalDept.getLv1Name());
            lv1s.add(lv1);
        }
        return R.ok().put("list", lv1s);
    }

    public R getLv2List(String lv1DeptCode) {
        List<BdHospitalDept> list = bdHospitalDeptMapper.getLv2List(lv1DeptCode);
        List<Map<String, Object>> lv2s = new ArrayList<Map<String, Object>>();
        Map<String, Object> lv2 = null;
        for (BdHospitalDept bdHospitalDept : list) {
            lv2 = new HashMap<String, Object>();
            lv2.put("lv2DeptCode", bdHospitalDept.getLv2Code());
            lv2.put("lv2DeptName", bdHospitalDept.getLv2Name());
            lv2s.add(lv2);
        }
        return R.ok().put("list", lv2s);
    }

    public R getLv3List(String lv3DeptCode) {
        List<BdHospitalDept> list = bdHospitalDeptMapper.getLv3List(lv3DeptCode);
        List<Map<String, Object>> lv3s = new ArrayList<Map<String, Object>>();
        Map<String, Object> lv3 = null;
        for (BdHospitalDept bdHospitalDept : list) {
            lv3 = new HashMap<String, Object>();
            lv3.put("lv3DeptCode", bdHospitalDept.getLv3Code());
            lv3.put("lv3DeptName", bdHospitalDept.getLv3Name());
            lv3s.add(lv3);
        }
        return R.ok().put("list", lv3s);
    }

    @Override
    public TemplateCases selectOneTemp(String tempCode) {
        return selectOne(Condition.create().eq("tmpCode", tempCode));
    }

    @Override
    public R updateOneTemp(TemplateCases data) {
//		TemplateCases temp=new TemplateCases();
//		boolean flag=false;
//		String status=data.getStatus();
//		int count=selectCount(Condition.create().eq("tmpName", data.getTmpName()));
//		if(status!=null&&count==0){
//			temp.setStatus(status);
//			temp.setChiefComplaint(data.getChiefComplaint());
//			temp.setLv1DeptCode(data.getLv1DeptCode());
//			temp.setLv1DeptName(data.getLv1DeptName());
//			temp.setLv2DeptCode(data.getLv2DeptCode());
//			temp.setLv2DeptName(data.getLv2DeptName());
//			temp.setAnamnesis(data.getAnamnesis());
//			temp.setIllnessHistory(data.getIllnessHistory());			
//			temp.setTmpName(data.getTmpName());
//			flag=update(temp,Condition.create().eq("tmpCode", data.getTmpCode()));
//		}	
        String lv1DeptCode = data.getLv1DeptCode();
        String lv2DeptCode = data.getLv2DeptCode();
        String lv3DeptCode = data.getLv3DeptCode();
        if (lv1DeptCode != null) {
            BdHospitalDept bdHospitE = new BdHospitalDept();
            bdHospitE.setLv1Code(lv1DeptCode);
            bdHospitE.setLevel(1);
            BdHospitalDept bdHospit = bdHospitalDeptMapper.selectOne(bdHospitE);
            data.setLv1DeptName(bdHospit.getLv1Name());
        }
        if (lv2DeptCode != null) {
            BdHospitalDept bdHospitE2 = new BdHospitalDept();
            bdHospitE2.setLv2Code(lv2DeptCode);
            bdHospitE2.setLevel(2);
            BdHospitalDept bdHospit2 = bdHospitalDeptMapper.selectOne(bdHospitE2);
            data.setLv2DeptName(bdHospit2.getLv2Name());
        }
        boolean flag = update(data, Condition.create().eq("id", data.getId()));
        return flag ? R.ok().put("msg", "更新成功") : R.error().put("msg", "更新失败");
    }

    @Override
    public Page<TemplateCases> getListTemp(Page<TemplateCases> page, int current, int pageSize, String lv1DeptCode, String lv2DeptCode,
                                           String tmpName, String templateType, String status) {
        Condition con = Condition.create();
        if (StringUtils.isNotBlank(lv1DeptCode)) {
            con.eq("lv1DeptCode", lv1DeptCode);
        }
        if (StringUtils.isNotBlank(lv2DeptCode)) {
            con.eq("lv2DeptCode", lv2DeptCode);
        }
        if (StringUtils.isNotBlank(tmpName)) {
            //con.eq("lv3Code", tmpName);
            con.like("tmpName", tmpName);
        }
        if (StringUtils.isNotBlank(templateType)) {
            con.eq("templateType", templateType);
        }
        if (StringUtils.isNotBlank(status)) {
            con.eq("status", status);
        } else {
            con.where("status !={0}", Constans.DELETED);
        }
        con.orderBy("updateTime", false);//降序排列
        selectPage(page, con);
        return page;
    }

}
