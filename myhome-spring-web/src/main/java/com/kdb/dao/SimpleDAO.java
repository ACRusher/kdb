package com.kdb.dao;

import org.springframework.stereotype.Repository;

/**
 * Created by zhouxiliang on 2015/12/19.
 */
@Repository
public class SimpleDAO {

    public String echo(String msg){
        return msg;
    }
}
