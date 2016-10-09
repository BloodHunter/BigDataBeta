package com.wbl.modal;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * Created with Simple_love
 * Date: 2016/4/7.
 * Time: 15:59
 */
public class FtpInfo {
        private static Properties prop = null;
        private static Logger logger = Logger.getLogger(FtpInfo.class);
        public  static String FTP_HOST;
        public  static String FTP_PORT;
        public  static String FTP_USER;
        public  static String FTP_PASSWORD;
        public  static String FTP_PUBLIC_PATH;
        public  static String FTP_PRIVATE_PATH;

        static {
                try {
                        if (prop == null){
                                prop = new Properties();
                                prop.load(PlatformInfo.class.getResourceAsStream("/connectInfo.properties"));
                        }
                        FTP_HOST = prop.getProperty("ftpSource.FTP_HOST");
                        FTP_PORT = prop.getProperty("ftpSource.FTP_PORT");
                        FTP_USER = prop.getProperty("ftpSource.FTP_USER");
                        FTP_PASSWORD = prop.getProperty("ftpSource.FTP_PASSWORD");
                        FTP_PUBLIC_PATH = prop.getProperty("ftpSource.FTP_PUBLIC_PATH");
                        FTP_PRIVATE_PATH = prop.getProperty("ftpSource.FTP_PRIVATE_PATH");
                }catch (IOException e){
                        logger.error("Load platformInfo properties error " + e);
                }
        }

        public static void main(String[] args) {
                System.out.println(FtpInfo.FTP_HOST);
        }
}
