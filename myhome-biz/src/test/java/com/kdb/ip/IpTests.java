package com.kdb.ip;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kdb.BaseTest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author xiliang.zxl
 * @date 2016-01-15 下午11:35
 */
public class IpTests extends BaseTest {

    //淘宝ip库 访问限制:qps 10
    private String taobaoIpUrl="http://ip.taobao.com/service/getIpInfo.php?ip={0}";

    //百度ip库 访问限制见百度开发中心
    private String baiduIpUrl="http://api.map.baidu.com/location/ip?ak={0}&ip={1}";

    /**
     * 百度开发者有两个账号： 15701579698  zhou2214
     *
     */
    private String ak="z4EXnda4NLupDyfGcgAcEMbv";

    @Test
    public void testTaobaoIp(){
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> responseEntity=
                restTemplate.getForEntity(taobaoIpUrl, String.class, "123.123.62.247");
        System.out.println(responseEntity.getBody());
        JSONObject json=JSON.parseObject(responseEntity.getBody());
        JSONObject data= (JSONObject) json.get("data");
        for(String key :data.keySet()){
            System.out.println(key+" "+data.get(key));
        }
    }

    @Test
    public void testBaiduIp(){
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> responseEntity=
                restTemplate.getForEntity(baiduIpUrl, String.class, ak,"123.123.62.247");
        System.out.println(responseEntity.getBody());
        System.out.println(JSON.parseObject(responseEntity.getBody()));
    }


}
