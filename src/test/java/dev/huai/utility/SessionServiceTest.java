package dev.huai.utility;


import org.hibernate.SessionFactory;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class SessionServiceTest {
    private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

//https://junit.org/junit4/javadoc/4.8/org/junit/Assert.html
    @Test
    public void testGetSessionFactory() {
        assertEquals(true, sessionFactory.openSession().isConnected());
    }
}