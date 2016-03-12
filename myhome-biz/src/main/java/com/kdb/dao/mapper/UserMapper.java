package com.kdb.dao.mapper;

import com.kdb.annotation.MybatisMapper;
import com.kdb.model.User;

import java.util.List;
import java.util.Map;

/**
 * @author xiliang.zxl
 * @date 2016-01-13 下午10:09
 */
@MybatisMapper
public interface UserMapper extends TemplateMapper<User> {

    int insert(User user);

    int delete(User user);

    int update(User user);

    List<User> getByCondition(Map param);
}
