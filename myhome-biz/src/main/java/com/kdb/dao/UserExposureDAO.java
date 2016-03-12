package com.kdb.dao;

import com.kdb.dao.mapper.TemplateMapper;
import com.kdb.dao.mapper.UserExposureMapper;
import com.kdb.model.UserExposure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author xiliang.zxl
 * @date 2016-01-23 下午4:13
 */
@Repository
public class UserExposureDAO extends TemplateDAO<UserExposure> {

    @Autowired
    private UserExposureMapper userExposureMapper;


    @Override
    protected TemplateMapper<UserExposure> getMapper() {
        return userExposureMapper;
    }
}
