package dev.huai.services;

import dev.huai.models.Transaction;

import java.util.ArrayList;

public interface TransactionService {
    public boolean addTransaction(Transaction transaction);

    public ArrayList<Transaction> getPaidTransactionsByUser(Integer user_id);

    public boolean updateTransactionToPaid(Integer transaction_id);
}
