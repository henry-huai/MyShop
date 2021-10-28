package dev.huai.daos;

import dev.huai.models.Product;
import dev.huai.services.SessionService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;

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
        } catch (HibernateException e) {
            e.printStackTrace();
            sessionObj.getTransaction().rollback();
        }finally {
            if(sessionObj!=null)
                sessionObj.close();
        }
        return false;
    }

    @Override
    public boolean updateProductPrice(Integer product_id, BigDecimal newPrice) {
        try{
            sessionObj = sessionFactory.openSession();
            sessionObj.beginTransaction();
            Product product = (Product)sessionObj.get(Product.class, product_id);
            product.setProductPrice(newPrice);
            sessionObj.update(product);
            sessionObj.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            sessionObj.getTransaction().rollback();
        }finally {
            if(sessionObj!=null)
                sessionObj.close();
        }
        return false;
    }

    @Override
    public boolean deleteProductById(Integer product_id) {
        try{
            sessionObj = sessionFactory.openSession();
            sessionObj.beginTransaction();
            Product product = (Product) sessionObj.get(Product.class, product_id);
            if(product == null)
                return false;
            sessionObj.delete(product);
            sessionObj.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            sessionObj.getTransaction().rollback();
        }finally {
            if(sessionObj!=null)
                sessionObj.close();
        }
        return false;
    }
}
