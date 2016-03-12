package com.kdb.dao.mapper;

import java.util.List;
import java.util.Map;

/**
 * @author xiliang.zxl
 * @date 2016-01-23 下午4:26
 */
public interface TemplateMapper<T extends com.kdb.model.BaseModel> {

    int insert(T model);

    int deleteById(Long id);

    int update(T article);

    List<T> getByCondition(Map param);
}
