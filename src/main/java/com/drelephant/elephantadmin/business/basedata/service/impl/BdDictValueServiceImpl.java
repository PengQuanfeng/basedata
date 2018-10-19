package com.drelephant.elephantadmin.business.basedata.service.impl;

import com.drelephant.elephantadmin.business.basedata.entity.BdDictValue;
import com.drelephant.elephantadmin.business.basedata.mapper.BdDictValueMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdDictValueService;
import com.drelephant.framework.base.common.R;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据字典表 服务实现类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Service
public class BdDictValueServiceImpl extends ServiceImpl<BdDictValueMapper, BdDictValue> implements BdDictValueService {

	@Override
	public List<BdDictValue> listByTypeCode(String typeCode) {
		Condition con=Condition.create();
		con.eq("typeCode", typeCode);
		List<BdDictValue> BdDictValue=selectList(con);
		return BdDictValue;
	}
	
}
