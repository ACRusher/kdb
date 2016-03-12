package com.kdb.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiliang.zxl
 * @date 2016-01-31 下午4:20
 */
public class Location {
    //维度
    private @Getter @Setter String lat ;
    //经度
    private @Getter @Setter String lng;
}
