package com.kdb.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiliang.zxl
 * @date 2016-01-10 下午6:33
 */
public class BaseModel implements Serializable {

    private long id;
    private Date gmtCreate;
    private Date gmtModify;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}
