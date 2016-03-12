package com.kdb.manager;

import com.google.common.collect.Lists;
import com.kdb.dao.OperatorsDAO;
import com.kdb.dao.VillageOperatorsDAO;
import com.kdb.model.Operators;
import com.kdb.model.VillageOperators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-01-31 下午1:58
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class VillageOperatorsManager {

    @Autowired
    private VillageOperatorsDAO villageOperatorsDAO;
    @Autowired
    private OperatorsDAO operatorsDAO;

    public boolean addVillageOperators(VillageOperators villageOperators){
        return villageOperatorsDAO.addModel(villageOperators)==1;
    }

    public List<VillageOperators> getByVillageId(Long villageId){
        return villageOperatorsDAO.getByVillageId(villageId);
    }

    public VillageOperators getByVillageIdAndOperatorId(Long villageId,Long operatorId){
        return villageOperatorsDAO.getByVillageIdAndOperatorId(villageId, operatorId);
    }

    public VillageOperators getById(long id){
        return villageOperatorsDAO.getById(id);
    }

    /**
     * 当无数据时，使用这个提供default数据
     * @return
     */
    public List<VillageOperators> getDefaultVillageOperators(){
        List<Operators> operatorsList=operatorsDAO.getAllOperators();
        List<VillageOperators> list=new ArrayList<VillageOperators>();
        for(Operators operators : operatorsList){
            VillageOperators t=villageOperatorsDAO.getByOperatorId(operators.getId());
            if(t!=null) list.add(t);
        }
        return list;
    }
}
