package model.accounts;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AccountList {

    private static HashMap<String, Account> accountList;

    public static void newAccountList() {
        accountList = new HashMap<String, Account>();
    }

    public static boolean add(String username, Account account) {
        if (!accountList.containsKey(username)) {
            accountList.put(username, account);
            return true;
        } else {
            return false;
        }
    }

    public static Account getAccount(String username) {
        return accountList.get(username);
    }

    public static boolean contains(String username) {
        return accountList.containsKey(username);
    }

}

