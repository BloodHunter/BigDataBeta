package com.wbl.util;

import com.wbl.modal.FtpInfo;
import com.wbl.modal.exception.FTPException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created with Simple_love
 * Date: 2016/4/7.
 * Time: 16:37
 */
public class FtpUtil {
        private static Logger logger = Logger.getLogger(FtpUtil.class);

        /**
         * Get connection with FTP server
         * @return ftpClient
         * @throws FTPException Connect to ftp server fail
         */
        public static FTPClient getConnect() throws FTPException {
                FTPClient ftpClient = new FTPClient();
                try {
                        ftpClient.connect(FtpInfo.FTP_HOST, Integer.parseInt(FtpInfo.FTP_PORT));
                        ftpClient.login(FtpInfo.FTP_USER,FtpInfo.FTP_PASSWORD);
                        ftpClient.setControlEncoding("GBK");
                        FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
                        conf.setServerLanguageCode("zh");
                        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置文件类型
                        //ftpClient.enterRemotePassiveMode();
                } catch (IOException e) {
                        e.printStackTrace();
                        throw new FTPException("Connect to ftp server fail");
                }
                return ftpClient;
        }

        /**
         * Make a dir as private space for user
         * @param name User's private space is named by userName;
         * @throws FTPException Make private space fail
         */
        public static void makePrivateSpace(String name) throws FTPException {
                FTPClient ftpClient = null;
                ftpClient = getConnect();
                try {
                        try {
                                ftpClient.makeDirectory(parseName(name));
                        }finally {
                                ftpClient.logout();
                        }
                } catch ( IOException e) {
                        e.printStackTrace();
                        throw new FTPException("Make private space fail");
                }
        }

