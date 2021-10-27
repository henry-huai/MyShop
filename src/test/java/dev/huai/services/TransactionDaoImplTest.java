package dev.huai.services;
import dev.huai.daos.TransactionDao;
import dev.huai.daos.TransactionDaoImpl;
import dev.huai.models.Product;
import dev.huai.models.Transaction;
import dev.huai.models.User;
import org.junit.Test;

import java.math.BigDecimal;
import static junit.framework.TestCase.*;
public class TransactionDaoImplTest {

    private TransactionDao transactionDao = new TransactionDaoImpl();

    @Test
    public void testAddTransaction(){
        User user = new User();
        user.setLastName("Huai");
        user.setFirstName("Renhan");
        user.setPassword("1234");
        user.setEmail("renhan@gmail.com");
        user.setUserId(1);
        BigDecimal price = new BigDecimal("1.99");
        Product product = new Product();
        product.setProductPrice(price);
        product.setProductName("Apple");
        product.setProductId(1);
        Transaction transaction = new Transaction();
        transaction.setProduct(product);
        transaction.setQuantity(2);
        transaction.setUser(user);
        assertTrue(transactionDao.addTransaction(transaction));

    }
    @Test
    public void testGetPaidTransactionsByUser(){

    }

    @Test
    public void testUpdateTransactionToPaid(){

    }

}
