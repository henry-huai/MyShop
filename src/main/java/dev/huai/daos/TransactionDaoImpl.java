package dev.huai.daos;

import dev.huai.models.Transaction;
import dev.huai.models.User;
import dev.huai.utility.HibernateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Iterator;

@Repository
public class TransactionDaoImpl implements TransactionDao{
    private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
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
            User user = sessionObj.get(User.class, user_id);
            if(user == null)
                return null;
            // criteria for hibernate 4
//            Criteria criteria = sessionObj.createCriteria(Transaction.class)
//                            .add(Restrictions.eq("user", user))
//                    .add(Restrictions.eq("is_paid", true));

            CriteriaBuilder builder = sessionObj.getCriteriaBuilder();
            CriteriaQuery<Transaction> query = builder.createQuery(Transaction.class);
            Root<Transaction> root = query.from(Transaction.class);
            query.select(root);
            query.where(builder.equal(root.get("user"), user)).where(builder.equal(root.get("is_paid"), true));
            Query<Transaction> criteria=sessionObj.createQuery(query);
            for(Iterator iterator = criteria.list().iterator(); iterator.hasNext();){
                Transaction transaction = (Transaction) iterator.next();
                transactionsList.add(transaction);
                System.out.println(transaction.toString());
            }
            return transactionsList;
        } catch (HibernateException e) {
            e.printStackTrace();
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
            Transaction transaction = sessionObj.get(Transaction.class, transaction_id);
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
