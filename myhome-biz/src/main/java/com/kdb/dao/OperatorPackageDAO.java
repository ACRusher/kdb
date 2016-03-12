package com.kdb.dao;

import com.kdb.dao.common.ColumnFilter;
import com.kdb.dao.common.ParamMap;
import com.kdb.dao.common.Where;
import com.kdb.dao.mapper.OperatorPackageMapper;
import com.kdb.dao.mapper.TemplateMapper;
import com.kdb.model.OperatorPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-01-23 下午4:01
 */
@Repository
public class OperatorPackageDAO extends TemplateDAO<OperatorPackage> {

    @Autowired
    private OperatorPackageMapper operatorPackageMapper;

    @Override
    protected TemplateMapper<OperatorPackage> getMapper() {
        return operatorPackageMapper;
    }

    public List<OperatorPackage> getPackageByOperatorId(long id){
        ParamMap paramMap=ParamMap.instance();
        Where.ListWrapper<ColumnFilter> wrapper=Where.andList();
        wrapper.add(ColumnFilter.instance().column("operator_id").value(id).type(ColumnFilter.Type.EQ).build());

        paramMap.where(Where.instance().or(wrapper.build()).build());
        paramMap.limit(0, 100);
        return operatorPackageMapper.getByCondition(paramMap.build());
    }
}
