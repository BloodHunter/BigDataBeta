package com.wbl.aop;

import static com.wbl.modal.Enum.Activity.*;

import com.wbl.domain.UserInfo;
import com.wbl.modal.exception.NoFileUploadException;
import com.wbl.modal.exception.NotLoginException;
import com.wbl.service.AccountService;
import com.wbl.service.ProvService;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Iterator;

/**
 * Created with Simple_love
 * Date: 2016/4/14.
 * Time: 15:02
 */
@Aspect
public class ProvAspect {


    private Logger logger = Logger.getLogger(ProvAspect.class);
    @Autowired
    private AccountService accountService;

    @Autowired
    private ProvService provService;

    @Before(value = "com.wbl.aop.AopPointCut.operate() &&" + "@annotation(activity)",argNames = "joinPoint,activity")
    public void recordProv(JoinPoint joinPoint,ProvAnnotation activity) throws Exception{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        switch (activity.value()){
            case DOWNLOAD:
                recordDownloadProv(isLogin(request),request);
                break;
            case UPLOAD:
                recordUploadProv(isLogin(request),joinPoint);
                break;
            case SPLIT:
                recordSplitProv(joinPoint);
                break;
            case AGGREGATION:
                recordAggregationProv(joinPoint);
                break;
            case EXPORT:
                recordExportProv(joinPoint);
                break;

        }
    }

    public void recordDownloadProv(UserInfo userInfo,HttpServletRequest request){
        String dataName;
        try {
            dataName = new String(request.getParameter("dataName").getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            dataName = request.getParameter("dataName");
        }
        provService.recordDownloadProv(dataName,userInfo);
    }

    public void recordUploadProv(UserInfo userInfo,JoinPoint joinPoint) throws NoFileUploadException {
        Object[] args = joinPoint.getArgs();
        MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) args[0];
        String dataName = (String) args[1];
        String description = (String) args[2];
        String type = (String) args[3];
        String category = (String) args[4];
        Iterator iterator = mulRequest.getFileNames();
        MultipartFile file = mulRequest.getFile(iterator.next().toString());
        if (file.isEmpty())
            throw new NoFileUploadException("您还未选择上传的文件");
        long size = file.getSize();
        provService.recordUploadProv(dataName, description, type, size, category, userInfo);
    }

    public void recordSplitProv(JoinPoint joinPoint) throws UnsupportedEncodingException {
        Object[] args = joinPoint.getArgs();
        String fileName = (String)args[0];
        fileName = new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
        String dataName = (String)args[1];
        String userName = (String)args[2];
        UserInfo userInfo = accountService.findUser(userName);
        if (userInfo == null)
            throw new RuntimeException("用户不存在");
        provService.recordSplitProv(fileName, dataName, userInfo);
    }

    public void recordAggregationProv(JoinPoint joinPoint) throws UnsupportedEncodingException {
        Object[] args = joinPoint.getArgs();
        String fileName = (String)args[0];
        fileName = new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
        String userName = (String)args[1];
        UserInfo userInfo = accountService.findUser(userName);
        if (userInfo == null)
            throw new RuntimeException("用户不存在");
        provService.recordAggregation(fileName, userInfo);
    }

    public void recordExportProv(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        String userName = (String)args[0];
        String dataName = (String)args[1];
        UserInfo userInfo = accountService.findUser(userName);
        if (userInfo == null)
            throw new RuntimeException("用户不存在");
        provService.recordExportProv(dataName,userInfo);
    }

    public UserInfo isLogin(HttpServletRequest request) throws NotLoginException{
        String userName = (String) request.getSession().getAttribute("user");
        if ( userName == null)
            throw new NotLoginException("您还为登录");
        return accountService.findUser(userName);
    }
}
