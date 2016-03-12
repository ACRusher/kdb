package com.kdb.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 百度搜索 小区下拉列表
 *
 * @author xiliang.zxl
 * @date 2016-01-31 下午4:19
 */
public class BaiduVillageDTO {

    //经纬度
    private @Getter @Setter Location location;
    //城市
    private @Getter @Setter String city;
    //分区 （如海淀区）
    private @Getter @Setter String district;
    //小区名称
    private @Getter @Setter String name;

    private @Getter @Setter Long cityId;

}
