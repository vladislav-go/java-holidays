package ee.vladislav.utility;

import ee.vladislav.exceptions.HolidayException;
import ee.vladislav.objects.Holiday;
import ee.vladislav.objects.MovableHoliday;

import java.time.LocalDate;

/**
 * Performs holiday calculations.
 */
public final class HolidayCalculator {

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

    public Holiday getMovableHoliday(MovableHoliday movableHoliday, int year) throws HolidayException {
        Holiday holiday = new Holiday();
        holiday.setName(movableHoliday.getName());
        holiday.setDayOff(movableHoliday.isDayOff());
        LocalDate date = getMovableHolidayDate(movableHoliday.getCode(), year);
        holiday.setDay(date.getDayOfMonth());
        holiday.setMonth(date.getMonthValue());
        return holiday;
    }

    private LocalDate getMovableHolidayDate(String holidayCode, int year) throws HolidayException {
        LocalDate date = null;

        if ("EASTER_SUNDAY".equals(holidayCode)) {
            date = getEasterSundayDate(year);
        }

        if ("GOOD_FRIDAY".equals(holidayCode)) {
            date = getEasterSundayDate(year).minusDays(2);
        }

        if ("PENTECOST".equals(holidayCode)) {
            date = getEasterSundayDate(year).plusDays(49);
        }

        if (date == null) {
            throw new HolidayException("Could not determine holiday date.");
        }

        return date;
    }
}
