package com.gdut.artificialsystem.util;

public class StringUtil {

    public static boolean isEmptyStr(String str){
        if(str==null)
            return true;
        else if(str.equals(""))
            return true;
        else
            return false;
    }

    public static boolean notDigitalStr(String str){

        try {
            int num=Integer.valueOf(str);
            return false;
        }catch (Exception e){
            return true;
        }
    }
}
