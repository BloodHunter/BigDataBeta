package com.wbl.dao;

import com.wbl.modal.MultipleDataSource;
import com.wbl.service.OuterDataService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with Simple_love
 * Date: 2016/4/26.
 * Time: 14:19
 */
public class OuterDataDaoTest extends BaseDao {
        @Autowired
        private OuterDataDao outerDataDao;

        @Test
        public void testGetReaderName() throws Exception{
                MultipleDataSource.setDataSourceKey("otherDataSource");
                System.out.println(outerDataDao.getReaderName());
        }

        @Test
        public void testGetBookLendInfo() throws Exception{
                MultipleDataSource.setDataSourceKey("otherDataSource");
                System.out.println(outerDataDao.getBookLendInfo());
        }

        @Test
        public void testGetStudentBasicInfo() throws Exception{
                MultipleDataSource.setDataSourceKey("otherDataSource");
                System.out.println(outerDataDao.getStudentBasicInfo());
        }

        @Test
        public void testGetStudentBookLendCount() throws Exception{
                MultipleDataSource.setDataSourceKey("otherDataSource");
                System.out.println(outerDataDao.getStudentBookLend());
        }
}
