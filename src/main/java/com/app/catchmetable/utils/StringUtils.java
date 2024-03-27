package com.app.catchmetable.utils;

public class StringUtils {

    public static boolean isEmpty(String s){
        return s == null || s.length() == 0;
    }
    public static boolean isBlank(String s){
        return isEmpty(s) || s.trim().isBlank();
    }
    public static String nvl(Object o,String replace){
        if(o==null){
            return replace;
        }
        return o.toString();
    }

}
