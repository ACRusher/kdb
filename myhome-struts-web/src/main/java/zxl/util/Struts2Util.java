package zxl.util;

import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiliang.zxl
 * @date 2015-11-08 下午12:36
 */
public class Struts2Util {

    public static void renderText(String text){
        HttpServletResponse response=ServletActionContext.getResponse();
        response.setContentType("text/plain;charset=utf-8");
        try {
            response.getWriter().print(text);
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