        /**
         * Upload file  which has not been published to private space of platform and private space of user
         * @param is file stream
         * @param dataName data name
         * @param fileName data original name
         * @param path path of user private space
         * @throws FTPException File upload to private space fail
         */
        public static void fileUploadToPrivate(InputStream is,String dataName,String fileName,String path) throws FTPException {
                dataName = parseName(getOriginalName(dataName, fileName));
                path = File.separator + parseName(path);
                upload(is,dataName,FtpInfo.FTP_PRIVATE_PATH);
                ByteArrayInputStream in = null;
                ByteArrayOutputStream fos = new ByteArrayOutputStream();
                FTPClient ftpClient = getConnect();
                try {
                        try {
                                ftpClient.changeWorkingDirectory(FtpInfo.FTP_PRIVATE_PATH);
                                ftpClient.retrieveFile(dataName, fos);
                                in = new ByteArrayInputStream(fos.toByteArray());
                                ftpClient.changeWorkingDirectory(path);
                                ftpClient.storeFile(dataName, in);
                        }finally {
                                fos.close();
                               if (in!=null)
                                       in.close();
                                ftpClient.logout();
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                        throw new FTPException("File upload to private space fail");
                }
        }

        /**
         * upload file to FTP server
         * @param in file stream
         * @param dataName data name
         * @param path file path in FTP server
         * @throws FTPException File upload to ftp server fail
         */
        private static void upload(InputStream in,String dataName,String path) throws FTPException {
                FTPClient ftpClient = null;
                ftpClient = getConnect();
                try {
                        try{
                                ftpClient.changeWorkingDirectory(path);
                                ftpClient.storeFile(dataName, in);
                        }finally {
                                in.close();
                                ftpClient.logout();
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                        throw new FTPException("File upload to ftp server fail");
                }
        }

        /**
         * Download file from public space of platform
         * @param dataName  name of downloaded file
         * @return file stream
         * @throws FTPException File download from public fail
         */
        public static byte[] fileDownloadFromPublic(String dataName) throws FTPException {
                FTPClient ftpClient = getConnect();
                dataName = parseName(dataName);
                try {
                       try {
                               ftpClient.changeWorkingDirectory(FtpInfo.FTP_PUBLIC_PATH);
                               InputStream in = ftpClient.retrieveFileStream(dataName);
                               return IOUtils.toByteArray(in);
                       }finally {
                               ftpClient.logout();
                       }
                } catch (IOException e) {
                        e.printStackTrace();
                        throw new FTPException("File download from public fail");
                }
        }

        /**
         * Move file which is in private space to public space
         * @param dataName name of moved file
         * @throws FTPException File move from private to public fail
         */
        public static void fileMoveFromPrivateToPublic(String dataName) throws FTPException {
                dataName = parseName(dataName);
                FTPClient ftpClient = getConnect();
                try {
                        try {
                                ftpClient.rename(FtpInfo.FTP_PRIVATE_PATH + File.separator + dataName,
                                        FtpInfo.FTP_PUBLIC_PATH + File.separator + dataName);
                        }finally {
                                ftpClient.logout();
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                        throw new FTPException("File move from private to public fail");
                }
        }

        /**
         * Remove file which name is dataName
         * @param dataName name of removed file
         * @param path file path in FTP server
         * @throws FTPException remove file fail
         */
        public static void removeFile(String dataName,String path) throws FTPException {
                FTPClient ftpClient = getConnect();
                dataName = parseName(dataName);
                path = File.separator + parseName(path);
                try {
                        try {
                                ftpClient.changeWorkingDirectory(path);
                                ftpClient.deleteFile(dataName);
                                ftpClient.changeWorkingDirectory(FtpInfo.FTP_PUBLIC_PATH);
                                ftpClient.deleteFile(dataName);
                                ftpClient.changeWorkingDirectory(FtpInfo.FTP_PRIVATE_PATH);
                                ftpClient.deleteFile(dataName);
                        }finally {
                                ftpClient.logout();
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                        throw new FTPException("Remove file's name : " + dataName + " fail");
                }
        }

        /**
         * Get all file names in dir
         * @param dir name of dir
         * @return file names
         * @throws FTPException Get file names in dir fail
         */
        public static List<String> getFileNamesInDir(String dir) throws FTPException {
                List<String> fileNames ;
                dir = File.separator + parseName(dir);
                FTPClient ftpClient = getConnect();
                try {
                        try {
                                ftpClient.changeWorkingDirectory(dir);
                                fileNames = Arrays.asList(ftpClient.listNames());
                                return fileNames;
                        }finally {
                                ftpClient.logout();
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                        throw new FTPException("Get file names in dir fail");
                }
        }

        /**
         * Parse fileName
         * @param dataName name of data which is made by user
         * @param fileName name fo file
         * @return originalName
         */
        private static String getOriginalName(String dataName,String fileName){
                return dataName  + "."+ fileName.split("\\.")[1];
        }

        /**
         * Parse name code to ISO-8859-1
         * @param name parse string
         * @return string which code is ISO-8859-1
         * @throws FTPException Parse name fail
         */
        private static String parseName(String name) throws FTPException {
                try {
                        return new String(name.getBytes("GBK"),"ISO-8859-1");
                } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        throw new FTPException("Parse name fail");
                }
        }


        public static void main(String[] args) throws FTPException, IOException {
                //makePrivateSpace("魏wbl");
               /* File file = new File("F://图片.jpg");
                fileUploadToPrivate(new FileInputStream(file),"图片test","图片.jpg","魏wbl");
                fileMoveFromPrivateToPublic("图片test.jpg");*/
                /*System.out.println(getFileNamesInDir("魏wbl"));
                System.out.println(getFileNamesInDir("public"));*/
               /* File file = new File("F://图片test.jpg");
                FileOutputStream os = new FileOutputStream(file);
                InputStream is = fileDownloadFromPublic("图片.jpg");
                byte[] buffer = new byte[1024];
                int c ;
                while ((c = is.read(buffer)) != -1)
                        os.write(buffer,0,c);*/
                makePrivateSpace("简单爱小败");
        }
}
