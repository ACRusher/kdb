package com.kdb.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiliang.zxl
 * @date 2016-01-19 下午11:38
 */
public class UserExposure extends BaseModel {
    private static final long serialVersionUID = -1098504465270478433L;
    private @Getter @Setter Long operatorId;
    private @Getter @Setter Long userId;
    private @Getter @Setter String userName;
    private @Getter @Setter String userPhone;
    private @Getter @Setter String userIdentityId;
    private @Getter @Setter String title;
    private @Getter @Setter String content;
}
