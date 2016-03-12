package com.kdb.dao.mapper;

import com.kdb.annotation.MybatisMapper;
import com.kdb.model.Operators;
import com.kdb.vo.Person;

import java.util.List;
import java.util.Map;

/**
 * Created by zhouxiliang on 2015/12/18.
 */
@MybatisMapper
public interface PersonMapper {


    List<Person> getByCondition(Map param);
}
