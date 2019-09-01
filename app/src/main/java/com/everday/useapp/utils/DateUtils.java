package com.everday.useapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/1
 * description: 日期工具类
 */
public class DateUtils {
    private static String FORMAT_TYPE = "yyyy-MM-dd";
    // 获取当前时间
    public static String getNowData(String time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_TYPE);// HH:mm:ss
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            date = new Date();
        }
        return new SimpleDateFormat("yyyy/MM/dd").format(date);
    }

    // 获取当前时间
    public static String[] getNowData(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        simpleDateFormat.format(date);
        String time = simpleDateFormat.format(date);
        String[] dates = time.split(" ");
        return dates;
    }

    public static Date stringToDate(String time) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpledateformat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 根据日期转换long
     * @param time
     * @return
     */
    public static long stringToLong(String time) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpledateformat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
    public static long stringToLong(String time, String sim) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat(sim);
        Date date = null;
        try {
            date = simpledateformat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 根据日期long转换指定日期格式
     * @param time
     * @return
     */
    public static String getLongToString(long time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月");
        Date d = new Date(time);
        return sf.format(d);
    }
    public static String getLongToString(String type, long time) {
        SimpleDateFormat sf = new SimpleDateFormat(type);
        Date d = new Date(time);
        return sf.format(d);
    }
    public static String getLongToString(SimpleDateFormat sf, long time) {
        Date d = new Date(time);
        return sf.format(d);
    }
    /**
     * Date转换String
     * @param time
     * @return
     */
    public static String DateToString(Date time) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = null;
        try {
            date = simpledateformat.format(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 根据日期获取当月第几天
     * @param date
     * @return
     */
    public static String getDay(String date){
        String return_day = "" ;
        try {
            String s [] = date.split("-") ;
            String mday = s[2] ;
            if(mday.startsWith("0")){
                return_day = mday.substring(1) ;
            }else{
                return_day = mday ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(return_day+"这是转换后的日期");
        return return_day ;
    }

    /**
     * String 转换 Date
     * @param date
     * @return
     */
    public static Date getDate(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date format_date = null;
        try {
            format_date = sdf.parse(date);// 将字符串转换为日期
        } catch (Exception e) {
            System.out.println("输入的日期格式不合理！");
        }
        return format_date;
    }

    /**
     * 转换日期格式
     * @param date
     * @return
     */
    public static String getStringDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat msdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date format_date = null;
        String mdate = "";
        try {
            format_date = sdf.parse(date);// 将字符串转换为日期
            mdate = msdf.format(format_date);
        } catch (Exception e) {
            System.out.println("输入的日期格式不合理！");
        }
        return mdate;
    }
    public static String getStringDate(String date, String simp) {
        SimpleDateFormat sdf = new SimpleDateFormat(simp);
        Date format_date = null;
        String mdate = "";
        try {
            format_date = sdf.parse(date);
            mdate = sdf.format(format_date);
        } catch (Exception e) {
            mdate = sdf.format(new Date());
        }
        return mdate;
    }
    public static String getStringDate2(String date) {

        // Date currentTime = new Date();
        // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd
        // HH:mm:ss");
        // String dateString = formatter.format(currentTime);
        // String hour;
        // hour = dateString.substring(11, 13);
        // return hour;
        //
        //
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date format_date = null;
        String mdate = "";
        try {
            format_date = sdf.parse(date);// 将字符串转换为日期
            mdate = format_date.getMonth() + "月" + format_date.getDay() + "日";
        } catch (Exception e) {
            System.out.println("输入的日期格式不合理！");
        }
        return mdate;
    }

    // 根据日期取得星期几
    public static String getWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(date);
        return week;
    }

    // 取得某个月有多少天
    public static int getDaysOfMonth(Calendar calendar, int year, int month) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int days_of_month = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return days_of_month;
    }

    // 取得某个月有多少天
    public static int getDaysOfMonth() {
        int days_of_month = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
        return days_of_month;
    }

    // 取得日期是一周的第几天
    public static int getDaysOfWeek(Calendar calendar, int year, int month, int day) {
        calendar.setTime(getDate(year + "-" + month + "-" + day));
        int days_of_week = calendar.get(Calendar.DAY_OF_WEEK);
        return days_of_week;
    }

    // 取得日期是一周的第几天
    public static int getDaysOfWeek() {
        int days_of_week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        return days_of_week;
    }
    // 取得日期是一周的第几天
    public static int getDaysOfWeek(Calendar calendar) {
        int days_of_week = calendar.get(Calendar.DAY_OF_WEEK);
        return days_of_week;
    }

    public static int getMonth(Calendar calendar) {
        int month = calendar.get(Calendar.MONTH) + 1;
        return month;
    }

    public static int getYear(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    public static int getDay(Calendar calendar) {
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        return days;
    }

    public static String getNextDay(int days) {
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
        // 得到当前时间，+3天
        rightNow.add(Calendar.DAY_OF_MONTH, days);
        String date = sim.format(rightNow.getTime());
        return date;

    }

    public static String getNextDay2(int days) {
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-M-d");
        // 得到当前时间，+3天
        rightNow.add(Calendar.DAY_OF_MONTH, days);
        String date = sim.format(rightNow.getTime());
        return date;

    }

    public static String getNextDayofDate(int days) {
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat sim = new SimpleDateFormat("d");
        rightNow.add(Calendar.DAY_OF_MONTH, days);
        String date = sim.format(rightNow.getTime());

        return date;

    }

    public static boolean getNext7DayisCurrentMonth() {
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat sim = new SimpleDateFormat("m");
        rightNow.add(Calendar.DAY_OF_MONTH, 7);
        int currenMonth = Calendar.getInstance().get(Calendar.MONTH);

        return Calendar.getInstance().get(Calendar.MONTH) == rightNow.get(Calendar.MONTH) ? true : false;

    }

    public static int string2Day(String time) {
        return Integer.parseInt(time.substring(8));
    }

    public static String getWeekday(String date) {// 必须yyyy-MM-dd
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdw = new SimpleDateFormat("E");
        Date d = null;
        try {
            d = sd.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdw.format(d);
    }
    /**
     * 获取当前日期是星期几<br>
     *
     * @param
     * @return 当前日期是星期几
     */
    public static String getWeekDayString(){
        String weekString = "";
        final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        weekString = dayNames[dayOfWeek - 1];
        return weekString;
    }
    /**
     * 获取当前日期是星期几<br>
     *
     * @param time
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        Date date = null;
        int w = 0;
        try {
            date = sdf.parse(time);
            cal.setTime(date);
            w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0)
                w = 0;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return weekDays[w];
    }
    /**
     * 获取当前日期
     * @return
     */
    public static String getDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * 当前日期
     * @return
     */
    public static long getCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        try {
            Date parse = dateFormat.parse(dateFormat.format(new Date()));
            return parse.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getDateOnlyWeek(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getCurrentDate(String type){
        SimpleDateFormat dateFormat = new SimpleDateFormat(type);
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * 获取当前日期的前一天时间
     * @return
     */
    public static String getYesterday(String sim, int amount){
        SimpleDateFormat sdf=new SimpleDateFormat(sim);
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        date = calendar.getTime();
        return sdf.format(date);
    }

    /**
     * 获取当前日期的明天时间
     * @return
     */
    public static String getAcquired(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        date = calendar.getTime();
        return sdf.format(date);
    }


    /**
     * 开始是否早于结束
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean compareDate(String startTime, String endTime) {
        long start = stringToLong(startTime, FORMAT_TYPE);
        long end = stringToLong(endTime, FORMAT_TYPE);
        return start <= end;
    }

    /**
     * 根据当前日期获取之前n年,或者之后n年
     * @param time
     * @param year
     * @return
     */
    public static String beforeOrafterDate(String time, int year) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //得到日历
        Calendar calendar = Calendar.getInstance();
        //把当前时间赋给日历
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        //得到n前的时间
        Date before7days = calendar.getTime();
        return sdf.format(before7days);
    }

    /**
     * 校验time日期是否大于或等于now
     * @param time
     * @param now
     * @return
     */
    public static boolean belongDate(String time, String now) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentdate = null;
        Date date = null;
        try {
            currentdate = sdf.parse(time);
            date = sdf.parse(now);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (currentdate.getTime() >= date.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean belongDate1(String time, String now) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentdate = null;
        Date date = null;
        try {
            currentdate = sdf.parse(time);
            date = sdf.parse(now);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (currentdate.getTime() <= date.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2){
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++) {
                if(i%4==0 && i%100!=0 || i%400==0){
                    timeDistance += 366;
                }else{
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2-day1) ;
        }else{
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }
}
