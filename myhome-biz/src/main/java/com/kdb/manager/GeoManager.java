package com.kdb.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xiliang.zxl
 * @date 2016-01-17 下午3:44
 */
@Component
public class GeoManager {
    private static final Logger logger = LoggerFactory.getLogger(GeoManager.class);

    //通过名称搜经纬度
    private String baiduGeoUrl="http://api.map.baidu.com/geocoder/v2/?ak={0}&output=json&address={1}&city=北京市";

    //通过经纬度搜结构化地址信息  lat ,lon
    private String baiduReGeoUrl="http://api.map.baidu.com/geocoder/v2/?ak={0}&location={1}&output=json&pois={2}";

     //这个ak是限制ip的
    private String ak_ip="z4EXnda4NLupDyfGcgAcEMbv";

    @Autowired
    private RestTemplate restTemplate;

    public JSONObject addressToGeo(String address){

        ResponseEntity<String> responseEntity=
                restTemplate.getForEntity(baiduGeoUrl, String.class, ak_ip,address);
        return JSON.parseObject(responseEntity.getBody());
    }


    public JSONObject geoToAddress(String lat,String lng){
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> responseEntity=
                restTemplate.getForEntity(baiduReGeoUrl, String.class, ak_ip,lat+","+lng,0);
        System.out.println(responseEntity.getBody());
        return JSON.parseObject(responseEntity.getBody());
    }

}
