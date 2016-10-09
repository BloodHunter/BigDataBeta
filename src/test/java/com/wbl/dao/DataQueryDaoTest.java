package com.wbl.dao;

import com.wbl.domain.DataInfo;
import com.wbl.domain.Source;
import com.wbl.modal.CountModal;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created with Simple_love
 * Date: 2016/3/28.
 * Time: 16:27
 */
public class DataQueryDaoTest extends BaseDao {
        @Autowired
        private DataQueryDao dataQueryDao;
        @Test
        public void testGetDataInfoOrderByTime() throws Exception{
                /*String source = new String("个人".getBytes("GBK"),"UTF-8");
                System.out.println(source);*/
                List<DataInfo> list = dataQueryDao.getDataInfoOrderByTime("image", null, 0,2048,10,0, 10);
                System.out.println(list);
        }

        @Test
        public void testGetDataInfoOrderByDownloadTimes() throws Exception{
               /* List<DataInfo> list = dataQueryDao.getDataInfoOrderByDownloadTimes("image", null, 0, 10);
                System.out.println(list);*/
        }

        @Test
        public void testGetDataInfoOrderByDataSize() throws Exception{
                List<DataInfo> list = dataQueryDao.getDataInfoOrderByDataSize("image",null,0,0,10,0,10);
                System.out.println(list);
        }

        @Test
        public void testGetTotalPagesOfDataInfo() throws Exception{
                System.out.println(dataQueryDao.getTotalPagesOfDataInfo("image","not null",0,0));
        }

        @Test
        public void testGetDataOperateTimesByDays() throws Exception{
                List<CountModal> list = dataQueryDao.getDataOperateTimesByDays("platA-1", "09", "2015");
                System.out.println(list);
        }

        @Test
        public void testGetDataOperateTimesByMonth() throws Exception{
                List<CountModal> list = dataQueryDao.getDataOperateTimesByMonth("platA-1", "2015");
                System.out.println(list);
        }

        @Test
        public void testGetDataOperateTimesByYear() throws Exception{
                List<CountModal> list = dataQueryDao.getDataOperateTimesByYear("platA-1");
                System.out.println(list);
        }

        @Test
        public void testGetDataOperateTimesByType() throws Exception{
                List<CountModal> list = dataQueryDao.getDataOperateTimesByType("platA-1");
                System.out.println(list);
        }

        @Test
        public void testGetHotDataByDownloadTimes() throws Exception{
                List<CountModal> list = dataQueryDao.getHotDataByDownloadTimes();
                System.out.println(list);
        }

        /*@Test
        public void testGetDataIdByUserUsed(){
                List<String> list = dataQueryDao.getDataIdByUserUsed("platF");
                System.out.println(list);
        }*/

        @Test
        public void testGetCategory() throws Exception{
                System.out.println(dataQueryDao.getCategory());
        }

        @Test
        public void testGetLikeData() throws Exception{
                System.out.println(dataQueryDao.getLikeData("platA-4", "90"));
        }

        @Test
        public void testGetLikeUser() throws Exception{
                System.out.println(dataQueryDao.getLikeUser("wbl"));
        }
}
