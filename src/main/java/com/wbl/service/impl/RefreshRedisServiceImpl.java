package com.wbl.service.impl;

import com.wbl.dao.RedisClient;
import com.wbl.service.RefreshRedisService;
import com.wbl.util.KeyUtil;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by wbl on 16/10/9.
 */
public class RefreshRedisServiceImpl implements RefreshRedisService {

    private static Logger logger = Logger.getLogger(RefreshRedisServiceImpl.class);

    @Resource
    private RedisClient redisClient;

    private int hour = 23;
    private int minute = 0;
    private int second = 0;

    private long schedulePeriod = 24 * 60 * 60 * 1000;

    @Override
    public void init(){
        Date date = getRefreshTime(hour,minute,second);
        try {
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    refreshDataOperateTimes();
                    refreshHotData();
                    refreshLikeData();
                    refreshRecommendData();
                }
            };
            Timer timer = new Timer();
            timer.schedule(task,date,schedulePeriod);
            logger.info("refresh data service start");
        }catch (Exception e){
            logger.error("refresh redis data fail");
        }
    }

    private Date getRefreshTime(int hour,int minute,int second){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,second);
        return calendar.getTime();
    }

    @Override
    public void refreshDataOperateTimes() {
        String pattern = "*_operateTimes";
        Set<String> keys = redisClient.keys(pattern);
        redisClient.del(keys);
    }


    @Override
    public void refreshHotData() {
        String key = "hot_data_key";
        redisClient.del(key);
    }

    @Override
    public void refreshLikeData() {
        String pattern = "*_like_data_key";
        Set<String> keys = redisClient.keys(pattern);
        redisClient.del(keys);
    }

    @Override
    public void refreshRecommendData() {
        String pattern = "*_recommend_data_key";
        Set<String> keys = redisClient.keys(pattern);
        redisClient.del(keys);
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}
