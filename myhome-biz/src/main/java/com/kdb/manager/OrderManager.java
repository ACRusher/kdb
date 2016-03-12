package com.kdb.manager;

import com.kdb.dao.OrderDAO;
import com.kdb.dao.mapper.OrderMapper;
import com.kdb.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author xiliang.zxl
 * @date 2016-02-28 下午11:51
 */
@Service
@Transactional(rollbackFor = Exception.class,propagation = Propagation.SUPPORTS)
public class OrderManager {

    @Resource
    private OrderDAO orderDAO;

    public boolean createOrder(Order order){
       return orderDAO.addModel(order)==1;
    }
}
