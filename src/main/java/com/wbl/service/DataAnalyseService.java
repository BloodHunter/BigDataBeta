package com.wbl.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created with Simple_love
 * Date: 2016/3/30.
 * Time: 9:00
 */
public interface DataAnalyseService {
        JSONArray getDataOperateTimesInDay(String dataName,String month,String year);
        JSONArray getDataOperateTimesInMonth(String dataName,String year);
        JSONArray getDataOperateTimesInYear(String dataName);
        JSONArray getDataOperateTimesByType(String dataName);

        JSONObject getHotData();
        JSONObject getLikeData(String dataName);

        JSONObject getUserRecommendData(String userName);
}
