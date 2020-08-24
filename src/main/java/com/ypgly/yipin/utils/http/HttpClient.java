package com.ypgly.yipin.utils.http;

import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


/**
 * @author yzy
 */
public class HttpClient {

    /**
     * 向目的URL发送post请求
     * @param url       目的url
     * @param params    发送的参数
     * @return  AdToutiaoJsonTokenData
     */
    public static ResponseEntity<String> sendPostRequest(String url, MultiValueMap<String, String> params){
        RestTemplate client = new RestTemplate();
        //新建Http头，add方法可以添加参数
        HttpHeaders headers = new HttpHeaders();
        //MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        //设置请求发送方式
        HttpMethod method = HttpMethod.POST;
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //headers.setContentType(type);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //System.out.println("requestEntity="+requestEntity);
        //执行HTTP请求，将返回的结构使用String 类格式化（可设置为对应返回值格式的类）
        //ResponseEsntity<String> response = client.exchange(url, method, requestEntity,String .class);
        ResponseEntity<String> exchange = client.exchange(url, method, requestEntity, String.class);

        return exchange;
    }

    /**
     * 向目的URL发送get请求
     * @param url       目的url
     * @param params    发送的参数
     * @param headers   发送的http头，可在外部设置好参数后传入
     * @return  String
     */
    public static ResponseEntity<String> sendGetRequest(String url, MultiValueMap<String, String> params, HttpHeaders headers){
        RestTemplate client = new RestTemplate();

        HttpMethod method = HttpMethod.GET;
        // 以表单的方式提交
        //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，将返回的结构使用String 类格式化
        ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);

        return response;
    }
    /**
     * 向目的URL发送post请求
     * @param url       目的url
     * @param params    发送的参数
     * @return  AdToutiaoJsonTokenData
     */
    public static ResponseEntity<String> sendPostRequestHeader(String url, MultiValueMap<String, String> params, HttpHeaders headers){
        RestTemplate client = new RestTemplate();
        //新建Http头，add方法可以添加参数
        //HttpHeaders headers = new HttpHeaders();
        //MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        //设置请求发送方式
        HttpMethod method = HttpMethod.POST;
        // 以表单的方式提交
        //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //headers.setContentType(type);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //System.out.println("requestEntity="+requestEntity);
        //执行HTTP请求，将返回的结构使用String 类格式化（可设置为对应返回值格式的类）
        //ResponseEsntity<String> response = client.exchange(url, method, requestEntity,String .class);
        ResponseEntity<String> exchange = client.exchange(url, method, requestEntity, String.class);

        return exchange;
    }

    /**
     * 向目的URL发送get请求
     * @param url       目的url
     * @param params    发送的参数
     * @param headers   发送的http头，可在外部设置好参数后传入
     * @return  String
     */
    public static ResponseEntity<byte[]> sendGetRequestBy(String url, MultiValueMap<String, String> params, HttpHeaders headers){
        RestTemplate client = new RestTemplate();

        HttpMethod method = HttpMethod.GET;
        // 以表单的方式提交
        //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，将返回的结构使用String 类格式化
        ResponseEntity<byte[]> response = client.exchange(url, method, requestEntity, byte[].class);


        return response;
    }
}
