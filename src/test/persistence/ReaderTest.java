package persistence;

import model.food.Food;
import model.food.FoodList;
import model.meal.Meal;
import model.meal.MealList;
import persistence.readers.LogReader;
import persistence.readers.FoodReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//        Banana,118.0,0.3,105.0,27.0,0.0,1.0
//        Buckwheat,170.0,0.5,175.0,39.0,1.0,7.0
//        Chicken Breast,120.0,2.0,208.0,0.0,5.0,37.0

class ReaderTest {
//
//    @Test
//    void testParseFoodsFile() {
//        try {
//            FoodList foods = FoodReader.readFoods(new File("./data/foodsTest.txt"));
//            Food food1 = foods.get(0);
//            assertEquals(food1.getName(), "Banana");
//            assertEquals(food1.getWeight(), 118.0);
//            assertEquals(food1.getCost(), 0.3);
//            assertEquals(food1.getCalories(), 105.0);
//            assertEquals(food1.getCarbs(), 27.0);
//            assertEquals(food1.getFats(), 0.0);
//            assertEquals(food1.getProteins(), 1.0);
//            Food food2 = foods.get(1);
//            assertEquals(food2.getName(), "Buckwheat");
//            assertEquals(food2.getWeight(), 170.0);
//            assertEquals(food2.getCost(), 0.5);
//            assertEquals(food2.getCalories(), 175.0);
//            assertEquals(food2.getCarbs(), 39.0);
//            assertEquals(food2.getFats(), 1.0);
//            assertEquals(food2.getProteins(), 7.0);
//            Food food3 = foods.get(2);
//            assertEquals(food3.getName(), "Chicken Breast");
//            assertEquals(food3.getWeight(), 120.0);
//            assertEquals(food3.getCost(), 2.0);
//            assertEquals(food3.getCalories(), 208.0);
//            assertEquals(food3.getCarbs(), 0.0);
//            assertEquals(food3.getFats(), 5.0);
//            assertEquals(food3.getProteins(), 37.0);
//        } catch (IOException e) {
//            fail("IOException should not have been thrown");
//        }
//    }
//
////    Chicken Breast,120.0,2.0,208.0,0.0,5.0,37.0,140.0,2
////    Apple,130.0,0.5,100.0,24.0,0.0,1.0,50.0,2
//
//    @Test
//    void testParseMealsFile() {
//        try {
//            MealList log = LogReader.readMeals(new File("./data/logTest.txt"));
//            Meal meal1 = log.get(0);
//            assertEquals(meal1.getName(), "Chicken Breast");
//            assertEquals(meal1.getFoodWeight(), 120.0);
//            assertEquals(meal1.getCost(), 2.0);
//            assertEquals(meal1.getCalories(), 208.0);
//            assertEquals(meal1.getCarbs(), 0.0);
//            assertEquals(meal1.getFats(), 5.0);
//            assertEquals(meal1.getProteins(), 37.0);
//            assertEquals(meal1.getAmount(), 140.0);
//            assertEquals(meal1.getDay(), 2);
//            Meal meal2 = log.get(1);
//            assertEquals(meal2.getName(), "Apple");
//            assertEquals(meal2.getFoodWeight(), 130.0);
//            assertEquals(meal2.getCost(), 0.5);
//            assertEquals(meal2.getCalories(), 100.0);
//            assertEquals(meal2.getCarbs(), 24.0);
//            assertEquals(meal2.getFats(), 0.0);
//            assertEquals(meal2.getProteins(), 1.0);
//            assertEquals(meal2.getAmount(), 50.0);
//            assertEquals(meal2.getDay(), 2);
//        } catch (IOException e) {
//            fail("IOException should not have been thrown");
//        }
//    }
//
//    @Test
//    void testIOException() {
//        try {
//            FoodReader.readFoods(new File("./path/does/not/exist/testy.txt"));
//            fail();
//        } catch (IOException e) {
//            // expected
//        }
//        try {
//            LogReader.readMeals(new File("./path/does/not/exist/testy.txt"));
//            fail();
//        } catch (IOException e) {
//            // expected
//        }
//    }
}