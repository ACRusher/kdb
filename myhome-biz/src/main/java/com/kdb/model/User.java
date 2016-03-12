package com.kdb.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;

/**
 * @author xiliang.zxl
 * @date 2016-01-13 下午10:00
 */
public class User  extends BaseModel{

    private @Getter @Setter String name;
    private @Getter @Setter Integer age;
    private @Getter @Setter String identityId;
    private @Getter @Setter String phone;
    private @Getter @Setter String address;
    @Transient
    private @Getter @Setter String password;
    private @Getter @Setter String headPicture;
    private @Getter @Setter String nick;
    private @Getter @Setter Long operatorId;
    private @Getter @Setter Long villageId;


}
