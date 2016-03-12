package com.kdb.service;

import com.kdb.dao.SimpleDAO;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @author xiliang.zxl
 * @date 2015-12-04 上午12:39
 */
@Service
public class SimpleService {

    @Resource
    private SimpleDAO simpleDAO;

    public String doNothing(String str){
        return simpleDAO.echo(str);
    }
}
