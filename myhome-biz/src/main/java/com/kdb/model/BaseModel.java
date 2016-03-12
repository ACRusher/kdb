package com.kdb.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiliang.zxl
 * @date 2016-01-13 下午10:01
 */
public class BaseModel implements Serializable {
    private static final long serialVersionUID = 5015194028746586492L;
    protected @Getter @Setter Long id;
    protected @Getter @Setter Date gmtCreate;
    protected @Getter @Setter Date gmtModify;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
