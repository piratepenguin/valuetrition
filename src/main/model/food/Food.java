package model.food;

// represents a food item with nutritional and economical data

public class Food {
    String name;
    double weight;
    double cost;
    double calories;
    short costTimes; // how many times prices have been logged, used to find avg price
    double totalCost;
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
        this.totalCost = 0;
        this.cost = 0;
        primaryType = "none";
    }

    public Food(String name, double weight, double cost, double calories, double carbs, double fats, double proteins) {
        this.carbs = carbs;
        this.fats = fats;
        this.proteins = proteins;
        this.calories = calories;
        this.name = name;
        this.weight = weight;
        this.cost = cost;
        this.totalCost = cost;
        this.costTimes = 1;
        primaryType = determinePrimary();
    }


    // GETTER METHODS:

    public double getCost() {
        return cost;
    }

    public double getTotalCost() {
        return totalCost;
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

    public double getWeight() {
        return weight;
    }

    public double getCalories() {
        return calories;
    }

    public String getPrimaryType() {
        return primaryType;
    }


    // END OF GETTER METHODS

    // MODIFIES: this
    // EFFECTS: changes food name
    public void editName(String name) {
        this.name = name;
    }

    public void editCost(double cost) {
        this.cost = cost;
    }

    public void editWeight(double weight) {
        this.weight = weight;
    }

    public void editCalories(double calories) {
        this.calories = calories;
    }

    public void editCarbs(double carbs) {
        this.carbs = carbs;
    }

    public void editFats(double fats) {
        this.fats = fats;
    }

    public void editProteins(double proteins) {
        this.proteins = proteins;
    }

    //EFFECTS: returns the primary macro nutrient type of the food
    public String determinePrimary() {

        if (carbs * 4 > proteins * 4 + fats * 8.8) {
            return "carbs";
        } else if (proteins * 4 > carbs * 4 + fats * 8.8) {
            return "proteins";
        } else if (fats * 8.8 > proteins * 4 + carbs * 4) {
            return "fats";
        } else if (fats * 8.8 + proteins * 4 > carbs * 4 * 6) {
            return "fats & proteins";
        } else if (fats * 8.8 + carbs * 4 > proteins * 4 * 6) {
            return "carbs & fats";
        } else if (proteins * 4 + carbs * 4 > fats * 8.8 * 6) {
            return "carbs & proteins";
        } else {
            return "carbs & fats & proteins";
        }
    }

    // MODIFIES: this
    // EFFECTS: adds instance of purchase by increasing total money spent on food by price and adding 1 to costTimes
    public void addPurchaseInfo(double price) {
        costTimes++;
        totalCost += price;
        this.cost = totalCost / costTimes;
    }


    // EFFECTS: returns all the food's information
    public String viewInfo() {
        return "food: " + name + "\nweight: " + weight + "g\ncost: $" + cost + "\ncalories: "
                + calories + "\ncarbs: " + carbs + "g\nfats: " + fats + "g\nproteins: " + proteins
                + "g\nprimary type: " + primaryType + "\nfood value: " + this.value();
    }

    //EFFECTS: returns foods calories divided by cost in dollars
    public int caloriesPerDollar() {
        return (int) (calories / cost);
    }

    // EFFECTS: determines and returns whether food is very low, low, medium, good, or of excellent value
    //          as well as its calories per dollar
    public String value() {
        if (this.caloriesPerDollar() < 100) {
            return ("very low\ncalories per dollar: " + this.caloriesPerDollar());
        } else if (caloriesPerDollar() < 200) {
            return ("low\ncalories per dollar: " + this.caloriesPerDollar());
        } else if (caloriesPerDollar() < 300) {
            return ("medium\ncalories per dollar: " + this.caloriesPerDollar());
        } else if (caloriesPerDollar() < 500) {
            return ("good\ncalories per dollar: " + this.caloriesPerDollar());
        } else {
            return ("excellent\ncalories per dollar: " + this.caloriesPerDollar());
        }
    }

}
