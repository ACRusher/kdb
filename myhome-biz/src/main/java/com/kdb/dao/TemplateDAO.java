package com.kdb.dao;

import com.kdb.dao.common.ColumnFilter;
import com.kdb.dao.common.ParamMap;
import com.kdb.dao.common.Where;
import com.kdb.dao.mapper.TemplateMapper;
import com.kdb.model.BaseModel;

import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-01-23 下午4:36
 */
public abstract class TemplateDAO<T extends BaseModel> {

    protected abstract TemplateMapper<T> getMapper();

    public int addModel(T model) {
        return getMapper().insert(model);
    }

    public int deleteById(Long id) {
        return getMapper().deleteById(id);
    }

    public int updateById(T model) {
        return getMapper().update(model);
    }

    public T getById(Long id) {
        ParamMap paramMap = ParamMap.instance();
        Where.ListWrapper<ColumnFilter> wrapper = Where.andList();
        wrapper.add(ColumnFilter.instance().column("id").value(id).type(ColumnFilter.Type.EQ).build());
        paramMap.where(Where.instance().or(wrapper.build()).build());
        List<T> list = getMapper().getByCondition(paramMap.build());
        if (list != null && list.size() > 0) return list.get(0);
        return null;
    }
}
