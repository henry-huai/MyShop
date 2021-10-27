package dev.huai.services;

import dev.huai.models.Product;
import dev.huai.models.Transaction;
import dev.huai.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.AnnotationConfiguration;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistry;

public class SessionService {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory()
    {
        try
        {
            if (sessionFactory == null)
            {
                Configuration configuration = new Configuration();
                configuration.configure("hibernate.cfg.xml");
//                .addAnnotatedClass(User.class)
//                .addAnnotatedClass(Product.class)
//                .addAnnotatedClass(Transaction.class)
//                .configure();
//                String url = System.getenv("Project2DB_URL");
//                String username = System.getenv("Project2DB_Username");
//                String password = System.getenv("Project2DB_Password");
//                configuration.setProperty("hibernate.connection.url", url);
//                configuration.setProperty("hibernate.connection.username", username);
//                configuration.setProperty("hibernate.connection.password", password);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }
            return sessionFactory;
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
