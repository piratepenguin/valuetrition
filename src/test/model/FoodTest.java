package model;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Test.*;

class FoodTest {

    Food banana = new Food("banana", 1,2, 100, 24, 0, 1); // very bad value
    Food rice = new Food("rice", 1,5.0, 3000, 700, 11, 25); // extremely good value
    Food beef = new Food("beef", 1, 2, 450, 0, 15, 70); // medium value
    Food apple = new Food("apple", 1, 0.75, 100, 24, 0, 1); // bad value
    Food milk = new Food("milk", 1, 6, 2000, 180, 65, 180); // good value
    Food nuts = new Food("nuts", 1, 2.5, 600, 5, 50, 30); // medium value
    Food cf = new Food("cf", 1, 2.5, 1000, 101, 50, 10);
    Food cp = new Food("cp", 1, 2.5, 600, 100, 15, 100);
    Food fp = new Food("fp", 1, 2.5, 600, 20, 50, 100);

    FoodList foodlist;

    @BeforeEach
    void beforeEach() {
        foodlist = new FoodList();
    }

    @Test
    void getterMethodTests() {

        Food emptyFood = new Food();
        assertEquals("unnamed food", emptyFood.getName());
        assertEquals(0.0, emptyFood.getCost());
        assertEquals(0.0, emptyFood.getTotalCost());
        assertEquals(apple.getName(), "apple");
        assertEquals(apple.getWeight(), 1.0);
        assertEquals(apple.getCalories(), 100.0);
        assertEquals(apple.getCost(), 0.75);
        assertEquals(apple.getCarbs(), 24.0);
        assertEquals(apple.getFats(), 0.0);
        assertEquals(apple.getProteins(), 1.0);
        assertEquals(apple.getPrimaryType(), "carbs");
        assertEquals("proteins", beef.getPrimaryType());
        assertEquals("fats", nuts.getPrimaryType());
        assertEquals("carbs & fats", cf.getPrimaryType());
        assertEquals("carbs & proteins", cp.getPrimaryType());
        assertEquals("fats & proteins", fp.getPrimaryType());
        assertEquals("carbs & fats & proteins", milk.getPrimaryType());
    }
    @Test
    void editFoodTest() {
        apple.addPurchaseInfo(1.25);
        assertEquals(1.0, apple.getCost());
        assertEquals(2.0, apple.getTotalCost());
        apple.addPurchaseInfo(2);
        assertEquals(4.0/3.0, apple.getCost());
        apple.editName("ambrosia");
        assertEquals("ambrosia", apple.getName());
        assertEquals("food: ambrosia\nweight: 1.0g\ncost: $1.3333333333333333\ncalories: 100.0\ncarbs: 24.0g\nfats: 0.0g\nproteins: 1.0g"
        + "\nprimary type: carbs\nfood value: very low\ncalories per dollar: 75.0", apple.viewInfo());
    }

    @Test
    void valueTest() {
        assertEquals(banana.value(), "very low\ncalories per dollar: 50.0");
        assertEquals(apple.value(), "low\ncalories per dollar: 133.33333333333334");
        assertEquals(beef.value(), "medium\ncalories per dollar: 225.0");
        assertEquals(milk.value(), "good\ncalories per dollar: 333.3333333333333");
        assertEquals(rice.value(), "excellent\ncalories per dollar: 600.0");
    }


}