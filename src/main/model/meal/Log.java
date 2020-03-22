package model.meal;

import model.exceptions.LogNotFoundException;
import persistence.Saveable;
import persistence.readers.FoodReader;

import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//       represents a Log, which has:
//        - a MealList assigned to each existing day (int)
//        - each MealList represents what meals were eaten that day

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

    public MealList getLogForDay(int date) throws LogNotFoundException {
        if (log.get(date) != null) {
            return log.get(date);
        } else {
            throw new LogNotFoundException();
        }
    }

    public String getLogForDayAsString(int date) {
        if (log.get(date) != null) {
            return log.get(date).toString();
        } else {
            return "No meals logged for today!";
        }
    }


    public int getLastDay() {
        int lastDay = 1;
        Set<Integer> allDates = log.keySet();
        for (Integer i : allDates) {
            if (i > lastDay) {
                lastDay = i;
            }
        }
        return lastDay;
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
