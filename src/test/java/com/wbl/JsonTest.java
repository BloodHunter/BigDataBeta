package com.wbl;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created withSimple_love
 * Date: 2016/1/14.
 * Time: 10:31
 */
public class JsonTest {
        public static void main(String[] args) {
                JSONObject downloadTimes = new JSONObject();
                downloadTimes.put("name", "download times");
                JSONArray child = new JSONArray();
                child.add(new JSONObject().put("name", 999));
                downloadTimes.put("children", child);
                JSONObject download = new JSONObject();
                download.put("name", "download");
                JSONArray download_children = new JSONArray();
                download_children.add(downloadTimes);
                download_children.add(new JSONObject().put("name", "detail"));

                JSONObject owner = new JSONObject();
                owner.put("name","owner");
                JSONArray owner_children = new JSONArray();
                JSONObject object = new JSONObject();
                object.put("name","platA");
                owner_children.add(object);
                owner.put("children", owner_children);

                JSONObject time = new JSONObject();
                time.put("name","time");
                JSONArray time_children = new JSONArray();
                JSONObject temp = new JSONObject();
                temp.put("name","2014");
                time_children.add(temp);
                time.put("children", time_children);

                JSONObject source = new JSONObject();
                source.put("name", "source");
                JSONArray source_children = new JSONArray();
                source_children.add(owner);
                source_children.add(time);
                source.put("children",source_children);

                JSONObject data = new JSONObject();
                data.put("name", "data1");
                JSONArray data_children = new JSONArray();
                data_children.add(download);
                data_children.add(source);
                data.put("children",data_children);
                System.out.println(data);
        }
}
