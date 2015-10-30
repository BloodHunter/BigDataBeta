package com.wbl.dao;

import com.wbl.domain.Prov;

import java.util.List;

/**
 * Created by Simple_love on 2015/10/26.
 *
 */
public interface ProvDao {
        List<Prov> getProv();
        List<Prov> getProv(String agent);
        List<Prov> getProv(int start, int end);

        boolean saveProv(Prov prov);
        boolean updateProv(Prov prov);
}
