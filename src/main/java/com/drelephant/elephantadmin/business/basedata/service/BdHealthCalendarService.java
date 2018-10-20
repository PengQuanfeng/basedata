package com.drelephant.elephantadmin.business.basedata.service;

import java.util.List;

import javax.annotation.Nullable;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.service.IService;
import com.drelephant.elephantadmin.business.basedata.entity.BdHealthCalendar;

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

    boolean deleteBdHealthCalendar(@Param("id") String id);


    /**
     * selectListWeek
     *
     * @param dateStr dateStr
     * @return list
     */
    List<BdHealthCalendar> selectListWeek(@Nullable String dateStr);

    /**
     * selectListMonth
     *
     * @param dateStr dateStr
     * @return list
     */
    List<String> selectListMonth(@Nullable  String dateStr);
}
