package com.boce.flcp.util;

import com.google.common.collect.Maps;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import net.sf.cglib.beans.BeanMap;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.awt.SystemColor.info;

public class CommonUtils {
    /**利用MD5进行加密
     * @param str  待加密的字符串
     * @return  加密后的字符串
     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException
     */
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    /**判断用户密码是否正确
    * @param newpasswd  用户输入的密码
     * @param oldpasswd  数据库中存储的密码－－用户密码的摘要
    * @return
    * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static boolean checkpassword(String newpasswd,String oldpasswd) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        if(EncoderByMd5(newpasswd).equals(oldpasswd))
        return true;
        else
        return false;
    }

    /**
     * @Title: isBlank
     * @Description: TODO(判断为空)
     * @author Tangxu
     * @param str
     * @return
     * @throws
     */
    public static boolean isBlank(String str) {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0))
            return true;
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @Title: isNotBlank
     * @Description: TODO(判断不为空)
     * @author Tangxu
     * @param str
     * @return
     * @throws
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * @Title: getDate
     * @Description: TODO(获取当前时间指定输出格式)
     * @Author xulovehua
     * @Date 2017/11/22 13:45
     * @Param [format]
     * @return java.lang.String
     */
    public static String getDate(String format){
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(new Date());
    }

    /**
     * @Title: getTwoDay
     * @Description: TODO(间隔时间)
     * @Author xulovehua
     * @Date 2017/12/14 14:13
     * @Param [sj1, sj2]
     * @return java.lang.String
     */
    public static String getTwoIntervalTime(String begin) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String interval_time ="";
        try {
            java.util.Date beginDate = myFormatter.parse(begin);
            long day = (new Date().getTime() - beginDate.getTime()) / (1000);
            long day1=day/(24*3600);
            long hour1=day%(24*3600)/3600;
            long minute1=day%3600/60;
            interval_time = day1+"天"+hour1+"小时"+minute1+"分";
        } catch (Exception e) {
            return "";
        }
        return interval_time;
    }

    public static List<Date> getBetweenDates(Date start, Date end) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        tempStart.add(Calendar.DAY_OF_YEAR, 1);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        while (tempStart.before(tempEnd)) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }

    /**
     * @Title: getBetweenDates
     * @Description: TODO (计算两个日期之间的日期，根据指定格式输出)
     * @Author xulovehua
     * @Date 2017/12/21 16:31
     * @Param [start, end]
     * @return java.util.List<java.lang.String>
     */
    public static List<String> getBetweenDatesAndPrefix(String start,String end,String format,String prefix){ //
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sd = new SimpleDateFormat(format);
        List<String> list = new ArrayList<String>(); //保存日期集合
        try {
            Date date_start = sdf.parse(start);
            Date date_end = sdf.parse(end);
            Date date =date_start;
            Calendar cd = Calendar.getInstance();//用Calendar 进行日期比较判断
            while (date.getTime()<=date_end.getTime()){
                list.add(prefix+sd.format(date));
                cd.setTime(date);
                cd.add(Calendar.DATE, 1);//增加一天 放入集合
                date=cd.getTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @Title: beanToMap
     * @Description: TODO
     * @Author xulovehua
     * @Date 2017/12/25 10:35
     * @Param [bean]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key+"", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * @Title: getPercentage
     * @Description: TODO  (获取两个数之间的百分比)
     * @Author xulovehua
     * @Date 2017/12/25 14:41
     * @Param [num1, num2]
     * @return java.lang.String
     */
    public static String getPercentage(int num1,int num2){
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        return numberFormat.format((float)num1/num2);
    }

    public static void main(String[] args) {
//        System.out.println(getTwoIntervalTime("2017-11-27 12:59:52"));
//        LinkedList linkList = new LinkedList();
//        linkList.add("1");
//        System.out.println(linkList.getFirst()+"---"+linkList.getLast());
//        System.out.println(getDate("yyyyMMdd"));
//        System.out.println(new BigDecimal("1000.00"));
        int num1 = 0;
        int num2 = 5;
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((float) num1 / (float) num2 * 100);
        System.out.println("num1和num2的百分比为:" + getPercentage(1,3) );

    }

}
