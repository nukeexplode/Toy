package com.julio.utils;

/** 自定义工具类
 * @author Julio
 * @date 2020/11/19-21:13
 **/
public class Jutils {

    /** 对任意字符串判断其是否为空值null或空串
     * @param str
     * @return boolean
     */
    public static boolean nullOrEmpty(String str){
        if (str == null || "".equals(str) || str.length() == 0){
            return true;
        } else{
            return false;
        }
    }

    /**将两个字符串数组拼接成一个数组
     * @param str1,str2
     * @return str[]
     */
    public static String[] mergeArray(String[] str1,String[] str2){
        if (str1 != null && str2 !=null){
            String[] str3 = new String[str1.length+str2.length];
            System.arraycopy(str1,0,str3,0,str1.length);//源数组，源数组起始位、目标数组、目标数组起始位、拷贝个数
            System.arraycopy(str2,0,str3,str1.length,str2.length);
            return str3;
        }else{
            return null;
        }
    }
}
