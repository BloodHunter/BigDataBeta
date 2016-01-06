package test.com.wbl.dao;

import com.wbl.dao.PageQueryDao;
import com.wbl.domain.Prov;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Simple_love on 2015/11/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PageQueryTest {

        @Autowired
        private PageQueryDao pageQueryDao;

        @Test
        public void testGetProv() throws Exception{
                List<Prov> provs = pageQueryDao.getProv(60,100);
                System.out.println(provs);
        }
}
