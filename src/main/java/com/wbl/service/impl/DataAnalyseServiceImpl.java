package com.wbl.service.impl;

import com.google.protobuf.InvalidProtocolBufferException;
import com.wbl.dao.DataQueryDao;
import com.wbl.dao.ProvDao;
import com.wbl.dao.RedisClient;
import com.wbl.domain.DataInfo;
import com.wbl.modal.CountModal;
import com.wbl.service.DataAnalyseService;
import com.wbl.util.KeyUtil;
import com.wbl.util.PbMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with Simple_love
 * Date: 2016/3/30.
 * Time: 9:27
 */
@Repository("dataAnalyseService")
public class DataAnalyseServiceImpl implements DataAnalyseService {
    private static Logger logger = Logger.getLogger(DataAnalyseServiceImpl.class);
    @Resource
    private DataQueryDao dataQueryDao;
    @Resource
    private ProvDao provDao;

    @Resource
    private RedisClient redisClient;

    @Override
    public JSONArray getDataOperateTimesInDay(String dataName, String month, String year) {
        JSONArray result = new JSONArray();
        String key = KeyUtil.getOperateTimesInDayKey(dataName,month,year);
        List<String> countModalByte = redisClient.getListAllValues(key);
        List<CountModal> countModals = new ArrayList<>();
        //redis不存在数据，则从MySQL中获取
        if (CollectionUtils.isEmpty(countModalByte)){
            countModals = getDataOperateTimesInDayFromMysql(dataName,month,year);
            countModals.forEach(countModal -> redisClient.lpush(key,PbMapper.countModal2Pb(countModal)));
        }else {
            countModals = countModalByte.stream().map(cmpb->PbMapper.pb2CountModal(cmpb.getBytes())).collect(Collectors.toList());
        }
        parseCountModal(result,countModals);
        return result;
    }

    @Override
    public JSONArray getDataOperateTimesInMonth(String dataName, String year) {
        JSONArray result = new JSONArray();
        String key = KeyUtil.getOperateTimesInMonthKey(dataName, year);
        List<String> countModalByte = redisClient.getListAllValues(key);
        List<CountModal> countModals = new ArrayList<>();
        if (CollectionUtils.isEmpty(countModalByte)){
            countModals = getDataOperateTimesInMonthFromMysql(dataName,year);
            countModals.forEach(countModal -> redisClient.lpush(key,PbMapper.countModal2Pb(countModal)));
        }else {
            countModals = countModalByte.stream().map(cmpb->PbMapper.pb2CountModal(cmpb.getBytes())).collect(Collectors.toList());
        }
        parseCountModal(result,countModals);
        return result;

    }

    @Override
    public JSONArray getDataOperateTimesInYear(String dataName) {
        JSONArray result = new JSONArray();
        String key = KeyUtil.getOperateTimeInYearKey(dataName);
        List<String> countModalByte = redisClient.getListAllValues(key);
        List<CountModal> countModals = new ArrayList<>();
        if (CollectionUtils.isEmpty(countModalByte)){
            countModals = getDataOperateTimesInYearFromMysql(dataName);
            countModals.forEach(countModal -> redisClient.lpush(key,PbMapper.countModal2Pb(countModal)));
        }else {
            countModals = countModalByte.stream().map(cmpb->PbMapper.pb2CountModal(cmpb.getBytes())).collect(Collectors.toList());
        }
        parseCountModal(result,countModals);
        return result;
    }

    @Override
    public JSONArray getDataOperateTimesByType(String dataName) {
        JSONArray result = new JSONArray();
        String key = KeyUtil.getOperateTimeByTypeKey(dataName);
        List<String> countModalByte = redisClient.getListAllValues(key);
        List<CountModal> countModals = new ArrayList<>();
        if (CollectionUtils.isEmpty(countModalByte)) {
            countModals = getDataOperateTimesByTypeFromMysql(dataName);
            countModals.forEach(countModal -> redisClient.lpush(key,PbMapper.countModal2Pb(countModal)));
        }else {
            countModals = countModalByte.stream().map(cmpb->PbMapper.pb2CountModal(cmpb.getBytes())).collect(Collectors.toList());
        }
        parseCountModal(result,countModals);
        return result;
    }

    @Override
    public JSONObject getHotData() {
        JSONObject result = new JSONObject();
        String key = KeyUtil.getHotDataKey();
        List<String> countModalByte = redisClient.getListAllValues(key);
        List<CountModal> countModals = new ArrayList<>();
        if (CollectionUtils.isEmpty(countModalByte)) {
            countModals = getHotDataFromMysql();
            countModals.forEach(countModal -> redisClient.lpush(key,PbMapper.countModal2Pb(countModal)));
        }else {
            countModals = countModalByte.stream().map(cmpb->PbMapper.pb2CountModal(cmpb.getBytes())).collect(Collectors.toList());
        }
        result.put("data",countModals);
        return result;
    }

    @Override
    public JSONObject getLikeData(String dataName) {
        JSONObject result = new JSONObject();
        String key = KeyUtil.getLikeDataKey(dataName);
        List<String> countModalByte = redisClient.getListAllValues(key);
        List<CountModal> countModals = new ArrayList<>();
        if (CollectionUtils.isEmpty(countModalByte)) {
            countModals = getLikeDataFromMysql(dataName);
            countModals.forEach(countModal -> redisClient.lpush(key,PbMapper.countModal2Pb(countModal)));
        }else {
            countModals = countModalByte.stream().map(cmpb->PbMapper.pb2CountModal(cmpb.getBytes())).collect(Collectors.toList());
        }
        result.put("data",countModals);
        return result;
    }

