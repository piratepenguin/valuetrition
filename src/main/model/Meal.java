package model;

@SuppressWarnings("checkstyle:LineLength")
public class Meal {

    Food food;
    double amount;
    int day;

    String name;
    double cost;
    double calories;
    double carbs;
    double fats;
    double proteins;
    String primaryType;

    public Meal(Food food, double amount, int day) {
        this.food = food;
        this.amount = amount;
        this.day = day;
        name = food.getName();
        cost = food.getCost();
        calories = food.getCalories();
        carbs = food.getCarbs();
        fats = food.getFats();
        proteins = food.getProteins();
        primaryType = food.getPrimaryType();
    }

    public double getCost() {
        return cost;
    }

    public double getProteins() {
        return proteins;
    }

    public double getFats() {
        return fats;
    }

    public double getCarbs() {
        return carbs;
    }

    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }

    public String getPrimaryType() {
        return primaryType;
    }
}
