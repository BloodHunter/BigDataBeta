package com.wbl.service.impl;

import com.wbl.dao.DataQueryDao;
import com.wbl.domain.DataInfo;
import com.wbl.modal.Page;
import com.wbl.modal.exception.FTPException;
import com.wbl.service.DataOperateService;
import com.wbl.util.FtpUtil;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created with Simple_love
 * Date: 2016/4/13.
 * Time: 14:43
 */
@Repository
public class DataOperateServiceImpl implements DataOperateService {

    private Logger logger = Logger.getLogger(DataOperateServiceImpl.class);

    private static final long MB_SIZE = 1024 * 1024;
    private static final long GB_SIZE = 1024 * MB_SIZE;
    private static final String SOURCE_FROM_PLATFORM = "NOT NULL";
    private static final String SOURCE_FROM_PERSON = "NULL";
    private static final int PAGESIZE = 5;

    @Resource
    private DataQueryDao queryDao;

    @Override
    public JSONObject queryData(String type, int size, int source, int sort,int category,int currentPage) {
        JSONObject result = new JSONObject();
        type = "all".equals(type) ? null : type;
        long[] sizes = getDataSize(size);
        String from = source == 0?null:(source == 1?SOURCE_FROM_PLATFORM:SOURCE_FROM_PERSON);
        Page<DataInfo> page = new Page<>();
        int total = queryDao.getTotalPagesOfDataInfo(type,from,sizes[0],sizes[1]);
        int pages = total % PAGESIZE == 0 ? total/PAGESIZE:total/PAGESIZE+1;
        int startRow = (currentPage - 1) * PAGESIZE;
        switch (sort){
            case 0:
                page.addAll(queryDao.getDataInfoOrderByTime(type,from,sizes[0],sizes[1],category,startRow,PAGESIZE));
                break;
            case 1:
                page.addAll(queryDao.getDataInfoOrderByDownloadTimes(type,from,sizes[0],sizes[1],category,startRow,PAGESIZE));
                break;
            case 2:
                page.addAll(queryDao.getDataInfoOrderByDataSize(type,from,sizes[0],sizes[1],category,startRow,PAGESIZE));
                break;
        }
        result.put("data",page);
        result.put("pages",pages);
        return result;
    }

    @Override
    public byte[] fileDownload(String dataName) throws FTPException {
               /* InputStream in = FtpUtil.fileDownloadFromPublic(dataName);
                try {
                        return IOUtils.toByteArray(in);
                } catch (IOException e) {
                        e.printStackTrace();
                        logger.error("File download fail");
                }
                return null;*/
        try {
            dataName = new String(dataName.getBytes("ISO8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return FtpUtil.fileDownloadFromPublic(dataName);
    }

    private long[] getDataSize(int size){
        long[] sizes = new long[2];
        switch (size){
            case 1:
                sizes[0] = 0;
                sizes[1] = 100 * MB_SIZE;
                break;
            case 2:
                sizes[0] = 100 * MB_SIZE;
                sizes[1] = 500 * MB_SIZE;
                break;
            case 3:
                sizes[0] = 500 * MB_SIZE;
                sizes[1] = GB_SIZE;
                break;
            case 4:
                sizes[0] = GB_SIZE;
                sizes[1] = 0;
                break;
        }
        return sizes;
    }
}
