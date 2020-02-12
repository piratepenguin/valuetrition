package model;

import model.Food;
import java.util.*;

public class FoodList {
    private ArrayList<Food> foodlist;

    public FoodList() {
        foodlist = new ArrayList<>();
    }

    public void removeFood(String foodname) {

        foodlist.removeIf(food -> food.getName().equals(foodname));
    }

}
