package com.wbl.service;

import com.wbl.domain.ReceivedParam;
import net.sf.json.JSONObject;

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

        String getRelationBetweenPlatform(String dataId);

        boolean requestIsExist(ReceivedParam param);
}
