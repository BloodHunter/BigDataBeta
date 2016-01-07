package com.wbl.controller;

import com.wbl.dao.PageQueryDao;
import com.wbl.dao.ProvDao;
import com.wbl.domain.DataInfo;
import com.wbl.domain.Prov;
import com.wbl.domain.ReceivedParam;
import com.wbl.modal.Enum.ResultType;
import com.wbl.modal.ProvImage;
import com.wbl.service.ProvService;
import com.wbl.util.DrawImageUtil;
import com.wbl.util.HttpRequestUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;

import static com.wbl.modal.Enum.ResultType.*;
import static com.wbl.modal.Enum.QueryMessage.*;

import java.io.*;
import java.util.*;

/**
 * Created by Simple_love on 2015/10/30.
 */
@Controller
@RequestMapping("/prov")
public class ProvController {
        private static Map<String,List<String>> relationMap = new HashMap<>();
        private Logger logger = Logger.getLogger(this.getClass());

        private JSONObject dataObj = new JSONObject();

        @Autowired
        private ProvService provService;

        @Autowired
        private ProvDao provDao;

        @Autowired
        private PageQueryDao pageQueryDao;

        @RequestMapping("/index")
        public String index(){
                return "index";
        }

        @RequestMapping("/queryProvByName")
        public @ResponseBody JSONObject queryProvByName(@RequestParam("dataName") String dataName){
                List<Prov> list = new ArrayList<Prov>();
                list = provDao.getProv(1,10);
                ProvImage image = new ProvImage(list);
                image.draw(dataName);
                dataObj.put("result", SUCCESS.name());
                dataObj.put("queryID",dataName);
                dataObj.put("totalNum",image.getTotalImageNum());
                return dataObj;
        }

        @RequestMapping("/queryProvDetail")
        public @ResponseBody JSONObject queryProvDetail(@RequestParam("startRow")int startRow,
                                                        @RequestParam("endRow")int endRow){
                List<Prov> provs = pageQueryDao.getProv(startRow,endRow);
                if (provs == null || provs.isEmpty()){
                        dataObj.put("result",ERROR);
                        dataObj.put("msg","查询失败");
                }else {
                        dataObj.put("result",SUCCESS);
                        dataObj.put("provs",provs);
                }
                return dataObj;
        }

        @RequestMapping("/svgTest")
        public String svgTest(){
                return "svgTest";
        }

        @RequestMapping("/provTest")
        public String provTest(){
                return "provTest";
        }

        @RequestMapping("/imageTest")
        public @ResponseBody JSONObject uploadFile(HttpServletRequest request) throws Exception{
                CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
                if (resolver.isMultipart(request)){
                        MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
                        Iterator iterator = mulRequest.getFileNames();
                        while (iterator.hasNext()){
                                MultipartFile file = mulRequest.getFile(iterator.next().toString());
                                InputStream in = file.getInputStream();
                                byte[] buffer = new byte[1024];
                                OutputStream out = new FileOutputStream(new File("F:test.jpg"));
                                while (in.read(buffer) != -1)
                                        out.write(buffer);
                                out.close();
                                in.close();
                        }
                }
                dataObj.put("result", SUCCESS.name());
                return dataObj;
        }

        /**
        *make a query request for relation between platforms
        * @param dataName name whit query data
        * @return  dataObj contains param RESULT , RESULT may be SUCCESS or ERROR
        * */
        @RequestMapping("/queryPlatformRelation")
        public @ResponseBody JSONObject queryPlatformRelation(@RequestParam("dataName")String dataName){
                dataObj = provService.queryPlatformRelation(dataName);
                String queryId = dataObj.getString(QUERY_ID.name());
                if (SUCCESS.equals(dataObj.getString(RESULT.toString()))){
                        logger.debug("Relations between platform is " + relationMap.get(queryId));
                        DrawImageUtil.draw(relationMap.get(queryId),queryId);
                }
                relationMap.remove(queryId);
                return dataObj;
        }

        /**
         *query relations with other platform, and make a query request to up stream and down stream
         * @param param receivedParam
         * @return dataObj contains param RESULT , RESULT may be SUCCESS or ERROR
         */
        @RequestMapping("/queryRelation")
        public @ResponseBody JSONObject queryRelation(@RequestParam("receivedParam")String param){
                dataObj = provService.queryRelation(param);
                return dataObj;
        }


        /**
         * get relations when other platform report their relations
         * @param report  relations in other platform. include two key {queryId,relations}
         * @return dataObj
         */
        @RequestMapping("/reportRelation")
        public @ResponseBody JSONObject reportRelation(@RequestParam("report")String report){
                JSONObject reportParam = JSONObject.fromObject(report);
                String queryId = reportParam.getString(QUERY_ID.name());
                String relations = reportParam.getString(RELATIONS.name());
                List<String> relation = relationMap.get(queryId);
                if (relation == null)
                        relation = new ArrayList<>();
                if (relations != null && !relation.contains(relations)){
                        relation.add(relations);
                        relationMap.put(queryId,relation);
                }
                dataObj.put(RESULT,SUCCESS);
                return dataObj;
        }
}
