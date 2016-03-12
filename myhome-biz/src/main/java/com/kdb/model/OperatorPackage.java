package com.kdb.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiliang.zxl
 * @date 2016-01-19 下午11:52
 */
public class OperatorPackage extends BaseModel {
    private static final long serialVersionUID = 5864209274588372884L;
    private @Getter @Setter Long operatorId;
    private @Getter @Setter String serviceType;
    private @Getter @Setter Integer fee;
    private @Getter @Setter String time;
    private @Getter @Setter String description;
}
