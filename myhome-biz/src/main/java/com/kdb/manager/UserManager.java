package com.kdb.manager;

import com.kdb.dao.UserDAO;
import com.kdb.model.User;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiliang.zxl
 * @date 2016-01-23 下午9:45
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class UserManager {

    @Autowired
    private UserDAO userDAO;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public User getUserByPhone(String phone){
        if(!NumberUtils.isNumber(phone) || phone.length()!=11){
            return null;
        }
        return userDAO.getByPhone(phone);
    }

    public User getUserById(long id){
        return userDAO.getById(id);
    }

    public boolean saveUser(User user){
        return userDAO.addModel(user)==1;
    }

}
