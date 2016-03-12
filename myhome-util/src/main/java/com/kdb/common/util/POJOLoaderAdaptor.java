package com.kdb.common.util;

import java.io.InputStream;

/**
 * Created by zhouxiliang on 2016/1/25.
 */
public abstract class POJOLoaderAdaptor<T> implements POJOLoader<T> {
    @Override
    public T loadFromXml(InputStream inputStream) {
        return null;
    }

    @Override
    public T loadFromXml(String xml) {
        return null;
    }

    @Override
    public T loadFromJson(InputStream inputStream) {
        return null;
    }

    @Override
    public T loadFromJson(String json) {
        return null;
    }

    @Override
    public T loadFromBinary(InputStream inputStream) {
        return null;
    }

    @Override
    public T loadFromBinary(byte[] data) {
        return null;
    }
}
