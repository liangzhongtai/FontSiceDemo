package com.lzt.fontsicedemo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by liangzhongtai on 2017/8/31.
 */
public class SPUtil {
    public final static int FONT_SMALL = 1;
    public final static int FONT_NORMAL = 0;
    public final static int FONT_BIG =2;
    private static SharedPreferences sp;
    public final static String APP_FONT_SIZE = "app_font_sice";
    public static int getInt(Context context, String key,int defValue){
        sp = getSP(context);
        return sp.getInt(key, defValue);
    }

    public static void setInt(Context context,String key,int value){
        sp = getSP(context);
        sp.edit().putInt(key, value).commit();
    }

    private static SharedPreferences getSP(Context context){
        if(sp==null){
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
            return sp;
        }else{
            return sp;
        }
    }
}
