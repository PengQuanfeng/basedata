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
import com.drelephant.elephantadmin.business.basedata.entity.BdBanner;
import com.drelephant.elephantadmin.business.basedata.entity.BdQuickReply;
import com.drelephant.elephantadmin.business.basedata.mapper.BdQuickReplyMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdQuickReplyService;
import com.drelephant.framework.base.common.R;
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

	@Override
	public R moveUp(String id) {
		List<BdQuickReply> list = bdQuickReplyMapper.getAll();
		int orderNumber = 0;
		BdQuickReply bdQuickReply = null;
		for (int i = 0; i < list.size(); i++) {
			bdQuickReply = list.get(i);
			if (bdQuickReply.getId().equals(id)) {
				orderNumber = bdQuickReply.getOrderNumber();
				if (i == 0) { // 当前记录是排在最前面的记录
					break;
				} else {
					bdQuickReplyMapper.updateOrderNumber(id, orderNumber - 1); // 前移1个号码
					
					// 前一个BdBanner后移1个号码
					BdQuickReply prQuickReply = list.get(i - 1);
					bdQuickReplyMapper.updateOrderNumber(prQuickReply.getId(), prQuickReply.getOrderNumber() + 1);
				}
			}
		}
		return R.ok();
	}

	@Override
	public R moveDown(String id) {
		List<BdQuickReply> list = bdQuickReplyMapper.getAll();
		int orderNumber = 0;
		BdQuickReply bdQuickReply = null;
		for (int i = 0; i < list.size(); i++) {
			bdQuickReply = list.get(i);
			if (bdQuickReply.getId().equals(id)) {
				orderNumber = bdQuickReply.getOrderNumber();
				if (i == (list.size() - 1)) { // 当前记录是排在最后面的记录
					break;
				} else {
					bdQuickReplyMapper.updateOrderNumber(id, orderNumber + 1); // 后移1个号码
					
					// 下一个BdBanner前移1个号码
					BdQuickReply nextQuickReply = list.get(i + 1);
					bdQuickReplyMapper.updateOrderNumber(nextQuickReply.getId(), nextQuickReply.getOrderNumber() - 1);
				}
			}
		}
		return R.ok();
	}

}
