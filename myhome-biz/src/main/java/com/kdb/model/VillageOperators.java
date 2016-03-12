package com.kdb.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiliang.zxl
 * @date 2016-01-19 下午11:45
 */
public class VillageOperators extends BaseModel {
    private static final long serialVersionUID = 7220972479037484958L;
    private @Getter @Setter Long villageId;
    private @Getter @Setter Long operatorId;
    private @Getter @Setter String star;
    private @Getter @Setter String speed;
    private @Getter @Setter String service;
    private @Getter @Setter String stable;
    private @Getter @Setter String upload;
    private @Getter @Setter String download;
    private @Getter @Setter String ping;
    private @Getter @Setter Integer commentCount;
    private @Getter @Setter Integer speedCount;
    private @Getter @Setter String likes;
    private @Getter @Setter String complaint;
}
