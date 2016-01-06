package com.wbl.service.impl;

import com.wbl.dao.ProvDao;
import com.wbl.domain.*;
import com.wbl.modal.PlatformInfo;
import com.wbl.service.ProvService;
import com.wbl.util.GraphvizUtil;
import com.wbl.util.HttpRequestUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.wbl.modal.Enum.ResultType.ERROR;
import static com.wbl.modal.Enum.ResultType.RESULT;
import static com.wbl.modal.Enum.ResultType.SUCCESS;
import static com.wbl.modal.Enum.QueryMessage.*;

/**
 * Created by Simple_love on 2016/1/4.
 */
@Repository("provService")
public class ProvServiceImpl implements ProvService {
        private Logger logger = Logger.getLogger(this.getClass());
        @Autowired
        private ProvDao provDao;

        @Override
        public JSONObject queryPlatformRelation(String dataName) {
                JSONObject dataObj = new JSONObject();
                DataInfo dataInfo = provDao.getDataInfoByName(dataName);
                if (dataInfo == null){
                        logger.error("Query data name with " + dataName +" is not exist");
                        dataObj.put(RESULT, ERROR);
                        dataObj.put(MESSAGE,"Data name with \" + dataName +\" is not exist");
                        return dataObj;
                }
                SendParam sendParam = makeSendParam(dataInfo.getDataId());
                provDao.save(sendParam);
                String param = "ReceivedParam=" + JSONObject.fromObject(sendParam).toString();
                JSONObject result = JSONObject.fromObject(HttpRequestUtil.doPostRequest(PlatformInfo.PLATFORM_QUERY_URL,param));
                dataObj.put(QUERY_ID,sendParam.getQueryFrom()+"_" + sendParam.getQueryFor() + "_" + sendParam.getRequestId());
                if (result.get(RESULT) == ERROR){
                        dataObj.put(RESULT,ERROR);
                        dataObj.put(MESSAGE,"Query with queryId " + dataObj.get(QUERY_ID) + " is failed");
                }else {
                        dataObj.put(RESULT, SUCCESS);
                }
                return dataObj;
        }

        @Override
        public JSONObject queryRelation(String param) {
                JSONObject dataObj = new JSONObject();
                ReceivedParam receivedParam = (ReceivedParam) JSONObject.toBean(JSONObject.fromObject(param), ReceivedParam.class);
                if (!requestIsExist(receivedParam)){
                        provDao.save(receivedParam);
                        String relations = getRelationBetweenPlatform(receivedParam.getDataId());
                        String queryId = receivedParam.getQueryFrom()+"_" + receivedParam.getQueryFor()+"_"+receivedParam.getRequestId();
                        JSONObject report = new JSONObject();
                        report.put(QUERY_ID,queryId);
                        report.put(RELATIONS,relations);
                        String requestPram = "report=" + report.toString();
                        JSONObject reportResult = JSONObject.fromObject(HttpRequestUtil.doPostRequest(receivedParam.getReportUrl(),requestPram));
                        if (reportResult.get(RESULT) == ERROR){
                                logger.warn("QueryId with " + queryId + " report to " + receivedParam.getReportUrl() + " is failed");
                                dataObj.put(RESULT,ERROR);
                        }else {
                                if (!queryAdjacentRelation(receivedParam)){
                                        logger.warn("QueryId with " + queryId + "query adjacent platform relation is failed");
                                        dataObj.put(RESULT,ERROR);
                                }else
                                        dataObj.put(RESULT,SUCCESS);
                        }
                }
                return dataObj;
        }

        private boolean queryAdjacentRelation(ReceivedParam receivedParam){
                List<String> dataIdList = getRelatedDataId(receivedParam.getDataId());
                for (String dataId : dataIdList){
                        if (!queryForUpStream(receivedParam, dataId)  || !queryForDownStream(receivedParam, dataId))
                                return false;
                }
                return true;
        }

        private boolean queryForUpStream(ReceivedParam param,String dataId){
                for (Source source:provDao.getSource(dataId)){
                        SendParam sendParam = new SendParam(dataId,param.getQueryFrom(),param.getQueryFor(),
                                param.getRequestId(),param.getReportUrl());
                        String requestParam = "receivedParam=" + JSONObject.fromObject(sendParam).toString();
                        String queryUrl = source.getUrl();
                        if (queryUrl != null){
                                JSONObject response = JSONObject.fromObject(HttpRequestUtil.doPostRequest(param.getReportUrl(),requestParam));
                                if (response.get(RESULT) == ERROR){
                                        String queryId = param.getQueryFrom() + "_" + param.getQueryFor() + "_" + param.getRequestId();
                                        logger.error("QueryId with " + queryId +"  query for upStream[" + source.getSource() +"] is failed" );
                                        return false;
                                }
                        }
                }
                return true;
        }

