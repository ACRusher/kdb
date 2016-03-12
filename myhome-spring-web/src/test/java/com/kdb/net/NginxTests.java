package com.kdb.net;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 懿斐 on 2016/1/30.
 */
public class NginxTests {

    @Test
    public void testNginx(){
        RestTemplate restTemplate=new RestTemplate();
        long s=System.currentTimeMillis();
        Map param=new HashMap();
        param.put("a", "1");
        param.put("b", "1");
        ResponseEntity<String> responseEntity=
                restTemplate.postForEntity("http://shop988.com/a={a}", "abc", String.class, param);
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        System.out.println("cost : "+(System.currentTimeMillis()-s));
    }

    public static void main(String[] args) {
        Map param=new HashMap();
        param.put("a", "1");
        System.out.println(param);
    }
}
