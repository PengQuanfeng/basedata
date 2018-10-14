package com.drelephant.elephantadmin.business.basedata.service.impl;

import com.drelephant.elephantadmin.business.basedata.entity.BdHealthCalendar;
import com.drelephant.elephantadmin.business.basedata.mapper.BdHealthCalendarMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdHealthCalendarService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 大象健康日历 服务实现类
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Service
public class BdHealthCalendarServiceImpl extends ServiceImpl<BdHealthCalendarMapper, BdHealthCalendar> implements BdHealthCalendarService {
	@Autowired
	BdHealthCalendarMapper bdHealthCalendarMapper;
	@Override
	@Transactional
	public void saveBdHealthCalendar(BdHealthCalendar entity) {
		bdHealthCalendarMapper.saveBdHealthCalendar(entity);		
	}

	@Override
	@Transactional
	public void deleteBdHealthCalendar(String id) {
		bdHealthCalendarMapper.deleteHealthCalendarById(id);		
	}
	
}
