package jaxb;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Assert;
import org.springframework.util.ResourceUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2015-11-08 下午3:31
 */
@XmlType //一般不用
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL) // 定义节点的输出顺序
@XmlRootElement(name = "root") //根节点
@XmlAccessorType(XmlAccessType.NONE) //可以设定如何自动访问属性，这儿设置为none方便演示
public class Person implements Serializable {
    private static final long serialVersionUID = 2777942778587053040L;
    private String name;
    private int age;
    private String phone;
    @XmlTransient //代表不输出到xml
    private volatile int random;
    @XmlAttribute //代表属性
    private boolean alive;
    @XmlElement //代表一个子节点
    private Pet pet;
    @XmlJavaTypeAdapter(MyXmlAdapter.class) //可以自定义java类型到xml string类型的映射
    @XmlElement
    private Date birthDay;

    private List<Person> girlFriends;

    @XmlElementWrapper(name = "girlFriends") //可以在集合上面加这个属性，代表一个可变列表
    @XmlElement(name = "girl")
    public List<Person> getGirlFriends() {
        return girlFriends;
    }

    public void setGirlFriends(List<Person> girlFriends) {
        this.girlFriends = girlFriends;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @XmlElement
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static void main(String[] args) throws Exception {
        URL file = ResourceUtils.getURL("myhome-web/src/test/resources/person.xml");
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Assert.assertNotNull(file);

        Person person = (Person) context.createUnmarshaller().unmarshal(file.openStream());
        Assert.assertNotNull(person);
        System.out.println(ToStringBuilder.reflectionToString(person));

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(person, System.out);
    }
}
