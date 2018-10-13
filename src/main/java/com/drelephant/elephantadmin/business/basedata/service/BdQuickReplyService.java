package com.drelephant.elephantadmin.business.basedata.service;

import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.drelephant.elephantadmin.business.basedata.entity.BdQuickReply;
/**
 * <p>
 *  快捷回复设置
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public interface BdQuickReplyService extends IService<BdQuickReply>{
	Map<String,String> getServiceType(String type);
	void saveQuickReply(BdQuickReply entity);
	void updateQuickReply(BdQuickReply entity);
	Page<BdQuickReply> queryQuickReplyInfo(int offset, int limit, String id);
	void deleteQuickReplyById(String id);
}
