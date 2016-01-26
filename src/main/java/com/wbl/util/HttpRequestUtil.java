package com.wbl.util;

import com.wbl.modal.exception.RequestException;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by Simple_love on 2015/11/13.
 */
public class HttpRequestUtil {
        private static Logger logger = Logger.getLogger(HttpRequestUtil.class);

        /*
        * 发起GET请求
        * @param url 请求的URL地址
        * @param paramMap 请求参数
        * @return 远程响应结果
        * */
        public static String doGetRequest(String url, Map<String,String> paramMap){
                //请求返回的响应结果
                StringBuilder responseResult = new StringBuilder();

                //读取Http请求的响应流，即服务器以流的形式返回响应结果
                BufferedReader reader = null;

                StringBuilder sb = new StringBuilder();

                //封装后的请求参数
                String params = "";

                try {
                        //如果有请求参数，则对请求参数进行封装
                        if (paramMap!=null && !paramMap.isEmpty()){
                                for (String name:paramMap.keySet()){
                                        //对请求参数进行编码，避免乱码
                                        sb.append(name).append("=").append(java.net.URLEncoder.encode(paramMap.get(name),"UTF-8"));
                                        sb.append("&");
                                }
                                String temp_params = sb.toString();
                                params = temp_params.substring(0,temp_params.length()-1);
                        }

                        String full_url = url + "?" + params;

                        //创建URL对象
                        URL connectUrl= new URL(full_url);

                        //打开URL连接
                        HttpURLConnection httpURLConnection = (HttpURLConnection) connectUrl.openConnection();

                        //设置http请求头中的属性
                        httpURLConnection.setRequestProperty("Accept","*/*");
                        httpURLConnection.setRequestProperty("Connection","keep-alive");
                        httpURLConnection.setRequestProperty("User Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36");

                        httpURLConnection.connect();

                        //保存http响应的头部
                        Map<String,List<String>> responseHeader = httpURLConnection.getHeaderFields();

                        for (String key:responseHeader.keySet()){
                                System.out.println(key + " : " + responseHeader.get(key));
                        }

                        //定义BufferedReader输入流来读取URL的响应,并设置编码方式
                        reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));
                        String line;
                        while ((line = reader.readLine())!=null){
                                responseResult.append(line);
                        }
                }catch (Exception e){
                        e.printStackTrace();
                }finally {
                       try {
                               if (reader != null)
                                       reader.close();
                       }catch (Exception e){
                               e.printStackTrace();
                       }
                }
                return responseResult.toString();
        }

        /*
        * 发起POST请求
        *
        *@param url 请求的URL地址
        * @param paramMap 请求参数
        * @return 远程响应结果
        * */
        public static String doPostRequest(String url, Map<String,String> paramMap){
                StringBuilder responseResult = new StringBuilder();
                BufferedReader in = null;
                PrintWriter out = null;
                StringBuilder sb = new StringBuilder();
                String params = "";

                try {
                        if (paramMap != null && !paramMap.isEmpty()){
                                for (String name: paramMap.keySet()){
                                        sb.append(name).append("=").append(java.net.URLEncoder.encode(paramMap.get(name), "UTF-8"));
                                }
                                String temp_params = sb.toString();
                                params = temp_params.substring(0,temp_params.length() - 1);
                        }

                        URL connectUrl = new URL(url);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) connectUrl.openConnection();

                        //设置http请求头中的属性
                        httpURLConnection.setRequestProperty("Accept","*/*");
                        httpURLConnection.setRequestProperty("Connection", "keep-alive");
                        httpURLConnection.setRequestProperty("User Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36");

                        /*
                        * URL 连接可用于输入和/或输出。如果打算使用 URL 连接进行输入，则将 DoInput 标志设置为 true；
                        * 如果打算使用 URL 连接进行输出，则将 DoOutput 标志设置为 true；
                        * */
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.setDoOutput(true);

                        // 获取HttpURLConnection对象对应的输出流
                        out = new PrintWriter(httpURLConnection.getOutputStream());
                        /*
                        * POST方式提交参数，参数是在请求体中发送的，就是和GET的区别。
                        * */
                        //发送请求参数
                        out.write(params);

                        out.flush();

                        httpURLConnection.connect();

                        //定义BufferedReader输入流来读取URL的响应，设置编码方式
                        in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));
                        String line;

                        //// 读取返回的内容
                        while ((line = in.readLine()) != null){
                                responseResult.append(line);
                        }
                }catch (Exception e){
                        e.printStackTrace();
                }finally {
                        try {
                                if (out != null)
                                        out.close();
                                if (in != null)
                                        in.close();
                        }catch (Exception e){
                                e.printStackTrace();
                        }
                }
                return responseResult.toString();
        }

        public static  String doPostRequest(String url,String params) throws RequestException {
                PrintWriter out = null;
                BufferedReader in = null;
                String result = "";
                HttpURLConnection conn = null;
                int code = 200;
                try {
                        URL realUrl = new URL(url);
                         conn = (HttpURLConnection) realUrl.openConnection();
                        conn.setRequestProperty("accept", "*/*");
                        conn.setRequestProperty("connection", "Keep-Alive");
                        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                        //conn.setRequestProperty("Content-Type","text/plain;charset=UTF-8");

                        conn.setDoOutput(true);
                        conn.setDoInput(true);
                        out = new PrintWriter(conn.getOutputStream());
                        //System.out.println(params);
                        out.print(params);
                        out.flush();
                        code = conn.getResponseCode();
                        in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
                        String line;
                        while ((line = in.readLine()) != null) {
                                result += "\n" + line;
                        }
                } catch (ConnectException e) {
                        throw new RequestException("Connect to URL :" + url +" timeout");
                }catch (IOException e){
                        throw new RequestException("Server returned HTTP response code: " + code +" for URL" + url);
                }
                finally {
                        try {
                                if (out != null) {
                                        out.close();
                                }
                                if (in != null) {
                                        in.close();
                                }
                        } catch (IOException ex) {
                                ex.printStackTrace();
                        }
                }
                return result;
        }

        public static String uploadFile(String url,String path) throws IOException{
                File file = new File(path);
                return uploadFile(url,new FileInputStream(file));
        }

        public static String uploadFile(String url,InputStream inputStream){
                OutputStream out = null;
                BufferedReader in = null;
                // 换行符
                final String newLine = "\r\n";
                final String boundaryPrefix = "--";
                // 定义数据分隔线
                String BOUNDARY = "========7d4a6d158c9";
                try {
                        URL url1 = new URL(url);
                        HttpURLConnection conn = (HttpURLConnection) url1.openConnection();

                        conn.setRequestProperty("connection", "Keep-Alive");
                        conn.setRequestProperty("Charsert", "UTF-8");
                        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

                        conn.setDoInput(true);
                        conn.setDoOutput(true);
                        conn.setUseCaches(false);

                        StringBuilder sb = new StringBuilder();
                        sb.append(boundaryPrefix);
                        sb.append(BOUNDARY);
                        sb.append(newLine);
                        // 文件参数,photo参数名可以随意修改
                        sb.append("Content-Disposition: form-data;name=\"photo\";filename=\"" + "test"
                                + "\"" + newLine);
                        sb.append("Content-Type:application/octet-stream");
                        // 参数头设置完以后需要两个换行，然后才是参数内容
                        sb.append(newLine);
                        sb.append(newLine);

                        // 将参数头的数据写入到输出流中
                        out = new DataOutputStream(conn.getOutputStream());
                        out.write(sb.toString().getBytes());

                        InputStream cin = new DataInputStream(inputStream);
                        byte[] buffer = new byte[1024];
                        while ((cin.read(buffer)) != -1)
                                out.write(buffer);
                        cin.close();
                        out.write(newLine.getBytes());

                        out.write((newLine + boundaryPrefix + BOUNDARY + boundaryPrefix + newLine).getBytes());
                        out.flush();
                        conn.connect();

                        StringBuilder buffer1 = new StringBuilder();
                        in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String line;
                        while ((line = in.readLine()) != null)
                                buffer1.append(line);
                        return buffer1.toString();
                }catch (Exception e){
                        e.printStackTrace();
                }finally {
                       try {
                               if (in != null)
                                       in.close();
                               if (out != null)
                                       out.close();
                       }catch (IOException e){
                               e.printStackTrace();
                       }
                }
                return null;
        }

        public static void main(String[] args) throws Exception{
              /*  System.out.println(HttpRequestUtil.doGetRequest("http://www.baidu.com", null));
                System.out.println(HttpRequestUtil.doPostRequest("http://www.baidu.com",null));*/
                //System.out.println(uploadFile("http://localhost:8080/BigDataBeta/prov/imageTest","other1-0.jpg"));
                System.out.println(HttpRequestUtil.doPostRequest("http://localhost:8080/BigDataBeta/prov/queryRelation",""));
        }
}
