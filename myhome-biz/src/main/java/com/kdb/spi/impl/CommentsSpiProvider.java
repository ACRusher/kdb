package com.kdb.spi.impl;

import com.kdb.manager.ArticleCommentsManager;
import com.kdb.manager.UserManager;
import com.kdb.model.ArticleComments;
import com.kdb.model.User;
import com.kdb.spi.AjaxSpi;
import com.kdb.util.Constants;
import com.kdb.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author xiliang.zxl
 * @date 2016-02-28 上午11:55
 */
@Service
public class CommentsSpiProvider implements AjaxSpi<Boolean> {

    @Resource
    private ArticleCommentsManager articleCommentsManager;
    @Resource
    private UserManager userManager;

    @Override
    public Result<Boolean> service(Map<String, Object> param, HttpServletRequest request) {
        Long articleId =Long.valueOf(request.getParameter("articleId"));
        Long toAuthorId =Long.valueOf(request.getParameter("toAuthorId"));
        String content= request.getParameter("content");
        User user= (User) param.get("loginUser");
        User toUser=userManager.getUserById(toAuthorId);
        ArticleComments articleComments=new ArticleComments();
        articleComments.setGmtModify(new Date());
        articleComments.setGmtCreate(new Date());
        articleComments.setContent(content);
        articleComments.setToAuthorName(maskPhone(toUser.getPhone()));
        articleComments.setToAuthorId(toUser.getId());
        articleComments.setArticleId(articleId);
        articleComments.setAuthorId(user.getId());
        articleComments.setAuthorName(maskPhone(user.getPhone()));
        articleComments.setType(1);
        if(articleCommentsManager.addArtileCommetns(articleComments)){
            Result<Boolean> result=new Result<Boolean>(Constants.RESULT_CODE_SUCCESS,Constants.RESULT_MESSAGE_SUCCESS,true);
            return result;
        }
        Result<Boolean> result=new Result<Boolean>(Constants.RESULT_CODE_PARAM_ERROR,Constants.RESULT_MESSAGE_PARAM_ERROR,false);
        return result;
    }

    private String maskPhone(String phone){
        return phone.substring(0,3)+"****"+phone.substring(7);
    }
}
