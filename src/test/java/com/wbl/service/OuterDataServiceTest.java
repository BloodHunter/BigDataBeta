package com.wbl.service; 

import com.wbl.dao.BaseDao;
import com.wbl.modal.MultipleDataSource;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

/** 
* OuterDataService Tester. 
* 
* @author <Simple_love> 
* @since <pre>���� 28, 2016</pre> 
* @version 1.0 
*/ 
public class OuterDataServiceTest extends BaseDao{

        @Autowired
        private OuterDataService outerDataService;

        @Before
        public void before() throws Exception { 
        
        } 
        
        @After
        public void after() throws Exception { 
        
        } 
        
        /** 
        * 
        * Method: getReaderName() 
        * 
        */ 
        @Test
        public void testGetReaderName() throws Exception { 
                //TODO: Test goes here...
                MultipleDataSource.setDataSourceKey("otherDataSource");
                outerDataService.getReaderName();
        }
        
        /** 
        * 
        * Method: exportTable(String fileName, String dataName, String userName) 
        * 
        */ 
        @Test
        public void testExportTable() throws Exception { 
                //TODO: Test goes here... 
        } 
        
                
                /** 
        * 
        * Method: writeToFile(List list, String fileName, String dataName, String userName) 
        * 
        */ 
        @Test
        public void testWriteToFile() throws Exception { 
                //TODO: Test goes here... 
                /* 
                try { 
                   Method method = OuterDataService.getClass().getMethod("writeToFile", List.class, String.class, String.class, String.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */
                MultipleDataSource.setDataSourceKey("otherDataSource");
                outerDataService.exportTable("test","book_lend","wbl");
        }

        @Test
        public void testExportData() throws Exception{
                MultipleDataSource.setDataSourceKey("otherDataSource");
                System.out.println(outerDataService.exportData("student"));
        }

        @Test
        public void testImportData() throws Exception{
                outerDataService.importData("http://10.103.241.73:8090/BigDataBeta/operate/export","student","test","wbl");
        }
} 
