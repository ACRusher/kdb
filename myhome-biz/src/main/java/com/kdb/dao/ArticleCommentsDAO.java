package com.kdb.dao;

import com.kdb.dao.common.ColumnFilter;
import com.kdb.dao.common.ParamMap;
import com.kdb.dao.common.Where;
import com.kdb.dao.mapper.ArticleCommentsMapper;
import com.kdb.dao.mapper.TemplateMapper;
import com.kdb.model.ArticleComments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-01-23 下午3:05
 */
@Repository
public class ArticleCommentsDAO extends TemplateDAO<ArticleComments> {

    @Autowired
    private ArticleCommentsMapper articleCommentsMapper;

    @Override
    protected TemplateMapper<ArticleComments> getMapper() {
        return articleCommentsMapper;
    }

    public List<ArticleComments> getByArticleId(long articleId){
        ParamMap paramMap=ParamMap.instance();
        Where.ListWrapper<ColumnFilter> andList=Where.andList();
        andList.add(ColumnFilter.instance().column("article_id").value(articleId).type(ColumnFilter.Type.EQ).build());
        paramMap.where(Where.instance().or(andList.build()).build());
        paramMap.asc("gmt_create");
        return articleCommentsMapper.getByCondition(paramMap.build());
    }

    public List<ArticleComments> getArticleCommentsByToAuthorId(long authorId){
        ParamMap paramMap=ParamMap.instance();
        Where.ListWrapper<ColumnFilter> andList=Where.andList();
        andList.add(ColumnFilter.instance().column("to_author_id").value(authorId).type(ColumnFilter.Type.EQ).build());
        paramMap.where(Where.instance().or(andList.build()).build());
        paramMap.asc("gmt_create");
        return articleCommentsMapper.getByCondition(paramMap.build());
    }
}
