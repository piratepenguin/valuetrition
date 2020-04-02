package model.meal;

import model.exceptions.LogNotFoundException;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
            return "No meals logged for day " + date + "!";
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

        Iterator<Map.Entry<Integer,MealList>> logIterator = log.entrySet().iterator();
        while (logIterator.hasNext()) {
            Map.Entry<Integer,MealList> mapElement = logIterator.next();
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
