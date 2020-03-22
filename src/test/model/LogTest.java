package model;

import model.exceptions.FoodNotFoundException;
import model.exceptions.LogNotFoundException;
import model.food.Food;
import model.meal.Log;
import model.meal.Meal;
import model.meal.MealList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class LogTest {

    Log log;
    Log log2;
    Meal blueberryMeal = new Meal(new Food("blueberry", 100,2, 100, 24, 0, 1), 300, 1); // very bad value
    Meal beefMeal = new Meal(new Food("beef", 200, 3, 450, 10, 30, 70), 200, 1); // low value
    Meal riceMeal = new Meal(new Food("rice", 3000,6.0, 3000, 700, 11, 25), 500, 2); // extremely good value


    @BeforeEach
    void runBefore() {
        log = new Log();
        log2 = new Log();
        log.logMeal(1, blueberryMeal);
        log.logMeal(1, beefMeal);
        log.logMeal(2, riceMeal);
    }

    @Test
    void getLogForDayTest() {
        try {
            MealList mealList1 = log.getLogForDay(1);
            Meal observedBlueberryMeal = mealList1.get(0);
            Meal observedBeefMeal = mealList1.get(1);
            MealList mealList2 = log.getLogForDay(2);
            Meal observedRiceMeal = mealList2.get(0);

            assertEquals(observedBlueberryMeal.getName(), "blueberry");
            assertEquals(observedBeefMeal.getName(), "beef");
            assertEquals(observedRiceMeal.getName(), "rice");

            assertEquals(log.getLastDay(), 2);
        } catch (LogNotFoundException ignored) {
            fail();
        }

        try {
            log.getLogForDay(3);
            fail();
        } catch (LogNotFoundException ignored) {
            // expected
        }
    }

    @Test
    void getLogForDayAsStringTest() {
        assertEquals("No meals logged for today!", log.getLogForDayAsString(3));
        assertEquals("No meals logged for today!", log.getLogForDayAsString(0));
        assertEquals("| Day 1| blueberry - 300Cal, beef - 450Cal", log.getLogForDayAsString(1));
    }


}
