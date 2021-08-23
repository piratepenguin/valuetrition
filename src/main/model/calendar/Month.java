package model.calendar;

import java.util.function.Predicate;

public enum Month {

    JAN(1,"January"), FEB(2, "February"), MAR(3, "March"), APR(4, "April"),
    MAY(5, "May"), JUN(6, "June"), JUL(7, "July"), AUG(8, "August"),
    SEP(9, "September"), OCT(10, "October"), NOV(11, "November"), DEC(12, "December");

    int asInt;
    String asString;

    Month(int i, String name) {
        asInt = i;
        asString = name;
    }

    public Month stringToMonth(String str) {
        return Month.valueOf(str);
    }

    public Month getNext() {
        int newMonthInt = (getAsInt() % 12) + 1;
        for (Month m : Month.values()) {
            if (m.asInt == newMonthInt) {
                return m;
            }
        }
        return SEP;
    }

    public Month getPrev() {
        int newMonthInt = (asInt == 1) ? 12 : asInt - 1;

        for (Month m : Month.values()) {
            if (m.asInt == newMonthInt) {
                return m;
            }
        }
        return SEP;
    }

    public int getAsInt() {
        return asInt;
    }

    @Override
    public String toString() {
        return asString;
    }
}
