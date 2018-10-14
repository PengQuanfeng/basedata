package com.drelephant.elephantadmin.business.basedata.service;

import com.drelephant.elephantadmin.business.basedata.entity.BdBanner;
import com.drelephant.framework.base.common.R;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * banner 服务类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public interface BdBannerService extends IService<BdBanner> {
	/**
	 * 新增首页配图
	 * @param bdBanner
	 * @return
	 */
	R insertBdBander(BdBanner bdBanner);
	/**
	 * 修改数据
	 * @param bdBanner
	 * @return
	 */
	R updateBdBander(BdBanner bdBanner);
	/**
	 * 根据id查询单条记录
	 * @param id
	 * @return
	 */
	BdBanner selectOneBd(String id);
	/**
	 * 列表数据
	 * @return
	 */
	List<BdBanner> getListBd();
	/**
	 * 根据id进行逻辑删除
	 * @param id
	 * @return
	 */
	R updateStatus(String id);
	/**
	 * 更新ordernumber字段
	 * @param id
	 * @return
	 */
	R updateOrderNum(String id);
	
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
