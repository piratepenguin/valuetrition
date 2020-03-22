package model;

import model.food.Food;
import model.food.FoodList;
import model.meal.Meal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    Meal blueberryMeal = new Meal(new Food("blueberry", 100,2, 100, 24, 0, 1), 300, 1); // very bad value
    Meal riceMeal = new Meal(new Food("rice", 3000,6.0, 3000, 700, 11, 25), 500, 1); // extremely good value
    Meal beefMeal = new Meal(new Food("beef", 200, 3, 450, 10, 30, 70), 200, 2); // low value
    Meal milkMeal = new Meal(new Food("milk", 4000, 6, 2000, 180, 65, 180),500,3); // good value
    Meal nutsMeal = new Meal(new Food("nuts", 200, 2.5, 600, 5, 50, 30), 100, 3); // medium value
    Meal foodFromFileMeal = new Meal(0, new Food("foodFromFile", 200, 2.5, 600, 5, 50, 30), 100, 3); // medium value

    FoodList foodlist;

    @BeforeEach
    void beforeEach() {
        foodlist = new FoodList();
    }

    @Test
    void getterMethodTests() {

        assertEquals(blueberryMeal.getName(), "blueberry");
        assertEquals(blueberryMeal.getFoodWeight(), 100);
        assertEquals(blueberryMeal.getCalories(), 300.0);
        assertEquals(blueberryMeal.getCost(), 6.0);
        assertEquals(blueberryMeal.getCarbs(), 72.0);
        assertEquals(blueberryMeal.getFats(), 0.0);
        assertEquals(blueberryMeal.getProteins(), 3.0);
        assertEquals(blueberryMeal.getWeight(), 300);
        assertEquals(blueberryMeal.getDay(), 1);
        assertEquals(blueberryMeal.getPrimaryType(), "carbs");
        assertEquals(foodFromFileMeal.getPrimaryType(), "fats");
        assertEquals(riceMeal.getPrimaryType(), "carbs");
        assertEquals(beefMeal.getPrimaryType(), "fats & proteins");
        assertEquals("fats", nutsMeal.getPrimaryType());
        assertEquals("carbs & fats & proteins", milkMeal.getPrimaryType());
    }

    @Test
    void editMealTest() {
        assertEquals(riceMeal.getCost(), 1.0);
        riceMeal.editCost(0.75);
        assertEquals(0.75, riceMeal.getCost());
        riceMeal.editAmount(1500);
        assertEquals(0.75 * 3, riceMeal.getCost());
        assertEquals(3, milkMeal.getDay());
        milkMeal.editDay(1);
        assertEquals(1, milkMeal.getDay());
    }
}