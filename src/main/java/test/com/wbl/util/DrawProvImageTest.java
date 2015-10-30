package test.com.wbl.util;

import com.wbl.dao.ProvDao;
import com.wbl.domain.Prov;
import com.wbl.modal.ProvImage;
import com.wbl.util.DrawProvImage;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * DrawProvImage Tester.
 *
 * @author <Authors name>
 * @since <pre>ʮ�� 30, 2015</pre>
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DrawProvImageTest {

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
         * Method: draw()
         *
         */
        @Test
        public void testDraw() throws Exception {
//TODO: Test goes here...
                List<Prov> provs = provDao.getProv(1,10);
                DrawProvImage drawProvImage = new DrawProvImage("download1.jpg",provs);
                drawProvImage.draw();
        }

        @Test
        public void testDrawForOther() throws Exception{
                List<Prov> provs = provDao.getProv(11,25);
                //DrawProvImage drawProvImage = new DrawProvImage("other.jpg",provs);
                ProvImage image = new ProvImage(provs,10);
                image.draw("other");
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


}
