package services;

import config.TestAppConfig;
import config.TestDataConfig;
import models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.UserService;
import services.UserServiceDao;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class, TestDataConfig.class})
public class UserTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceDao userDao;

    @Before
    public void initDB() {
        User u = new User();
        u.setUsername("that");
        u.setAlias("guy");
        u.setSpecialties("who");
        u.setPassword("fills");
        u.setWeaknesses("thedb");
        userDao.save(u);
    }

    @Test(expected = PersistenceException.class)
    public void testNoDupes() {
        User u = new User();
        u.setUsername("that");
        u.setAlias("guy");
        u.setPassword("alreadytaken");
        u.setSpecialties("foo");
        userDao.save(u);
    }

    @Test
    public void testUserUpdate() {
        User u = userDao.findFirstByUsername("that");
        Assert.assertNotNull(u);
        u.setAlias("fu manchu");
        userDao.save(u);
        Assert.assertNotNull(userDao.findFirstByAlias("fu manchu"));
    }

    @Test(expected = ConstraintViolationException.class)
    public void testIncompleteUser() {
        User u = new User();
        u.setUsername("none");
        u.setPassword("shallpass");
        userDao.save(u);
    }

    @Test
    public void testDeleteUser() {
        User u = userDao.findFirstByUsername("that");
        userDao.delete(u);
        Assert.assertNull(userDao.findFirstByUsername("that"));
    }
}
