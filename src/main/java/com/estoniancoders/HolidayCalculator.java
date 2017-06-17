package com.estoniancoders;

import java.time.LocalDate;

class HolidayCalculator {

    /**
     * Calculate the Easter Sunday date for the specified year using the Anonymous Gregorian algorithm.
     *
     * @return Easter Sunday date for the specified year.
     */
    static LocalDate getEasterSundayDate(int year) {
        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        int month = (h + l - 7 * m + 114) / 31;
        int day = ((h + l - 7 * m + 114) % 31) + 1;
        return LocalDate.of(year, month, day);
    }

    /**
     * Get the Good Friday date for the specified year, which is the Friday before the Easter Sunday.
     *
     * @return Good Friday date for the specified year.
     */
    static LocalDate getGoodFridayDate(int year) {
        return getEasterSundayDate(year).minusDays(2);
    }

    /**
     * Get the Pentecost date, which is the 50th day after the Easter Sunday, inclusive.
     *
     * @return Pentecost date for the specified year.
     */
    static LocalDate getPentecostDate(int year) {
        return getEasterSundayDate(year).plusDays(49);
    }

    static LocalDate calculateMovableHolidayDate(String holidayCode, int year) {
        LocalDate date = null;

        if ("GOOD_FRIDAY".equals(holidayCode)) {
            date = getGoodFridayDate(year);
        }

        if ("EASTER_SUNDAY".equals(holidayCode)) {
            date = getEasterSundayDate(year);
        }

        if ("PENTECOST".equals(holidayCode)) {
            date = getPentecostDate(year);
        }

        return date;
    }
}
