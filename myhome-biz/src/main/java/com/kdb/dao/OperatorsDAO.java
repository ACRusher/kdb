package com.kdb.dao;

import com.kdb.dao.common.ColumnFilter;
import com.kdb.dao.common.ParamMap;
import com.kdb.dao.common.Where;
import com.kdb.dao.mapper.OperatorsMapper;
import com.kdb.dao.mapper.TemplateMapper;
import com.kdb.model.Operators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-01-23 下午4:05
 */
@Repository
public class OperatorsDAO extends TemplateDAO<Operators> {

    @Autowired
    private OperatorsMapper operatorsMapper;


    @Override
    protected TemplateMapper<Operators> getMapper() {
        return operatorsMapper;
    }

    public Operators getByName(String name) {
        ParamMap paramMap = ParamMap.instance();
        Where.ListWrapper<ColumnFilter> wrapper = Where.andList();
        wrapper.add(ColumnFilter.instance().column("name").value(name).type(ColumnFilter.Type.EQ).build());
        paramMap.where(Where.instance().or(wrapper.build()).build());
        List<Operators> list= operatorsMapper.getByCondition(paramMap.build());
        if(list!=null && list.size()>0) return list.get(0);
        return null;
    }

    public List<Operators> getAllOperators() {
        ParamMap paramMap = ParamMap.instance();
        Where.ListWrapper<ColumnFilter> wrapper = Where.andList();
        wrapper.add(ColumnFilter.instance().column("id").value(0).type(ColumnFilter.Type.GE).build());
        paramMap.where(Where.instance().or(wrapper.build()).build());
        return operatorsMapper.getByCondition(paramMap.build());
    }
}
