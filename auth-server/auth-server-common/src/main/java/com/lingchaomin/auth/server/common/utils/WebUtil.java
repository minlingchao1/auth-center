package com.lingchaomin.auth.server.common.utils;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * USER:minlingchao
 * DATE:2016/9/10
 * TIME:20:49
 * DESCRIPTION:网络请求util
 */
public class WebUtil {

    public static final Logger LOG= LoggerFactory.getLogger(WebUtil.class);

    /**
     * post请求，带有默认编码
     * @param Url
     * @param data
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static String doPost(String Url, NameValuePair[] data) throws HttpException, IOException {

        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");
        method.setRequestBody(data);
        client.getParams().setContentCharset("UTF-8");
        client.getParams().setConnectionManagerTimeout(30000);
        client.getParams().setSoTimeout(30000);
        client.executeMethod(method);
        return method.getResponseBodyAsString();
    }

    /**
     * post请求
     * @param Url 地址
     * @param data 参数
     * @param charset 编码
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static String doPost(String Url, NameValuePair[] data, String charset) throws HttpException, IOException {

        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=" + charset);
        method.setRequestBody(data);
        client.getParams().setContentCharset(charset);
        client.getParams().setConnectionManagerTimeout(10000);
        client.executeMethod(method);
        return method.getResponseBodyAsString();
    }

    /**
     * get请求
     * @param url
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static String get(String url,NameValuePair[] data) throws HttpException, IOException {

        String params="";
        List<String> paramsList=new ArrayList<String>();
        for (NameValuePair nameValuePair:data){
            String param=nameValuePair.getName()+"="+nameValuePair.getValue();
            paramsList.add(param);
        }

        params=StringUtils.join(paramsList,"&");

        url=url+"?"+params;

        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

        GetMethod method = new GetMethod(url);
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        int status = client.executeMethod(method);
        if (status != HttpStatus.SC_OK) {
            LOG.error("Get subway rate error, status code:" + status);
        } else {
            return method.getResponseBodyAsString();
        }

        return null;
    }

    public static String doPostMessage(String Url, Map<String, String> headers, String charset) throws HttpException,
            IOException {

        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);
        client.getParams().setContentCharset(charset);
        client.getParams().setConnectionManagerTimeout(10000);
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=" + charset);
        method.setRequestBody(JsonUtil.toJson(headers));
        client.executeMethod(method);
        return method.getResponseBodyAsString();
    }

    public static String get(String url) throws HttpException, IOException {

        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

        GetMethod method = new GetMethod(url);
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        int status = client.executeMethod(method);
        if (status != HttpStatus.SC_OK) {
            LOG.error("Get subway rate error, status code:" + status);
        } else {
            return method.getResponseBodyAsString();
        }

        return null;
    }
}
