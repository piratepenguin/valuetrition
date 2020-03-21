package model.meal;

import persistence.Saveable;
import persistence.readers.FoodReader;

import java.io.PrintWriter;
import java.util.HashMap;

public class Log implements Saveable {

    private HashMap<Integer,MealList> log;

    public Log() {
        log = new HashMap<>();
    }

    public void logMeal(int date, Meal meal) {
        if (log.get(date) == null) {
            log.put(date, new MealList());
        }
        log.get(date).add(meal);
    }

    public MealList getLogForDay(int date) {
        return log.get(date);
    }

    @Override
    public void save(PrintWriter printWriter) {
        for (int i = 0; i <= log.size(); i++) {
            if (log.get(i) != null) {
                MealList mealList = log.get(i);
                mealList.save(printWriter);
            }
        }
    }


}
