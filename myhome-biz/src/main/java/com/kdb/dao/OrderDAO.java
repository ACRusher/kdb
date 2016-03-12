package com.kdb.dao;

import com.kdb.dao.mapper.OrderMapper;
import com.kdb.dao.mapper.TemplateMapper;
import com.kdb.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author xiliang.zxl
 * @date 2016-01-23 下午4:08
 */
@Repository
public class OrderDAO extends TemplateDAO<Order> {

    @Autowired
    private OrderMapper orderMapper;


    @Override
    protected TemplateMapper<Order> getMapper() {
        return orderMapper;
    }
}
