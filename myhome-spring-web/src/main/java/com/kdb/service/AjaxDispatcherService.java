package com.kdb.service;

import com.kdb.manager.DispatcherManager;
import com.kdb.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author xiliang.zxl
 * @date 2016-01-10 下午5:04
 */
@Service
public class AjaxDispatcherService {

    @Autowired
    private DispatcherManager dispatcherManager;

    /**
     * ajax 分发服务
     * @param method
     * @param params
     * @param request
     * @param <T>
     * @return
     */
    public <T> Result<T> service(String method,Map<String,Object> params,HttpServletRequest request){
        return  dispatcherManager.service(method,params,request);
    }
}
