package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
public class persistentAccountDAO implements AccountDAO {
    private SQLiteDatabase database;
    private DataBase DB1;
    private String[] allColumns = {DataBase.ACCOUNT_NUMBER, DataBase.BANK_NAME,
            DataBase.ACCOUNT_HOLDER_NAME,DataBase.BALANCE};
    public persistentAccountDAO(Context context) {
        DB1 = new DataBase(context);
        }
     public void open() throws SQLException {
        database = DB1.getWritableDatabase();
       }

             public void close() {
                 DB1.close();
        }
     @Override
    public List<String> getAccountNumbersList() {
        List<String> accountNumberList = new ArrayList<String>();
        String args[] = {String.valueOf(DataBase.ACCOUNT_NUMBER)};
        Cursor cursor = database.query(DataBase.TABLE_ACCOUNTS,args,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            accountNumberList.add(cursor.getString(0));
            cursor.moveToNext();
            }
        cursor.close();
        return accountNumberList;
        }
     @Override
     public List<Account> getAccountsList() {
        List<Account> accounts = new ArrayList<Account>();

                 Cursor cursor = database.query(DataBase.TABLE_ACCOUNTS,
                 allColumns, null, null, null, null, null);
        cursor.moveToFirst();
         while (!cursor.isAfterLast()) {
            Account account = cursorToAccount(cursor);
            accounts.add(account);
            cursor.moveToNext();
            }
         // make sure to close the cursor
                cursor.close();
         return accounts;
        }
    @Override
    public Account getAccount(String accountNo) throws InvalidAccountException {
         String args[] = {String.valueOf(accountNo)};
        Cursor cursor = database.rawQuery("SELECT * FROM projects WHERE project_id=?", args);
        cursor.moveToFirst();
        Account account = cursorToAccount(cursor);
        return account;
        }
    @Override
    public void addAccount(Account account) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBase.ACCOUNT_NUMBER,account.getAccountNo());
        contentValues.put(DataBase.ACCOUNT_HOLDER_NAME,account.getAccountHolderName());
        contentValues.put(DataBase.BANK_NAME,account.getBankName());
        contentValues.put(DataBase.BALANCE, account.getBalance());
        database.insert(DataBase.TABLE_ACCOUNTS, null, contentValues);
        }
    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {
        database.delete(DataBase.TABLE_ACCOUNTS, DataBase.ACCOUNT_NUMBER +
                 " = " + accountNo, null);
        }
    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {
        Account account = getAccount(accountNo);
         Double balance = account.getBalance();
        if(expenseType == ExpenseType.EXPENSE){
             balance = balance - amount;
            }else{
          balance = balance + amount;
            }
        account.setBalance(balance);
        removeAccount(accountNo);
        addAccount(account);
        }

        private Account cursorToAccount(Cursor cursor){
        String accountName = cursor.getString(0);
        String bankName = cursor.getString(1);
        String accountHolderName = cursor.getString(2);
        Double balance = cursor.getDouble(3);
        return new Account(accountName,bankName,accountHolderName,balance);
         }
    }