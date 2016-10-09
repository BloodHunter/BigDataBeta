package com.wbl.dao;

import com.wbl.domain.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with Simple_love
 * Date: 2016/4/6.
 * Time: 13:58
 */
@Repository("userDao")
public interface UserDao {
    void addUser(UserInfo userInfo);
    List<UserInfo> findUserByName(String name);
    List<UserInfo> findUserByEmail(String email);
}
