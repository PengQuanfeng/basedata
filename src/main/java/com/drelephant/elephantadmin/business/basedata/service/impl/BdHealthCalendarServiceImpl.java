package com.drelephant.elephantadmin.business.basedata.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.drelephant.elephantadmin.business.basedata.entity.BdHealthCalendar;
import com.drelephant.elephantadmin.business.basedata.mapper.BdHealthCalendarMapper;
import com.drelephant.elephantadmin.business.basedata.service.BdHealthCalendarService;
import com.drelephant.elephantadmin.business.basedata.util.Constans;
import com.drelephant.elephantadmin.business.basedata.util.DateUtils;

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
    public void saveBdHealthCalendar(BdHealthCalendar entity) {
    	BdHealthCalendar con = new BdHealthCalendar();
    	con.setPublishTime(entity.getPublishTime());
    	BdHealthCalendar oldBdHealthCalendar = bdHealthCalendarMapper.selectOne(con);
    	if (oldBdHealthCalendar == null) {
    		entity.setStatus(Constans.ACTIVE);
            bdHealthCalendarMapper.saveBdHealthCalendar(entity); // 新增
    	} else {
    		oldBdHealthCalendar.setPublishTime(entity.getPublishTime());
    		//
    		Condition con0 = Condition.create();
    		con0.eq("id", oldBdHealthCalendar.getId());
			//
    		oldBdHealthCalendar.setStatus(Constans.ACTIVE);
    		oldBdHealthCalendar.setContentPicId(entity.getContentPicId());
    		oldBdHealthCalendar.setTitle(entity.getTitle());
    		update(oldBdHealthCalendar, con0); // 更新
    	}
    }

    @Override
    public boolean deleteBdHealthCalendar(String id) {    	
        BdHealthCalendar item = new BdHealthCalendar();
        item.setStatus(Constans.DELETED);
		Condition con = Condition.create();
		con.eq("id", id);
		return update(item, con);
    }

    @Override
    public List<BdHealthCalendar> selectListWeek(@Nullable String dateStr) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = s.parse(dateStr);
        } catch (Exception e) {
            date = new Date();
        }
        //todo 根据时间 ，得出一周的时间范围。
        //todo dateToWeek() 方法需要完善.日期范围不准确
        List<Date> dateList = DateUtils.dateToWeek(date);
        String start = s.format(dateList.get(0));
        String end = s.format(dateList.get(dateList.size() - 1));
        //2.select
        List<BdHealthCalendar> dbList = getListBetween(start, end);
        //3.数据封装
        List<BdHealthCalendar> resultList = new ArrayList<BdHealthCalendar>();
        BdHealthCalendar data;
        for (Date date1 : dateList) {
            //比较, date1的时间和 返回结果中的数据的时间相同
            Optional<BdHealthCalendar> firstEntity = dbList.stream().filter(v -> StringUtils.equals(s.format(v.getPublishTime()), s.format(date1))).findFirst();
            //匹配则取实体, 无则new实体
            if (firstEntity.isPresent()) {
                resultList.add(firstEntity.get());
            } else {
                data = new BdHealthCalendar();
                data.setPublishTime(date1);
                resultList.add(data);
            }
        }
        return resultList;
    }

    @Override
    public List<String> selectListMonth(@Nullable String dateStr) {
        //1，截取,异常则返回空数组。
        int year, month;
        try {
            if (StringUtils.isBlank(dateStr)) {
                LocalDate now = LocalDate.now();
                year = now.getYear();
                month = now.getMonth().getValue();
            } else {
                year = Integer.parseInt(StringUtils.substring(dateStr, 0, 4));
                month = Integer.parseInt(StringUtils.substring(dateStr, 5, 7));
            }
        } catch (Exception e) {
            return Collections.emptyList();
        }
        //2. 如果传入是十月， 则 ，应当查询的是上月20号 ~下月10号
        String start;
        String end;
        if (month == 1) {
            start = (year - 1) + "-12-20";
            end = year + "-" + (month + 1) + "-" + 10;

        } else if (month == 12) {
            start = year + "-" + (month - 1) + "-" + 20;
            end = (year + 1) + "-01-10";
        } else {
            start = year + "-" + (month - 1) + "-" + 20;
            end = year + "-" + (month + 1) + "-" + 10;
        }
        //3.select
        List<BdHealthCalendar> listBetween = getListBetween(start, end);
        List<String> hasDateList = new ArrayList<String>();
        SimpleDateFormat s = new SimpleDateFormat("y-M-d");
        listBetween.forEach(v -> {
            hasDateList.add(s.format(v.getPublishTime()));
        });
        return hasDateList;
    }

    /**
     * getListBetween start-end time str
     *
     * @param start
     * @param end
     * @return
     */
    private List<BdHealthCalendar> getListBetween(@Nonnull String start, @Nonnull String end) {
        Condition condition = Condition.create();
        final String delete = "DEL";
        condition.between("publishTime", start, end).where("`status`!={0}", delete).orderBy("publishTime");
        return selectList(condition);
    }


}
