package com.kdb.dao;

import com.kdb.dao.common.ColumnFilter;
import com.kdb.dao.common.ParamMap;
import com.kdb.dao.common.Where;
import com.kdb.dao.mapper.ArticlesMapper;
import com.kdb.dao.mapper.TemplateMapper;
import com.kdb.model.Articles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-01-20 下午11:58
 */
@Repository
public class ArticleDAO extends TemplateDAO<Articles> {

    @Autowired
    private ArticlesMapper articlesMapper;

    @Override
    protected TemplateMapper<Articles> getMapper() {
        return articlesMapper;
    }

    public List<Articles> getArticles(int limit,int offset,int type,String keyword){
        ParamMap paramMap=ParamMap.instance();
        Where.ListWrapper<ColumnFilter> wrapper=Where.andList();
        wrapper.add(ColumnFilter.instance().column("type").value(type).type(ColumnFilter.Type.EQ).build());
        if(keyword!=null){
            wrapper.add(ColumnFilter.instance().column("title").value("%" + keyword + "%").
                    type(ColumnFilter.Type.LIKE).build());
        }
        paramMap.where(Where.instance().or(wrapper.build()).build());
        paramMap.limit(offset, limit);
        paramMap.desc("gmt_create");
        return articlesMapper.getByCondition(paramMap.build());
    }

    public List<Articles> getRecentArticles(int size){
        ParamMap paramMap=ParamMap.instance();
        paramMap.limit(0, size);
        paramMap.desc("gmt_create");
        return articlesMapper.getByCondition(paramMap.build());
    }
}
