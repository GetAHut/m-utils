package com.xyt.utils.common;

import io.micrometer.core.instrument.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 常用Util类
 *      类型转换
 *
 * @Author: abby
 * @Date: 2020/12/28 14:13
 */
public class ConvertUtil {

    public ConvertUtil(){

    }

    /**
     * String转换
     * @param p_vlaue
     * @return
     */
    public static String toString(Object p_vlaue){
        return p_vlaue == null ? "" : p_vlaue.toString();
    }

    /**
     * 取map值
     * @param map
     * @param key
     * @return
     */
    public static String toString(Map<String, Object> map, String key){
        if (map == null){
            return null;
        }else {
            return map.containsKey(key) ? toString(map.get(key)) : null;
        }
    }

    public static String toString(Map<String, Object> map, String  key, String defaultValue){
        if (map == null){
            return defaultValue;
        } else {
            return map.containsKey(key) ? toString(map.get(key)) : defaultValue;
        }
    }

    /**
     * 转换String 且非空 空格
     * @param p_value
     * @param def
     * @return
     */
    public static String toString(Object p_value, String def){
        String ret = p_value == null ? def : p_value.toString().trim();
        return StringUtils.isEmpty(ret) ? def : ret;
    }

    /**
     * 判断为true 或者false
     *       true 、1 、 是 、 on
     * @param str
     * @return
     */
    public static Boolean toBoolean(String str){
        if (str == null){
            return false;
        } else {
            return str.equalsIgnoreCase("true") || str.equalsIgnoreCase("1")
                    || str.equalsIgnoreCase("是") || str.equalsIgnoreCase("on");
        }
    }

    public static Boolean objToBoolean(Object obj){
        return obj == null ? false : toBoolean(obj.toString());
    }

    public static Boolean objToBoolean(Map<String, Object> map, String key){
        if (map == null){
            return false;
        } else {
            return map.containsKey(key) ? objToBoolean(map.get(key)) : false;
        }
    }

    /**
     * 强转成日期格式
     * @param p_value
     * @return
     */
    public static Date objToDate(Object p_value){
        if (p_value == null){
            return null;
        } else if (p_value instanceof Date){
            return (Date)p_value;
        } else {
            try {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(p_value.toString());
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * 日期转字符串
     * @param p_value
     * @param format  格式
     * @return
     */
    public static String dateToString(Date p_value, String format){
        return p_value == null ? "" : new SimpleDateFormat(format).format(p_value);
    }

    public static String dataToString(Date p_value){
        return dateToString(p_value, "yyyy_MM-dd HH:mm:ss");
    }

    public static Date objToDate(Map<String, Object> map, String key){
        if (map == null){
            return null;
        } else {
            return map.containsKey(key) ? objToDate(map.get(key)) : null;
        }
    }

    public static String toDateString(Map<String, Object> map, String key){
        if (map == null){
            return null;
        } else {
            return map.containsKey(key) ? dateToString(objToDate(map.get(key)), "yyyy-MM-dd") : null;
        }
    }

    public static Object toDate(String strDate){
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object toDate(String strDate, String format){
        try {
            return new SimpleDateFormat(format).parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toDateTime(String strDate, String format){
        if (strDate == null){
            return null;
        } else {
            try {
                Date dt = new SimpleDateFormat(format).parse(strDate);
                return dataToString(dt);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }

        }
    }

    /**
     * 整型
     * @param p_value
     * @return
     */
    public static Integer toInt(Object p_value){
        return p_value != null && StringUtils.isBlank(p_value.toString()) ? Integer.parseInt(p_value.toString()) : 0;
    }

    public static Integer toInt(Map<String, Object> map, String key){
        if (map == null){
            return 0;
        } else {
            return map.containsKey(key) ? toInt(map.get(key)) : 0;
        }

    }

    /**
     * 浮点型
     * @param p_value
     * @return
     */
    public static Float toFloat(Object p_value){
        return p_value != null && StringUtils.isBlank(p_value.toString()) ? Float.parseFloat(p_value.toString()) : 0.0F;
    }

    public static Float toFloat(Map<String, Object> map, String key){
        if (map == null){
            return 0.0F;
        } else {
            return map.containsKey(key) ? toFloat(map.get(key)) : 0.0F;
        }
    }

    /**
     * Double类型转换
     * @param p_value
     * @return
     */
    public static Double toDouble(Object p_value){
        return p_value != null && StringUtils.isBlank(p_value.toString()) ? Double.parseDouble(p_value.toString()) : 0.0D;
    }

    public static Double toDouble(Map<String, Object> map, String key){
        if (map == null){
            return 0.0D;
        } else {
            return map.containsKey(key) ? toDouble(map.get(key)) : 0.0D;
        }
    }

    /**
     *  Long类型转换
     * @param p_value
     * @return
     */
    public static Long toLong(Object p_value){
        return p_value == null ? 0L : Long.parseLong(p_value.toString());
    }

    public static Long toLong(Map<String, Object> map, String key){
        if (map == null){
            return 0L;
        } else {
            return map.containsKey(key) ? toLong(map.get(key)) : 0L;
        }
    }

    /**
     *  clob转文本
     * @param clob
     * @return
     */
    public static String clob2ToString(Clob clob){
        String result = null;

        try {
            Reader cs = clob.getCharacterStream();
            BufferedReader reader = new BufferedReader(cs);
            String line = reader.readLine();

            StringBuffer sb;

            for (sb = new StringBuffer(); line != null; line = reader.readLine()){
                sb.append(line);
            }
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }



}
