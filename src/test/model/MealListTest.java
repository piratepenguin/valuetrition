package model;

import model.exceptions.FoodNotFoundException;
import model.food.Food;
import model.meal.Meal;
import model.meal.MealList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MealListTest {

    Meal banana = new Meal(new Food("banana", 100,0.7, 100, 24, 0, 1), 200, 1);
    Meal rice = new Meal(new Food("rice", 3000,5.0, 3000, 700, 11, 25), 4500, 1);
    Meal beef = new Meal(new Food("beef", 100,5, 500, 0, 50, 70),250,2);
    Meal milk = new Meal(new Food("milk", 4000, 1,1500, 120, 70, 100),500,3);
    Meal beef2 = new Meal(new Food("beef", 100,5, 500, 0, 50, 70),250,2);
    MealList meallist;

    @BeforeEach
    public void BeforeEach() {
        meallist = new MealList();
        meallist.add(banana);
        meallist.add(rice);
        meallist.add(beef);
        meallist.add(milk);
    }

    @Test
    public void addTest() {
        meallist.clear();
        assertEquals(0, meallist.size());
        meallist.add(banana);
        assertEquals(1, meallist.size());
        meallist.add(rice);
        meallist.add(beef);
        assertEquals(3, meallist.size());
        meallist.add(milk);
        assertEquals(4, meallist.size());
    }

    @Test                                         // tests removeMeal and GetMeal
    public void removeMealTest() {
        try {
            assertEquals(4, meallist.size());
            meallist.removeMeal("banana",1);
            assertEquals(3, meallist.size());
            try {
                meallist.removeMeal("banana",2);
                fail();
            } catch (FoodNotFoundException ignored) {}
        } catch (FoodNotFoundException e) { fail(); }

        try {
            beef2 = meallist.getMeal("beef",1);
            fail();
        } catch (FoodNotFoundException ignored) { }

        try {
            meallist.getMeal("beef",2);
        } catch (FoodNotFoundException ignored) { fail(); }

        try {
            meallist.removeMeal("beef",2);
        } catch (FoodNotFoundException ignored) {
            fail();
        }
        try {
            meallist.getMeal("beef",2 );
            fail();
        } catch (FoodNotFoundException ignored) { }
    }
    @Test
    public void getLastTest() {
        assertEquals(milk, meallist.getLast());
        try {
            meallist.removeMeal("milk",3);
        } catch (FoodNotFoundException ignored) { fail(); }
        assertEquals(beef, meallist.getLast());
        meallist.clear();
        meallist.add(rice);
        assertEquals(rice,meallist.getLast());
    }

    @Test
    public void toStringTest() {
        assertEquals("| Day 1| banana - 200.0Cal, rice - 4500.0Cal, \n" +
                              "| Day 2| beef - 1250.0Cal, \n" +
                              "| Day 3| milk - 187.5Cal", meallist.toString());
    }


}
