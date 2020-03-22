package ui;

import model.exceptions.NullFoodException;
import model.food.Food;
import model.meal.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.food.CreateFoodUI;
import ui.food.EditFoodUI;
import ui.food.FoodUI;

import static org.junit.jupiter.api.Assertions.fail;

public class UITest {

    private CreateFoodUI createFoodUI;
    Food banana = new Food("banana", 1, 2, 100, 24, 0, 1); // very bad value

    @BeforeEach
    void runBefore() {
        createFoodUI = new CreateFoodUI();
    }

    @Test
    void NullFoodExceptionTest() {
        try {
            createFoodUI.setFields("",5,5,5,5,5,5);
            createFoodUI.checkFormNotBlank();
            fail();
        } catch (NullFoodException ignored) {
            // expected
        }

    }







}
