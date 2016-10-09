package com.wbl.modal.exception;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with Simple_love
 * Date: 2016/4/18.
 * Time: 10:24
 */
public class GlobalExceptionResolver extends SimpleMappingExceptionResolver {
        private Logger logger = Logger.getLogger(GlobalExceptionResolver.class);
        @Override
        protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
                                                  Object handler, Exception ex) {
                String viewName = determineViewName(ex, request);
                response.setCharacterEncoding("UTF-8");
                logger.debug(viewName);
                if (viewName != null){
                        if (!(request.getHeader("accept").contains("application/json")) ||
                                (request.getHeader("X-Requested-With")!=null &&
                                        request.getHeader("X-Requested-With").contains("XMLHttpRequest"))){
                                Integer statusCoder = determineStatusCode(request,viewName);
                                if (statusCoder != null)
                                        applyStatusCodeIfPossible(request,response,statusCoder);
                                logger.debug("JSP格式返回" + viewName);
                                return getModelAndView(viewName,ex,request);
                        }else {
                                try {
                                        PrintWriter writer = response.getWriter();
                                        writer.write(ex.getMessage());
                                        writer.flush();
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                                logger.debug("JSON格式返回" + viewName);
                                return null;
                        }
                }else{
                        return null;
                }
        }
}
