package com.kdb.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author xiliang.zxl
 * @date 2016-01-24 上午11:24
 */
public class ParamUtil {

    public static int getInt(String value,int defaultValue){
        if(StringUtils.isBlank(value) || !NumberUtils.isNumber(value)) return defaultValue;
        return Integer.valueOf(value);
    }
}
