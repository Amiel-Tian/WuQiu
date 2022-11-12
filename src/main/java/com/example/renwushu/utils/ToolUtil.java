package com.example.renwushu.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ToolUtil {
    // 获取指定年指定月的开始天数和结束天数
    public static Map<String,String> getFirstDayAndLastDayOfTheSpecifiedMonth(int year, int month) {
        // 获取当前分区的日历信息(这里可以使用参数指定时区)
        Calendar calendar = Calendar.getInstance();
        // 设置年
        calendar.set(Calendar.YEAR, year);
        // 设置月，月份从0开始
        calendar.set(Calendar.MONTH, month - 1);
        // 设置为指定月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.SECOND,0); //这是将当天的【秒】设置为0
        calendar.set(Calendar.MINUTE,0); //这是将当天的【分】设置为0
        calendar.set(Calendar.HOUR_OF_DAY,0); //这是将当天的【时】设置为0
        // 获取指定月第一天的时间
        Date start = calendar.getTime();
        // 设置日历天数为当前月实际天数的最大值，即指定月份的最后一天
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        calendar.set(Calendar.SECOND,59); //这是将当天的【秒】设置为0
        calendar.set(Calendar.MINUTE,59); //这是将当天的【分】设置为0
        calendar.set(Calendar.HOUR_OF_DAY,23); //这是将当天的【时】设置为0
        // 获取最后一天的时间
        Date end = calendar.getTime();
        //定义字符串所要格式化数据的格式：yyyy-MM-dd HH:mm:ss
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        //创建对象sdf，将格式放入new中初始化，既sdf对象按照yyyy-MM-dd HH:mm:ss对数据进行格式化
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        //sdf对象使用format方法对数据date进行格式化，字符串类型转StringBuffer类型的数据
        // 设置返回信息,返回样式根据需求自行格式化
        Map<String,String> dateMap = new HashMap<>();
        dateMap.put("start",sdf.format(start));
        dateMap.put("end",sdf.format(end));
        return dateMap;
    }

}
