package com.wbl.controller;

import static com.wbl.modal.Enum.ResultType.*;
import static com.wbl.modal.Enum.Activity.*;

import com.wbl.aop.ProvAnnotation;
import com.wbl.domain.DataInfo;
import com.wbl.modal.exception.FTPException;
import com.wbl.service.DataOperateService;
import com.wbl.service.OuterDataService;
import com.wbl.util.FtpUtil;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created with Simple_love
 * Date: 2016/4/8.
 * Time: 9:38
 */
@Controller
@RequestMapping("/operate")
public class DataOperateController {


        private Logger logger = Logger.getLogger(DataOperateController.class);

        private JSONObject result = new JSONObject();
        @Autowired
        private DataOperateService dataOperateService;

        @Autowired
        private OuterDataService outerDataService;

        @RequestMapping("/upload/index")
        public String uploadIndex(){
                return "operate/upload";
        }

        @RequestMapping("/upload")
        @ResponseBody
        @ProvAnnotation(UPLOAD)
        public JSONObject upload(HttpServletRequest request,@RequestParam("dataName")String dataName,
                                 @RequestParam("description")String description,@RequestParam("type")String type,
                                 @RequestParam("category")String category) throws Exception{

                CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
                if (resolver.isMultipart(request)){
                        MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
                        Iterator iterator = mulRequest.getFileNames();
                        while (iterator.hasNext()){
                                MultipartFile file = mulRequest.getFile(iterator.next().toString());
                                /*DataInfo dataInfo = new DataInfo(dataName,type,file.getSize(),description,getCategory(category));
                                System.out.println(dataInfo);*/
                                FtpUtil.fileUploadToPrivate(file.getInputStream(),dataName,file.getOriginalFilename(), (String) request.getSession().getAttribute("user"));
                        }
                }
                result.put(RESULT, SUCCESS);
                return result;
        }

        @RequestMapping("/download")
        @ProvAnnotation(DOWNLOAD)
        public ResponseEntity<byte[]> download(@RequestParam("dataName")String dataName) throws Exception {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentDispositionFormData("attachment", dataName);
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                return new ResponseEntity<byte[]>(dataOperateService.fileDownload(dataName),
                        headers, HttpStatus.CREATED);
        }

        @RequestMapping("/split")
        @ResponseBody
        @ProvAnnotation(SPLIT)
        public JSONObject splitTable(@RequestParam("fileName")String fileName,
                                      @RequestParam("dataName")String dataName,@RequestParam("userName")String userName) throws Exception{
                return outerDataService.exportTable(fileName,dataName,userName);
        }

        @RequestMapping("/aggregation")
        @ResponseBody
        @ProvAnnotation(AGGREGATION)
        public JSONObject aggregation(@RequestParam("fileName")String fileName,
                                      @RequestParam("userName")String userName) throws Exception{
                return outerDataService.aggregation(fileName, userName);
        }


        @RequestMapping("/export")
        @ResponseBody
        @ProvAnnotation(EXPORT)
        public JSONObject exportData(@RequestParam("userName")String userName,
                                 @RequestParam("dataName")String dataName){
                return outerDataService.exportData(dataName);
        }

        @RequestMapping("/import")
        @ResponseBody
        @ProvAnnotation(IMPORT)
        public JSONObject importData(@RequestParam("url")String url,
                                     @RequestParam("srcName")String srcName,@RequestParam("destName")String destName,
                                     @RequestParam("userName")String userName){
                return outerDataService.importData(url,srcName,destName,userName);
        }
}
