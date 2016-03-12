package com.kdb.spi;

import com.kdb.vo.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author xiliang.zxl
 * @date 2016-01-20 下午11:02
 */
public interface AjaxSpi<T> {

    Result<T> service(Map<String,Object> param,HttpServletRequest request);
}
