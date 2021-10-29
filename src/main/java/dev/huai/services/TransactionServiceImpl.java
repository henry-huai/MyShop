package dev.huai.services;

import dev.huai.daos.TransactionDao;
import dev.huai.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionDao transactionDao;

    @Override
    public boolean addTransaction(Transaction transaction) {
        return transactionDao.addTransaction(transaction);
    }

    @Override
    public ArrayList<Transaction> getPaidTransactionsByUser(Integer user_id) {
        return transactionDao.getPaidTransactionsByUser(user_id);
    }

    @Override
    public boolean updateTransactionToPaid(Integer transaction_id) {
        return transactionDao.updateTransactionToPaid(transaction_id);
    }
}
