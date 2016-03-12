package com.kdb.model;

import com.kdb.vo.Article;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;

/**
 * @author xiliang.zxl
 * @date 2016-01-19 下午11:54
 */
public class Articles extends BaseModel {
    private static final long serialVersionUID = 2575829158496517351L;
    private @Getter @Setter String title;
    private @Getter @Setter String content;
    private @Getter @Setter String pictures;
    private @Getter @Setter String tag;
    private @Getter @Setter Long authorId;
    private @Getter @Setter String authorName;
    private @Getter @Setter String authorPicture;
    private @Getter @Setter Integer type;

    public Article toVO(){
        Article article=new Article();
        article.setId(this.id);
        article.setTag(this.tag);
        article.setTitle(this.title);
        article.setContent(this.content);
        article.setAuthorName(this.authorName);
        article.setAuthorId(this.authorId);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        article.setTime(sdf.format(this.getGmtCreate()));
        article.setAuthorPic(this.authorPicture);
        return article;
    }

}
