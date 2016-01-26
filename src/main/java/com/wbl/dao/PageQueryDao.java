package com.wbl.dao;

import com.wbl.domain.Prov;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Simple_love on 2015/11/1.
 */
@Repository
public interface PageQueryDao {
        List<Prov> getProv(@Param("startRow")int startRow,@Param("endRow")int endRow);
        List<Prov> getProvByDataId(@Param("dataId")String dataId,@Param("startRow")int startRow,@Param("rowLength")int rowLength);
        int getPagesByDataIdFromProv(@Param("dataId")String dataId);
}
