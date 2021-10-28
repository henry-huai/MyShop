package dev.huai.services;

import dev.huai.models.Product;
import dev.huai.models.Transaction;
import dev.huai.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class SessionService {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory()
    {

        if (sessionFactory == null)
        {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
//            Properties settings = new Properties();
//            settings.put(Environment.URL, System.getenv("Project2DB_URL"));
//            settings.put(Environment.USER, System.getenv("Project2DB_Username"));
//            settings.put(Environment.PASS, System.getenv("Project2DB_Password"));
//            settings.put(Environment.DRIVER, "org.postgresql.Driver");
//            settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
//
//            settings.put(Environment.HBM2DDL_AUTO, "update");
//            settings.put(Environment.SHOW_SQL, "true");
//            configuration.setProperties(settings);

//            String url = System.getenv("Project2DB_URL");
//            String username = System.getenv("Project2DB_Username");
//            String password = System.getenv("Project2DB_Password");
//            configuration.setProperty("hibernate.connection.url", url);
//            configuration.setProperty("hibernate.connection.username", username);
//            configuration.setProperty("hibernate.connection.password", password);
//            configuration.addAnnotatedClass(User.class);
//            configuration.addAnnotatedClass(Product.class);
//            configuration.addAnnotatedClass(Transaction.class);


            //ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
