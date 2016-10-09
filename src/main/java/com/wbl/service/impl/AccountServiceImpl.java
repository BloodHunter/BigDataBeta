package com.wbl.service.impl;

import com.wbl.dao.DataQueryDao;
import com.wbl.dao.ProvDao;
import com.wbl.dao.UserDao;
import com.wbl.domain.DataInfo;
import com.wbl.domain.UserInfo;
import com.wbl.modal.exception.FTPException;
import com.wbl.service.AccountService;
import com.wbl.util.FtpUtil;
import com.wbl.util.TimeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with Simple_love
 * Date: 2016/4/6.
 * Time: 15:05
 */
@Repository("accountService")
public class AccountServiceImpl implements AccountService {
    private Logger logger = Logger.getLogger(AccountServiceImpl.class);
    @Resource
    private UserDao userDao;

    @Resource
    private DataQueryDao dataQueryDao;

    @Resource
    private ProvDao provDao;

    @Override
    public void register(UserInfo user){
        user.setInsertDate(TimeUtil.getCurrentTime("yyyy-MM-dd"));
        try {
            FtpUtil.makePrivateSpace(user.getUserName());
            userDao.addUser(user);
        } catch (FTPException e) {
            e.printStackTrace();
            logger.error("User register fail");
        }
    }

    @Override
    public boolean checkName(String name) {
        List<UserInfo> list = userDao.findUserByName(name);
        return list.isEmpty();
    }

    @Override
    public boolean checkEmail(String email) {
        List<UserInfo> list = userDao.findUserByEmail(email);
        return list.isEmpty();
    }

    @Override
    public boolean login(String account, String password) {
        return loginByName(account,password) || loginByEmail(account,password);
    }

    @Override
    public UserInfo findUser(String account) {
        List<UserInfo> list = userDao.findUserByName(account);
        if (!list.isEmpty()){
            return list.get(0);
        }
        list = userDao.findUserByEmail(account);
        return list.get(0);
    }

    @Override
    public List<DataInfo> getDataInfoByUserUpload(int ownerId) {
        return dataQueryDao.getDataInfoByUserUpload(ownerId);
    }

    @Override
    public List<DataInfo> getDataInfoByUserDownload(String userName) throws FTPException {
        List<String> names = FtpUtil.getFileNamesInDir(userName);
        List<DataInfo> list = new ArrayList<>();
        for (String dataName:names){
            list.add(provDao.getDataInfoByName(dataName));
        }
        return list;
    }

    private boolean loginByName(String name,String password){
        List<UserInfo> list = userDao.findUserByName(name);
        return !list.isEmpty() && password.equals(list.get(0).getPassword());
    }

    private boolean loginByEmail(String email,String password){
        List<UserInfo> list = userDao.findUserByEmail(email);
        return !list.isEmpty() && password.equals(list.get(0).getPassword());
    }
}
