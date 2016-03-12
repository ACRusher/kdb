package com.kdb.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiliang.zxl
 * @date 2016-01-19 下午11:57
 */
public class ArticleComments extends BaseModel {
    private static final long serialVersionUID = -7007600530606226091L;
    private @Getter @Setter Long articleId;
    private @Getter @Setter Integer sequence;
    private @Getter @Setter String content;
    private @Getter @Setter Long authorId;
    private @Getter @Setter String authorName;
    private @Getter @Setter Long toAuthorId;
    private @Getter @Setter String toAuthorName;
    private @Getter @Setter Integer toSequence;
    private @Getter @Setter Integer type;
}
