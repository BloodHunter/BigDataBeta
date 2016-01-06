package test.com.wbl.dao.impl;

import com.wbl.dao.ProvDao;
import com.wbl.domain.Prov;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * ProvDaoImpl Tester.
 *
 * @author <Authors name>
 * @since <pre>ʮ�� 26, 2015</pre>
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ProvDaoImplTest {

        private enum ACTIVITY{
                DOWNLOAD,UPLOAD,AGGRAGATION,IMPORT,EXPORT
        }

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
                List<Prov> provs = provDao.getProv();
                System.out.println(provs);

                provs = provDao.getProv(1,5);
                System.out.println(provs);
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
         * Method: saveProv(Prov prov)
         *
         */
        @Test
        public void testSaveProv() throws Exception {
                List<Prov> provs = new ArrayList<Prov>();
                String time = "2015-10-27 09:32:00";
                /*Platform A*/
                provs.add(new Prov("platA","platA-1","userA-1",ACTIVITY.UPLOAD.name(),null,time));
                /*Platform B*/
                provs.add(new Prov("platB","platB-1","userB-1",ACTIVITY.UPLOAD.name(),null,time));
                provs.add(new Prov("platB","platA-1","platB",ACTIVITY.DOWNLOAD.name(),"platA:platA-1",time));
                provs.add(new Prov("platB","platB-2","userB-2",ACTIVITY.AGGRAGATION.name(),"platA:platA-1",time));
                provs.add(new Prov("platB","platB-2","userB-2",ACTIVITY.AGGRAGATION.name(),"platB:platB-1",time));
                provs.add(new Prov("platB","platB-3","platC",ACTIVITY.EXPORT.name(),"platB:platB-2",time));
                provs.add(new Prov("platB","platB-4","platD",ACTIVITY.EXPORT.name(),"platB:platB-2",time));
                provs.add(new Prov("platB","platB-5","platE",ACTIVITY.EXPORT.name(),"platB:platB-2",time));

                /*Platform C*/
                provs.add(new Prov("platC","platB-3","platC",ACTIVITY.IMPORT.name(),"platB:platB-3",time));

                /*Platform D*/
                provs.add(new Prov("platD","platB-4","platD",ACTIVITY.IMPORT.name(),"platB:platB-4",time));

                /*Platform E*/
                provs.add(new Prov("platE","platB-5","platE",ACTIVITY.IMPORT.name(),"platB:platB-5",time));

                /*Platform F*/
                provs.add(new Prov("platF","platA-1","platF",ACTIVITY.DOWNLOAD.name(),"platA:platA-1",time));
                provs.add(new Prov("platF","platB-3","platF",ACTIVITY.DOWNLOAD.name(),"platC:platB-3",time));
                provs.add(new Prov("platF","platF-2","platF",ACTIVITY.AGGRAGATION.name(),"platF:platB-3",time));
                provs.add(new Prov("platF","platF-2","platF",ACTIVITY.AGGRAGATION.name(),"platF:platA-1",time));

                for (Prov prov : provs)
                        provDao.save(prov);
        }

        /**
         *
         * Method: updateProv(Prov prov)
         *
         */
        @Test
        public void testUpdateProv() throws Exception {
//TODO: Test goes here...
        }

        @Test
        public void testInsertProv() throws Exception{
                String flag = "A";
                String prefix = "plat" + flag;
                int count = 50;
                String time = "2015-10-27 09:32:00";
                for (int i = 1; i <= count; i++){
                        provDao.save(new Prov(prefix,prefix+"-1","user" +flag +"-" +i,ACTIVITY.DOWNLOAD.name(),
                                prefix + ":" +prefix+"-1",time));
                }
        }
}
