package com.drelephant.elephantadmin.business.basedata.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.drelephant.elephantadmin.business.basedata.entity.BdQuickReply;
import com.drelephant.framework.base.common.R;
/**
 * <p>
 *  快捷回复设置
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public interface BdQuickReplyService extends IService<BdQuickReply>{
	
	List<Map<String, String>> getServiceTypes();
	
	void saveQuickReply(BdQuickReply entity);
	void updateQuickReply(BdQuickReply entity);
	Page<BdQuickReply> queryQuickReplyInfo(int offset, int limit);
	void deleteQuickReplyById(String id);
	/**
	 * 上移
	 * @param id
	 * @return
	 */
	R moveUp(String id);
	
	/**
	 * 下移
	 * @param id
	 * @return
	 */
	R moveDown(String id);
}
