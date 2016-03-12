package datasource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import zxl.model.Person;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2015-10-25 上午12:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Transactional
public class LocalDBTest {

    @Resource
    private DataSource dataSource;
    @Resource
    private SessionFactory sessionFactory;

    @Test
    public void testConnection()throws Exception{
        Assert.assertNotNull(dataSource);
        Connection connection=dataSource.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("select  * from person ");
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }
    }
    @Test
    public void testHibernate()throws Exception{
        Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Person.class);
        criteria.add(Restrictions.eq("id",1));
        List<Person> list=criteria.list();
        System.out.println(list.get(0));
    }
}
