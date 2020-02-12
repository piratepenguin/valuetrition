package model;

public class Food {
    String name;
    double cost;
    short costTimes; // how many times prices have been logged, used to find avg price
    double carbs;
    double fats;
    double proteins;
    double calories;
    String primarytype;

    public Food() {
        this.carbs = 0;
        this.fats = 0;
        this.proteins = 0;
        this.name = "unnamed food";
        this.calories = 0;
        this.cost = 0;
        primarytype = "none";
    }

    public Food(String name, double cost, double calories, double carbs, double fats, double proteins) {
        this.carbs = carbs;
        this.fats = fats;
        this.proteins = proteins;
        this.calories = calories;
        this.name = name;
        this.cost = cost;
        primarytype = determinePrimary();
    }


    // GETTER MTETHODS:

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

    public void updateName(String newname) {
        this.name = newname;
    }

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

    public void addPurchaseInfo(double price) {
        costTimes++;
        this.cost = (cost + price) / costTimes;
    }


}
