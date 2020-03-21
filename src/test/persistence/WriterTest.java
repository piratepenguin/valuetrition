package persistence;

import model.food.Food;
import model.food.FoodList;
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
//    private static final String FOOD_TEST_FILE = "./data/foodWriterTest.txt";
//    private static final String MEAL_TEST_FILE = "./data/mealWriterTest.txt";
//    private static final String FUNKY_FILE = "./data/zoinks/like/this/is/not/a/real/destination/scooby";
//    private Writer foodTestWriter;
//    private Writer mealTestWriter;
//    Food bananaFood = new Food("banana", 100, 0.7, 100, 24, 0, 1);
//    Food riceFood = new Food("rice", 3000, 5.0, 3000, 700, 11, 25);
//    Food buckwheatFood = new Food("buckwheat", 1, 0.7, 100, 24, 0, 1);
//
//    Meal bananaMeal = new Meal(bananaFood, 150, 1);
//    Meal riceMeal = new Meal(riceFood, 1500, 2);
//    Meal appleMeal = new Meal(0, new Food("apple", 100, 0.75, 100, 24, 0, 2), 200, 2);
//    Meal milkMeal = new Meal(0, new Food("milk", 4000, 1, 1500, 100, 40, 100), 2000, 3);
//    FoodList foodlist;
//    MealList log;
//
//    @BeforeEach
//    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
//        foodTestWriter = new Writer(new File(FOOD_TEST_FILE));
//        foodlist = new FoodList();
//        foodlist.add(bananaFood);
//        foodlist.add(riceFood);
//        foodlist.add(buckwheatFood);
//        mealTestWriter = new Writer(new File(MEAL_TEST_FILE));
//        log = new MealList();
//        log.add(bananaMeal);
//        log.add(riceMeal);
//        log.add(appleMeal);
//        log.add(milkMeal);
//    }
//
//    @Test
//    void testWriteLog() {
//        // save meal log to file
//        mealTestWriter.write(log);
//        mealTestWriter.close();
//
//        // now read them back in and verify that the accounts have the expected values
//        try {
//            MealList observedLog = LogReader.readMeals(new File(MEAL_TEST_FILE));
//            Meal observedBanana = observedLog.get(0);
//            Meal observedRice = observedLog.get(1);
//            Meal observedApple = observedLog.get(2);
//            Meal observedMilk = observedLog.get(3);
//            assertEquals("banana", observedBanana.getName());
//            assertEquals(100, observedBanana.getFoodWeight());
//            assertEquals(0.7 * 1.5, observedBanana.getCost());
//            assertEquals(100 * 1.5, observedBanana.getCalories());
//            assertEquals(24 * 1.5, observedBanana.getCarbs());
//            assertEquals(0.0, observedBanana.getFats());
//            assertEquals(1.5, observedBanana.getProteins());
//            assertEquals(150, observedBanana.getAmount());
//            assertEquals(1, observedBanana.getDay());
//            assertEquals("rice", observedRice.getName());
//            assertEquals("apple", observedApple.getName());
//            assertEquals("milk", observedMilk.getName());
//            assertEquals("carbs & fats & proteins", observedMilk.getPrimaryType());
//            assertEquals(1500, observedMilk.getCalories());
//        } catch (IOException e) {
//            fail("IOException should not have been thrown");
//        }
//        try {
//            MealList observedLog = LogReader.readMeals(new File(FUNKY_FILE));
//            fail("how did you find such a funky file?");
//        } catch (IOException ignored) {
//        }
//    }
//
//    @Test
//    void testWriteFoods() {
//        // save food database to file
//        foodTestWriter.write(foodlist);
//        foodTestWriter.close();
//
//        // now read them back in and verify that the accounts have the expected values
//        try {
//            FoodList observedFoods = FoodReader.readFoods(new File(FOOD_TEST_FILE));
//            Food observedBanana = observedFoods.get(0);
//            Food observedRice = observedFoods.get(1);
//            Food observedBuckwheat = observedFoods.get(2);
//            assertEquals("banana", observedBanana.getName());
//            assertEquals(100, observedBanana.getWeight());
//            assertEquals(0.7 , observedBanana.getCost());
//            assertEquals(100, observedBanana.getCalories());
//            assertEquals(24, observedBanana.getCarbs());
//            assertEquals(0.0, observedBanana.getFats());
//            assertEquals(1.0, observedBanana.getProteins());
//            assertEquals("rice", observedRice.getName());
//            assertEquals(3000, observedRice.getWeight());
//            assertEquals(5.0 , observedRice.getCost());
//            assertEquals(3000, observedRice.getCalories());
//            assertEquals(700, observedRice.getCarbs());
//            assertEquals(11, observedRice.getFats());
//            assertEquals(25, observedRice.getProteins());
//            assertEquals("buckwheat", observedBuckwheat.getName());
//            assertEquals("carbs", observedBuckwheat.getPrimaryType());
//            assertEquals(24, observedBuckwheat.getCarbs());
//        } catch (IOException e) {
//            fail("IOException should not have been thrown");
//        }
//        try {
//            MealList observedLog = LogReader.readMeals(new File(FUNKY_FILE));
//            fail("how did you find such a funky file?");
//        } catch (IOException ignored) {
//        }
//    }
}