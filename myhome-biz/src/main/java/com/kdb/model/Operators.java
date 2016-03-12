package com.kdb.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiliang.zxl
 * @date 2016-01-19 下午11:51
 */
public class Operators extends BaseModel {
    private static final long serialVersionUID = 4484215115756650358L;
    private @Getter @Setter String name;
    private @Getter @Setter String code;
    private @Getter @Setter String logo;
    private @Getter @Setter String extra;
    private @Getter @Setter String period;
    private @Getter @Setter Integer fee;
}
