package com.kdb.manager;

import com.kdb.dao.VillageDAO;
import com.kdb.model.Village;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-01-31 下午1:49
 */

@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class VillageManager {

    @Autowired
    private VillageDAO villageDAO;

    public boolean addVillage(Village village){
        return villageDAO.addModel(village)==1;
    }

    public Village getByName(String city,String area,String villageName){
        return villageDAO.getByName(city,area,villageName);
    }

    public Village getById(long id){
        return villageDAO.getById(id);
    }

}
