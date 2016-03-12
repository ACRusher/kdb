package com.kdb.geo;

import com.alibaba.fastjson.JSON;
import com.kdb.BaseTest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xiliang.zxl
 * @date 2016-01-16 上午1:22
 */
public class GeoTests extends BaseTest {

    //通过名称搜经纬度
    private String baiduGeoUrl="http://api.map.baidu.com/geocoder/v2/?ak={0}&output=json&address={1}&city=北京市&sn={2}";

    //通过经纬度搜结构化地址信息  lat ,lon
    private String baiduReGeoUrl="http://api.map.baidu.com/geocoder/v2/?ak={0}&location={1}&output=json&pois={2}";

    /**
     * 这个ak是限制ip的
     *
     */
    private String ak_ip="z4EXnda4NLupDyfGcgAcEMbv";
    /**
     * 这个ak需要签名
     */
    private String ak_sn="m6suClXGDVLFBDEwH6YBrz1t";
    private String privateKey="KT5u0wyjrsAVTmzDheNmZQpX62BeuYxo";

    @Test
    public void testBaiduGeo(){
        RestTemplate restTemplate=new RestTemplate();
        Map<String,String> param=new LinkedHashMap<String, String>();
        param.put("ak",ak_sn);
        param.put("output","json");
        param.put("address","牡丹园小区");
        param.put("city","北京市");
        String sn=sign(param,privateKey);
        ResponseEntity<String> responseEntity=
                restTemplate.getForEntity(baiduGeoUrl, String.class, ak_sn,"牡丹园小区",sn);
        System.out.println(responseEntity.getBody());
        System.out.println(JSON.parseObject(responseEntity.getBody()));
    }


    @Test
    public void testBaiduReGeo(){
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> responseEntity=
                restTemplate.getForEntity(baiduReGeoUrl, String.class, ak_ip,"39.984365043089,116.38121224711",0);
        System.out.println(responseEntity.getBody());
        System.out.println(JSON.parseObject(responseEntity.getBody()));
    }


    private String sign(Map<String,String> param,String privateKey)  {
        try {
            String queryString = toQueryString(param);
            String wholeStr = new String("/geocoder/v2/?" + queryString + privateKey);
            return MD5(URLEncoder.encode(wholeStr, "utf-8"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    // 对Map内所有value作utf8编码，拼接返回结果
    public String toQueryString(Map<?, ?> data)
            throws UnsupportedEncodingException {
        StringBuffer queryString = new StringBuffer();
        for (Map.Entry<?, ?> pair : data.entrySet()) {
            queryString.append(pair.getKey() + "=");
            queryString.append(URLEncoder.encode((String) pair.getValue(),
                    "UTF-8") + "&");
        }
        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }
        return queryString.toString();
    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

}
