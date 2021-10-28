package dev.huai.daos;

import dev.huai.models.User;
import dev.huai.services.SessionService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;

public class UserDaoImpl implements UserDao{
    private static SessionFactory sessionFactory = SessionService.getSessionFactory();
    private static Session sessionObj;

    @Override
    public boolean addUser(User user) {
        try{
            sessionObj = sessionFactory.openSession();
            sessionObj.beginTransaction();
            sessionObj.save(user);
            sessionObj.getTransaction().commit();
            return true;
        }catch(Exception sqlException){
            if(null != sessionObj.getTransaction()) {
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
    public boolean updateUserPassword(Integer user_id, String newPassword) {
        try{
            sessionObj = sessionFactory.openSession();
            sessionObj.beginTransaction();
            User user = (User)sessionObj.get(User.class, user_id);
            user.setPassword(newPassword);
            sessionObj.update(user);
            sessionObj.getTransaction().commit();
            return true;
        }catch(Exception sqlException){
            if(null != sessionObj.getTransaction()) {
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
    public boolean updateBalanceDeposit(Integer user_id, BigDecimal deposit) {
        try{
            sessionObj = sessionFactory.openSession();
            sessionObj.beginTransaction();
            User user = (User)sessionObj.get(User.class, user_id);
            user.setBalance(user.getBalance().add(deposit));
            sessionObj.update(user);
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
    public boolean updateBalanceCashOut(Integer user_id, BigDecimal cash_out_amount) {
        try{
            sessionObj = sessionFactory.openSession();
            sessionObj.beginTransaction();
            User user = (User)sessionObj.get(User.class, user_id);
            // Check if there is enough fund for purchasing
            if(user.getBalance().subtract(cash_out_amount).compareTo(new BigDecimal(0)) < 0)
                return false;
            user.setBalance(user.getBalance().subtract(cash_out_amount));
            sessionObj.update(user);
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
    public User getUserByCredential(Integer user_id, String password) {
        try{
            sessionObj = sessionFactory.openSession();
            User user = (User)sessionObj.get(User.class, user_id);
            System.out.println(user.toString());
            if(user.getPassword().equals(password))
                return user;
        } catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            if(sessionObj!=null)
                sessionObj.close();
        }
        return null;
    }
}
