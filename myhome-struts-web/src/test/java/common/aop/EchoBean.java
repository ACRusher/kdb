package common.aop;

import common.aop.annotation.Aop;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiliang.zxl
 * @date 2015-10-25 下午3:44
 */
//@Aop
public class EchoBean {
    public String echo(String words){
        return words;
    }
    public String say(String words){
        return words;
    }
    public String sing(String words){
        return words;
    }
    @Transactional
    public String search(){
        return "success";
    }

    public String drink(){
        return "water";
    }
    public void argFunc(String s,int i,boolean b){
        return;
    }
}
