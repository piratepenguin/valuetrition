package model.accounts;

import javafx.util.Pair;


public class AccountID {

    String username;
    String password;
    Pair<String, String> accountID;

    public AccountID(String username, String password) {
        this.username = username;
        this.password = password;
        accountID = new Pair<>(username, password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Pair<String, String> getAccountID() {
        return accountID;
    }

}
