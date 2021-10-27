package dev.huai.daos;

import dev.huai.models.Transaction;
import dev.huai.models.User;

import java.util.ArrayList;

public interface TransactionDao {

    public boolean addTransaction(Transaction transaction);

    public ArrayList<Transaction> getPaidTransactionsByUser(User user);

    public boolean updateTransactionToPaid(Transaction transaction);


}
