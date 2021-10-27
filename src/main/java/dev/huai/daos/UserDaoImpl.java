package dev.huai.daos;

import dev.huai.models.User;
import dev.huai.services.SessionService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
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
    public boolean updateUserPassword(String password) {
        return false;
    }

    @Override
    public User getUserByCredential(Integer user_id, String password) {
        return null;
    }
}
