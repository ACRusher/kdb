package com.kdb.common.util;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * Created by zhouxiliang on 2016/1/25.
 */
public abstract class POJODefaultLoader<T extends Object> extends POJOLoaderAdaptor<T> {

    protected Class<?> getParameterType(){
        Type type=getClass().getGenericSuperclass();
        if(! (type instanceof ParameterizedType) ){
            return null;
        }
        ParameterizedType parameterizedType= (ParameterizedType) type;
        Type[]types=parameterizedType.getActualTypeArguments();
        if(types==null || types.length==0) {
            return null;
        }
        return (Class<?>) types[0];
    }

    @Override
    public T loadFromXml(InputStream inputStream) {
        return super.loadFromXml(inputStream);
    }

    @Override
    public T loadFromXml(String xml) {

        return super.loadFromXml(xml);
    }

    /**
     * 默认使用 Alibaba的fastjson包
     *
     * @param inputStream
     * @return
     */
    @Override
    public T loadFromJson(InputStream inputStream) {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return loadFromJson(sb.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 默认使用 Alibaba的fastjson包
     *
     * @param json
     * @return
     */
    @Override
    public T loadFromJson(String json) {
        try {
            return (T) JSON.parseObject(json,getParameterType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 默认使用JDK序列化协议
     *
     * @param inputStream
     * @return 异常情况下 返回 NULL
     */
    @Override
    public T loadFromBinary(InputStream inputStream)  {
        try {
            ObjectInputStream objectInputStream=new ObjectInputStream(inputStream);
            return (T) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 默认使用JDK 序列化协议
     *
     * @param data
     * @return
     */
    @Override
    public T loadFromBinary(byte[] data) {
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(data);
        return loadFromBinary(byteArrayInputStream);
    }
}
