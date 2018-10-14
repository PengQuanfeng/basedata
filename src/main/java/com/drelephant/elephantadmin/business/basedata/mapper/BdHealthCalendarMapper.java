package com.drelephant.elephantadmin.business.basedata.mapper;

import com.drelephant.elephantadmin.business.basedata.entity.BdHealthCalendar;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
	/**
	 * 根据传入的日期获取当周
	 * @param page
	 * @param id
	 * @return
	 */
	List<BdHealthCalendar> bdHealthCalendar(Pagination page, @Param("id") String id);

	/**
	 * 根据id删除图片
	 * @param id
	 * @return
	 */
	int deleteHealthCalendarById(@Param("id") String id);
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	int saveBdHealthCalendar(BdHealthCalendar entity);
}