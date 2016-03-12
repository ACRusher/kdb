package com.kdb.common.util;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Demo类
 *
 * Created by zhouxiliang on 2016/1/25.
 */
public class POJOMapLoader extends POJODefaultLoader<HashMap> {

    public static void main(String[] args) throws IOException {
        POJOMapLoader pojoMapLoader=new POJOMapLoader();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream(1024);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);

        HashMap hashMap=new HashMap();
        hashMap.put("a", 1);
        objectOutputStream.writeObject(hashMap);

        Object obj=pojoMapLoader.loadFromBinary(byteArrayOutputStream.toByteArray());
        System.out.println(obj);

        Object obj1=pojoMapLoader.loadFromBinary(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        System.out.println(obj1);

        Object obj2=pojoMapLoader.loadFromJson(JSON.toJSONString(hashMap));
        System.out.println(obj2);

        Object obj3=pojoMapLoader.loadFromJson(new ByteArrayInputStream(JSON.toJSONString(hashMap).getBytes()));
        System.out.println(obj3);

    }
}
