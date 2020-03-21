package model;

import model.exceptions.FoodNotFoundException;
import model.food.Food;
import model.food.FoodList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodListTest {

    Food banana = new Food("banana", 1,0.7, 100, 24, 0, 1);
    Food rice = new Food("rice", 1,5.0, 3000, 700, 11, 25);
    Food buckwheat = new Food("buckwheat", 1,0.7, 100, 24, 0, 1);
    Food apple = new Food("apple",1, 0.7, 100, 24, 0, 1);
    Food milk = new Food("milk", 5, 1,1500, 250, 40, 15);
    Food emptyFood = new Food("unnamed food", 1,0, 0, 0, 0, 0);
    FoodList foodlist;

    @BeforeEach
    public void BeforeEach() {
        foodlist = new FoodList();
        foodlist.add(banana);
        foodlist.add(rice);
        foodlist.add(buckwheat);
        foodlist.add(apple);
        foodlist.add(milk);
    }

    @Test
    public void addTest() {
        foodlist.clear();
        assertEquals(0, foodlist.size());
        foodlist.add(banana);
        assertEquals(1, foodlist.size());
        foodlist.add(rice);
        foodlist.add(buckwheat);
        assertEquals(3, foodlist.size());
        foodlist.add(apple);
        foodlist.add(milk);
    }

    @Test
    public void removeFoodTest() {
        try {
            foodlist.removeFood("banana");
            assertEquals(4, foodlist.size());
            try {
                foodlist.removeFood("banana");
                fail();
            } catch (FoodNotFoundException ignored) {}
        } catch (FoodNotFoundException e) {
            fail();
        }
        try {
            foodlist.getFood("banana");
            fail();
        } catch (FoodNotFoundException ignored) { }
        try {
            assertEquals(apple, foodlist.getFood("apple"));
        } catch (FoodNotFoundException ignored) {
            fail();
        }
    }

    @Test
    public void toStringTest() {
        assertEquals("banana, rice, buckwheat, apple, milk", foodlist.toString());
    }


}
