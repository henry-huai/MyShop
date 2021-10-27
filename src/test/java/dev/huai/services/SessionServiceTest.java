package dev.huai.services;


import org.junit.Test;

import static junit.framework.TestCase.*;

public class SessionServiceTest {
    SessionService sessionService = new SessionService();

//https://junit.org/junit4/javadoc/4.8/org/junit/Assert.html
    @Test
    public void testGetSessionFactory() {
        assertEquals(true, sessionService.getSessionFactory().openSession().isConnected());
    }
}