package com.kdb.manager;

import com.kdb.dao.UserSpeedDAO;
import com.kdb.dao.mapper.UserSpeedMapper;
import com.kdb.model.UserSpeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiliang.zxl
 * @date 2016-01-31 下午2:24
 */

@Service
@Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
public class UserSpeedManager {

    @Autowired
    private UserSpeedDAO userSpeedDAO;

    public boolean addUserSpeed(UserSpeed userSpeed){
        return userSpeedDAO.addModel(userSpeed)==1;
        //TODO 更新 village operator 表
    }


}
