package model;

import model.accounts.Account;
import model.accounts.AccountList;
import model.exceptions.AccountNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountListTest {

    Account account;
    Account account2;
    AccountList accountList;


    @BeforeEach
    void beforeEach() {
        account = new Account("denis", "123");
        account2 = new Account("alex", "dog7");
        AccountList.newAccountList();
    }

    @Test
    void addTest() {
        assertTrue(AccountList.add("denis", account));
        assertTrue(AccountList.add("alex", account2));
        assertFalse(AccountList.add("denis", account));
        assertFalse(AccountList.add("denis", account2));
        assertFalse(AccountList.add("alex", account2));
    }

    @Test
    void getterTest() {
        try {
            assertTrue(AccountList.add("denis", account));
            assertTrue(AccountList.add("alex", account2));
            assertEquals(AccountList.getAccount("denis"), account);
            assertEquals(AccountList.getAccount("alex"), account2);
            assertTrue(AccountList.contains("alex"));
            assertTrue(AccountList.contains("denis"));
        } catch (AccountNotFoundException ignored) {
            fail();
        }

        try {
            AccountList.getAccount("john");
            fail();
        } catch (AccountNotFoundException ignored) {
           // expected;
        }
    }


}
