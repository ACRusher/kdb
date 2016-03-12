package com.kdb.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xiliang.zxl
 * @date 2016-01-10 下午5:22
 */
public class WebUtil {

    public static void responseJsonp(String callback,Object data,HttpServletResponse response){
        StringBuilder sb=new StringBuilder();
        sb.append("try{")
                .append(callback)
                .append("(")
                .append(JSON.toJSONString(data))
                .append(")}catch(e){}");
        try {
            response.setContentType("application/x-javascript;charset=utf-8");
            PrintWriter printWriter=response.getWriter();
            printWriter.write(sb.toString());
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    public static void responseJson(Object data,HttpServletResponse response){
        try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter printWriter=response.getWriter();
            printWriter.write(JSON.toJSONString(data));
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }
}
