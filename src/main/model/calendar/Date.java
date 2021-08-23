package model.calendar;

public class Date {

//    enum Month { January, February, March, April, May, June, July, August, September, October, November, December }

    static Month month;
    static int year;
    static int day;
    static boolean isLeapYear;

    public void setDate(int day, String month, int year) {
        Date.day = day;
        Date.month = Month.valueOf(month);
        Date.year = year;
    }

    public static void previous() {
        day--;
        checkUnderflow();
    }

    public static void next() {
        day++;
        checkOverflow();
    }

    public static void checkOverflow() {
        isLeapYear = (year % 4 == 0);
        switch (month) {
            case January:
                if (day > 31) {
                    day = 1;
                    month = Month.February;
                }
            case February:
                if (isLeapYear) {
                    if (day > 29) {
                        day = 1;
                        month = Month.March;
                    }
                } else {
                    if (day > 28) {
                        day = 1;
                        month = Month.March;
                    }
                }
            case March:
                if (day > 31) {
                    day = 1;
                    month = Month.April;
                }
            case April:
                if (day > 30) {
                    day = 1;
                    month = Month.May;
                }
            case May:
                if (day > 31) {
                    day = 1;
                    month = Month.June;
                }
            case June:
                if (day > 30) {
                    day = 1;
                    month = Month.July;
                }
            case July:
                if (day > 31) {
                    day = 1;
                    month = Month.August;
                }
            case August:
                if (day > 31) {
                    day = 1;
                    month = Month.September;
                }
            case September:
                if (day > 30) {
                    day = 1;
                    month = Month.October;
                }
            case October:
                if (day > 31) {
                    day = 1;
                    month = Month.November;
                }
            case November:
                if (day > 30) {
                    day = 1;
                    month = Month.December;
                }
            case December:
                if (day > 31) {
                    day = 1;
                    month = Month.January;
                    year++;
                }
        }
    }

    public static void checkUnderflow() {
        isLeapYear = (year % 4 == 0);
        if (day == 0) {
            switch (month) {
                case January:
                    year--;
                    month = Month.December;
                    day = 31;
                case February:
                    month = Month.January;
                    day = 31;
                case March:
                    month = Month.February;
                    day = isLeapYear ? 29 : 28;
                case April:
                    month = Month.March;
                    day = 31;
                case May:
                    month = Month.April;
                    day = 30;
                case June:
                    month = Month.May;
                    day = 31;
                case July:
                    month = Month.June;
                    day = 30;
                case August:
                    month = Month.July;
                    day = 31;
                case September:
                    month = Month.August;
                    day = 31;
                case October:
                    month = Month.September;
                    day = 30;
                case November:
                    month = Month.October;
                    day = 31;
                case December:
                    month = Month.November;
                    day = 30;
            }
        }
    }

    public static void setYear(int year) {
        Date.year = year;
    }

    public static void setMonth(String month) {
        Date.month = Month.valueOf(month);
    }

    public static void setDay(int day) {
        Date.day = day;
    }

    public static int getYear() {
        return year;
    }

    public static String getMonth() {
        return month.toString();
    }

    public static int getDay() {
        return day;
    }

    public static String viewAsString() {
        return month.toString() + " " + day + " " + year;
    }

    public static String encodeToString() {
        String dayString = day < 10 ? "0" + day : Integer.toString(day);
        return month.toString().substring(0,3) + dayString + year;
    }

    public static void parseDate(String dateString) {
        month = Month.valueOf(dateString.substring(0,3));
        day = Integer.parseInt(dateString.substring(3,5));
        year = Integer.parseInt(dateString.substring(5,9));
    }

    public static boolean firstLargerThanSecond(String date1, String date2) {
        if (Integer.parseInt(date1.substring(5,9)) > Integer.parseInt(date2.substring(5,9))) {
            return true;
        }
        if (Integer.parseInt(date1.substring(5,9)) < Integer.parseInt(date2.substring(5,9))) {
            return false;
        }
        if (Month.valueOf(date1.substring(0,3)).getAsInt() > Month.valueOf(date2.substring(0,3)).getAsInt()) {
            return true;
        }
        if (Month.valueOf(date1.substring(0,3)).getAsInt() < Month.valueOf(date2.substring(0,3)).getAsInt()) {
            return true;
        }
        return Integer.parseInt(date1.substring(3,5)) > Integer.parseInt(date2.substring(3,5));
    }

}
