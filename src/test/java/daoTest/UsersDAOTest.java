package daoTest;

import testUserProject.dao.UsersDAOInterface;
import testUserProject.dao.UsersDAOClass;
import testUserProject.model.Users;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

public class UsersDAOTest {
    private DriverManagerDataSource dataSource;
    private UsersDAOInterface dao;

    @Before
    public void setupBeforeEach(){
        dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/usersdb?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false");
        dataSource.setUsername("root");
        dataSource.setPassword("test-user");

        dao= new UsersDAOClass(dataSource);
    }


    @Test
    public void testSave(){
        Users user = new Users("Ivan", "Ivanov", "Ivanov@ya.ru", 1955);
        int result = dao.save(user);

        Assert.assertTrue(result>0);

        Users user2 = new Users("Anna", "Sidorova", "Sidorova@ya.ru", 1995);
        int result2 = dao.save(user2);

        Assert.assertTrue(result2>0);
    }

    @Test
    public void testUpdate(){
        Users user = new Users("Ivan", "Petrov", "Ivanov@ya.ru", 1955);
        user.setId(1);
        int result = dao.update(user);

        Assert.assertTrue(result>0);
    }

    @Test
    public void testGet(){
       Integer id1=1;
       Users user1 = dao.get(id1);
       Assert.assertNotNull(user1);

       Integer id2=0;
       Users user2 = dao.get(id2);
       Assert.assertNull(user2);
    }

    @Test
    public void testDelete(){
        Integer id1=1;
        int result = dao.delete(id1);
        Assert.assertTrue(result>0);

        Integer id2=0;
        int result2 = dao.delete(id2);
        Assert.assertFalse(result2>0);
    }

    @Test
    public void testGetAll(){
        List <Users> usersList = dao.getAll();
        Assert.assertTrue(!usersList.isEmpty());
    }


}
