package dev.huai.daos;

import dev.huai.models.Transaction;
import dev.huai.models.User;
import dev.huai.services.SessionService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

public class TransactionDaoImpl implements TransactionDao{
    private static SessionFactory sessionFactory = SessionService.getSessionFactory();
    private static Session sessionObj;

    @Override
    public boolean addTransaction(Transaction transaction) {
        try{
            sessionObj = sessionFactory.openSession();
            sessionObj.beginTransaction();
            sessionObj.save(transaction);
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
    public ArrayList<Transaction> getPaidTransactionsByUser(User user) {
        return null;
    }

    @Override
    public boolean updateTransactionToPaid(Transaction transaction) {
        return false;
    }
}
