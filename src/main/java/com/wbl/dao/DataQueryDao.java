package com.wbl.dao;

import com.wbl.domain.Category;
import com.wbl.domain.DataInfo;
import com.wbl.modal.CountModal;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with Simple_love
 * Date: 2016/3/28.
 * Time: 15:37
 */
@Repository
public interface DataQueryDao {
    /**
     * query dataInfo by type,source ,size and sort by upload time
     * @param type type of data
     * @param source source of data
     * @param minSize min size of data
     * @param maxSize max size of data
     * @param startRow row number
     * @param rowLength page length
     * @return dataInfo
     */

    List<DataInfo> getDataInfoOrderByTime(@Param("type")String type,@Param("source")String source,
                                          @Param("minSize")long minSize,@Param("maxSize")long maxSize,@Param("category")int category,
                                          @Param("startRow")int startRow,@Param("rowLength")int rowLength);

    /**
     * query dataInfo by type,source,size and sort by download times
     * @param type type of data
     * @param source source of data
     * @param minSize min size of data
     * @param maxSize max size of data
     * @param startRow row number
     * @param rowLength page length
     * @return dataInfo
     */
    List<DataInfo> getDataInfoOrderByDownloadTimes(@Param("type")String type,@Param("source")String source,
                                                   @Param("minSize")long minSize,@Param("maxSize")long maxSize,@Param("category")int category,
                                                   @Param("startRow")int startRow,@Param("rowLength")int rowLength);

    /**
     * query dataInfo by type,source,size and sort by size
     * @param type type of data
     * @param source source of data
     * @param minSize min size of data
     * @param maxSize max size of data
     * @param startRow row number
     * @param rowLength page length
     * @return dataInfo
     */
    List<DataInfo> getDataInfoOrderByDataSize(@Param("type")String type,@Param("source")String source,
                                              @Param("minSize")long minSize,@Param("maxSize")long maxSize,@Param("category")int category,
                                              @Param("startRow")int startRow,@Param("rowLength")int rowLength);

    int getTotalPagesOfDataInfo(@Param("type")String type,@Param("source")String source,
                                @Param("minSize")long minSize,@Param("maxSize")long maxSize);

    /**
     * query data times of operate in a day
     * @param dataId id of data
     * @param month month
     * @param year year
     * @return countModal
     */
    List<CountModal> getDataOperateTimesByDays(@Param("dataId")String dataId,@Param("month")String month,
                                               @Param("year")String year);

    /**
     * query data times of operate in a month
     * @param dataId id of data
     * @param year year
     * @return countModal
     */
    List<CountModal> getDataOperateTimesByMonth(@Param("dataId")String dataId,@Param("year")String year);

    /**
     * query data times of operate group by year
     * @param dataId id of data
     * @return countModal
     */
    List<CountModal> getDataOperateTimesByYear(@Param("dataId")String dataId);

    /**
     * query data operate times group by type
     * @param dataId id of data
     * @return countModal
     */
    List<CountModal> getDataOperateTimesByType(String dataId);

    List<CountModal> getHotDataByDownloadTimes();

    List<String> getDataIdByUserUsed(String userName);

    List<DataInfo> getDataInfoByUserUpload(int ownerId);


    List<Category> getCategory();

    List<CountModal> getLikeData(@Param("dataId")String dataId,@Param("category")String category);

    List<String> getLikeUser(String userName);
}
