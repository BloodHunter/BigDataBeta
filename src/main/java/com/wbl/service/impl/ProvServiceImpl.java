package com.wbl.service.impl;

import com.wbl.dao.PageQueryDao;
import com.wbl.dao.ProvDao;
import com.wbl.domain.*;
import com.wbl.modal.Enum.ResultType;
import com.wbl.modal.Page;
import com.wbl.modal.PlatformInfo;
import com.wbl.modal.exception.RequestException;
import com.wbl.service.ProvService;
import com.wbl.util.GraphvizUtil;
import com.wbl.util.HttpRequestUtil;
import com.wbl.util.JsonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import netscape.javascript.JSUtil;
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
        @Autowired
        private PageQueryDao pageQueryDao;



        /**
         *make a request to local query interface
         * @param dataName name with query data
         * @return dataObj contain param {RESULT,MSG,QUERY_ID}
         */
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
                //SendParam sendParam = makeSendParam(dataInfo.getDataId());
                ReceivedParam receivedParam = makeRequestParam(dataInfo.getDataId());
                //provDao.save(sendParam);
                String param = "receivedParam=" + JSONObject.fromObject(receivedParam).toString();
                JSONObject result = null;
                try {
                        result = JSONObject.fromObject(HttpRequestUtil.doPostRequest(PlatformInfo.PLATFORM_QUERY_URL, param));
                        if (ERROR.equals(result.getString(RESULT.toString()))){
                                dataObj.put(RESULT,ERROR);
                                dataObj.put(MESSAGE,"Query with data name with "+ dataName + " is failed");
                        }else {
                                dataObj.put(RESULT, SUCCESS);
                        }
                } catch (RequestException e) {
                        dataObj.put(RESULT,ERROR);
                        dataObj.put(MESSAGE,"Query with queryId " + dataObj.get(QUERY_ID) + " is failed");
                        logger.error("Query with queryId " + dataObj.get(QUERY_ID) + " is failed");
                        logger.error("Error message : " + e.getMessage());
                }finally {
                        dataObj.put(QUERY_ID,receivedParam.getQueryFrom()+"_" + receivedParam.getQueryFor() + "_" + receivedParam.getRequestId());
                }
                return dataObj;
        }


        /**
         *get relations in local platform and  report result to platform which start this query ,
         * if report success, then make a query request to up stream and down stream
         * @param param receivedParam
         * @return dataObj
         */
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
                        JSONObject reportResult = null;
                        try {
                                reportResult = JSONObject.fromObject(HttpRequestUtil.doPostRequest(receivedParam.getReportUrl(), requestPram));
                                if (ERROR.equals(reportResult.getString(RESULT.toString()))){
                                        logger.debug("QueryId with " + queryId + " report to " + receivedParam.getReportUrl() + " is failed");
                                        dataObj.put(RESULT,ERROR);
                                }else {
                                        logger.debug("QueryId with " + queryId + " report to " + receivedParam.getReportUrl() + " is success");
                                        if (!queryAdjacentRelation(receivedParam)){
                                                logger.warn("QueryId with " + queryId + "query adjacent platform relation is failed");
                                                dataObj.put(RESULT,ERROR);
                                        }else{
                                                logger.debug("QueryId with " + queryId + "query adjacent platform relation is success");
                                                dataObj.put(RESULT,SUCCESS);
                                        }
                                }
                        } catch (RequestException e) {
                                logger.error("QueryId with " + queryId + " report to " + receivedParam.getReportUrl() + " is failed");
                                logger.error("Error message: " + e.getMessage());
                                dataObj.put(RESULT,ERROR);
                        }
                }else {
                        logger.debug("Get repeated request");
                        dataObj.put(RESULT, SUCCESS);
                }
                return dataObj;
        }

        @Override
        public JSONObject queryProv(String queryId) {
                JSONObject dataObj = new JSONObject();
                String[] tokens = queryId.split("_");
                if (tokens.length != 3){
                        logger.error("QueryId[" + queryId + "] is illegal");
                        dataObj.put(RESULT,ERROR);
                        return dataObj;
                }else {
                        String queryFrom = tokens[0];
                        String queryFor = tokens[1];
                        String requestId = tokens[2];
                        List<String> dataIds = provDao.getDataIdFromParam(queryFrom,queryFor,requestId);
                        List<Prov> provs = new ArrayList<>();
                        for (String dataId:dataIds){
                                provs.addAll(provDao.getProvs(dataId));
                        }
                        dataObj.put(PROVS, provs);
                        dataObj.put(RESULT, SUCCESS);
                }
                return dataObj;
        }

        /**
         * According data name to get prov
         * @param dataName name of query data
         * @return prov records of this data
         */
        @Override
        public List queryProvByName(String dataName) {
                DataInfo dataInfo = provDao.getDataInfoByName(dataName);
                if (dataInfo == null){
                        logger.error("The data named" + dataName + " is not exist");
                        return null;
                }
                return provDao.getProvs(dataInfo.getDataId());
        }

        /**
         * Get prov records of data by page
         * @param dataName name of query data
         * @param currentPage current page number
         * @return prov records of data in current page number
         */
        @Override
        public JSONObject getProvByPage(String dataName, String currentPage) {
                JSONObject dataObj = new JSONObject();
                DataInfo dataInfo = provDao.getDataInfoByName(dataName);
                if (dataInfo == null){
                        logger.error("The data named" + dataName + " is not exist");
                        dataObj.put(RESULT,ERROR);
                        return null;
                }
                Page<Prov> result = new Page<>();
                int curr = Integer.parseInt(currentPage);
                int pageSize = result.getPageSize();
                int startRow = curr == 1 ? curr: (curr - 1) * result.getPageSize() + 1;
                int rowLength =  result.getPageSize();
                result.addAll(pageQueryDao.getProvByDataId(dataInfo.getDataId(), startRow, rowLength));
                int total = pageQueryDao.getPagesByDataIdFromProv(dataInfo.getDataId());
                int pages = total % pageSize == 0 ? total /pageSize : total /pageSize + 1;
                dataObj.put(RESULT,SUCCESS);
                dataObj.put("Page_result",result);
                dataObj.put("pages",pages);
                return dataObj;
        }

        /**
         * Produce JSON for svg, thr structure of JSON is {"name": "",children:[] }
         * @param queryId the id of query prov
         * @return JSON which can draw a tree
         */
        @Override
        public JSONObject produceSvgJson(String queryId) {
                JSONObject dataObj = new JSONObject();
                String[] tokens = queryId.split("_");
                if (tokens.length != 3){
                        logger.error("QueryId[" + queryId + "] is illegal");
                        dataObj.put(RESULT,ERROR);
                        return dataObj;
                }else {
                        String queryFrom = tokens[0];
                        String queryFor = tokens[1];
                        String requestId = tokens[2];
                        /*List<String> dataIds = provDao.getDataIdFromParam(queryFrom,queryFor,requestId);
                        JSONObject root = new JSONObject();
                        root.put("name","root");
                        JSONArray children = new JSONArray();
                        for (String dataId:dataIds){
                                long downloadTimes = provDao.getTimesForDownload(dataId);
                                DataInfo dataInfo = provDao.getDataInfoByDataId(dataId);
                                Prov prov = provDao.getDataSourceInfo(dataId);
                                if (prov == null){
                                        children.add(JsonUtil.makeDataDetailJson(dataInfo.getDataName(),downloadTimes,"Unknow","Unknow"));
                                }else {
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        String time = sdf.format(prov.getTime());
                                        children.add(JsonUtil.makeDataDetailJson(dataInfo.getDataName(), downloadTimes, prov.getAgent(), time));
                                }
                        }
                        root.put("children", children);
                        dataObj.put("root", root);*/
                        List<String> dataIds = getRelatedDataId(queryFor);
                        for (String dataId:dataIds){
                                long downloadTimes = provDao.getTimesForDownload(dataId);
                                DataInfo dataInfo = provDao.getDataInfoByDataId(dataId);
                                Prov prov = provDao.getDataSourceInfo(dataId);
                                JSONObject data = new JSONObject();
                                data.put("times",downloadTimes);
                                if (prov == null){
                                        data.put("source","unknow");
                                        data.put("time","unknow");
                                }else {
                                        data.put("source",prov.getAgent());
                                        data.put("time",prov.getTime());
                                }
                                dataObj.put(dataInfo.getDataName(),data);
                        }
                }
                return dataObj;
        }

        @Override
        public String produceDotString(String queryId) {
                String[] tokens = queryId.split("_");
                if (tokens.length != 3){
                        logger.error("QueryId[" + queryId + "] is illegal");
                        return null;
                }else{
                        String queryFor = tokens[1];
                        return getRelationBetweenData(queryFor);
                }
        }

        /**
         *make a query request  to up stream and down stream
         * @param receivedParam query param
         * @return if query success, return true,otherwise return false
         */
        private boolean queryAdjacentRelation(ReceivedParam receivedParam){
                List<String> dataIdList = getRelatedDataId(receivedParam.getDataId());
                for (String dataId : dataIdList){
                        if (!queryForUpStream(receivedParam, dataId)  || !queryForDownStream(receivedParam, dataId))
                                return false;
                }
                return true;
        }

        /**
         * make a query request to up stream
         * @param param query param
         * @param dataId the id of query data
         * @return if query success, return true,otherwise return false
         */
        private boolean queryForUpStream(ReceivedParam param,String dataId){
                for (Source source:provDao.getSource(dataId)){
                        /*SendParam sendParam = new SendParam(dataId,param.getQueryFrom(),param.getQueryFor(),
                                param.getRequestId(),param.getReportUrl());*/
                        param.setDataId(dataId);
                        String requestParam = "receivedParam=" + JSONObject.fromObject(param).toString();
                        String queryUrl = source.getUrl();
                        if (queryUrl != null){
                                logger.debug("Query for up stream url: " + queryUrl + " and dataId = " + dataId);
                                JSONObject response = null;
                                String queryId = param.getQueryFrom() + "_" + param.getQueryFor() + "_" + param.getRequestId();
                                try {
                                        response = JSONObject.fromObject(HttpRequestUtil.doPostRequest(queryUrl, requestParam));
                                        if (ERROR.equals(response.getString(RESULT.toString()))){
                                                logger.error("QueryId with " + queryId +"  query for upStream[" + source.getSource() +"] is failed" );
                                                return false;
                                        }
                                } catch (RequestException e) {
                                        logger.error("QueryId with " + queryId +"  query for upStream[" + source.getSource() +"] is failed" );
                                        logger.error("Error message: " + e.getMessage());
                                        return false;
                                }

                        }
                }
                return true;
        }

        /**
         * make a query request to down stream
         * @param param query param
         * @param dataId the id of query data
         * @return if query success , return true, otherwise return false
         */
        private boolean queryForDownStream(ReceivedParam param,String dataId){
                for (Next next:provDao.getNext(dataId)){
                        /*SendParam sendParam = new SendParam(dataId,param.getQueryFrom(),param.getQueryFor(),
                                param.getRequestId(),param.getReportUrl());*/
                        param.setDataId(dataId);
                        String requestParam = "receivedParam=" + JSONObject.fromObject(param).toString();
                        String queryUrl = next.getUrl();
                        if (queryUrl != null){
                                logger.debug("Query for down Stream url: " + queryUrl + " and dataId = " + dataId);
                                JSONObject response = null;
                                String queryId = param.getQueryFrom() + "_" + param.getQueryFor() + "_" + param.getRequestId();
                                try {
                                        response = JSONObject.fromObject(HttpRequestUtil.doPostRequest(queryUrl, requestParam));
                                        if (ERROR.equals(response.getString(RESULT.toString()))){
                                                logger.error("QueryId with " + queryId +"  query for downStream["  + next.getNext() +"] is failed" );
                                                return false;
                                        }
                                } catch (RequestException e) {
                                        logger.error("QueryId with " + queryId +"  query for upStream[" + next.getNext() +"] is failed" );
                                        logger.error("Error message: " + e.getMessage());
                                        return false;
                                }
                        }
                }
                return true;
        }

        /**
         * according to dataId,get relation between local platform with other platform
         * @param dataId the id of query data
         * @return according relations to make dot line
         */
        @Override
        public String getRelationBetweenPlatform(String dataId) {
                StringBuilder builder = new StringBuilder();
                List<String> dataIdList = getRelatedDataId(dataId);
                builder.append(PlatformInfo.PLATFORM_NAME );
                builder.append("[URL=" + "\"" + PlatformInfo.PLATFORM_QUERY_PROV_URL + "\"]\n");
                for (String value : dataIdList){
                        for (Next next : provDao.getNext(value)){
                                builder.append(PlatformInfo.PLATFORM_NAME ).append(GraphvizUtil.LINKSIGN).append(next.getNext());
                                builder.append("\n");
                        }
                }
                return builder.toString();
        }

        /**
         * According to dataId,get relation between data
         * @param dataId the id of query data
         * @return according to relations to make dot line
         */
        private String getRelationBetweenData(String dataId) {
                List<String> dataList = new ArrayList<>();
                StringBuffer buffer = new StringBuffer();
                if (!haveRelationWithOtherData(dataId)){
                        DataInfo dataInfo = provDao.getDataInfoByDataId(dataId);
                        return dataInfo.getDataName();
                }
                Queue<String> queue = new LinkedList<>();
                queue.offer(dataId);
                while (!queue.isEmpty()){
                        String tempId = queue.poll();
                        dataList.add(tempId);
                        String name = provDao.getDataInfoByDataId(tempId).getDataName();
                        for (RelationInfo info: provDao.getAncestor(tempId)){
                                if (!dataList.contains(info.getAncestor())){
                                        String parentName = provDao.getDataInfoByDataId(info.getAncestor()).getDataName();
                                        buffer.append(parentName);
                                        buffer.append(GraphvizUtil.LINKSIGN);
                                        buffer.append(name);
                                        buffer.append("\n");
                                        dataList.add(info.getAncestor());
                                        queue.offer(info.getAncestor());
                                }
                        }


                        for (RelationInfo info : provDao.getSuccessor(tempId)){
                                if (!dataList.contains(info.getSuccessor())){
                                        String childName = provDao.getDataInfoByDataId(info.getSuccessor()).getDataName();
                                        buffer.append(name);
                                        buffer.append(GraphvizUtil.LINKSIGN);
                                        buffer.append(childName);
                                        buffer.append("\n");
                                        dataList.add(info.getSuccessor());
                                        queue.offer(info.getSuccessor());
                                }
                        }
                }
                return buffer.toString();
        }


        /**
         *make sure the query request is repeated or not
         * @param param receivedParam
         * @return if request is repeated return true, otherwise return false
         */
        @Override
        public boolean requestIsExist(ReceivedParam param) {
                ReceivedParam tempReceivedParam = provDao.getReceivedParam(param);
                /*if (tempReceivedParam != null)
                        return true;

                SendParam tempSendParam = provDao.getSendParam(new SendParam(param.getDataId(),param.getQueryFrom(),
                        param.getQueryFor(), param.getRequestId(),param.getReportUrl()));
                return tempSendParam != null;*/
                return tempReceivedParam != null;
        }



        /**
         * get ancestor and successor of data
         * @param dataId the id of data
         * @return ancestor and successor of data
         */
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

        /**
         * whether data have ancestor or successor
         * @param dataId the id of data
         * @return if data have ancestor or successor,return true,otherwise false
         */
        private boolean haveRelationWithOtherData(String dataId){
                DataInfo dataInfo = provDao.getDataInfoByDataId(dataId);
                return dataInfo.getRelation() != DATA_RELATION_EMPTY;
        }

        /**
         * make param when send a request
         * @param dataId the id of data
         * @return sendParam
         */
        private synchronized SendParam makeSendParam(String dataId){
                SendParam param = new SendParam();
                param.setDataId(dataId);
                param.setQueryFor(dataId);
                param.setQueryFrom(PlatformInfo.PLATFORM_NAME);
                param.setReportUrl(PlatformInfo.PLATFORM_REPORT_URL);
                Format format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                param.setRequestId(format.format(new Date()));
                return param;
        }

        private synchronized ReceivedParam makeRequestParam(String dataId){
                ReceivedParam param = new ReceivedParam();
                param.setDataId(dataId);
                param.setQueryFor(dataId);
                param.setQueryFrom(PlatformInfo.PLATFORM_NAME);
                param.setReportUrl(PlatformInfo.PLATFORM_REPORT_URL);
                Format format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
                param.setRequestId(format.format(new Date()));
                return param;
        }

        public static void main(String[] args) {
                /*ReceivedParam receivedParam = new ReceivedParam();
                String param = "receivedParam=" + JSONObject.fromObject(receivedParam).toString();
                System.out.println(HttpRequestUtil.doPostRequest("http://10.103.241.73:8090/BigDataBeta/prov/queryRelation",param));*/
                JSONObject dataObj= new JSONObject();
                dataObj.put(RESULT,SUCCESS);
                System.out.println(SUCCESS.equals(dataObj.getString(RESULT.toString())));
                System.out.println(RESULT);
        }

}
