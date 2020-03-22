package persistence;

import model.accounts.Account;
import model.accounts.AccountList;
import model.exceptions.AccountNotFoundException;
import model.exceptions.LogNotFoundException;
import model.food.Food;
import model.food.FoodList;
import model.meal.Log;
import model.meal.Meal;
import model.meal.MealList;
import persistence.readers.*;
import persistence.writers.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class WriterTest {
    private static final String FOOD_TEST_FILE = "./data/foodWriterTest.txt";
    private static final String MEAL_TEST_FILE = "./data/mealWriterTest.txt";
    private static final String ACCOUNT_TEST_FILE = "./data/accountWriterTest.txt";
    private static final String FUNKY_FILE = "./data/zoinks/like/this/is/not/a/real/destination/scooby";
    private Writer foodTestWriter;
    private Writer mealTestWriter;
    private Writer accountTestWriter;
    Food bananaFood = new Food("banana", 100, 0.7, 100, 24, 0, 1);
    Food riceFood = new Food("rice", 3000, 5.0, 3000, 700, 11, 25);
    Food buckwheatFood = new Food("buckwheat", 1, 0.7, 100, 24, 0, 1);

    Meal bananaMeal = new Meal(bananaFood, 150, 1);
    Meal riceMeal = new Meal(riceFood, 1500, 2);

    FoodList foodlist;
    Log log;
    AccountList accountList;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        foodTestWriter = new Writer(new File(FOOD_TEST_FILE));
        foodlist = new FoodList();
        foodlist.add(bananaFood);
        foodlist.add(riceFood);
        foodlist.add(buckwheatFood);

        mealTestWriter = new Writer(new File(MEAL_TEST_FILE));
        log = new Log();
        log.logMeal(bananaMeal.getDay(), bananaMeal);
        log.logMeal(riceMeal.getDay(), riceMeal);

        accountTestWriter = new Writer(new File(ACCOUNT_TEST_FILE));
        accountList = new AccountList();
        AccountList.newAccountList();
        Account denisAccount = new Account("denis", "b7");
        AccountList.add("denis", denisAccount);
        Account jillAccount = new Account("jill", "pass123");
        AccountList.add("jill", jillAccount);
    }

    @Test
    void testWriteLog() {
        // save meal log to file
        mealTestWriter.write(log);
        mealTestWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            Log observedLog = LogReader.readLog(new File(MEAL_TEST_FILE));
            MealList day1 = observedLog.getLogForDay(1);
            MealList day2 = observedLog.getLogForDay(2);
            Meal observedBanana = day1.get(0);
            Meal observedRice = day2.get(0);

            assertEquals(day1.size(), 1);
            assertEquals(day2.size(), 1);

            // for banana:
            assertEquals("banana", observedBanana.getName());
            assertEquals(100, observedBanana.getFoodWeight());
            assertEquals(0.7, observedBanana.getFoodCost());
            assertEquals(100, observedBanana.getFoodCalories());
            assertEquals(24, observedBanana.getFoodCarbs());
            assertEquals(0.0, observedBanana.getFoodFats());
            assertEquals(1, observedBanana.getFoodProteins());

            assertEquals(100 * 1.5, observedBanana.getWeight());
            assertEquals(0.7 * 1.5, observedBanana.getCost());
            assertEquals(100 * 1.5, observedBanana.getCalories());
            assertEquals(24 * 1.5, observedBanana.getCarbs());
            assertEquals(0.0 * 1.5, observedBanana.getFats());
            assertEquals(1 * 1.5, observedBanana.getProteins());
            assertEquals(1, observedBanana.getDay());

            // now for rice:
            assertEquals("rice", observedRice.getName());
            assertEquals(3000, observedRice.getFoodWeight());
            assertEquals(5.0, observedRice.getFoodCost());
            assertEquals(3000, observedRice.getFoodCalories());
            assertEquals(700, observedRice.getFoodCarbs());
            assertEquals(11, observedRice.getFoodFats());
            assertEquals(25, observedRice.getFoodProteins());

            assertEquals(3000 * 0.5, observedRice.getWeight());
            assertEquals(5.0 * 0.5, observedRice.getCost());
            assertEquals(3000 * 0.5, observedRice.getCalories());
            assertEquals(700 * 0.5, observedRice.getCarbs());
            assertEquals(11 * 0.5, observedRice.getFats());
            assertEquals(25 * 0.5, observedRice.getProteins());
            assertEquals(2, observedRice.getDay());
        } catch (IOException | LogNotFoundException e) {
            fail("Exception should not have been thrown");
        }
        try {
            Log observedLog = LogReader.readLog(new File(FUNKY_FILE));
            fail("how did you find such a funky file?");
        } catch (IOException ignored) {
            // expected
        }
    }

    @Test
    void testWriteFoods() {
        // save food database to file
        foodTestWriter.write(foodlist);
        foodTestWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            FoodList observedFoods = FoodReader.readFoods(new File(FOOD_TEST_FILE));
            Food observedBanana = observedFoods.get(0);
            Food observedRice = observedFoods.get(1);
            Food observedBuckwheat = observedFoods.get(2);
            assertEquals("banana", observedBanana.getName());
            assertEquals(100, observedBanana.getWeight());
            assertEquals(0.7 , observedBanana.getCost());
            assertEquals(100, observedBanana.getCalories());
            assertEquals(24, observedBanana.getCarbs());
            assertEquals(0.0, observedBanana.getFats());
            assertEquals(1.0, observedBanana.getProteins());
            assertEquals("rice", observedRice.getName());
            assertEquals(3000, observedRice.getWeight());
            assertEquals(5.0 , observedRice.getCost());
            assertEquals(3000, observedRice.getCalories());
            assertEquals(700, observedRice.getCarbs());
            assertEquals(11, observedRice.getFats());
            assertEquals(25, observedRice.getProteins());
            assertEquals("buckwheat", observedBuckwheat.getName());
            assertEquals("carbs", observedBuckwheat.getPrimaryType());
            assertEquals(24, observedBuckwheat.getCarbs());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
        try {
            FoodList observedFoodList = FoodReader.readFoods(new File(FUNKY_FILE));
            fail("how did you find such a funky file?");
        } catch (IOException ignored) {
            // expected
        }
    }

    @Test
    void testWriteAccounts() {
        // save account list to file

        accountTestWriter.write(accountList);
        accountTestWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            AccountList.newAccountList();
            AccountList observedAccountList = AccountReader.readAccounts(new File(ACCOUNT_TEST_FILE));
            assertEquals(AccountList.getAccount("denis").getUsername(), "denis");
            assertEquals(AccountList.getAccount("denis").getPassword(), "b7");
            assertEquals(AccountList.getAccount("jill").getUsername(), "jill");
            assertEquals(AccountList.getAccount("jill").getPassword(), "pass123");
        } catch (IOException | AccountNotFoundException e) {
            fail("Exception should not have been thrown");
        }
        try {
            AccountList observedAccountList = AccountReader.readAccounts(new File(FUNKY_FILE));
            fail("how did you find such a funky file?");
        } catch (IOException ignored) {
            // expected
        }
    }


}