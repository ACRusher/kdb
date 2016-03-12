package com.kdb.spi.impl;

import com.kdb.manager.OrderManager;
import com.kdb.manager.VillageOperatorsManager;
import com.kdb.model.Order;
import com.kdb.model.User;
import com.kdb.model.VillageOperators;
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
 * @date 2016-02-28 下午11:49
 */
@Service
public class OrderSpiProvider implements AjaxSpi<Boolean> {

    @Resource
    private OrderManager orderManager;
    @Resource
    private VillageOperatorsManager villageOperatorsManager;

    @Override
    public Result<Boolean> service(Map<String, Object> param, HttpServletRequest request) {

        Long villageOperatorId=Long.valueOf(request.getParameter("villageOperatorId"));
        Integer inFiveCircle=Integer.valueOf(request.getParameter("inFiveCircle"));
        Long packageId=Long.valueOf(request.getParameter("packageId"));
        String userName=request.getParameter("userName");
        String place=request.getParameter("place");
        String phone=request.getParameter("phone");
        User user= (User) param.get("loginUser");
        VillageOperators villageOperators=villageOperatorsManager.getById(villageOperatorId);

        Order order=new Order();
        order.setGmtModify(new Date());
        order.setGmtCreate(new Date());
        order.setAddress(place);
        if(user!=null) order.setUserId(user.getId());
        order.setUserName(userName);
        order.setPhone(phone);
        order.setOperatorServiceId(packageId);
        order.setOperatorId(villageOperators.getOperatorId());

        if(orderManager.createOrder(order)){
            Result<Boolean> result=new Result<Boolean>(Constants.RESULT_CODE_SUCCESS,
                    Constants.RESULT_MESSAGE_SUCCESS,true);
            return result;
        }
        Result<Boolean> result=new Result<Boolean>(Constants.RESULT_CODE_SERVER_ERROR,
                Constants.RESULT_MESSAGE_SERVER_ERROR,false);
        return result;
    }
}
