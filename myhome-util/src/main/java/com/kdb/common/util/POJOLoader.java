package com.kdb.common.util;

import java.io.InputStream;

/**
 * POJO 加载器
 *
 * Created by zhouxiliang on 2016/1/25.
 */
public interface POJOLoader<T> {

    T loadFromXml(InputStream inputStream);

    T loadFromXml(String xml);

    T loadFromJson(InputStream inputStream);

    T loadFromJson(String json);

    T loadFromBinary(InputStream inputStream);

    T loadFromBinary(byte[] data);
}
