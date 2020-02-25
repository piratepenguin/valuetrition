package model;

import model.Food;
import java.util.*;

// represents a list of Food items
public class FoodList {
    private ArrayList<Food> foodlist;

    public FoodList() {
        foodlist = new ArrayList<>();
    }

    public int size() {
        return foodlist.size();
    }

    public void clear() {
        foodlist.clear();
    }

    // EFFECTS: removes food from list
    public void removeFood(String foodname) throws FoodNotFoundException {
        int size = foodlist.size();
        foodlist.removeIf(food -> food.getName().equals(foodname));
        if (foodlist.size() == size) {
            throw new FoodNotFoundException();
        }
    }

    public void add(Food food) {
        foodlist.add(food);
    }

    // EFFECTS: returns food with given name from list,
    //          if not found returns FoodNotFoundException
    public Food getFood(String name) throws FoodNotFoundException {
        for (Food food: foodlist) {
            if (food.getName().equals(name)) {
                return food;
            }
        }
        throw new FoodNotFoundException();

    }

    // EFFECTS: returns a string of all the foods contained in the list
    public String toString() {
        StringBuilder list = new StringBuilder();
        for (Food each : foodlist) {
            if (foodlist.indexOf(each) == foodlist.size() - 1) {
                list.append(each.getName());
            } else {
                list.append(each.getName()).append(", ");
            }
        }
        return list.toString();
    }
}
