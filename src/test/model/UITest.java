package model;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.NutritivityApp;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Test.*;

public class UITest {

    @Test
    void testInvalidUserException() {
        NutritivityApp app = new NutritivityApp("test");
        try {
            app.foodAction("an invalid choice");
            fail();
        } catch (InvalidUserChoiceException ignore) {}
    }
}
