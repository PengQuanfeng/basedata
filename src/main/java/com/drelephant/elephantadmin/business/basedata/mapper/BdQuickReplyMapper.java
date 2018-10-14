package com.drelephant.elephantadmin.business.basedata.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.drelephant.elephantadmin.business.basedata.entity.BdQuickReply;

/**
 * <p>
  * 快捷回复设置Mapper 接口
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Mapper
public interface BdQuickReplyMapper extends BaseMapper<BdQuickReply> {
	int saveQuickReply(BdQuickReply entity);
	int updateQuickReply(BdQuickReply entity);
	List<BdQuickReply> queryQuickReply(Pagination page, @Param("id") String id);
	int deleteQuickReplyById(@Param("id") String id);
	List<BdQuickReply> getAll();
	/**
	 * 更新 排序号
	 * @param id
	 * @param orderNumber
	 */
	void updateOrderNumber(@Param("id")String id, @Param("orderNumber")int orderNumber);
}