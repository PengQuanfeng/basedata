package com.drelephant.elephantadmin.business.basedata.util;

import com.drelephant.elephantadmin.business.basedata.entity.BdHealthCalendar;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Nullable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class DateUtils {
	public static void main(String[] args) throws ParseException {
            //1，截取,异常则返回空数组。
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        Date date = s.parse("2018-10-14");
        List<Date> dates = dateToWeek(date);
        dates.forEach(v-> System.out.println(v.getDate()));

    }
	/**
     * 根据日期获得所在周的日期 
     * @param mdate
     * @return
     */
    public static List<Date> dateToWeek(Date mdate) {
        int b = mdate.getDay();
        Date fdate;
        List<Date> list = new ArrayList<>();
        Long fTime = mdate.getTime() - b * 24 * 3600000;
        for (int a = 1; a <= 7; a++) {
            fdate = new Date();
            fdate.setTime(fTime + (a * 24 * 3600000));
            list.add(a-1, fdate);
        }
        return list;
    }
}
