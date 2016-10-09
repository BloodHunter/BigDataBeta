package com.wbl.service;

import java.util.Set;

/**
 * Created by wbl on 16/10/9.
 */
public interface RefreshRedisService {

    void init();

    void refreshDataOperateTimes();

    void refreshHotData();

    void refreshLikeData();

    void refreshRecommendData();
}
