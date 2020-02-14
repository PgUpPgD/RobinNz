package com.laoxing.robin.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: RobinNz
 * @description:
 * @author: Feri
 * @create: 2020-02-14 11:20
 */
public class HttpUtil {

    /**
     * get 获取json
     * */
    public static String getJson(String url){
        //1、创建请求方式对象
        HttpGet get=new HttpGet(url);
        //2、创建客户端对象
        HttpClient client= HttpClientBuilder.create().build();
        try {
            //3、获取请求结果
            HttpResponse response=client.execute(get);
            //4、校验http状态码
            if(response.getStatusLine().getStatusCode()==200){
                //获取响应的内容对象
                HttpEntity entity=response.getEntity();
                //转换结果位字符串
               return EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * json格式的参数
     * Post请求 获取json数据*/
    public static String postJson(String url,String paramjson){
        //1、创建请求方式对象
        HttpPost post=new HttpPost(url);
        //2、创建客户端对象
        HttpClient client= HttpClientBuilder.create().build();
        try {
            //3、设置post请求的参数
            StringEntity stringEntity=new StringEntity(paramjson, ContentType.APPLICATION_JSON);
            post.setEntity(stringEntity);
            //4、获取请求结果
            HttpResponse response=client.execute(post);
            //4、校验http状态码
            if(response.getStatusLine().getStatusCode()==200){
                //获取响应的内容对象
                HttpEntity entity=response.getEntity();
                //转换结果位字符串
                return EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * post 传递键值对参数 获取json格式*/
    public static String postJson(String url, Map<String,String> map){
        //1、创建请求方式对象
        HttpPost post=new HttpPost(url);
        //2、创建客户端对象
        HttpClient client= HttpClientBuilder.create().build();
        try {
            //3、设置post请求的参数
           List<NameValuePair> pairList=new ArrayList<>();
           for(String key:map.keySet()){
               pairList.add(new BasicNameValuePair(key,map.get(key)));
           }
            HttpEntity httpEntity=new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8"));
            post.setEntity(httpEntity);
            //4、获取请求结果
            HttpResponse response=client.execute(post);
            //5、校验http状态码
            if(response.getStatusLine().getStatusCode()==200){
                //6、获取响应的内容对象
                HttpEntity entity=response.getEntity();
                //7、转换结果位字符串
                return EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}