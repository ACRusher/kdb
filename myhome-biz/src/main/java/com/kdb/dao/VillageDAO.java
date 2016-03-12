package com.kdb.dao;

import com.kdb.dao.common.ColumnFilter;
import com.kdb.dao.common.ParamMap;
import com.kdb.dao.common.Where;
import com.kdb.dao.mapper.TemplateMapper;
import com.kdb.dao.mapper.VillageMapper;
import com.kdb.model.Village;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-01-23 下午4:18
 */
@Repository
public class VillageDAO extends TemplateDAO<Village> {

    @Autowired
    private VillageMapper villageMapper;

    @Override
    protected TemplateMapper<Village> getMapper() {
        return villageMapper;
    }

    public Village getByName(String city,String area,String village){
        ParamMap paramMap=ParamMap.instance();
        Where.ListWrapper<ColumnFilter> wrapper=Where.andList();
        wrapper.add(ColumnFilter.instance().column("city").value(city).type(ColumnFilter.Type.EQ).build());
        wrapper.add(ColumnFilter.instance().column("area").value(area).type(ColumnFilter.Type.EQ).build());
        wrapper.add(ColumnFilter.instance().column("village").value(village).type(ColumnFilter.Type.EQ).build());
        paramMap.where(Where.instance().or(wrapper.build()).build());
        List<Village> list= villageMapper.getByCondition(paramMap.build());
        if(list!=null && list.size()>0) return list.get(0);
        return null;
    }
}
