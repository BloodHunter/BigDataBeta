package com.wbl.controller;

import com.wbl.aop.ProvAnnotation;
import com.wbl.domain.DataInfo;
import com.wbl.domain.UserInfo;
import com.wbl.modal.Enum.Activity;
import com.wbl.service.ProvService;
import com.wbl.util.DrawImageUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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


        @RequestMapping("/index")
        public String index(){
                return "prov/index";
        }

        /**
        *make a query request for relation between platforms
        * @param dataName name whit query data
        * @return  dataObj contains param RESULT , RESULT may be SUCCESS or ERROR
        * */
        @ProvAnnotation(Activity.QUERY)
        @RequestMapping("/queryPlatformRelation")
        public @ResponseBody JSONObject queryPlatformRelation(@RequestParam("dataName")String dataName) throws Exception{
                dataObj = provService.queryPlatformRelation(dataName);
                String queryId = dataObj.getString(QUERY_ID.name());
                if (SUCCESS.equals(dataObj.getString(RESULT.toString()))){
                        logger.debug("Relations between platform is " + relationMap.get(queryId));
                        //DrawImageUtil.draw(relationMap.get(queryId),queryId);
                }
                //relationMap.remove(queryId);
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
                        relationMap.put(queryId, relation);
                }
                dataObj.put(RESULT, SUCCESS);
                return dataObj;
        }


        /**
         * Show relation between platform by svg
         * @param queryId id of query
         * @param response response
         * @return svg
         */
        @RequestMapping("/getSvgOfRelation")
        public HttpEntity getSvgOfRelation(@RequestParam("queryId")String queryId,HttpServletResponse response){
                byte[] svg = DrawImageUtil.draw(relationMap.get(queryId));
                response.setHeader("Access-Control-Allow-Origin","*");
                response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
                response.setHeader("Access-Control-Allow-Headers", "Content-Type");
                InputStream in = new ByteArrayInputStream(svg);
                try {
                        StreamUtils.copy(in,response.getOutputStream());
                        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
                } catch (IOException e) {
                        e.printStackTrace();
                }finally {
                        relationMap.remove(queryId);
                }
                return new ResponseEntity(HttpStatus.OK);
        }

        /**
         * Show relation between data
         * @param queryUrl url of query data relation
         * @param queryId id of query id
         * @return ModelAndView
         */
        @RequestMapping("/showDataRelation")
        public ModelAndView showDataRelation(@RequestParam("queryUrl")String queryUrl,
                                             @RequestParam("queryId")String queryId){
                ModelAndView mav = new ModelAndView();
                mav.setViewName("prov/showDataRelation");
                mav.addObject("queryId", queryId);
                mav.addObject("url", queryUrl);
                return mav;
        }


        /**
         * Get svg which is description of relation between data
         * @param queryId
         * @param response
         * @return
         */
        @RequestMapping("/getDataRelation")
        public HttpEntity getDataRelation(@RequestParam("queryId")String queryId,HttpServletResponse response){
                byte[] svg = DrawImageUtil.draw(provService.produceDotString(queryId));
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
                response.setHeader("Access-Control-Allow-Headers", "Content-Type");
                InputStream in = new ByteArrayInputStream(svg);
                try {
                        StreamUtils.copy(in,response.getOutputStream());
                        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return new ResponseEntity(HttpStatus.OK);
        }

        /**
         * Show information of data more detailed
         * @param url  url of get data information
         * @param dataName name of data
         * @return ModelAndView
         */
        @RequestMapping("/showDataInfo")
        public ModelAndView showDataInfo(@RequestParam("queryUrl")String url,
                                         @RequestParam("dataName")String dataName){
                ModelAndView view = new ModelAndView();
                view.setViewName("prov/showDataInfo");
                view.addObject("queryUrl", url);
                view.addObject("dataName", dataName);
                return view;
        }

        @RequestMapping("/getProvByPage")
        public @ResponseBody JSONObject getProvByPage(@RequestParam("dataName")String dataName,
                                                      @RequestParam("currentPage")String currentPage,
                                                      HttpServletResponse response){
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
                response.setHeader("Access-Control-Allow-Headers", "Content-Type");
                return provService.getProvByPage(dataName,currentPage);
        }

        @RequestMapping("/getProvByActivity")
        @ResponseBody
        public JSONObject getProvByActivity(@RequestParam("dataName")String dataName, @RequestParam("activity")String activity,
                                            @RequestParam("currentPage")String currentPage,HttpServletResponse response){
                setHeader(response);
                return provService.getProvByActivity(dataName,activity,currentPage);
        }

        @RequestMapping("/callbackForDownload")
        @ResponseBody
        public JSONObject callbackForDownload(@RequestParam("platform")String platform,@RequestParam("url")String url,
                                              @RequestParam("dataInfo")String dataInfo){
                DataInfo info = (DataInfo) JSONObject.toBean(JSONObject.fromObject(dataInfo), DataInfo.class);
                provService.recordDownloadProvFromOtherPlatform(platform, url, info);
                dataObj.put(RESULT, SUCCESS);
                return dataObj;
        }

        @RequestMapping("/getDataInfo")
        @ResponseBody
        public JSONObject getDataInfo(@RequestParam("dataName")String dataName,HttpServletResponse response){
                setHeader(response);
                return provService.getDataInfo(dataName);
        }

        private void setHeader(HttpServletResponse response){
                response.setHeader("Access-Control-Allow-Origin","*");
                response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
                response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        }

        /*@Deprecated
        @RequestMapping("/showProvDetail")
        public ModelAndView showProvDetail(@RequestParam("queryUrl") String url,
                                     @RequestParam("queryId")String queryId){
                ModelAndView mav = new ModelAndView();
                mav.setViewName("showProvDetail");
                mav.addObject("queryId",queryId);
                mav.addObject("url",url);
                return mav;
        }

        @Deprecated
        @RequestMapping("/produceSvgJson")
        public @ResponseBody JSONObject produceSvgJson(@RequestParam("queryId")String queryId){
                return provService.produceSvgJson(queryId);
        }


        @Deprecated
        @RequestMapping("/produceSvgByDotString")
        public HttpEntity produceSvgByDotString(@RequestParam("queryId")String queryId,HttpServletResponse response){
                byte[] svg = DrawImageUtil.draw(provService.produceDotString(queryId));
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
                response.setHeader("Access-Control-Allow-Headers", "Content-Type");
                InputStream in = new ByteArrayInputStream(svg);
                try {
                        StreamUtils.copy(in,response.getOutputStream());
                        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return new ResponseEntity(HttpStatus.OK);
        }

        @Deprecated
        @RequestMapping("/showMoreDetail")
        public ModelAndView showMoreDetail(@RequestParam("queryUrl")String url,
                                           @RequestParam("dataName")String dataName){
                ModelAndView view = new ModelAndView();
                view.setViewName("showMoreDetail");
                view.addObject("queryUrl", url);
                view.addObject("dataName", dataName);
                return view;
        }*/

}
