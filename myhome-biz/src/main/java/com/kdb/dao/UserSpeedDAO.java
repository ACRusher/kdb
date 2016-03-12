package com.kdb.dao;

import com.kdb.dao.mapper.TemplateMapper;
import com.kdb.dao.mapper.UserSpeedMapper;
import com.kdb.model.UserSpeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author xiliang.zxl
 * @date 2016-01-23 下午4:15
 */
@Repository
public class UserSpeedDAO extends TemplateDAO<UserSpeed> {

    @Autowired
    private UserSpeedMapper userSpeedMapper;

    @Override
    protected TemplateMapper<UserSpeed> getMapper() {
        return userSpeedMapper;
    }
}
