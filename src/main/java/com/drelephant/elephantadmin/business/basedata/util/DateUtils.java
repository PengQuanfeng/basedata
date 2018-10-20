package com.drelephant.elephantadmin.business.basedata.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
	public static void main(String[] args) throws ParseException {
            //1，截取,异常则返回空数组。
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        Date date = s.parse("2018-11-01");
        List<Date> dates = dateToWeek(date);
        dates.forEach(v-> System.out.println(v.getDate()));

    }
	
//	/**
//     * 根据日期获得所在周的日期 
//     * @param mdate
//     * @return
//     */
//    public static List<Date> dateToWeek(Date mdate) {
//        int b = mdate.getDay();
//        Date fdate;
//        List<Date> list = new ArrayList<>();
//        Long fTime = mdate.getTime() - b * 24 * 3600000;
//        for (int a = 1; a <= 7; a++) {
//            fdate = new Date();
//            fdate.setTime(fTime + (a * 24 * 3600000));
//            list.add(a-1, fdate);
//        }
//        return list;
//    }
	
	/**
     * 根据日期获得所在周的日期 
     * @param date
     * @return
     */
    public static List<Date> dateToWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DAY_OF_WEEK);
//        if (day == 1) { // 星期日
//        	c.add(Calendar.DAY_OF_YEAR, -7);
//        } else {
//        	c.add(Calendar.DAY_OF_YEAR, -1 * day + 1);
//        }
        c.add(Calendar.DAY_OF_YEAR, -1 * day);
        //
    	List<Date> list = new ArrayList<>();
    	for (int a = 0; a <= 6; a++) {
    		c.add(Calendar.DAY_OF_YEAR, 1);
    		list.add(c.getTime());
    	}
    	return list;
    }
}
