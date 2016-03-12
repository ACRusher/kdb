package jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xiliang.zxl
 * @date 2015-11-08 下午5:56
 */
public class MyXmlAdapter extends XmlAdapter<String,Date> {


    @Override
    public Date unmarshal(String v) throws Exception {
        if(v!=null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
            return sdf.parse(v);
        }
        return null;
    }

    @Override
    public String marshal(Date v) throws Exception {
        if(v!=null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
            return sdf.format(v);
        }
        return null;
    }
}
