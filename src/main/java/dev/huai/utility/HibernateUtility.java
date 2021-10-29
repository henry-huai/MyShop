package dev.huai.utility;

import dev.huai.models.Product;
import dev.huai.models.Transaction;
import dev.huai.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.springframework.stereotype.Component;

import java.util.Properties;

public class HibernateUtility {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory()
    {

        if (sessionFactory == null)
        {
            //Configuration configuration = new Configuration();

            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "org.postgresql.Driver");
            String url = System.getenv("Project2DB_URL");
            String username = System.getenv("Project2DB_Username");
            String password = System.getenv("Project2DB_Password");
//Project2DB_URL=jdbc:postgresql://localhost:5432/postgres;Project2DB_Username=postgres;Project2DB_Password=7499
            settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
            settings.put(Environment.USER, "postgres");
            settings.put(Environment.PASS, "7499");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.HBM2DDL_AUTO, "update");

            sessionFactory =  new Configuration()
                    .setProperties(settings)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(Transaction.class)
                    .buildSessionFactory();
        }
        return  sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
