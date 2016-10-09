package com.wbl.service;

import com.wbl.domain.DataInfo;
import com.wbl.domain.ReceivedParam;
import com.wbl.domain.Source;
import com.wbl.domain.UserInfo;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by Simple_love on 2016/1/4.
 *
 */
public interface ProvService {
        static final int DATA_STATUS_NUPUBLISHED = 0;
        static final int DATA_STATUS_PUBLISHED = 1;
        static final int DATA_STATUS_UNUPLOAD = 2;
        static final int DATA_RELATION_EMPTY = 0;
        static final int DATA_RELATION_NOT_EMPTY = 1;
        static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

        JSONObject queryPlatformRelation(String dataName);
        JSONObject queryRelation(String param);

        JSONObject queryProv(String queryId);
        List queryProvByName(String dataName);
        JSONObject getProvByPage(String dataName,String currentPage);
        JSONObject getProvByActivity(String dataName,String activity,String currentPage);

        //JSONObject produceSvgJson(String queryId);
        String produceDotString(String queryId);

        String getRelationBetweenPlatform(String dataId);

        boolean requestIsExist(ReceivedParam param);

        void recordDownloadProv(String dataName,UserInfo userInfo);

        void recordDownloadProvFromOtherPlatform(String platform,String url,DataInfo dataInfo);

        void recordUploadProv(String dataName,String description,String type,long size,
                              String category,UserInfo userInfo);

        void recordSplitProv(String fileName,String dataName,UserInfo userInfo);

        void recordAggregation(String fileName,UserInfo userInfo);

        void recordExportProv(String dataName,UserInfo userInfo);

        void recordImportProv(Source source,DataInfo dataInfo);

        JSONObject getDataInfo(String dataName);
}
