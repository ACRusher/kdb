package com.kdb.manager;

import com.kdb.dao.OperatorsDAO;
import com.kdb.dao.common.ColumnFilter;
import com.kdb.dao.common.ParamMap;
import com.kdb.dao.common.Where;
import com.kdb.model.Operators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-01-31 下午1:43
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class OperatorManager {

    @Autowired
    private OperatorsDAO operatorsDAO;

    public boolean addOperator(Operators operators){
        return operatorsDAO.addModel(operators)==1;
    }

    public Operators getByName(String name){
        return operatorsDAO.getByName(name);
    }

    @Cacheable(value = "operator-cache" , key = "'all_operators'")
    public List<Operators> getAllOperators(){
        return operatorsDAO.getAllOperators();
    }


    public Operators getById(Long id){
        return operatorsDAO.getById(id);
    }
}
