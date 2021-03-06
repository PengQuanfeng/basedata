package com.drelephant.elephantadmin.business.basedata.service;

import com.drelephant.elephantadmin.business.basedata.entity.BdDictValue;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 数据字典表 服务类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public interface BdDictValueService extends IService<BdDictValue> {
	/**
	 * 根据对应的typeCode值
	 * @param typeCode
	 * @return
	 */
	List<BdDictValue> listByTypeCode(String typeCode);
}
