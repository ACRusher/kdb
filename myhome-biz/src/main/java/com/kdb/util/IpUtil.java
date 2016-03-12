package com.kdb.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author xiliang.zxl
 * @date 2016-01-23 下午9:29
 */
public class IpUtil {

    //淘宝ip库 访问限制:qps 10
    private static String taobaoIpUrl="http://ip.taobao.com/service/getIpInfo.php?ip={0}";

    //ipip.net 的免费获取运营商api，接口速度比淘宝快很多
    private static String ipipUrl="http://freeapi.ipip.net/{0}";

    /**
     * rt在1秒以上 尽量少调用
     * @param ip
     * @return
     */
    public static String getOperatorName(String ip){
//       return getOperatorNameByIPIP(ip);
       return getOperatorNameByTaobao(ip);
    }

    private static String getOperatorNameByTaobao(String ip){
        String result="NULL";
        RestTemplate restTemplate=ApplicationContextUtil.getBean("restTemplate");
//        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> responseEntity=
                restTemplate.getForEntity(taobaoIpUrl, String.class, ip);

        if(responseEntity.getStatusCode().value()!=200) return result;
        JSONObject json= JSON.parseObject(responseEntity.getBody());
        JSONObject data= (JSONObject) json.get("data");
//        System.out.println(data);
        for(String key :data.keySet()){
            if("isp".equals(key)) result= data.get(key).toString();
        }
        if("鹏博士".equals(result)){
            result="宽带通";
        }
        return result;
    }

    private static String getOperatorNameByIPIP(String ip){
        RestTemplate restTemplate=ApplicationContextUtil.getBean("restTemplate");
//        RestTemplate restTemplate=new RestTemplate();
        String result=null;
        ResponseEntity<String> responseEntity=
                restTemplate.getForEntity(ipipUrl, String.class, ip);

        if(responseEntity.getStatusCode().value()!=200) return null;
        JSONArray arr= JSON.parseArray(responseEntity.getBody());
        result=arr.get(arr.size()-1).toString();
        if(result.contains("/")){
            String[]t=result.split("/");
            result=t[t.length-1];
        }
        return result;
    }


    public static void main(String[] args) {
        long s=System.currentTimeMillis();
        System.out.println(getOperatorNameByTaobao("124.192.56.30"));
        System.out.println(System.currentTimeMillis()-s);
        s=System.currentTimeMillis();
        System.out.println(getOperatorNameByIPIP("124.192.56.30"));
        System.out.println(System.currentTimeMillis()-s);
    }


}
