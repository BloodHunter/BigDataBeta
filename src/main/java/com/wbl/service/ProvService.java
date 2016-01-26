package com.wbl.service;

import com.wbl.domain.ReceivedParam;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by Simple_love on 2016/1/4.
 */
public interface ProvService {
        static final int DATA_STATUS_NUPUBLISHED = 0;
        static final int DATA_STATUS_PUBLISHED = 1;
        static final int DATA_STATUS_UNUPLOAD = 2;
        static final int DATA_RELATION_EMPTY = 0;

        JSONObject queryPlatformRelation(String dataName);
        JSONObject queryRelation(String param);

        JSONObject queryProv(String queryId);
        List queryProvByName(String dataName);
        JSONObject getProvByPage(String dataName,String currentPage);

        JSONObject produceSvgJson(String queryId);
        String produceDotString(String queryId);

        String getRelationBetweenPlatform(String dataId);

        boolean requestIsExist(ReceivedParam param);
}
