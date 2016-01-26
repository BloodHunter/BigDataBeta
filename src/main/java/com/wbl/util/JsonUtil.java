package com.wbl.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created withSimple_love
 * Date: 2016/1/14.
 * Time: 11:26
 */
public class JsonUtil {
        private static final String NAME = "name";
        private static final String CHILDREN = "children";
        private static final String DOWNLOAD = "download";
        private static final String DOWNLOAD_TIMES = "download times";
        private static final String SOURCE = "source";
        private static final String DETAIL = "detail";
        private static final String OWNER = "owner";
        private static final String TIME = "time";

        public static JSONObject makeDataDetailJson(String dataName,long downloadTimes,String owner,String time){
                JSONObject dataJson = new JSONObject();
                dataJson.put(NAME, dataName);
                JSONArray children = new JSONArray();
                children.add(getDownloadJson(downloadTimes));
                children.add(getSourceJson(owner,time));
                dataJson.put(CHILDREN,children);
                return dataJson;
        }

        private static JSONObject getDownloadJson(long downloadTimes){
                JSONObject download = new JSONObject();
                download.put(NAME, DOWNLOAD);
                JSONArray children = new JSONArray();
                children.add(getDownloadTimesJson(downloadTimes));
                children.add(getDetailJson());
                download.put(CHILDREN,children);
                return download;
        }

        private static JSONObject getDownloadTimesJson(long downloadTimes){
                JSONObject downloadTimesJson = new JSONObject();
                downloadTimesJson.put(NAME, DOWNLOAD_TIMES);
                JSONArray children = new JSONArray();
                children.add(getTimesJson(downloadTimes));
                downloadTimesJson.put(CHILDREN, children);
                return downloadTimesJson;
        }

        private static JSONObject getTimesJson(long downloadTimes){
                JSONObject object = new JSONObject();
                object.put(NAME,downloadTimes);
                return object;
        }

        private static JSONObject getDetailJson(){
                JSONObject object = new JSONObject();
                object.put(NAME,DETAIL);
                return object;
        }

        private static JSONObject getSourceJson(String owner,String time){
                JSONObject source = new JSONObject();
                source.put(NAME, SOURCE);
                JSONArray children = new JSONArray();
                children.add(getOwenrJson(owner));
                children.add(getTimeJson(time));
                source.put(CHILDREN,children);
                return source;
        }

        private static JSONObject getOwenrJson(String owner){
                JSONObject ownerJson = new JSONObject();
                ownerJson.put(NAME, OWNER);
                JSONArray children = new JSONArray();
                children.add(getOwner(owner));
                ownerJson.put(CHILDREN,children);
                return ownerJson;
        }

        private static JSONObject getOwner(String owner){
                JSONObject object = new JSONObject();
                object.put(NAME,owner);
                return object;
        }

        private static JSONObject getTimeJson(String time){
                JSONObject timeJson = new JSONObject();
                timeJson.put(NAME, TIME);
                JSONArray children = new JSONArray();
                children.add(getTime(time));
                timeJson.put(CHILDREN,children);
                return timeJson;
        }

        private static JSONObject getTime(String time){
                JSONObject object = new JSONObject();
                object.put(NAME,time);
                return object;
        }

        public static void main(String[] args) {
                System.out.println(makeDataDetailJson("data1",999,"platA","2016-1-1"));
        }
}
