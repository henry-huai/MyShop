package dev.huai.daos;

import dev.huai.models.Transaction;
import dev.huai.models.User;
import dev.huai.services.SessionService;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

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
    public ArrayList<Transaction> getPaidTransactionsByUser(Integer user_id) {
        try{
            ArrayList<Transaction> transactionsList = new ArrayList<>();
            sessionObj = sessionFactory.openSession();
            sessionObj.beginTransaction();
            User user = (User) sessionObj.get(User.class, user_id);
            if(user == null)
                return null;
            Criteria criteria = sessionObj.createCriteria(Transaction.class)
                            .add(Restrictions.eq("user", user))
                    .add(Restrictions.eq("is_paid", true));
            for(Iterator iterator = criteria.list().iterator(); iterator.hasNext();){
                Transaction transaction = (Transaction) iterator.next();
                transactionsList.add(transaction);
                System.out.println(transaction.toString());
            }
            sessionObj.getTransaction().commit();
            return transactionsList;
        } catch (HibernateException e) {
            e.printStackTrace();
            sessionObj.getTransaction().rollback();
        }finally {
            if(sessionObj!=null)
                sessionObj.close();
        }
        return null;
    }

    @Override
    public boolean updateTransactionToPaid(Integer transaction_id) {
        try{
            sessionObj = sessionFactory.openSession();
            sessionObj.beginTransaction();
            Transaction transaction = (Transaction) sessionObj.get(Transaction.class, transaction_id);
            if(transaction == null)
                return false;
            transaction.setIs_paid(true);
            sessionObj.update(transaction);
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
