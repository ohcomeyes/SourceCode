package com.boce.flcp.sms;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @ClassName SendMsgService TODO(短信接口)
 * @author  Tangxu
 * @version V1.0 Tangxu 2016-1-27 下午04:29:36 Mark $
 */
public class SmsService {
    private static String account ="cf_txenergy";//帐号cf_wxhdcs   cf_txenergy
    private static String password = "86419e516063bf9904dd23d3dbcca541";//密码wxhdcs@456   13873930219
    private static String Url = "http://106.ihuyi.com/webservice/sms.php?method=Submit";//短信接口地址

    public static void sendSmg(String mobile,String content){
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);

        //client.getParams().setContentCharset("GBK");
        client.getParams().setContentCharset("UTF-8");
        method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
        String message = new String("您的验证码是：" + content + "。请不要把验证码泄露给其他人。");
        //System.out.println(mobile);
        try {
            NameValuePair[] data = {//提交短信
                new NameValuePair("account", account),
			    new NameValuePair("password", password), //密码可以使用明文密码或使用32位MD5加密
//              new NameValuePair("password", CommonUtils.EncoderByMd5(password)),
                new NameValuePair("mobile", mobile),
                new NameValuePair("content", message),};
            method.setRequestBody(data);
            client.executeMethod(method);
            String SubmitResult =method.getResponseBodyAsString();
            //System.out.println(SubmitResult);
            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();
            String code = root.elementText("code");
            String msg = root.elementText("msg");
            String smsid = root.elementText("smsid");
            System.out.println(code);
            System.out.println(msg);
            System.out.println(smsid);
            if("2".equals(code)){
                System.out.println("短信提交成功");
            }
        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        SmsService.sendSmg("13450353471", "542842");
//        JSONObject json = new JSONObject();
//        json.element("demand_id","2");
//        json.element("design_describe","etuddtug");
//        json.element("user_id","20007");
//        json.element("design_imgs","1512982866893.jpg%http://ozeg1t2zn.bkt.clouddn.com/1512982866893.jpg#1512982870634.jpg%http://ozeg1t2zn.bkt.clouddn.com/1512982870634.jpg#1512982873062.jpg%http://ozeg1t2zn.bkt.clouddn.com/1512982873062.jpg#1513046686504.jpg%http://ozeg1t2zn.bkt.clouddn.com/1513046686504.jpg#1513046690376.jpg%http://ozeg1t2zn.bkt.clouddn.com/1513046690376.jpg");
////        String content = "account=一个大肥人";
////        content +="&password=123";
//        System.out.println(json.toString());
//        String result = doJsonPost("http://192.168.1.107:8080/api/demands/submitDesign",json.toString());
//        System.out.println(result);
//        doGet();
//        uploadFile(new File("D:\\1.jpg"),"http://192.168.1.107:8080/upload/file");
    }

    public static void doGet(){
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod("http://192.168.1.107:8080/user/validate?account=13450353471");
        //client.getParams().setContentCharset("GBK");
        client.getParams().setContentCharset("UTF-8");
//        method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
        try {
            client.executeMethod(method);
            String SubmitResult =method.getResponseBodyAsString();
            System.out.println(SubmitResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String uploadFile(File file, String RequestURL){
        String result = null;
        String BOUNDARY = "letv"; // 边界标识 随机生成
        String PREFIX = "--", LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form-data"; // 内容类型
        try {
            URL  url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true); // 允许输入流
            conn.setDoOutput(true); // 允许输出流
            conn.setUseCaches(false); // 不允许使用缓存
            conn.setRequestMethod("POST"); // 请求方式
            conn.setRequestProperty("Charset", "utf-8"); // 设置编码
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
                    + BOUNDARY);
            if (file != null) {
                /**
                 * 当文件不为空，把文件包装并且上传
                 */
                DataOutputStream dos = new DataOutputStream(
                        conn.getOutputStream());
                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);
                /**
                 * 这里重点注意： name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
                 * filename是文件的名字，包含后缀名的 比如:abc.png
                 */
                sb.append("Content-Disposition: form-data; name=\"file\"; filename=\""
                        + file.getName() + "\"" + LINE_END);
                sb.append("Content-Type: application/ctet-stream" + LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
                InputStream is = new FileInputStream(file);
                byte[] bytes = new byte[1024 * 1024];
                int len = 0;
                while ((len = is.read(bytes)) != -1) {
                    dos.write(bytes, 0, len);
                }
                is.close();
                dos.write(LINE_END.getBytes());
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END)
                        .getBytes();
                dos.write(end_data);
                dos.flush();
                /**
                 * 获取响应码 200=成功 当响应成功，获取响应的流
                 */
                int res = conn.getResponseCode();
                // if(res==200)
                // {
                InputStream input = conn.getInputStream();
                StringBuffer sb1 = new StringBuffer();
                int ss;
                while ((ss = input.read()) != -1) {
                    sb1.append((char) ss);
                }
                result = sb1.toString();
                result = new String(result.getBytes("iso8859-1"), "utf-8");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    public static String doJsonPost(String urlPath, String json) {
        String result = "";
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
//            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type","application/json");
            // 设置接收类型否则返回415错误
//            conn.setRequestProperty("accept","*/*");//此处为暴力方法设置接受所有类型，以此来防范返回415;
//            conn.setRequestProperty("accept","application/json");
            conn.connect();
            // 往服务器里面发送数据
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.write(json.getBytes());
            out.flush();
            out.close();
            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                result = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
