package com.kdb.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiliang.zxl
 * @date 2016-01-19 下午11:41
 */
public class UserSpeed extends BaseModel {
    private static final long serialVersionUID = -7528159066937770254L;
    private @Getter @Setter Long userId;
    private @Getter @Setter Long villageOperatorsId;
    private @Getter @Setter String upload;
    private @Getter @Setter String download;
    private @Getter @Setter String ping;
    private @Getter @Setter String ip;
    private @Getter @Setter String address;
}
