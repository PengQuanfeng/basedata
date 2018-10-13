package com.drelephant.elephantadmin.business.basedata.mapper;

import com.drelephant.elephantadmin.business.basedata.entity.BdHealthCalendar;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
  * 大象健康日历 Mapper 接口
 * </p>
 *
 * @author com.drelephant
 * @since 2018-10-09
 */
@Mapper
public interface BdHealthCalendarMapper extends BaseMapper<BdHealthCalendar> {	
	List<BdHealthCalendar> bdHealthCalendar();
}