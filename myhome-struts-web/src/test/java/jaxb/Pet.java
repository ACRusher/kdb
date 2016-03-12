package jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author xiliang.zxl
 * @date 2015-11-08 下午5:50
 */
@XmlRootElement(name="pet")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pet implements Serializable {
    private static final long serialVersionUID = 5332313069713386631L;
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
