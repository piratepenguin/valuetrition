package model;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Test.*;

class MealTest {

    Meal blueberry = new Meal(new Food("blueberry", 100,2, 100, 24, 0, 1), 300, 1); // very bad value
    Meal rice = new Meal(new Food("rice", 3000,6.0, 3000, 700, 11, 25), 500, 1); // extremely good value
    Meal beef = new Meal(new Food("beef", 200, 3, 450, 10, 30, 70), 200, 2); // low value
    Meal milk = new Meal(new Food("milk", 4000, 6, 2000, 180, 65, 180),500,3); // good value
    Meal nuts = new Meal(new Food("nuts", 200, 2.5, 600, 5, 50, 30), 100, 3); // medium value

    FoodList foodlist;

    @BeforeEach
    void beforeEach() {
        foodlist = new FoodList();
    }

    @Test
    void getterMethodTests() {

        assertEquals(blueberry.getName(), "blueberry");
        assertEquals(blueberry.getFoodWeight(), 100);
        assertEquals(blueberry.getCalories(), 300.0);
        assertEquals(blueberry.getCost(), 6.0);
        assertEquals(blueberry.getCarbs(), 72.0);
        assertEquals(blueberry.getFats(), 0.0);
        assertEquals(blueberry.getProteins(), 3.0);
        assertEquals(blueberry.getAmount(), 300);
        assertEquals(blueberry.getDay(), 1);
        assertEquals(blueberry.getPrimaryType(), "carbs");
        assertEquals(rice.getPrimaryType(), "carbs");
        assertEquals(beef.getPrimaryType(), "fats & proteins");
        assertEquals("fats", nuts.getPrimaryType());
        assertEquals("carbs & fats & proteins", milk.getPrimaryType());
    }

    @Test
    void editMealTest() {
        assertEquals(rice.getCost(), 1.0);
        rice.editCost(0.75);
        assertEquals(0.75, rice.getCost());
        rice.editAmount(1500);
        assertEquals(0.75 * 3, rice.getCost());
        assertEquals(3, milk.getDay());
        milk.editDay(1);
        assertEquals(1, milk.getDay());
    }
}