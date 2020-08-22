package pl.sdacademy;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.service.spi.ServiceException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SessionFactoryProviderTest {
    @Test
    public void createSessionFactoryProvider() {
        SessionFactoryProvider sessionFactoryProvider = new SessionFactoryProvider();
        try {
            sessionFactoryProvider.getSessionFactory();
        } catch (Exception e) {
            throw new AssertionError("Connection failed");
        }
    }
}
