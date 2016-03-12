package com.kdb.manager;

import com.kdb.dao.OperatorPackageDAO;
import com.kdb.model.OperatorPackage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-02-28 下午7:52
 */
@Service
@Transactional(rollbackFor = Exception.class,propagation = Propagation.SUPPORTS)
public class OperatorPackageManager {

    @Resource
    private OperatorPackageDAO operatorPackageDAO;

    public List<OperatorPackage> getPackageByOperatorId(long id){
        return operatorPackageDAO.getPackageByOperatorId(id);
    }
}
