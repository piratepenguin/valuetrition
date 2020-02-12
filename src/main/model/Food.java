package model;

public class Food {
    String name;
    double cost;
    double calories;
    double costTimes; // how many times prices have been logged, used to find avg price
    double carbs;
    double fats;
    double proteins;
    String primaryType;

    public Food() {
        this.carbs = 0;
        this.fats = 0;
        this.proteins = 0;
        this.name = "unnamed food";
        this.calories = 0;
        this.costTimes = 1;
        this.cost = 0;
        primaryType = "none";
    }

    public Food(String name, double cost, double calories, double carbs, double fats, double proteins) {
        this.carbs = carbs;
        this.fats = fats;
        this.proteins = proteins;
        this.calories = calories;
        this.name = name;
        this.cost = cost;
        this.costTimes = 1;
        primaryType = determinePrimary();
    }


    // GETTER METHODS:

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


    // END OF GETTER METHODS

    //EFFECTS: returns the primary macro nutrient type of the food
    private String determinePrimary() {

        if (carbs * 4 > proteins * 4 + fats * 2.2) {
            return "carbs";
        } else if (proteins * 4 > proteins * 4 + fats * 2.2) {
            return "protein";
        } else if (fats * 2.2 > proteins * 4 + carbs * 4) {
            return "fats";
        } else {
            return "none";
        }
    }

    // MODOFIES: this
    // EFFECTS: adds instance of purchase
    public void addPurchaseInfo(double price) {
        costTimes++;
        this.cost = (cost + price) / costTimes;
    }


    // MODIFIES: this
    // EFFECTS: changes food name
    public void editName(String name) {
        this.name = name;
    }

    // EFFECTS: returns all the food's information
    public String viewInfo() {
        return "food: " + name + "\ncost: " + cost + "\ncalories: " + calories + "\ncarbs: "
                + carbs + "\nfats: " + fats + "\nproteins: " + proteins + "\nprimary type: " + primaryType
                + "\nfood value: " + this.value();
    }

    public double caloriesPerDollar() {
        return calories / cost;
    }


    public String value() {
        if (this.caloriesPerDollar() < 100) {
            return ("very low\ncalories per dollar: " + this.caloriesPerDollar());
        } else if (caloriesPerDollar() <= 200) {
            return ("low\ncalories per dollar: " + this.caloriesPerDollar());
        } else if (caloriesPerDollar() <= 300) {
            return ("medium\ncalories per dollar: " + this.caloriesPerDollar());
        } else if (caloriesPerDollar() <= 500) {
            return ("good\ncalories per dollar: " + this.caloriesPerDollar());
        } else {
            return ("excellent\ncalories per dollar: " + this.caloriesPerDollar());
        }
    }

}