        private boolean queryForDownStream(ReceivedParam param,String dataId){
                for (Next next:provDao.getNext(dataId)){
                        SendParam sendParam = new SendParam(dataId,param.getQueryFrom(),param.getQueryFor(),
                                param.getRequestId(),param.getReportUrl());
                        String requestParam = "receivedParam=" + JSONObject.fromObject(sendParam).toString();
                        String queryUrl = next.getUrl();
                        if (queryUrl != null){
                                JSONObject response = JSONObject.fromObject(HttpRequestUtil.doPostRequest(param.getReportUrl(),requestParam));
                                if (response.get(RESULT) == ERROR){
                                        String queryId = param.getQueryFrom() + "_" + param.getQueryFor() + "_" + param.getRequestId();
                                        logger.error("QueryId with " + queryId +"  query for downStream["  + next.getNext() +"] is failed" );
                                        return false;
                                }
                        }
                }
                return true;
        }

        @Override
        public String getRelationBetweenPlatform(String dataId) {
                StringBuilder builder = new StringBuilder();
                List<String> dataIdList = getRelatedDataId(dataId);
                builder.append(PlatformInfo.PLATFORM_NAME );
                builder.append("[URL=" + "\"" + PlatformInfo.PLATFORM_QUERY_URL + "\"]\n");
                for (String value : dataIdList){
                        for (Next next : provDao.getNext(value)){
                                builder.append(PlatformInfo.PLATFORM_NAME ).append(GraphvizUtil.LINKSIGN).append(next.getNext());
                                builder.append("\n");
                        }
                }
                return builder.toString();
        }

        @Override
        public boolean requestIsExist(ReceivedParam param) {
                ReceivedParam tempReceivedParam = provDao.getReceivedParam(param);
                if (tempReceivedParam != null)
                        return true;

                SendParam tempSendParam = provDao.getSendParam(new SendParam(param.getDataId(),param.getQueryFrom(),
                        param.getQueryFor(), param.getRequestId(),param.getReportUrl()));
                return tempSendParam != null;
        }

        /*private String getNextRelation(List<String> dataIdList){
                StringBuilder builder = new StringBuilder();
                builder.append(PlatformInfo.PLATFORM_NAME );
                builder.append("[URL=" + "\"" + PlatformInfo.PLATFORM_QUERY_URL +"\"]\n" );
                for (String dataId : dataIdList){
                        for (Next next : provDao.getNext(dataId)){
                                builder.append(PlatformInfo.PLATFORM_NAME ).append(GraphvizUtil.LINKSIGN).append(next.getNext());
                                builder.append("\n");
                        }
                }
                return builder.toString();
        }*/

        /*private String getSourceRelation(List<String> dataList){
                StringBuilder builder = new StringBuilder();
                for (String dataId : dataList){
                        for (Source source : provDao.getSource(dataId)){
                                builder.append(source.getSource());
                                builder.append("[URL=\"" ).append(source.getUrl()).append("\"]\n");
                                builder.append(source.getSource()).append(GraphvizUtil.LINKSIGN).append(PlatformInfo.PLATFORM_NAME);
                                builder.append("\n");
                        }
                }
                return builder.toString();
        }*/

        private List<String> getRelatedDataId(String dataId){
                List<String> dataIdList = new ArrayList<>();
                if (!haveRelationWithOtherData(dataId)){
                        dataIdList.add(dataId);
                        return dataIdList;
                }
                Queue<String> queue = new LinkedList<>();
                queue.offer(dataId);
                while (!queue.isEmpty()){
                        String tempDataId = queue.poll();
                        dataIdList.add(tempDataId);
                        if (haveRelationWithOtherData(tempDataId)){
                                for (RelationInfo info: provDao.getAncestor(tempDataId)){
                                        if (!dataIdList.contains(info.getAncestor())){
                                                dataIdList.add(info.getAncestor());
                                                queue.offer(info.getAncestor());
                                        }
                                }

                                for (RelationInfo info : provDao.getSuccessor(tempDataId)){
                                        if (!dataIdList.contains(info.getSuccessor())){
                                                dataIdList.add(info.getSuccessor());
                                                queue.offer(info.getSuccessor());
                                        }
                                }
                        }
                }
                return dataIdList;
        }

        private boolean haveRelationWithOtherData(String dataId){
                DataInfo dataInfo = provDao.getDataInfoByDataId(dataId);
                return dataInfo.getRelation() != DATA_RELATION_EMPTY;
        }

        private synchronized SendParam makeSendParam(String dataId){
                SendParam param = new SendParam();
                param.setDataId(dataId);
                param.setQueryFor(dataId);
                param.setQueryFrom(PlatformInfo.PLATFORM_NAME);
                param.setReportUrl(PlatformInfo.PLATFORM_REPORT_URL);
                Format format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
                param.setRequestId(format.format(new Date()));
                return param;
        }

}