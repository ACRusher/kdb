package com.kdb.manager;

import com.kdb.dao.ArticleCommentsDAO;
import com.kdb.model.ArticleComments;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-02-28 上午10:43
 */
@Service
@Transactional(rollbackFor = Exception.class , propagation = Propagation.SUPPORTS)
public class ArticleCommentsManager {

    @Resource
    private ArticleCommentsDAO articleCommentsDAO;

    public List<ArticleComments> getArticleComments(long articleId){
        return articleCommentsDAO.getByArticleId(articleId);
    }

    public boolean addArtileCommetns(ArticleComments articleComments){
        return articleCommentsDAO.addModel(articleComments)==1;
    }

    public List<ArticleComments> getArticleCommentsByToAuthorId(long authorId){
        return articleCommentsDAO.getArticleCommentsByToAuthorId(authorId);
    }

}
