package com.wbl.dao;

import com.wbl.domain.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

/**
 * Created with Simple_love
 * Date: 2016/4/25.
 * Time: 10:30
 */
public class ProvDaoTest extends BaseDao {
        @Autowired
        private ProvDao provDao;

        @Test
        public void testSaveProv() throws Exception{
                int n = 3;
                Prov prov = new Prov("platA","platA-8","userA-1","DOWNLOAD","platA:platA-8",new Timestamp(111111));
                for (int i = 1; i <= n;i++)
                        provDao.saveProv(prov);
        }

        @Test
        public void testSaveDataInfo() throws Exception{
                DataInfo dataInfo = new DataInfo("test","test",1,"test",100,"test","test",1,1,new Timestamp(11111));
                provDao.saveDataInfo(dataInfo);
        }

        @Test
        public void testUpdateDataInfo() throws Exception{
                DataInfo dataInfo = new DataInfo("test","test",2,"test",100,"test","test",1,1,new Timestamp(11111));
                provDao.updateDataInfo(dataInfo);
        }

        @Test
        public void testSaveNext() throws Exception{
                Next next = new Next("test","test","test");
                provDao.saveNext(next);
        }

        @Test
        public void testSaveSource() throws Exception{
                Source source = new Source("test","test","test");
                provDao.saveSource(source);
        }

        @Test
        public void testSaveReceivedParam() throws Exception{

        }

        @Test
        public void testSaveRelationInfo() throws Exception{
                RelationInfo info = new RelationInfo("test","test");
                provDao.saveRelationInfo(info);
        }

        @Test
        public void testIsNextExist() throws Exception{
                Next next = new Next("platA-2","platB","test");
                System.out.println(provDao.isNextExist(next));
        }

        @Test
        public void testGetNext() throws Exception{
                System.out.println(provDao.getNext("platA-2"));
        }

        @Test
        public void testIsSourceExist() throws Exception{
                Source source = new Source("platA-1","platA",null);
                System.out.println(provDao.isSourceExist(source));
        }

        @Test
        public void testGetSource() throws Exception{
                System.out.println(provDao.getSource("platA-1"));
        }

        @Test
        public void testIsRelationInfoExist() throws Exception{
                System.out.println(provDao.isRelationExist(new RelationInfo("platA-1", "platA-2")));
        }

        @Test
        public void testGetAncestor() throws Exception{
                System.out.println(provDao.getAncestor("platA-3"));
        }

        @Test
        public void testGetSuccessor() throws Exception{
                System.out.println(provDao.getSuccessor("platA-1"));
        }

        @Test
        public void testGetDataInfoByName() throws Exception{
                System.out.println(provDao.getDataInfoByName("data3.bmp"));
        }

        @Test
        public void testGetDataInfoByDataId() throws Exception{
                System.out.println(provDao.getDataInfoByDataId("platA-1"));
        }

        @Test
        public void testGetLastDataInfo() throws Exception{
                System.out.println(provDao.getLastDataInfo());
        }

        @Test
        public void testGetReceivedParam() throws Exception{
                ReceivedParam param = new ReceivedParam(1,"platA-2","platA","platA-1","2016-04-19_11-05-48",null);
                System.out.println(provDao.getReceivedParam(param));
        }

        @Test
        public void testGetProvByDataId() throws Exception{
                System.out.println(provDao.getProvByDataId("platA-2", 0, 10));
        }

        @Test
        public void testGetPagesByDataIdFromProv() throws Exception{
                System.out.println(provDao.getPagesByDataIdFromProv("platA-1"));
        }

        @Test
        public void testGetProvByDataIdAndActivity() throws Exception{
                System.out.println(provDao.getProvByDataIdAndActivity("platA-1", "DOWNLOAD", 0, 10));
        }

        @Test
        public void testGetPagesByDataIdAndActivity() throws Exception{
                System.out.println(provDao.getPagesByDataIdAndActivity("platA-1","DOWNLOAD"));
        }
}
