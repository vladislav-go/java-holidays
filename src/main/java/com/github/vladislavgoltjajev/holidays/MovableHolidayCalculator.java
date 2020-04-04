package com.github.vladislavgoltjajev.holidays;


import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
final class MovableHolidayCalculator {

    @SneakyThrows
    public LocalDate getMovableHolidayDate(MovableHolidayCode holidayCode, int year) {
        switch (holidayCode) {
            case EASTER_SUNDAY:
                return getEasterSundayDate(year);
            case GOOD_FRIDAY:
                return getEasterSundayDate(year).minusDays(2);
            case PENTECOST:
                return getEasterSundayDate(year).plusDays(49);
            default:
                throw new HolidayException("Could not determine date for holiday " + holidayCode);
        }
    }

    /**
     * Calculate the Easter Sunday date for the specified year using the Anonymous Gregorian algorithm.
     *
     * @return Easter Sunday date for the specified year.
     */
    private LocalDate getEasterSundayDate(int year) {
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
}
