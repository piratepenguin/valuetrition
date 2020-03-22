package model.accounts;

import model.exceptions.AccountNotFoundException;
import model.food.Food;
import persistence.Saveable;
import persistence.readers.AccountReader;
import persistence.readers.FoodReader;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


// represents a database of user accounts
public class AccountList implements Saveable {

    private static HashMap<String, Account> accountList;

    public static void newAccountList() {
        accountList = new HashMap<>();
    }

    public static boolean add(String username, Account account) {
        if (!accountList.containsKey(username)) {
            accountList.put(username, account);
            return true;
        } else {
            return false;
        }
    }

    public static Account getAccount(String username) throws AccountNotFoundException {
        if (accountList.containsKey(username)) {
            return accountList.get(username);
        } else {
            throw new AccountNotFoundException();
        }
    }

    public static boolean contains(String username) {
        return accountList.containsKey(username);
    }

    @Override
    public void save(PrintWriter printWriter) {
        for (Map.Entry<String, Account> entry : accountList.entrySet()) {
            printWriter.print(entry.getKey());
            printWriter.print(AccountReader.DELIMITER);
            printWriter.println(entry.getValue().getPassword());
        }
    }
}

