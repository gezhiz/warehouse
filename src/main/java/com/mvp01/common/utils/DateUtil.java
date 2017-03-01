package com.mvp01.common.utils;

import com.mvp01.common.exception.ParamException;
import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liudx on 16/1/7.
 */
public class DateUtil {
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String HOUR_MINUTE_FORMAT = "HH:mm";
    public static final Date BIG_DATE = DateUtil.parseToDate(DateUtil.getDateByYear(100), DateUtil.DATE_FORMAT);

    public static String getNowDateTime() {
        return format(new Date(), DATETIME_FORMAT);
    }

    public static String getNowDate() {
        return format(new Date(), DATE_FORMAT);
    }

    public static String format(Calendar calendar, String format) {
        return format(calendar.getTime(), format);
    }

    public static String format(Date date, String format) {
        if (StringUtils.isBlank(format)) {
            format = DATETIME_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String format(Long timestamp, String format) {
        Date date = new Date(timestamp);
        return format(date, format);
    }

    public static Date parseToDate(String var, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(var);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Calendar parseToCalendar(String var, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar ca = null;
        try {
            Date var0 = sdf.parse(var);
            ca = Calendar.getInstance();
            ca.setTime(var0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ca;
    }

    public static boolean expired(String date, long sec) {
        Date d = parseToDate(date, DATETIME_FORMAT);
        if (d == null) {
            return true;
        }

        Date now = new Date();
        if (now.getTime() - d.getTime() >= (sec * 1000)) {
            return true;
        }

        return false;
    }

    public static String getDateTimeByDay(int day) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, day);
        String dayDate = sdf.format(c.getTime());
        return dayDate;
    }

    public static String getDateByDay(int day) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, day);
        String dayDate = sdf.format(c.getTime());
        return dayDate;
    }

    public static Date getDateDateByDay(int day) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }

