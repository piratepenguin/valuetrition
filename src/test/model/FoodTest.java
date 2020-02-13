package model;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Test.*;

class FoodTest {

    Food banana = new Food("banana", 0.7, 100, 24, 0, 1);
    Food rice = new Food("rice", 5.0, 3000, 700, 11, 25);
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
        assertEquals(apple.getCalories(), 100.0);
        assertEquals(apple.getCost(), 0.7);
        assertEquals(apple.getCarbs(), 24.0);
        assertEquals(apple.getFats(), 0.0);
        assertEquals(apple.getProteins(), 1.0);
        assertEquals(apple.getPrimaryType(), "carbs");
    }
    @Test
    void foodComplexMethodsTests() {
        apple.addPurchaseInfo(1.3);
        assertEquals(1.0, apple.getCost());
        apple.addPurchaseInfo(2);
        assertEquals(4.0/3.0, apple.getCost());
        apple.editName("ambrosia");
        assertEquals("ambrosia", apple.getName());
        assertEquals("food: ambrosia\ncost: 1.3333333333333333\ncalories: 100.0\ncarbs: 24.0\nfats: 0.0\nproteins: 1.0"
        + "\nprimary type: carbs\nfood value: very low\ncalories per dollar: 75.0", apple.viewInfo());

    }



}