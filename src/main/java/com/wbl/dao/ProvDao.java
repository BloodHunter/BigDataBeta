package com.wbl.dao;

import com.wbl.domain.*;

import java.util.List;

/**
 * Created by Simple_love on 2015/10/26.
 *
 */
public interface ProvDao {
        List<Prov> getProv();
        List<Prov> getProv(String agent);
        List<Prov> getProv(int start, int end);

        boolean save(Prov prov);
        boolean update(Prov prov);

        boolean save(DataInfo dataInfo);
        boolean update(DataInfo dataInfo);

        boolean save(Next next);
        boolean save(Source source);
        boolean save(RelationInfo relationInfo);
        boolean save(ReceivedParam receivedParam);
        boolean save(SendParam sendParam);

        List getProvs(String dataId);
        List<Next> getNext(String dataID);
        List<Source> getSource(String dataID);
        List<RelationInfo> getAncestor(String dataID);
        List<RelationInfo> getSuccessor(String dataID);

        DataInfo getDataInfoByDataId(String dataId);
        DataInfo getDataInfoByName(String dataName);

        ReceivedParam getReceivedParam(ReceivedParam param);
        SendParam getSendParam(SendParam param);

        List getDataIdFromParam(String queryFrom,String queryFor,String requestId);

        long getTimesForDownload(String dataId);
        Prov getDataSourceInfo(String dataId);

}
