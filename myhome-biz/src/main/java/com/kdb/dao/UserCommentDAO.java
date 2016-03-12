package com.kdb.dao;

import com.kdb.dao.common.ColumnFilter;
import com.kdb.dao.common.ParamMap;
import com.kdb.dao.common.Where;
import com.kdb.dao.mapper.TemplateMapper;
import com.kdb.dao.mapper.UserCommentMapper;
import com.kdb.model.UserComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-01-23 下午4:10
 */
@Repository
public class UserCommentDAO extends TemplateDAO<UserComment> {

    @Autowired
    private UserCommentMapper userCommentMapper;

    @Override
    protected TemplateMapper<UserComment> getMapper() {
        return userCommentMapper;
    }

    public List<UserComment> getUserCommentByVillageOperatorId(long id,int size){
        ParamMap paramMap=ParamMap.instance();
        Where.ListWrapper<ColumnFilter> andList=Where.andList();
        andList.add(ColumnFilter.instance().column("village_operator_id").value(id).type(ColumnFilter.Type.EQ).build());
        paramMap.where(Where.instance().or(andList.build()).build());
        paramMap.desc("gmt_create");
        paramMap.limit(0,size);
        return userCommentMapper.getByCondition(paramMap.build());
    }
}
