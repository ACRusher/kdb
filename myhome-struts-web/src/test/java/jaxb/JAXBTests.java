package jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;

/**
 * @author xiliang.zxl
 * @date 2016-01-26 上午12:23
 */
public class JAXBTests {

    public static void main(String[] args) throws JAXBException {
        JAXBContext context=JAXBContext.newInstance(Person.class);

        Marshaller marshaller=context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Person person=new Person();
        person.setName("tt");
        person.setGirlFriends(new ArrayList<Person>());
        String path=JAXBTests.class.getClassLoader().getResource("").getPath();
        System.out.println(path);
        marshaller.marshal(person, new File(path + "tt.txt"));

        Unmarshaller unmarshaller=context.createUnmarshaller();
        Person clone= (Person) unmarshaller.unmarshal(new File(path+"person.xml"));
        System.out.println(clone.getName());
    }
}
