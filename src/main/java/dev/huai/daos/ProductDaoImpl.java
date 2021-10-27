package dev.huai.daos;

import dev.huai.models.Product;
import dev.huai.services.SessionService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ProductDaoImpl implements ProductDao{

    private static SessionFactory sessionFactory = SessionService.getSessionFactory();
    private static Session sessionObj;

    @Override
    public boolean addProduct(Product product) {
        try{
            sessionObj = sessionFactory.openSession();
            sessionObj.beginTransaction();
            sessionObj.save(product);
            sessionObj.getTransaction().commit();
            return true;
        }catch(Exception sqlException){
            if(null != sessionObj.getTransaction()) {
                System.out.println("\nTransaction Is Being Rolled Back");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        return false;
    }

    @Override
    public boolean updateProduct() {
        return false;
    }

    @Override
    public boolean deleteProduct() {
        return false;
    }
}
