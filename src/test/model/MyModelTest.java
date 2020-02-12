package model;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Test.*;

class MyModelTest {

    Food banana = new Food("banana", 0.7, 100, 24, 0, 1);
    Food rice = new Food("rice", 5, 3000, 700, 11, 25;
    Food buckwheat = new Food("buckwheat", 0.7, 100, 24, 0, 1);
    Food apple = new Food("apple", 0.7, 100, 24, 0, 1);
    Food milk = new Food("milk", 5, 1500, 250, 40, 15);
    Food emptyFood = new Food("unnamed food", 0, 0, 0, 0, 0);

    FoodList foodlist;

    @BeforeEach
    void beforeEach() {
        foodlist = new FoodList();
    }

    @Test
    void getterMethodTests() {
        assertEquals(apple.getName(), "apple");
        assertEquals(apple.getCalories(), "100");
        assertEquals(apple.getCost(), 0.7);
        assertEquals(apple.getCarbs(), 24);
        assertEquals(apple.getFats(), 0);
        assertEquals(apple.getProteins(), 1);
        assertEquals(apple.getPrimaryType(), "carbs");
    }


}