package com.wbl.dao.impl; 

import com.wbl.dao.BaseDao;
import com.wbl.dao.ProvDao;
import com.wbl.domain.Next;
import com.wbl.domain.Source;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

/** 
* ProvDaoImpl Tester. 
* 
* @author <Simple_love> 
* @since <pre>һ�� 4, 2016</pre> 
* @version 1.0 
*/ 
public class ProvDaoImplTest extends BaseDao{

        @Autowired
        private ProvDao provDao;
        @Before
        public void before() throws Exception { 
        
        } 
        
        @After
        public void after() throws Exception { 
        
        } 
        
        /** 
        * 
        * Method: getProv() 
        * 
        */ 
        @Test
        public void testGetProv() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: getProv(String agent) 
        * 
        */ 
        @Test
        public void testGetProvAgent() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: getProv(int start, int end) 
        * 
        */ 
        @Test
        public void testGetProvForStartEnd() throws Exception { 
                //TODO: Test goes here...
                System.out.println(provDao.getProv(1,3));
        } 
        
        /** 
        * 
        * Method: save(Prov prov) 
        * 
        */ 
        @Test
        public void testSaveProv() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: update(Prov prov) 
        * 
        */ 
        @Test
        public void testUpdateProv() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: save(DataInfo dataInfo) 
        * 
        */ 
        @Test
        public void testSaveDataInfo() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: update(DataInfo dataInfo) 
        * 
        */ 
        @Test
        public void testUpdateDataInfo() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: save(Next next) 
        * 
        */ 
        @Test
        public void testSaveNext() throws Exception { 
                //TODO: Test goes here...
                System.out.println( provDao.save(new Next("platA-1","platB","platB_URL")));
        } 
        
        /** 
        * 
        * Method: save(Source source) 
        * 
        */ 
        @Test
        public void testSaveSource() throws Exception { 
                //TODO: Test goes here...
                System.out.println(provDao.save(new Source("platA-1","platA","platA-URL")));
        } 
        
        /** 
        * 
        * Method: save(RelationInfo relationInfo) 
        * 
        */ 
        @Test
        public void testSaveRelationInfo() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: getNext(String dataID) 
        * 
        */ 
        @Test
        public void testGetNext() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: getSource(String dataID) 
        * 
        */ 
        @Test
        public void testGetSource() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: getAncestor(String dataID) 
        * 
        */ 
        @Test
        public void testGetAncestor() throws Exception { 
                //TODO: Test goes here... 
        } 
        
        /** 
        * 
        * Method: getSuccessor(String dataID) 
        * 
        */ 
        @Test
        public void testGetSuccessor() throws Exception { 
                //TODO: Test goes here... 
        } 
        
                
                /** 
        * 
        * Method: isExist(Next next) 
        * 
        */ 
        @Test
        public void testIsExistNext() throws Exception { 
                //TODO: Test goes here... 
                /* 
                try { 
                   Method method = ProvDaoImpl.getClass().getMethod("isExist", Next.class); 
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
        * Method: isExist(Source source) 
        * 
        */ 
        @Test
        public void testIsExistSource() throws Exception { 
                //TODO: Test goes here... 
                /* 
                try { 
                   Method method = ProvDaoImpl.getClass().getMethod("isExist", Source.class); 
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
        * Method: isExist(RelationInfo relationInfo) 
        * 
        */ 
        @Test
        public void testIsExistRelationInfo() throws Exception { 
                //TODO: Test goes here... 
                /* 
                try { 
                   Method method = ProvDaoImpl.getClass().getMethod("isExist", RelationInfo.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
        } 
} 
