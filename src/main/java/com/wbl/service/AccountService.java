package com.wbl.service;

import com.wbl.domain.DataInfo;
import com.wbl.domain.UserInfo;
import com.wbl.modal.exception.FTPException;

import java.util.List;

/**
 * Created with Simple_love
 * Date: 2016/4/6.
 * Time: 15:01
 */
public interface AccountService {
        void register(UserInfo user) throws FTPException;
        boolean checkName(String name);
        boolean checkEmail(String email);
        boolean login(String account,String password);
        UserInfo findUser(String account);
        List<DataInfo> getDataInfoByUserUpload(int ownerId);
        List<DataInfo> getDataInfoByUserDownload(String userName) throws FTPException;
}
