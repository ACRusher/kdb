package com.kdb.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiliang.zxl
 * @date 2016-01-19 下午11:49
 */
public class Order extends BaseModel {
    private static final long serialVersionUID = -4125144518276113733L;
    private @Getter @Setter Long userId;
    private @Getter @Setter String userName;
    private @Getter @Setter String address;
    private @Getter @Setter String phone;
    private @Getter @Setter Long operatorId;
    private @Getter @Setter String operatorArea;
    private @Getter @Setter String operatorServiceDesc;
    private @Getter @Setter Long operatorServiceId;

}
