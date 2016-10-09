package com.wbl.util;

/**
 * Created by wbl on 16/10/9.
 */
public class KeyUtil {

    public static String getOperateTimesInDayKey(String dataName,String month,String year){
        return dataName + "_" + month + "_" + year + "_in_day_operateTimes";
    }

    public static String getOperateTimesInMonthKey(String dataName,String year){
        return dataName + "_" + year + "_in_month_operateTimes";
    }

    public static String getOperateTimeInYearKey(String dataName){
        return dataName + "_in_year_operateTimes";
    }

    public static String getOperateTimeByTypeKey(String dataName){
        return dataName + "_by_type_operateTimes";
    }

    public static String getHotDataKey(){
        return "hot_data_key";
    }

    public static String getLikeDataKey(String dataName){
        return dataName + "_like_data_key";
    }

    public static String getUserRecommendDataKey(String userName){
        return userName + "_recommend_data_key";
    }
}
