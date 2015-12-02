package lk.ac.mrt.cse.dbs.simpleexpensemanager.control.exception;

/**
 * Created by SUBANGANI on 12/5/2015.
 */

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.ExpenseManager;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.InMemoryAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.InMemoryTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.persistentTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;

public class persistentDemoExpenseManager extends ExpenseManager {

    public persistentDemoExpenseManager() {
        setup();
    }

    @Override
    public void setup() {
        /*** Begin generating dummy data for In-Memory implementation ***/

        TransactionDAO persistentTransactionDAO = new persistentTransactionDAO();
        setTransactionsDAO(persistentTransactionDAO);

        AccountDAO persistentAccountDAO = new InMemoryAccountDAO();
        setAccountsDAO(persistentAccountDAO);

        // dummy data
        Account Acct1 = new Account("12345A", "Yoda Bank", "Anakin Skywalker", 10000.0);
        Account Acct2 = new Account("78945Z", "Clone BC", "Obi-Wan Kenobi", 80000.0);
        getAccountsDAO().addAccount(Acct1);
        getAccountsDAO().addAccount(Acct2);

        /*** End ***/
    }
}

