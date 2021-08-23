package model.meal;

import model.calendar.Date;
import model.exceptions.LogNotFoundException;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//       represents a Log, which has:
//        - a MealList assigned to each existing Date
//        - each MealList represents what meals were eaten that day

public class Log implements Saveable {

    private HashMap<String,MealList> log;

    public Log() {
        log = new HashMap<>();
    }

//    public void logMeal(String date, Meal meal) {
//        if (log.get(date) == null) {
//            log.put(date, new MealList());
//        }
//        log.get(date).add(meal);
//    }

    public void logMeal(Meal meal) {
        String date = meal.getDate();
        if (log.get(date) == null) {
            log.put(date, new MealList());
        }
        log.get(date).add(meal);
    }

    public MealList getLogForDay(String date) throws LogNotFoundException {
        if (log.get(date) != null) {
            return log.get(date);
        } else {
            throw new LogNotFoundException();
        }
    }

    public String getLogForDayAsString(String date) {
        if (log.get(date) != null) {
            return log.get(date).toString();
        } else {
            return "No meals logged for " + Date.viewAsString(date) + "!";
        }
    }


    public String getLastDay() {
        String lastDay = "JAN00000";
        Set<String> allDates = log.keySet();
        for (String str : allDates) {
            if (Date.firstLargerThanSecond(str, lastDay)) {
                lastDay = str;
            }
        }
        return lastDay;
    }


    @Override
    public void save(PrintWriter printWriter) {

        for (Map.Entry<String, MealList> mapElement : log.entrySet()) {
            mapElement.getValue().save(printWriter);
        }

        // log.forEach((k, v) -> log.get(k).save(printWriter));

//        /old code, reformatted to decrease coupling
//        for (int i = 0; i <= log.size(); i++) {
//            if (log.get(i) != null) {
//                MealList mealList = log.get(i);
//                mealList.save(printWriter);
//            }
//        }
    }

}
