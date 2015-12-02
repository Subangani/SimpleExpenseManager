package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.database.sqlite.SQLiteDatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.Date;
import java.util.List;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

/**
 * Created by SUBANGANI on 12/4/2015.
 */
public class persistentTransactionDAO implements TransactionDAO {
    private SQLiteDatabase database;
    private DataBase DB1;
    private String[] allColumns = {DataBase.DATE, DataBase.ACCOUNT_NUMBER,
            DataBase.AMOUNT,DataBase.EXPENSE_TYPE};

    private Transaction cursorToTransaction(Cursor cursor){
        String date = cursor.getString(0);
        String bankName = cursor.getString(1);
        String accountHolderName = cursor.getString(2);
        Double balance = cursor.getDouble(3);

        return new Account(accountName,bankName,accountHolderName,balance);
    }
    @Override
    public List<Transaction> getPaginatedTransactionLogs(int limit) {
        return null;
    }
    @Override
    public void logTransaction(Date date, String accountNo, ExpenseType expenseType, double amount) {
        }
    @Override
    public List<Transaction> getAllTransactionLogs() {
        return null;
        }


}
