package com.wbl.controller;

import static com.wbl.modal.Enum.ResultType.*;

import com.wbl.aop.ProvAnnotation;
import com.wbl.domain.UserInfo;
import com.wbl.modal.Enum.Activity;
import com.wbl.modal.exception.FTPException;
import com.wbl.service.AccountService;
import com.wbl.util.FtpUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with Simple_love
 * Date: 2016/4/1.
 * Time: 13:56
 */
@Controller
@RequestMapping("/account")
public class AccountController {

        private Logger logger = Logger.getLogger(AccountController.class);

        private JSONObject result = new JSONObject();

        @Resource
        private AccountService accountService;

        @RequestMapping("/register")
        public String register_index(){
                return "account/register";
        }

        @RequestMapping("/index")
        public String index(){
                return "account/login";
        }

        @RequestMapping("/profile")
        @ProvAnnotation(Activity.LOGIN)
        public ModelAndView profile(HttpServletRequest request) throws Exception{
                String account = (String) request.getSession().getAttribute("user");
                UserInfo userInfo = accountService.findUser(account);
                ModelAndView view = new ModelAndView("account/profile");
                view.addObject("userInfo",userInfo);
                view.addObject("uploadInfo",accountService.getDataInfoByUserUpload(userInfo.getUserId()));
                view.addObject("downloadInfo",accountService.getDataInfoByUserDownload(userInfo.getUserName()));
                return view;
        }

        @RequestMapping("/register_user")
        public void register_user(@RequestParam("user")String user,HttpServletResponse response,HttpServletRequest request) throws FTPException {
                UserInfo userInfo = (UserInfo) JSONObject.toBean(JSONObject.fromObject(user), UserInfo.class);
                System.out.println(userInfo);
                //accountService.register(userInfo);
                request.getSession().setAttribute("user",userInfo.getUserName());
                FtpUtil.makePrivateSpace(userInfo.getUserName());               //建立用户的私人空间
                try {
                        response.sendRedirect("index");
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        @RequestMapping("/login")
        @ResponseBody
        public JSONObject login(HttpSession session,@RequestParam("account")String account,
                                @RequestParam("password")String password){
                if (accountService.login(account,password)){
                        result.put(RESULT,SUCCESS);
                        session.setAttribute("user",accountService.findUser(account).getUserName());
                }else{
                        result.put(RESULT,ERROR);
                        result.put("msg","账号不存在或者密码错误");
                }
                return result;
        }

        @RequestMapping("/logout")
        public void logout(HttpServletResponse response,HttpServletRequest request){
                request.getSession().invalidate();
                request.removeAttribute("user");
                response.setHeader("Pragma", "No-cache");
                response.setHeader("Cache-Control", "no-cache");
                try {
                        response.sendRedirect(request.getHeader("Referer"));
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        @RequestMapping("/checkName")
        @ResponseBody
        public JSONObject checkName(@RequestParam("name")String name){
                if (accountService.checkName(name))
                        result.put(RESULT,SUCCESS);
                else
                        result.put(RESULT,ERROR);
                return result;
        }

        @RequestMapping("/checkEmail")
        @ResponseBody
        public JSONObject checkEmail(@RequestParam("email")String email){
                if (accountService.checkEmail(email))
                        result.put(RESULT,SUCCESS);
                else
                        result.put(RESULT,ERROR);
                return result;
        }

}
