package model.calendar;

public class Date {

//    enum Month { January, February, March, April, May, June, July, August, September, October, November, December }

    static Month month;
    static int year;
    static int day;
    static boolean isLeapYear;

    public static void setDate(int day, String month, int year) {
        Date.day = day;
        Date.month = Month.valueOf(month);
        Date.year = year;
    }

    public static void setDate(String encoded) {
        Date.month = Month.valueOf(encoded.substring(0,3));
        Date.day = Integer.parseInt(encoded.substring(3,5));
        Date.year = Integer.parseInt(encoded.substring(5));

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
            case JAN:
                if (day > 31) {
                    day = 1;
                    month = Month.FEB;
                }
            case FEB:
                if (isLeapYear) {
                    if (day > 29) {
                        day = 1;
                        month = Month.MAR;
                    }
                } else {
                    if (day > 28) {
                        day = 1;
                        month = Month.MAR;
                    }
                }
            case MAR:
                if (day > 31) {
                    day = 1;
                    month = Month.APR;
                }
            case APR:
                if (day > 30) {
                    day = 1;
                    month = Month.MAY;
                }
            case MAY:
                if (day > 31) {
                    day = 1;
                    month = Month.JUN;
                }
            case JUN:
                if (day > 30) {
                    day = 1;
                    month = Month.JUL;
                }
            case JUL:
                if (day > 31) {
                    day = 1;
                    month = Month.AUG;
                }
            case AUG:
                if (day > 31) {
                    day = 1;
                    month = Month.SEP;
                }
            case SEP:
                if (day > 30) {
                    day = 1;
                    month = Month.OCT;
                }
            case OCT:
                if (day > 31) {
                    day = 1;
                    month = Month.NOV;
                }
            case NOV:
                if (day > 30) {
                    day = 1;
                    month = Month.DEC;
                }
            case DEC:
                if (day > 31) {
                    day = 1;
                    month = Month.JAN;
                    year++;
                }
        }
    }

    public static void checkUnderflow() {
        isLeapYear = (year % 4 == 0);
        if (day == 0) {
            switch (month) {
                case JAN:
                    year--;
                    month = Month.DEC;
                    day = 31;
                case FEB:
                    month = Month.JAN;
                    day = 31;
                case MAR:
                    month = Month.FEB;
                    day = isLeapYear ? 29 : 28;
                case APR:
                    month = Month.MAR;
                    day = 31;
                case MAY:
                    month = Month.APR;
                    day = 30;
                case JUN:
                    month = Month.MAY;
                    day = 31;
                case JUL:
                    month = Month.JUN;
                    day = 30;
                case AUG:
                    month = Month.JUL;
                    day = 31;
                case SEP:
                    month = Month.AUG;
                    day = 31;
                case OCT:
                    month = Month.SEP;
                    day = 30;
                case NOV:
                    month = Month.OCT;
                    day = 31;
                case DEC:
                    month = Month.NOV;
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

    public static String encode() {
        String dayString = day < 10 ? "0" + day : Integer.toString(day);
        return month.toString().substring(0,3) + dayString + year;
    }

    public static String encode(String longString) {
        return Month.valueOf(longString).asString;
    }

    public static void parseDate(String dateString) {
        month = Month.valueOf(dateString.substring(0,3));
        day = Integer.parseInt(dateString.substring(3,5));
        year = Integer.parseInt(dateString.substring(5,9));
    }

    public static boolean firstLargerThanSecond(String date1, String date2) {
        if (Integer.parseInt(date1.substring(5)) > Integer.parseInt(date2.substring(5))) {
            return true;
        }
        if (Integer.parseInt(date1.substring(5)) < Integer.parseInt(date2.substring(5))) {
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
