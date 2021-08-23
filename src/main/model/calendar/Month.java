package model.calendar;

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



    public int getAsInt() {
        return asInt;
    }
}
