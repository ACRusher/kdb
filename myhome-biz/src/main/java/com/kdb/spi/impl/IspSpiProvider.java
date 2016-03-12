package com.kdb.spi.impl;

import com.kdb.spi.AjaxSpi;
import com.kdb.util.Constants;
import com.kdb.util.IpUtil;
import com.kdb.util.RequestUtils;
import com.kdb.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author xiliang.zxl
 * @date 2016-02-01 上午1:08
 */
@Service
public class IspSpiProvider implements AjaxSpi<String> {

    @Override
    public Result<String> service(Map<String, Object> param, HttpServletRequest request) {
        String ip= RequestUtils.getRemoteAddr(request);
        String operatorName=IpUtil.getOperatorName(ip);
        if(StringUtils.isBlank(operatorName)) operatorName="联通宽带";
        return new Result<String>(Constants.RESULT_CODE_SUCCESS,
                Constants.RESULT_MESSAGE_SUCCESS
                ,true,operatorName);
    }
}
