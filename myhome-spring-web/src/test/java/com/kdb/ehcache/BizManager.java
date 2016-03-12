package com.kdb.ehcache;

import com.kdb.dao.common.ColumnFilter;
import com.kdb.dao.common.ParamMap;
import com.kdb.dao.common.Where;
import com.kdb.dao.mapper.PersonMapper;
import com.kdb.vo.Person;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhouxiliang on 2015/12/18.
 */
@Service
public class BizManager {

    private AtomicInteger atomicInteger=new AtomicInteger(0);

    @Resource
    private PersonMapper personMapper;

    @Cacheable(value = "BizManager-uuid",key = "'uuid-'+#key")
    public String uuid(String key){
        return  atomicInteger.getAndIncrement()+"-["+key+"]-"+UUID.randomUUID().toString();
    }

    public List<Person> getAllPerson(){
        ParamMap paramMap=ParamMap.instance();
        Where.ListWrapper<ColumnFilter> wrapper=Where.andList();
        wrapper.add(ColumnFilter.instance().column("id").value("0").type(ColumnFilter.Type.GE));
        paramMap.where(Where.instance().or(wrapper.build()).build());
        return personMapper.getByCondition(paramMap.build());
    }
}
