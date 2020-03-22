package model;

import model.accounts.Account;
import model.food.FoodList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    Account account;

    @BeforeEach
    void beforeEach() {
        account = new Account("denis", "123");
    }

    @Test
    void createFilesTest() {
        account.createFiles();
        File directoryFile = account.getDirectoryFile();
        assertEquals(directoryFile.getPath(), ".\\data\\accounts\\denis");
        File foodsFile = account.getFoodsFile();
        assertEquals(foodsFile.getPath(), ".\\data\\accounts\\denis\\foods.txt");
        File mealsFile = account.getMealsFile();
        assertEquals(mealsFile.getPath(), ".\\data\\accounts\\denis\\meals.txt");
    }

    @Test
    void getterTest() {
        assertEquals(account.getUsername(), "denis");
        assertEquals(account.getPassword(), "123");
        assertEquals(account.getFoodsFileDir(), "./data/accounts/denis/foods.txt");
        assertEquals(account.getMealsFileDir(), "./data/accounts/denis/meals.txt");
    }

}
