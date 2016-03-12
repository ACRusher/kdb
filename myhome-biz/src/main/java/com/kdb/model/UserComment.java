package com.kdb.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiliang.zxl
 * @date 2016-01-19 下午11:31
 */
public class UserComment extends BaseModel {
    private static final long serialVersionUID = 2988421403841336906L;
    private @Getter @Setter Long authorId;
    private @Getter @Setter Long villageOperatorId;
    private @Getter @Setter String star;
    private @Getter @Setter String speed;
    private @Getter @Setter String service;
    private @Getter @Setter String stable;
    private @Getter @Setter String content;
    private @Getter @Setter String likes;
    private @Getter @Setter String complaint;

}