    public static String getDateByYear(int year) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, year);
        String dayDate = sdf.format(c.getTime());
        return dayDate;
    }

    public static Date getDateBeforeMinute(Integer expireMinute) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, expireMinute);
        return c.getTime();
    }

    public static Date getDateBeforeSeconds(Integer expireSeconds) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, expireSeconds);
        return c.getTime();
    }

    public static Date combineDateAndTime(Date date, String time) {
        String dateStr = DateUtil.format(date, DateUtil.DATE_FORMAT) + " " + time;
        return parseToDate(dateStr, DateUtil.DATETIME_FORMAT);
    }

    //获取凌晨日期
    public static Date getBeforeDraw(Date date) {
        String beforeDrawDateStr = DateUtil.format(date, DateUtil.DATE_FORMAT);//凌晨的时间
        beforeDrawDateStr += " 00:00:00";
        return parseToDate(beforeDrawDateStr, DATETIME_FORMAT);
    }

    //计算日期相差小时数
    public static int compareHours(Date first, Date second) {
        long nh = 1000 * 60 * 60;
        long milliseconds = first.getTime() - second.getTime();//相差毫秒数
        return (int) (milliseconds / nh);
    }

    /**
     * 获得起始日期 和 截止日期 区间的所有日期yyyy-MM-dd
     *
     * @param start
     * @param end
     * @return
     */
    public static List<String> getIntervalDateList(String start, String end) {

        if (StringUtils.isEmpty(start)) {
            return null;
        }
        String _start = start;
        String _end = end;
        if (StringUtils.isEmpty(end)) {
            _end = getNowDate();
        }

        List<String> list = new ArrayList<String>();

        try {

            Calendar dayc1 = new GregorianCalendar();
            Calendar dayc2 = new GregorianCalendar();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date daystart = df.parse(_start); //按照yyyy-MM-dd格式转换为日期
            Date dayend = df.parse(_end);
            dayc1.setTime(daystart); //设置calendar的日期
            dayc2.setTime(dayend);

            for (; dayc1.compareTo(dayc2) <= 0; ) {

                String _middle = "";
                int _day = (dayc1.get(Calendar.MONTH) + 1);
                if (_day < 10) {
                    _middle = dayc1.get(Calendar.YEAR) + "-0"
                            + _day + "-"
                            + dayc1.get(Calendar.DATE);
                } else {
                    _middle = dayc1.get(Calendar.YEAR) + "-"
                            + _day + "-"
                            + dayc1.get(Calendar.DATE);
                }

                list.add(_middle);
                dayc1.add(Calendar.DAY_OF_YEAR, 1);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static String switchDateFormat(Date date) {
        Date now = new Date();
        if (date.after(now)) {
            return format(date, "yyyy-MM-dd HH:mm");
        }
        long minute = 60 * 1000;
        long hour = 60 * minute;
        long day = 24 * hour;

        long d = date.getTime();
        long n = now.getTime();
        long diff = n - d;
        if (diff / minute < 60) {
            return diff / minute + "分钟前";
        }
        if (diff / hour < 24) {
            return diff / hour + "小时前";
        }
        if (diff / day <= 7) {
            return diff / day + "天前";
        }
        return format(date, "yyyy-MM-dd HH:mm");

    }

    /**
     * 计算两个日期相差多少分钟
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long getDateMinutes(Date startDate, Date endDate) {
        if (startDate.getTime() > endDate.getTime()) {
            throw new ParamException("日期有误");
        }
        Long second = (endDate.getTime() - startDate.getTime()) / 1000;//秒
        return second / 60;
    }

    /**
     * 计算两个日期相差多少秒
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long getDateSeconds(Date startDate, Date endDate) {
        if (startDate.getTime() > endDate.getTime()) {
            throw new ParamException("日期有误");
        }
        Long second = (endDate.getTime() - startDate.getTime()) / 1000;//秒
        return second;
    }

    /**
     * 计算两个日期相差的时分秒
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Map<String, Long> getDateDisTime(Date startDate, Date endDate) {
        if (startDate.getTime() > endDate.getTime()) {
            throw new ParamException("日期有误");
        }
        Map<String, Long> disTime = new HashMap<>();
        Long second = (endDate.getTime() - startDate.getTime()) / 1000;//秒
        Long hour = 0l;
        Long minute = 0l;
        minute = second / 60;
        second = second % 60;
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        disTime.put("hour", hour);
        disTime.put("minute", minute);
        disTime.put("second", second);
        return disTime;
    }

    /**
     * 计算两个日期时间差距，并获得00：00：00格式的时长
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getDateDisTimeStr(Date startDate, Date endDate) {
        Map<String, Long> disTime = getDateDisTime(startDate, endDate);
        StringBuilder durationStr = new StringBuilder();
        Long second = disTime.get("second");//秒
        String secondStr = "";
        Long minute = disTime.get("minute");//秒
        Long hour = disTime.get("hour");//秒
        if (hour < 10) {
            durationStr.append("0");
        }
        durationStr.append(hour);
        durationStr.append(":");
        if (minute < 10) {
            durationStr.append("0");
        }
        durationStr.append(minute);
        durationStr.append(":");
        if (second < 10) {
            durationStr.append("0");
        }
        durationStr.append(second);
        return durationStr.toString();
    }

    /**
     * 计算本周的起始日期～截止日期 本周一～本周日
     *
     * @return
     */
    public static HashMap<String, String> getCurrentWeeklyStart_End_Date() {

        HashMap<String, String> hashMap = new HashMap<String, String>();

        String startDate = getMondayOfThisWeek();
        String endDate = getSundayOfThisWeek();

        hashMap.put("startDate", startDate);
        hashMap.put("endDate", endDate);

        return hashMap;

    }

    /**
     * 获取本周的某周几的日期
     * @param weekDay 1-7： Monday－Sunday
     * @return
     */
    public static Calendar getWeekDayOfThisWeek(int weekDay){
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + weekDay);
        return c;
    }
    //得到本周周一
    public static String getMondayOfThisWeek() {
        Calendar c = getWeekDayOfThisWeek(1);
        return format(c, DATE_FORMAT);
    }

    //得到本周周日
    public static String getSundayOfThisWeek() {
        Calendar c = getWeekDayOfThisWeek(7);
        return format(c, DATE_FORMAT);
    }


    public static HashMap<String, String> getCurrentMonthStart_End_Date() {

        HashMap<String, String> hashMap = new HashMap<String, String>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMinimum(Calendar.DAY_OF_MONTH));

        String startDate = format(calendar.getTime(), DATE_FORMAT);

        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMaximum(Calendar.DAY_OF_MONTH));
        String endDate = format(calendar.getTime(), DATE_FORMAT);

        hashMap.put("startDate", startDate);
        hashMap.put("endDate", endDate);

        return hashMap;
    }

    public static String formatHour_Minute_Second(Long seconds) {

        String formatResult = "";

        if (null == seconds) {
            formatResult = "00:00:00";
        } else {

            Long hour = seconds / (60 * 60);
            Long leftSeconds = seconds % (60 * 60);
            Long minute = leftSeconds / 60;
            Long second = leftSeconds % 60;

            if (hour >= 10) {

                if (minute >= 10) {
                    if (second >= 10) {
                        formatResult = "" + hour + ":" + minute + ":" + second;
                    } else {
                        formatResult = "" + hour + ":" + minute + ":0" + second;
                    }

                } else {
                    if (second >= 10) {
                        formatResult = "" + hour + ":0" + minute + ":" + second;
                    } else {
                        formatResult = "" + hour + ":0" + minute + ":0" + second;
                    }
                }

            } else {

                if (minute >= 10) {
                    if (second >= 10) {
                        formatResult = "0" + hour + ":" + minute + ":" + second;
                    } else {
                        formatResult = "" + hour + ":" + minute + ":0" + second;
                    }

                } else {
                    if (second >= 10) {
                        formatResult = "0" + hour + ":0" + minute + ":" + second;
                    } else {
                        formatResult = "0" + hour + ":0" + minute + ":0" + second;
                    }
                }

            }


        }

        return formatResult;
    }

    /**
     * 通过秒获得小时单位的数值，所以最小计时为，并保留两位有效数值
     *
     * @param seconds
     * @return
     */
    public static Double getHoursBySeconds(Long seconds) {

        Double rest = (seconds * 1.0d) / (60 * 60);

        return rest;

//        BigDecimal bg = new BigDecimal(rest).setScale(2, RoundingMode.UP);
//        return bg.doubleValue();

//        DecimalFormat df = new DecimalFormat("#.##");
//        return Double.parseDouble(df.format(rest));


    }


    public static void main(String[] arg) {
//
//        List<String> rest = getIntervalDateList("2016-10-12","2016-10-18");
//        System.out.println(rest);

//        System.out.println(new Date(System.currentTimeMillis()));
//            Date date = parseToDate("2017-01-05 15:12:01", DATETIME_FORMAT);
//            System.out.println(switchDateFormat(date));

//        164
//        81
//        65596
//        18326

        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        System.out.println(df2.format(c.getTime()));


    }
}
