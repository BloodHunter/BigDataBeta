package com.wbl.controller;

import com.wbl.service.DataOperateService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with Simple_love
 * Date: 2016/4/13.
 * Time: 9:08
 */
@Controller
public class DataQueryController {

        private JSONObject result = new JSONObject();

        @Autowired
        private DataOperateService dataOperateService;

        @RequestMapping("/data/list")
        public String index(){
                return "data/index";
        }

        @RequestMapping("/data/query")
        @ResponseBody
        public JSONObject query(@RequestParam("type")String type,@RequestParam("size")int size,
                                @RequestParam("source")int source,@RequestParam("sort")int sort,@RequestParam("category")int category,
                                @RequestParam("currentPage")int currentPage){
                return dataOperateService.queryData(type,size,source,sort,category,currentPage);
        }

        @RequestMapping("/data/category")
        public ModelAndView showDataByCategory(@RequestParam("category")String category ){
                ModelAndView view = new ModelAndView("data/category");
                view.addObject("category",category);
                return view;
        }

}
