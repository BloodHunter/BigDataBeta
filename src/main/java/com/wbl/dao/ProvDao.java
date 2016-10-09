package com.wbl.dao;

import com.wbl.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with Simple_love
 * Date: 2016/4/25.
 * Time: 9:29
 */
@Repository("provDao")
public interface ProvDao {

    void saveProv(Prov prov);

    void saveDataInfo(DataInfo dataInfo);
    void updateDataInfo(DataInfo dataInfo);

    void saveNext(Next next);
    void saveSource(Source source);
    void saveRelationInfo(RelationInfo info);
    void saveReceivedParam(ReceivedParam param);

    Next isNextExist(Next next);
    List<Next> getNext(String dataId);

    Source isSourceExist(Source source);
    List<Source> getSource(String dataId);

    RelationInfo isRelationExist(RelationInfo info);
    List<RelationInfo> getAncestor(String dataId);
    List<RelationInfo> getSuccessor(String dataId);

    DataInfo getDataInfoByDataId(String dataId);
    DataInfo getDataInfoByName(String dataName);
    DataInfo getLastDataInfo();

    ReceivedParam getReceivedParam(ReceivedParam param);
    List<String> getDataIdFromReceivedParam(@Param("queryFrom")String queryFrom,
                                            @Param("queryFor")String queryFor,@Param("requestId")String requestId);

    List<Prov> getProvs(String dataId);
    List<Prov> getProvByDataId(@Param("dataId")String dataId,@Param("startRow")int startRow,@Param("rowLength")int rowLength);
    int getPagesByDataIdFromProv(@Param("dataId")String dataId);

    List<Prov> getProvByDataIdAndActivity(@Param("dataId")String dataId,@Param("activity")String activity,
                                          @Param("startRow")int startRow, @Param("rowLength")int rowLength);
    int getPagesByDataIdAndActivity(@Param("dataId")String dataId,@Param("activity")String activity);
}
