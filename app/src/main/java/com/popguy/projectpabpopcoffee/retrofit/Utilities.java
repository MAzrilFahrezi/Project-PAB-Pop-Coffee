package com.popguy.projectpabpopcoffee.retrofit;

import android.content.Context;
import android.content.SharedPreferences;

public class Utilities {

    private static final String PREFERENCE_FILE_KEY = "saveUser";

    public static void clearUser(Context context){
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("xEmail", null);
        editor.apply();
    }


    public static void setValue(Context context, String xPref, String xValue){
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(xPref, xValue);
        editor.commit();
    }

    public static String getValue(Context context, String xPref) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        String xValue = sp.getString(xPref, null);
        return xValue;
    }

    public static boolean checkValue(Context context, String xPref){
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        String xValue = sp.getString(xPref, null);
        return xValue != null;
    }

}
