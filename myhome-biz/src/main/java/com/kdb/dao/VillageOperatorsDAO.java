package com.kdb.dao;

import com.kdb.dao.common.ColumnFilter;
import com.kdb.dao.common.ParamMap;
import com.kdb.dao.common.Where;
import com.kdb.dao.mapper.TemplateMapper;
import com.kdb.dao.mapper.VillageOperatorsMapper;
import com.kdb.model.VillageOperators;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-01-23 下午4:21
 */
@Repository
public class VillageOperatorsDAO extends TemplateDAO<VillageOperators> {

    @Autowired
    private VillageOperatorsMapper villageOperatorsMapper;

    @Override
    protected TemplateMapper<VillageOperators> getMapper() {
        return villageOperatorsMapper;
    }

    public List<VillageOperators> getByVillageId(Long villageId){
        ParamMap paramMap=ParamMap.instance();
        Where.ListWrapper<ColumnFilter> wrapper=Where.andList();
        wrapper.add(ColumnFilter.instance().column("village_id").value(villageId).type(ColumnFilter.Type.EQ).build());
        paramMap.where(Where.instance().or(wrapper.build()).build());
        return villageOperatorsMapper.getByCondition(paramMap.build());
    }

    public VillageOperators getByOperatorId(Long operatorId){
        ParamMap paramMap=ParamMap.instance();
        Where.ListWrapper<ColumnFilter> wrapper=Where.andList();
        wrapper.add(ColumnFilter.instance().column("operator_id").value(operatorId).type(ColumnFilter.Type.EQ).build());
        paramMap.where(Where.instance().or(wrapper.build()).build());
        paramMap.limit(0, 3);
        List<VillageOperators> result= villageOperatorsMapper.getByCondition(paramMap.build());
        if(result!=null && result.size()>0){
            return result.get(0);
        }
        return null;
    }

    public VillageOperators getByVillageIdAndOperatorId(Long villageId,Long operatorId){
        ParamMap paramMap=ParamMap.instance();
        Where.ListWrapper<ColumnFilter> wrapper=Where.andList();
        wrapper.add(ColumnFilter.instance().column("village_id").value(villageId).type(ColumnFilter.Type.EQ).build());
        wrapper.add(ColumnFilter.instance().column("operator_id").value(operatorId).type(ColumnFilter.Type.EQ).build());
        paramMap.where(Where.instance().or(wrapper.build()).build());
        paramMap.limit(0, 3);
        List<VillageOperators> result= villageOperatorsMapper.getByCondition(paramMap.build());
        if(result!=null && result.size()>0){
            return result.get(0);
        }
        return null;
    }
}
