package model;

import model.Food;
import java.util.*;

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

    public Food getFood(String name) throws FoodNotFoundException {
        for (Food food: foodlist) {
            if (food.getName().equals(name)) {
                return food;
            }
        }
        throw new FoodNotFoundException();

    }

    public String toString() {
        String list = "";
        for (Food each : foodlist) {
            if (foodlist.indexOf(each) == foodlist.size() - 1) {
                list = list + each.getName();
            } else {
                list = list + each.getName() + ", ";
            }
        }
        return list;
    }
}
