package com.kdb.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiliang.zxl
 * @date 2016-01-19 下午11:44
 */
public class Village extends BaseModel {
    private static final long serialVersionUID = -7929859184699864378L;
    private @Getter @Setter String province;
    private @Getter @Setter String city;
    private @Getter @Setter String area;
    private @Getter @Setter String street;
    private @Getter @Setter String village;
    private @Getter @Setter String operators;
}
