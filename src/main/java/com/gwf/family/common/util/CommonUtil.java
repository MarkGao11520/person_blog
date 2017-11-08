package com.gwf.family.common.util;

/**
 * Created by gaowenfeng on 2017/11/7.
 */
public class CommonUtil {

    public static String[] spiltString(String source,String spilt){
        String[] ids = null;
        if(source.indexOf(spilt)==-1){
            ids = new String[1];
            ids[0] = source;
        }else
            ids = source.split(spilt);
        return ids;
    }
}
