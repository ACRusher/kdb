package com.kdb.manager;

import com.kdb.spi.AjaxSpi;
import com.kdb.util.ApplicationContextUtil;
import com.kdb.util.Constants;
import com.kdb.vo.Result;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author xiliang.zxl
 * @date 2016-01-10 下午5:15
 */
@Component
public class DispatcherManager<T>  implements InitializingBean{

    private Map<String, String> methodConfig = new HashMap<String, String>();

    public Result<T> service(String method, Map<String, Object> param, HttpServletRequest request) {
        if (!methodConfig.containsKey(method)) {
            return new Result(Constants.RESULT_CODE_PARAM_ERROR
                    , Constants.RESULT_MESSAGE_PARAM_ERROR, false);
        }
        AjaxSpi ajaxSpi = ApplicationContextUtil.getBean(methodConfig.get(method));
        if (ajaxSpi != null) {
            return ajaxSpi.service(param, request);
        }
        return new Result(Constants.RESULT_CODE_SERVICE_NOTFOUND
                , method + Constants.RESULT_MESSAGE_SERVICE_NOTFOUND, false);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        InputStream inputStream =getClass().getClassLoader().getResourceAsStream(Constants.SERVICE_CONFIG_FILE);
        Properties properties=new Properties();
        properties.load(inputStream);
        for(Object key : properties.keySet()){
            methodConfig.put(key.toString(),properties.getProperty(key.toString()));
        }
    }
}
