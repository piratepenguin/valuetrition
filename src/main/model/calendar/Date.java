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
        Date.month = Month.valueOf(encoded.substring(0, 3));
        Date.day = Integer.parseInt(encoded.substring(3, 5));
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
        // for JAN, MAR, MAY, JUL, AUG, OCT, DEC
        if (day > 31) {
            day = 1;
            year += (month == Month.DEC) ? 1 : 0;
            month = month.getNext();
            return;
        }
        switch (month) {
            case JAN: // all the months with 31 days
            case MAR:
            case MAY:
            case JUL:
            case AUG:
            case OCT:
            case DEC:
                break;
            case APR:
            case JUN:
            case SEP:
            case NOV:
                if (day > 30) {
                    day = 1;
                    month = month.getNext();
                }
                break;
            case FEB:
                if (isLeapYear) {
                    if (day > 29) {
                        day = 1;
                        month = month.getNext();
                    }
                } else {
                    if (day > 28) {
                        day = 1;
                        month = month.getNext();
                    }
                }
                break;
        }
    }

    public static void checkUnderflow() {
        isLeapYear = (year % 4 == 0);
        if (day < 1) {
            year -= (month == Month.JAN) ? 1 : 0;
            switch (month) {
                case JAN:
                case FEB:
                case APR:
                case JUN:
                case AUG:
                case SEP:
                case NOV:
                    month = month.getPrev();
                    day = 31;
                    break;
                case MAR:
                    month = month.getPrev();
                    day = isLeapYear ? 29 : 28;
                    break;
                case MAY:
                case JUL:
                case OCT:
                case DEC:
                    month = month.getPrev();
                    day = 30;
                    break;
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

    public static String viewAsString(String encoded) {
        Month m = month.stringToMonth(encoded.substring(0,3));
        return m.toString() + " " + encoded.substring(3,5) + " " + encoded.substring(5,9);
    }

    public static String encode() {
        String dayString = day < 10 ? "0" + day : Integer.toString(day);
        return month.toString().substring(0, 3) + dayString + year;
    }

    public static String encode(String longString) {
        return Month.valueOf(longString).asString;
    }

    public static void parseDate(String dateString) {
        month = Month.valueOf(dateString.substring(0, 3));
        day = Integer.parseInt(dateString.substring(3, 5));
        year = Integer.parseInt(dateString.substring(5, 9));
    }

    public static boolean firstLargerThanSecond(String date1, String date2) {
        if (Integer.parseInt(date1.substring(5)) > Integer.parseInt(date2.substring(5))) {
            return true;
        }
        if (Integer.parseInt(date1.substring(5)) < Integer.parseInt(date2.substring(5))) {
            return false;
        }
        if (Month.valueOf(date1.substring(0, 3)).getAsInt() > Month.valueOf(date2.substring(0, 3)).getAsInt()) {
            return true;
        }
        if (Month.valueOf(date1.substring(0, 3)).getAsInt() < Month.valueOf(date2.substring(0, 3)).getAsInt()) {
            return true;
        }
        return Integer.parseInt(date1.substring(3, 5)) > Integer.parseInt(date2.substring(3, 5));
    }


}
