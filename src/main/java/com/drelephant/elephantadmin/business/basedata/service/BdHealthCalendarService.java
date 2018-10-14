package com.drelephant.elephantadmin.business.basedata.service;

import com.drelephant.elephantadmin.business.basedata.entity.BdHealthCalendar;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 大象健康日历 服务类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
public interface BdHealthCalendarService extends IService<BdHealthCalendar> {
	void saveBdHealthCalendar(BdHealthCalendar entity);
	void deleteBdHealthCalendar(@Param("id") String id);
}
