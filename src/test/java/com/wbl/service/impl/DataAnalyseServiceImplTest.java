package com.wbl.service.impl; 

import com.wbl.dao.BaseDao;
import com.wbl.service.DataAnalyseService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

/** 
* DataAnalyseServiceImpl Tester. 
* 
* @author <Simple_love> 
* @since <pre>���� 3, 2016</pre> 
* @version 1.0 
*/ 
public class DataAnalyseServiceImplTest extends BaseDao{

        @Autowired
        private DataAnalyseService dataAnalyseService;

        @Before
        public void before() throws Exception { 
        
        } 
        
        @After
        public void after() throws Exception { 
        
        } 
        
        /** 
        * 
        * Method: getDataOperateTimesInDay(String dataName, String month, String year) 
        * 
        */ 
        @Test
        public void testGetDataOperateTimesInDay() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: getDataOperateTimesInMonth(String dataName, String year) 
        * 
        */ 
        @Test
        public void testGetDataOperateTimesInMonth() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: getDataOperateTimesInYear(String dataName) 
        * 
        */ 
        @Test
        public void testGetDataOperateTimesInYear() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: getDataOperateTimesByType(String dataName) 
        * 
        */ 
        @Test
        public void testGetDataOperateTimesByType() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: getHotData() 
        * 
        */ 
        @Test
        public void testGetHotData() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: getLikeData(String dataName) 
        * 
        */ 
        @Test
        public void testGetLikeData() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: getUserRecommendData(String userName) 
        * 
        */ 
        @Test
        public void testGetUserRecommendData() throws Exception { 
                //TODO: Test goes here...
                System.out.println(dataAnalyseService.getUserRecommendData("platC"));
        } 
        
                
                /** 
        * 
        * Method: getSortedMapByValue(Map<String,Integer> map) 
        * 
        */ 
        @Test
        public void testGetSortedMapByValue() throws Exception { 
                //TODO: Test goes here... 
                /* 
                try { 
                   Method method = DataAnalyseServiceImpl.getClass().getMethod("getSortedMapByValue", Map<String,Integer>.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
        } 
} 
