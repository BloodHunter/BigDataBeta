package com.wbl.modal;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Simple_love on 2016/1/4.
 */
public class PlatformInfo {
        private static Logger logger = Logger.getLogger(PlatformInfo.class);
        private static Properties prop = null;
        public static  String PLATFORM_NAME ;
        public static  String PLATFORM_REPORT_URL ;
        public static  String PLATFORM_QUERY_URL;
        public static String PLATFORM_QUERY_PROV_URL;
        static {
                try {
                        if (prop == null){
                                prop = new Properties();
                                prop.load(PlatformInfo.class.getResourceAsStream("/platformInfo.properties"));
                        }
                        PLATFORM_NAME = prop.getProperty("platformName");
                        PLATFORM_QUERY_URL = prop.getProperty("queryUrl");
                        PLATFORM_REPORT_URL = prop.getProperty("reportUrl");
                        PLATFORM_QUERY_PROV_URL = prop.getProperty("queryProvUrl");
                }catch (IOException e){
                        logger.error("Load platformInfo properties error " + e);
                }
        }

        public static void main(String[] args) {
                System.out.println(PLATFORM_NAME);
        }
}
