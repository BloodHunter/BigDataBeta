package com.wbl.dao;

import com.wbl.domain.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with Simple_love
 * Date: 2016/4/6.
 * Time: 14:21
 */
public class UserDaoTest extends BaseDao{

        @Autowired
        private UserDao userDao;

        @Test
        public void testAddUser() throws Exception{
                UserInfo userInfo = new UserInfo("wbl","123@902.com","123","100000");
                userDao.addUser(userInfo);
        }

        @Test
        public void testFindUserByName() throws Exception{
                System.out.println(userDao.findUserByName(null));
        }

        @Test
        public void testFindUserByEmail() throws Exception{
                System.out.println(userDao.findUserByEmail("123@902.com"));
        }
}
