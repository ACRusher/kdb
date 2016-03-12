package com.kdb.manager;

import com.kdb.dao.UserCommentDAO;
import com.kdb.model.UserComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-01-31 下午2:24
 */
@Service
@Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
public class UserCommentManager {

    @Autowired
    private UserCommentDAO userCommentDAO;

    public boolean addUserComment(UserComment userComment){
        return userCommentDAO.addModel(userComment)==1;
        //TODO 更新 village operator 表
    }

    /**
     * 按照gmt_create 取数据
     * @param id
     * @param size
     * @return
     */
    public List<UserComment> getUserCommentByVillageOperatorId(long id,int size){
        return userCommentDAO.getUserCommentByVillageOperatorId(id,size);
    }


}
