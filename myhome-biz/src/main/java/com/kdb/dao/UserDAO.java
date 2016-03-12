package com.kdb.dao;

import com.kdb.dao.common.ColumnFilter;
import com.kdb.dao.common.ParamMap;
import com.kdb.dao.common.Where;
import com.kdb.dao.mapper.TemplateMapper;
import com.kdb.dao.mapper.UserMapper;
import com.kdb.model.User;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-01-13 下午10:36
 */
@Repository
public class UserDAO extends TemplateDAO<User> {

    @Autowired
    private UserMapper userMapper;


    @Override
    protected TemplateMapper<User> getMapper() {
        return userMapper;
    }

    public User getByPhone(String phone){
        ParamMap paramMap=ParamMap.instance();
        Where.ListWrapper<ColumnFilter> wrapper= Where.andList();
        wrapper.add(ColumnFilter.instance().column("phone").value(phone).type(ColumnFilter.Type.EQ).build());
        paramMap.where(Where.instance().or(wrapper.build()).build());
        List<User> users=userMapper.getByCondition(paramMap.build());
        if(users!=null && users.size()>0) return users.get(0);
        return null;
    }
}
