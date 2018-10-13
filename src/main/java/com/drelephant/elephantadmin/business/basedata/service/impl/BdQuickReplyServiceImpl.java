package com.drelephant.elephantadmin.business.basedata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.drelephant.elephantadmin.business.basedata.entity.BdQuickReply;
import com.drelephant.elephantadmin.business.basedata.mapper.BdQuickReplyMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdQuickReplyService;
/**
 * <p>
 *  快捷回复设置
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Service
public class BdQuickReplyServiceImpl extends ServiceImpl<BdQuickReplyMapper,BdQuickReply> implements BdQuickReplyService{
	@Autowired
	BdQuickReplyMapper bdQuickReplyMapper;
	@Override
	public Map<String, String> getServiceType(String type) {
		Map<String,String> map = new HashMap<String,String>();
		//TODO:后续改用读数据字典方式
		if(StringUtils.equals("FWLX", type)){
			map.put("ZXZX", "在线咨询");
			map.put("KYMZ", "开药门诊");
			map.put("JCMZ", "检查门诊");
		}
		return map;
	}

	@Override
	@Transactional
	public void saveQuickReply(BdQuickReply entity) {
		bdQuickReplyMapper.saveQuickReply(entity);	
	}

	@Override
	@Transactional
	public void updateQuickReply(BdQuickReply entity) {
		// TODO Auto-generated method stub
		bdQuickReplyMapper.updateQuickReply(entity);
	}

	@Override
	public Page<BdQuickReply> queryQuickReplyInfo(int offset, int limit, String id) {
		// 构造分页实体
		Page<BdQuickReply> page = new Page<BdQuickReply>(offset, limit);
		List<BdQuickReply> bdQuickReplyList = bdQuickReplyMapper.queryQuickReply(page, id);
		if (CollectionUtils.isNotEmpty(bdQuickReplyList)) {
			page.setRecords(bdQuickReplyList);
			return page;
		}
		return page;
	}

	@Override
	public void deleteQuickReplyById(String id) {
		// TODO Auto-generated method stub
		bdQuickReplyMapper.deleteQuickReplyById(id);
	}

}
