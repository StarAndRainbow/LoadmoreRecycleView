package com.sanhen.www.learnkotlin.recycleview.test;

import android.text.TextUtils;

import java.util.List;
import java.util.Map;

public class EmptyUtil {

    /**
     * 判断字符串是否为空
     *
     * @param str 检查的字符串
     * @return true 为空（null，字符串长度为 0），false 不为空。
     */
    public static boolean isEmpty(CharSequence str) {
        return TextUtils.isEmpty(str);
    }


    /**
     * 判断字符串数组是否为空
     *
     * @param str 检查的字符串数组
     * @return true 为空（null，字符串长度为 0），false 不为空。
     */
    public static boolean isEmpty(String[] str) {
        return str==null||str.length<=0;
    }




    /**
     * 判断Map集合是否为空
     *
     * @param map 检查的对象
     * @return true 为空（null，Map Size 长度为 0），false 不为空。
     */
    public static boolean isEmpty(Map map) {
        return map==null||map.isEmpty();
    }

    /**
     * 判断List集合是否为空
     *
     * @param list 检查的对象
     * @return true 为空（null，Map Size 长度为 0），false 不为空。
     */
    public static boolean isEmpty(List list) {
        return list==null||list.isEmpty();

    }
    public  static String checkString(String arg){
        if (!TextUtils.isEmpty(arg)){
            return arg;
        }
        return "";
    }
    public  static String checkString(String arg,String callback){
        if (!TextUtils.isEmpty(arg)){
            return arg;
        }
        return callback;
    }

}