    @Override
    public JSONObject getUserRecommendData(String userName) {
        JSONObject result = new JSONObject();
        String key = KeyUtil.getUserRecommendDataKey(userName);
        List<String> recommendData = redisClient.getListAllValues(key);
        if (CollectionUtils.isEmpty(recommendData)) {
            recommendData = getUserRecommendDataFromMysql(userName);
            recommendData.forEach(data->redisClient.lpush(key,data));
        }
        result.put("data",recommendData);
        return result;
    }

    private List<CountModal> getDataOperateTimesInDayFromMysql(String dataName,String month,String year){
        DataInfo dataInfo = provDao.getDataInfoByName(dataName);
        if (dataName == null || month == null || year == null){
            logger.debug("dataName,month or year is null");
            return null;
        }
        if (dataInfo == null){
            logger.debug("data with name " + dataName + " is not exist");
            return null;
        }
        List<CountModal> countModals = dataQueryDao.getDataOperateTimesByDays(dataInfo.getDataId(),month,year);
        if (countModals.isEmpty())
            logger.debug("getDataOperateTimesInDay query result is empty");
        return countModals;
    }

    private List<CountModal> getDataOperateTimesInMonthFromMysql(String dataName,String year){
        DataInfo dataInfo = provDao.getDataInfoByName(dataName);
        if (dataName == null || year == null){
            logger.debug("dataName or year is null");
            return null;
        }
        if (dataInfo == null){
            logger.debug("data with name " + dataName + " is not exist");
            return null;
        }
        List<CountModal> countModals = dataQueryDao.getDataOperateTimesByMonth(dataInfo.getDataId(), year);
        if (countModals.isEmpty())
            logger.debug("getDataOperateTimesInMonth query result is empty");
        return countModals;
    }

    private List<CountModal> getDataOperateTimesInYearFromMysql(String dataName){
        DataInfo dataInfo = provDao.getDataInfoByName(dataName);
        if (dataName == null){
            logger.debug("dataName is null");
            return null;
        }
        if (dataInfo == null){
            logger.debug("data with name " + dataName + " is not exist");
            return null;
        }
        List<CountModal> countModals = dataQueryDao.getDataOperateTimesByYear(dataInfo.getDataId());
        if (countModals.isEmpty())
            logger.debug("getDataOperateTimesInYear query result is empty");
        return countModals;
    }

    private List<CountModal> getDataOperateTimesByTypeFromMysql(String dataName){
        DataInfo dataInfo = provDao.getDataInfoByName(dataName);
        if (dataName == null){
            logger.debug("dataName is null");
            return null;
        }
        if (dataInfo == null){
            logger.debug("data with name " + dataName + " is not exist");
            return null;
        }
        List<CountModal> countModals = dataQueryDao.getDataOperateTimesByType(dataInfo.getDataId());
        if (countModals.isEmpty())
            logger.debug("getDataOperateTimesByType query result is empty");
        return countModals;
    }

    private List<CountModal> getHotDataFromMysql(){
        List<CountModal> countModals = dataQueryDao.getHotDataByDownloadTimes();
        return countModals;
    }

    private List<CountModal> getLikeDataFromMysql(String dataName){
        DataInfo dataInfo = provDao.getDataInfoByName(dataName);
        List<CountModal> countModals = dataQueryDao.getLikeData(dataInfo.getDataId(), dataInfo.getCategory().substring(0, 2));
        return countModals;
    }

    private List<String> getUserRecommendDataFromMysql(String userName){
        List<String> users = dataQueryDao.getLikeUser(userName);
        Map<String,Integer> userCountMap = new HashMap<>();
        for (String name:users){
            List<String> datas = dataQueryDao.getDataIdByUserUsed(name);
            for (String data:datas){
                if (userCountMap.containsKey(data)){
                    int count = userCountMap.get(data);
                    count++;
                    userCountMap.put(data,count);
                }else
                    userCountMap.put(data,1);
            }
        }
        return getSortedMapByValue(userCountMap);
    }

    private List<String> getSortedMapByValue(Map<String,Integer> map){
        List<Map.Entry<String,Integer>> entries = new ArrayList<>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o1.getValue()-o2.getValue());
            }
        });
        List<String> result = new ArrayList<>();
        int count = 0;
        for (Map.Entry<String,Integer> entry:entries){
            result.add(parseDataName(entry.getKey()));
            count++;
            if (count == 6)
                break;
        }
        return result;
    }


    private String parseDataName(String dataId){
        DataInfo dataInfo = provDao.getDataInfoByDataId(dataId);
        if (dataInfo == null)
            return null;
        else
            return dataInfo.getDataName().split("\\.")[0];
    }

    private void parseCountModal(JSONArray result,List<CountModal> countModals){
        for (CountModal modal : countModals) {
            JSONObject object = new JSONObject();
            object.put("name",modal.getName());
            object.put("count",modal.getCount());
            result.add(object);
        }
    }
}
