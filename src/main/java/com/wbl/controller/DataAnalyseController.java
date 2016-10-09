package com.wbl.controller;

import com.wbl.service.DataAnalyseService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with Simple_love
 * Date: 2016/3/29.
 * Time: 13:54
 */
@Controller
@RequestMapping("/analyse")
public class DataAnalyseController {
        @Resource
        private DataAnalyseService dataAnalyseService;

        @RequestMapping("/getDataOperateTimesInDay")
        @ResponseBody
        public JSONArray getDataOperateTimesInDay(@RequestParam("dataName")String dataName,
                                                 @RequestParam("month")String month,@RequestParam("year")String year,
                                                  HttpServletResponse response){
                setHeader(response);
                JSONArray result = dataAnalyseService.getDataOperateTimesInDay(dataName, month, year);
                return result;
        }

        @RequestMapping("/getDataOperateTimesInMonth")
        @ResponseBody
        public JSONArray getDataOperateTimesInMonth(@RequestParam("dataName")String dataName,
                                                    @RequestParam("year")String year,HttpServletResponse response){
                setHeader(response);
                JSONArray result = dataAnalyseService.getDataOperateTimesInMonth(dataName, year);
                return result;
        }

        @RequestMapping("/getDataOperateTimesInYear")
        @ResponseBody
        public JSONArray getDataOperateTimesInYear(@RequestParam("dataName")String dataName,
                                                   HttpServletResponse response){
                setHeader(response);
                JSONArray result = dataAnalyseService.getDataOperateTimesInYear(dataName);
                return result;
        }

        @RequestMapping("/getDataOperateTimesByTimes")
        @ResponseBody
        public JSONArray getDataOperateTimesByTimes(@RequestParam("dataName")String dataName,
                                                   HttpServletResponse response){
                setHeader(response);
                JSONArray result = dataAnalyseService.getDataOperateTimesByType(dataName);
                return result;
        }

        @RequestMapping("/getHotData")
        @ResponseBody
        public JSONObject getHotData(){
                return dataAnalyseService.getHotData();
        }

        @RequestMapping("/getLikeData")
        @ResponseBody
        public JSONObject getLikeData(@RequestParam("dataName")String dataName){
                return dataAnalyseService.getLikeData(dataName);
        }

        @RequestMapping("/getUserRecommendData")
        @ResponseBody
        public JSONObject getUserRecommendData(HttpServletRequest request){
                String userName = (String) request.getSession().getAttribute("user");
                return dataAnalyseService.getUserRecommendData(userName);
        }

        @RequestMapping("/test")
        public String test(){
                return "prov/showDataInfo";
        }

        private void setHeader(HttpServletResponse response){
                response.setHeader("Access-Control-Allow-Origin","*");
                response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
                response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        }
}
