package com.wbl.service.impl; 

import com.wbl.dao.BaseDao;
import com.wbl.service.ProvService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

/** 
* ProvServiceImpl Tester. 
* 
* @author <Simple_love> 
* @since <pre>һ�� 19, 2016</pre> 
* @version 1.0 
*/ 
public class ProvServiceImplTest extends BaseDao{

        @Autowired
        private ProvService provService;

        @Before
        public void before() throws Exception { 
        
        } 
        
        @After
        public void after() throws Exception { 
        
        } 
        
        /** 
        * 
        * Method: queryPlatformRelation(String dataName) 
        * 
        */ 
        @Test
        public void testQueryPlatformRelation() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: queryRelation(String param) 
        * 
        */ 
        @Test
        public void testQueryRelation() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: queryProv(String queryId) 
        * 
        */ 
        @Test
        public void testQueryProv() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: queryProvByName(String dataName) 
        * 
        */ 
        @Test
        public void testQueryProvByName() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: getProvByPage(String dataName, String currentPage) 
        * 
        */ 
        @Test
        public void testGetProvByPage() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: produceSvgJson(String queryId) 
        * 
        */ 
        @Test
        public void testProduceSvgJson() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: getRelationBetweenPlatform(String dataId) 
        * 
        */ 
        @Test
        public void testGetRelationBetweenPlatform() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: getRelationBetweenData(String dataId) 
        * 
        */ 
        @Test
        public void testGetRelationBetweenData() throws Exception { 
                //TODO: Test goes here...
                //System.out.println(provService.getRelationBetweenData("platA-1"));
        } 
        
        /** 
        * 
        * Method: requestIsExist(ReceivedParam param) 
        * 
        */ 
        @Test
        public void testRequestIsExist() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: main(String[] args) 
        * 
        */ 
        @Test
        public void testMain() throws Exception { 
                //TODO: Test goes here... 
        } 
        
                
                /** 
        * 
        * Method: queryAdjacentRelation(ReceivedParam receivedParam) 
        * 
        */ 
        @Test
        public void testQueryAdjacentRelation() throws Exception { 
                //TODO: Test goes here... 
                /* 
                try { 
                   Method method = ProvServiceImpl.getClass().getMethod("queryAdjacentRelation", ReceivedParam.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
        } 
        /** 
        * 
        * Method: queryForUpStream(ReceivedParam param, String dataId) 
        * 
        */ 
        @Test
        public void testQueryForUpStream() throws Exception { 
                //TODO: Test goes here... 
                /* 
                try { 
                   Method method = ProvServiceImpl.getClass().getMethod("queryForUpStream", ReceivedParam.class, String.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
        } 
        /** 
        * 
        * Method: queryForDownStream(ReceivedParam param, String dataId) 
        * 
        */ 
        @Test
        public void testQueryForDownStream() throws Exception { 
                //TODO: Test goes here... 
                /* 
                try { 
                   Method method = ProvServiceImpl.getClass().getMethod("queryForDownStream", ReceivedParam.class, String.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
        } 
        /** 
        * 
        * Method: getRelatedDataId(String dataId) 
        * 
        */ 
        @Test
        public void testGetRelatedDataId() throws Exception { 
                //TODO: Test goes here... 
                /* 
                try { 
                   Method method = ProvServiceImpl.getClass().getMethod("getRelatedDataId", String.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
        } 
        /** 
        * 
        * Method: haveRelationWithOtherData(String dataId) 
        * 
        */ 
        @Test
        public void testHaveRelationWithOtherData() throws Exception { 
                //TODO: Test goes here... 
                /* 
                try { 
                   Method method = ProvServiceImpl.getClass().getMethod("haveRelationWithOtherData", String.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
        } 
        /** 
        * 
        * Method: makeSendParam(String dataId) 
        * 
        */ 
        @Test
        public void testMakeSendParam() throws Exception { 
                //TODO: Test goes here... 
                /* 
                try { 
                   Method method = ProvServiceImpl.getClass().getMethod("makeSendParam", String.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
        } 
        /** 
        * 
        * Method: makeRequestParam(String dataId) 
        * 
        */ 
        @Test
        public void testMakeRequestParam() throws Exception { 
                //TODO: Test goes here... 
                /* 
                try { 
                   Method method = ProvServiceImpl.getClass().getMethod("makeRequestParam", String.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
        } 
} 
