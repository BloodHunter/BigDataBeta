package com.wbl.service;

import com.wbl.modal.exception.FTPException;
import net.sf.json.JSONObject;

import java.io.FileInputStream;

/**
 * Created with Simple_love
 * Date: 2016/4/13.
 * Time: 11:28
 */
public interface DataOperateService {
        JSONObject queryData(String type,int size,int source,int sort,int category,int currentPage);
        byte[] fileDownload(String dataName) throws FTPException;
}
